package com.lucas.fleetmanager.utils;

import com.lucas.fleetmanager.dto.ResponseDTO;
import org.springframework.http.HttpStatus;

public class ResponseUtils {

    public static ResponseDTO buildOK(Object data) {
        return ResponseDTO.builder()
                .status(HttpStatus.OK.toString())
                .code(HttpStatus.OK.value())
                .data(data)
                .build();
    }

}
