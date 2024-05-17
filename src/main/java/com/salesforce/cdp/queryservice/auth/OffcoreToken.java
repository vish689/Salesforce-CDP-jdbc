package com.salesforce.cdp.queryservice.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Calendar;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OffcoreToken {
    private String accessToken;

    private String instanceUrl;

    private String tokenType;

    private int expiresIn;

    private String errorDescription;

    private Calendar expireTime;
}