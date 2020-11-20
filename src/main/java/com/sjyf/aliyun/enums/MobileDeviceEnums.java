package com.sjyf.aliyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Created by liaozy
 * @Date 2020/11/20 11:00
 */
@Getter
@AllArgsConstructor
public enum MobileDeviceEnums {

    ANDROID(1, "ANDROID"),
    IOS(2, "IOS")
    ;
    private final Integer code;

    private final String desc;
}
