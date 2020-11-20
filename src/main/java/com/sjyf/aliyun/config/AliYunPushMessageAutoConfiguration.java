package com.sjyf.aliyun.config;

import com.sjyf.aliyun.properties.AliYunPushMessageProperties;
import com.sjyf.aliyun.util.AliYunPushMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by liaozy
 * @Date 2020/11/19 15:00
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AliYunPushMessageProperties.class)
@ConditionalOnProperty(prefix = "aliyun.push", value = "enable", havingValue = "true")
public class AliYunPushMessageAutoConfiguration {

    private final AliYunPushMessageProperties aliYunPushMessageProperties;

    @Bean
    @ConditionalOnMissingBean
    public AliYunPushMessage aliYunPushMessage(){
        return new AliYunPushMessage(aliYunPushMessageProperties.getRegionId(), aliYunPushMessageProperties.getAccessKeyId(), aliYunPushMessageProperties.getAccessKeySecret(),
                aliYunPushMessageProperties.getAppKeyAndroid(), aliYunPushMessageProperties.getAppKeyIOS());
    }

}
