package com.sdd.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.response.ApiResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass

public class ResponseUtils {

    public <T> ApiResponse<T>  createSuccessResponse(T  data, TypeReference<T> tClass){
        ApiResponse<T> response  =   new ApiResponse<>();
        response.setResponse(data);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("succss");
        return response;
    }
}
