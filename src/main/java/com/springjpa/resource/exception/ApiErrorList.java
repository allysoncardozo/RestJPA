package com.springjpa.resource.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorList  extends  ApiError{

    private List<String> erros;
    public ApiErrorList(int code, String msg, LocalDateTime date, List<String> erros){
        super(code,msg, date);
        this.erros = erros;
    }
}
