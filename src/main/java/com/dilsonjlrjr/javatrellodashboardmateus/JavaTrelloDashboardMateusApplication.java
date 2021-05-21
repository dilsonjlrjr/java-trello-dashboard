package com.dilsonjlrjr.javatrellodashboardmateus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class })
public class JavaTrelloDashboardMateusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaTrelloDashboardMateusApplication.class, args);
    }
}
