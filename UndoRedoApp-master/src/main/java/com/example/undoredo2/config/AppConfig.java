package com.example.undoredo2.config;

import com.example.undoredo2.service.TextEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TextEditor textEditor() {
        return new TextEditor();
    }
}