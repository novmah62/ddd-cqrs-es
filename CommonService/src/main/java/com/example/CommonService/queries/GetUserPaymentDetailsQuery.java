package com.example.CommonService.queries;

public class GetUserPaymentDetailsQuery {

    private String userId;

    public GetUserPaymentDetailsQuery(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
