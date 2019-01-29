package com.culqi.tokens.controller;

import com.culqi.tokens.model.payload.request.TokensRequest;
import com.culqi.tokens.model.payload.response.ApiCulqiResponse;
import com.culqi.tokens.model.payload.response.BinListResponse;
import com.culqi.tokens.model.payload.response.TokensResponse;
import com.culqi.tokens.service.LookupBinListService;
import com.culqi.tokens.service.TokenService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Token rest controller", description = "description for token rest")
@RestController
@RequestMapping("/api")
public class CulqiController {

    private Logger logger = LoggerFactory.getLogger(CulqiController.class);

    @Autowired
    LookupBinListService lookupBinListService;

    @Autowired
    TokenService tokenService;

    @ApiOperation(
            value = "Send Token",
            response = ApiCulqiResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Not valid parameters"),
            @ApiResponse(code = 422, message = "Bad parameters"),
            @ApiResponse(code = 500, message = "System Error")
    })
    @PostMapping(value = "/tokens", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> receiveToken(@ApiParam(value = "The TokensRequest payload") @Valid @RequestBody TokensRequest tokensRequest) {

        String input = tokensRequest.getPan().substring(0, 6);
        logger.info("input: {}", input);
        BinListResponse binListResponse = lookupBinListService.getData(input);
        String token = tokenService.generate(tokensRequest.getPan(), tokensRequest.getExp_year(), tokensRequest.getExp_month());
        String brand = binListResponse.getScheme();
        TokensResponse tokensResponse = new TokensResponse(token, brand);

        return ResponseEntity.ok(new ApiCulqiResponse(tokensResponse, true));
    }

}
