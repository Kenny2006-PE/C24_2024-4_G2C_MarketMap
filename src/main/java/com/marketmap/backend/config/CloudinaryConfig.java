package com.marketmap.backend.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcuezuenj",
                "api_key", "414635529716239",
                "api_secret", "wyytj3TEYVwXt_MX-Hyqigkn09A"));
    }
}
