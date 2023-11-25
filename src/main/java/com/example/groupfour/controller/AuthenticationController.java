package com.example.groupfour.controller;

import com.example.groupfour.config.JwtUtils;
import com.example.groupfour.dto.LoginUser;
import com.example.groupfour.dto.OperationStatusDto;
import com.example.groupfour.dto.PasswordResetDto;
import com.example.groupfour.dto.PasswordResetRequest;
import com.example.groupfour.entity.User;
import com.example.groupfour.payload.response.JwtResponse;
import com.example.groupfour.service.UserDetailsImpl;
import com.example.groupfour.service.UserService;
import com.example.groupfour.ultilities.RequestOperationName;
import com.example.groupfour.ultilities.RequestOperationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AtomicReference<Logger> logger = new AtomicReference<>(LoggerFactory.getLogger(AuthenticationController.class));

    JwtUtils jwtUtils;

    private final AtomicReference<AuthenticationManager> authenticationManager = new AtomicReference<AuthenticationManager>();

    private final AtomicReference<UserService> userService = new AtomicReference<UserService>();

    @Autowired
    public AuthenticationController(JwtUtils jwtUtils, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager.set(authenticationManager);
        this.userService.set(userService);
    }

    @PostMapping("/signin")
    @Transactional
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUser loginUser) {

        String username = loginUser.getUsername();
        Optional<User> user= userService.get().getUserByUsername(username);
        if(!user.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        else if(user.get().isDeleted()==true){
            return ResponseEntity.badRequest().build();
        }

        Authentication authentication = authenticationManager.get().authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        User userLog = userService.get().getUserByUsername(userDetails.getUsername()).get();
        userLog.setLastLoginDate(new Date());
        userService.get().updateUser(userLog);
        logger.get().warn(userLog.toString());
        if (userLog.isDeleted() == true) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping(value = "/password-reset-request")
    public OperationStatusDto resetPasswordRequest(@RequestBody PasswordResetRequest passwordResetRequest) throws MessagingException {
        OperationStatusDto operationStatusDto = new OperationStatusDto();
        boolean operationResult = userService.get().requestPasswordReset(passwordResetRequest.getEmail());
        operationStatusDto.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        operationStatusDto.setOperationResult(RequestOperationStatus.ERROR.name());
        if (operationResult) {
            operationStatusDto.setOperationResult(RequestOperationStatus.SUCCESS.name());

        }

        return operationStatusDto;
    }

    @PostMapping(value = "/password-reset")
    public OperationStatusDto resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        OperationStatusDto operationStatusDto = new OperationStatusDto();
        boolean operationResult = userService.get().resetPassword(passwordResetDto.getToken(), passwordResetDto.getPassword());
        operationStatusDto.setOperationResult(RequestOperationStatus.ERROR.name());
        operationStatusDto.setOperationName(RequestOperationName.PASSWORD_RESET.name());
        if (operationResult) {
            operationStatusDto.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }
        return operationStatusDto;
    }
}
