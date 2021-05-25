package com.dilsonjlrjr.javatrellodashboardmateus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.dilsonjlrjr.javatrellodashboardmateus.mapper")
public class DatabaseConfig {
}
