package com.zestindia.product_management_api.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class BaseResponse {
    private int statusCode;
    private Object data;
    private String message;


    public BaseResponse setData(Object data) {
        this.data = data;
        this.statusCode = HttpStatus.OK.value();
        return this;
    }
    public BaseResponse setStatusCode(){
        this.statusCode = HttpStatus.OK.value();
        return this;
    }

    public BaseResponse setMessage(String message, Integer statusCode){
        this.statusCode = statusCode;
        this.message=message;
        return this;
    }

}
