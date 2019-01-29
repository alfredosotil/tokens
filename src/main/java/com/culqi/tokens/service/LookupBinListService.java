package com.culqi.tokens.service;

import com.culqi.tokens.model.payload.response.BinListResponse;

public interface LookupBinListService {

    /**
     *
     * @param input
     * @return BinListResponse
     */
    BinListResponse getData(String input);
}
