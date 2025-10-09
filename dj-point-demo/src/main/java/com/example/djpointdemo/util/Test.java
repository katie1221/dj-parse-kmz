package com.example.djpointdemo.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qzz
 * @date 2025/9/17
 */
@Slf4j
public class Test {
    public static void main(String[] args) {

        // 校验手机号是否合法
        String phoneNumber = "13720823095";
        // 手机号正则表达式
        String phoneRegex = "^1[3-9]\\d{9}$";
        if (!phoneNumber.matches(phoneRegex)) {
            System.out.println("手机号格式不正确，请输入正确的手机号");
        }
    }
}
