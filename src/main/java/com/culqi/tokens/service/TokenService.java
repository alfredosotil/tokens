package com.culqi.tokens.service;

public interface TokenService {

    String generate(String pan, int exp_year, int exp_month);
}
