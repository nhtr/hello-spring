package com.nhtr.commonmodule.model;

import java.util.List;

public class ApiInvalidResponse {
    private ApiInvalid invalid;
    private ApiResult result;
    private String field;
    private List<String> params;

    public ApiInvalidResponse() {
    }

    public ApiInvalidResponse(ApiInvalid invalid, ApiResult result, String field, List<String> params) {
        this.invalid = invalid;
        this.result = result;
        this.field = field;
        this.params = params;
    }

    public ApiInvalid getInvalid() {
        return invalid;
    }

    public ApiResult getResult() {
        return result;
    }

    public String getField() {
        return field;
    }

    public List<String> getParams() {
        return params;
    }

    public void setInvalid(ApiInvalid invalid) {
        this.invalid = invalid;
    }

    public void setResult(ApiResult result) {
        this.result = result;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
