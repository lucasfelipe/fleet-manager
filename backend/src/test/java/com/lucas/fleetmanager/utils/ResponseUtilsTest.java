package com.lucas.fleetmanager.utils;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilsTest {


    public static final String OBJECT_DATA = "OBJECT_DATA";

    @Test
    void testBuildOK() {
        var responseDTO = ResponseUtils.buildOK(OBJECT_DATA);
        assertEquals(responseDTO.getCode(), HttpStatus.OK.value());
        assertEquals(responseDTO.getStatus(), HttpStatus.OK.toString());
        assertEquals(OBJECT_DATA, responseDTO.getData());
        assertNull(responseDTO.getMessages());
    }


}
