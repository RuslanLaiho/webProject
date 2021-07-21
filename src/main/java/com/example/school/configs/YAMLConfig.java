package com.example.school.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
    private String url;
    private String user;
    private String password;

    public  YAMLConfig()
    {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/application.yml"));
            Yaml yaml = new Yaml();
            Map<String, Object>  yamlConfig = yaml.load(inputStream);

            url = (String) yamlConfig.get("Url");
            user = (String) yamlConfig.get("User");
            password = String.valueOf(yamlConfig.get("Password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void setUser(String user){
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUrl(){
        return url;
    }
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }

}
