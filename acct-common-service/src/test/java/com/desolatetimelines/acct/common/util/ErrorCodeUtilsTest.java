package com.desolatetimelines.acct.common.util;

import org.junit.jupiter.api.Test;

import static com.desolatetimelines.acct.common.util.ErrorCodeUtils.computeErrorCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCodeUtilsTest {

    @Test
    public void computeErrorCode_works() {
        assertEquals("0x010010001", computeErrorCode(1, 1, 1));
        assertEquals("0x0A00A000A", computeErrorCode(10, 10, 10));
        assertEquals("0x100100010", computeErrorCode(16, 16, 16));
        assertEquals("0x110110011", computeErrorCode(17, 17, 17));
        assertEquals("0x1A01A001A", computeErrorCode(26, 26, 26));
        assertEquals("0xFF0FF00FF", computeErrorCode(255, 255, 255));
        assertEquals("0xFFFFFFFFF", computeErrorCode(255, 4095, 65535));
    }

}
