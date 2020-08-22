package com.lucas.fleetmanager.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ResponseDTO {

    String status;
    Integer code;
    List<String> messages;
    Object data;

}
