package com.sjyf.aliyun.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliYunMessageProperties {

    private Boolean enable = false;
    private String accessKeyId;
    private String accessKeySecret;
    private String regionId = "cn-hangzhou";
    private String signName;
}
