package com.culqi.tokens.model.payload.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ApiModel(value = "Tokens Request", description = "Request for Tokens")
public class TokensRequest {

    @NotBlank(message = "parameter not blank")
    @ApiModelProperty(value = "pan", required = true, allowableValues = "NonEmpty String")
    @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$", message = "Must be a valid credit card number, that matches Visa, MasterCard, American Express, Diners Club, Discover, and JCB cards")
    private String pan;

    @ApiModelProperty(value = "exp_year", required = true, allowableValues = "NonEmpty String")
    @Min(value = 2018, message = "The year must be greater than 2017")
    @Max(value = 2900, message = "The year must be lesser than 2901")
    private int exp_year;

    @ApiModelProperty(value = "exp_month", required = true, allowableValues = "NonEmpty String")
    @Min(value = 1, message = "The month must be greater than 0")
    @Max(value = 12, message = "The month must be lesser than 13")
    private int exp_month;

    public TokensRequest(){}

    public TokensRequest(String pan, int exp_year, int exp_month){
        setPan(pan);
        setExp_year(exp_year);
        setExp_month(exp_month);
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public int getExp_month() {
        return exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }
}
