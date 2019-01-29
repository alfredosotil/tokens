package com.culqi.tokens.model.payload.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TokensResponse {

    private String token;
    private String brand;
    private String creation_dt;

    public TokensResponse(){}

    public TokensResponse(String token, String brand){
        setToken(token);
        setBrand(brand);
        setCreation_dt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCreation_dt() {
        return creation_dt;
    }

    public void setCreation_dt(String creation_dt) {
        this.creation_dt = creation_dt;
    }
}
