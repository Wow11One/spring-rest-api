package com.server.SongServer.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.server.SongServer")
@EnableWebMvc
public class ServerConfig implements WebMvcConfigurer {

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
