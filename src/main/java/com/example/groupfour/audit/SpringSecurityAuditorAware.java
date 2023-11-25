package com.example.groupfour.audit;

import com.example.groupfour.entity.User;
import com.example.groupfour.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class SpringSecurityAuditorAware implements AuditorAware<User>{
    Logger logger = LoggerFactory.getLogger(SpringSecurityAuditorAware.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        User auditor = new User();

        return Optional.ofNullable(auditor);
    }
    
}
