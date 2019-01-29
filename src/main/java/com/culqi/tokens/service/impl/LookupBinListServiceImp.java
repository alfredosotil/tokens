package com.culqi.tokens.service.impl;

import com.culqi.tokens.model.payload.response.BinListResponse;
import com.culqi.tokens.service.LookupBinListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LookupBinListServiceImp implements LookupBinListService {

    private Logger logger = LoggerFactory.getLogger(LookupBinListServiceImp.class);

    @Value("${binListUrl}")
    private String BINLISTURL;

    @Autowired
    RestTemplate restTemplate;

    /**
     * @param input
     * @return BinListResponse
     */
    public BinListResponse getData(String input) {
        String url = BINLISTURL.concat(input);
        logger.info("Url to get info: {}", url);
        BinListResponse binListResponse = restTemplate.postForObject(url, null, BinListResponse.class);
        return binListResponse;
    }
}
