package com.twentyfive.ticketapilayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.twentyfive.authorizationflow", "com.twentyfive.ticketapilayer"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class TicketApiLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketApiLayerApplication.class, args);
    }

}
