package com.sjyf.aliyun.config;

import com.sjyf.aliyun.properties.AliYunMessageProperties;
import com.sjyf.aliyun.util.AliYunSendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AliYunMessageProperties.class)
@ConditionalOnProperty(prefix = "aliyun.sms", value = "enable", havingValue = "true")
public class AliYunMessageAutoConfiguration {

    private final AliYunMessageProperties aliYunMessageProperties;

    @Bean
    public AliYunSendMessage aliYunSendMessage(){
        return new AliYunSendMessage(aliYunMessageProperties.getRegionId(), aliYunMessageProperties.getAccessKeyId(),
                aliYunMessageProperties.getAccessKeySecret(), aliYunMessageProperties.getSignName());
    }
}
