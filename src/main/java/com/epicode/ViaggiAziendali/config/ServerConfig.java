package com.epicode.ViaggiAziendali.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

    @Bean
    public Cloudinary configurazioneCloud(){
        Map<String,String> configurazione = new HashMap<String,String>();
        configurazione.put("cloud_name", "dkqtoqspe");
        configurazione.put("api_key", "468158447971657");
        configurazione.put("api_secret", "eX9hSN4XyxsvBsuoeVN49ZsiQWU");
        return new Cloudinary(configurazione);
    }
}