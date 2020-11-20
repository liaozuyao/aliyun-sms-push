package com.sjyf.aliyun.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AliYunSendMessage {

    public String regionId;
    public String accessKeyId;
    public String accessSecret;
    public String signName;

    /**
     *
     * @param phoneNum 发送手机
     * @param templateCode 短信模板code
     * @param templateParam 短信内容参数JSON格式
     * @return
     */
    public String sendMessage(String phoneNum, String templateCode, String templateParam){
        DefaultProfile profile = DefaultProfile.getProfile(this.regionId, this.accessKeyId, this.accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", this.regionId);
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", this.signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("response data : {}", response.getData());
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}