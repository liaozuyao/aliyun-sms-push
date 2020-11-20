package com.sjyf.aliyun.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Created by liaozy
 * @Date 2020/11/19 15:08
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "aliyun.push")
public class AliYunPushMessageProperties {

    private Boolean enable = false;
    private String accessKeyId;
    private String accessKeySecret;
    private String regionId = "cn-hangzhou";
    private String appKeyAndroid;
    private String appKeyIOS;
}
