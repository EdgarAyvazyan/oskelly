package com.mainserver.oskelly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EnableAutoConfiguration
public class OskellyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OskellyApplication.class, args);

    }
}
