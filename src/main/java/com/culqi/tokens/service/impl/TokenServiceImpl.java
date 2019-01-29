package com.culqi.tokens.service.impl;

import com.culqi.tokens.service.TokenService;
import com.culqi.tokens.util.CommonUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TokenServiceImpl implements TokenService {

    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${tokenPrefix}")
    private String tokenPrefix;

    public String generate(String pan, int exp_year, int exp_month) {
        String token = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yyyy-MM");
        String dateToBeParsed = String.valueOf(exp_year).concat("-").concat(String.valueOf(exp_month)).concat("-1");
        LocalDate date = CommonUtilities.getStringToLocalDateTime(dateToBeParsed, "yyyy-M-d").toLocalDate();
        String tokenPostfix = date.format(formatter);
        token = tokenPrefix.concat(pan).concat(tokenPostfix);
        logger.info("token generated: {}", token);
        return token;
    }
}
