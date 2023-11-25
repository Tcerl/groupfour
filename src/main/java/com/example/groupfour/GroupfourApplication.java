package com.example.groupfour;


import com.example.groupfour.audit.SpringSecurityAuditorAware;
import com.example.groupfour.entity.User;
import com.example.groupfour.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
public class GroupfourApplication implements CommandLineRunner{

	 @Resource
    FilesStorageService filesStorageService;

    public static void main(String[] args) {
        SpringApplication.run(GroupfourApplication.class, args);
    }

    @Bean
    public AuditorAware<User> auditorAwareBean() {
        return new SpringSecurityAuditorAware();
    }

    @Override
    public void run(String... args) throws Exception {
        if (!filesStorageService.existRootFolder()) {
            filesStorageService.initRootFolder();
        }
        if (!filesStorageService.existExcelFolder()) {
            filesStorageService.initExcelFolder();
        }
    }
}
