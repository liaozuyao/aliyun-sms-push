package com.sjyf.aliyun.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import com.google.gson.Gson;
import com.sjyf.aliyun.enums.MobileDeviceEnums;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @Created by liaozy
 * @Date 2020/11/19 15:00
 */
@Slf4j
@AllArgsConstructor
public class AliYunPushMessage {

    public String regionId;
    public String accessKeyId;
    public String accessSecret;
    public String appKeyAndroid;
    public String appKeyIOS;

    /**
     *
     * @param deviceTypeEnum  设备类型
     * @param userId 阿里云推送别名
     * @param title 消息头
     * @param body 消息体
     * @param extParam 扩展参数
     * @param activity 安卓activity地址 （如: com.zhongzhi.ui.home.activity.ActivityOrderInfo）
     */
    public void pushMessage(MobileDeviceEnums deviceTypeEnum, String userId, String title, String body, String extParam, String activity){
        DefaultProfile profile = DefaultProfile.getProfile(this.regionId, this.accessKeyId, this.accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        PushRequest pushRequest = new PushRequest();
        switch (deviceTypeEnum) {
            case IOS:
                // 推送目标。
                pushRequest.setAppKey(Long.valueOf(this.appKeyIOS));
                pushRequest.setTarget("ACCOUNT"); //推送目标。device：推送给设备; account：推送给指定帐号,tag：推送给自定义标签; all：推送给全部。
                pushRequest.setTargetValue(userId);
                pushRequest.setPushType("NOTICE"); // 消息类型MESSAGE NOTICE。
                pushRequest.setDeviceType("iOS"); // 设备类型ANDROID iOS ALL。
                // 推送配置。
                pushRequest.setTitle(title); // 消息的标题。
                pushRequest.setBody(body); // 消息的内容。
                pushRequest.setIOSApnsEnv("PRODUCT");
                pushRequest.setIOSExtParameters(extParam);
                // 推送配置：Android。
                //pushRequest.setAndroidNotifyType("BOTH");//通知的提醒方式。"VIBRATE"：震动、"SOUND"：声音、"BOTH"：声音和震动、NONE：静音
                //pushRequest.setAndroidOpenType("APPLICATION"); //点击通知后动作。"APPLICATION"：打开应用、"ACTIVITY"：打开AndroidActivity、"URL"：打开URL、"NONE"：无跳转。
                // 指定notificaitonchannel id。
                break;
            case ANDROID:
                // 推送目标。
                pushRequest.setAppKey(Long.valueOf(this.appKeyAndroid));
                pushRequest.setTarget("ACCOUNT"); //推送目标。device：推送给设备; account：推送给指定帐号,tag：推送给自定义标签; all：推送给全部。
                pushRequest.setTargetValue(userId);
                pushRequest.setPushType("NOTICE"); // 消息类型MESSAGE NOTICE。
                pushRequest.setDeviceType("ANDROID"); // 设备类型ANDROID iOS ALL。
                // 推送配置。
                pushRequest.setTitle(title); // 消息的标题。
                pushRequest.setBody(body); // 消息的内容。
                pushRequest.setAndroidOpenType("ACTIVITY");
                pushRequest.setAndroidActivity(activity);
                //设置辅助推送配置
                pushRequest.setAndroidPopupActivity(activity);
                pushRequest.setAndroidPopupTitle(title);
                pushRequest.setAndroidPopupBody(body);
                // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
                pushRequest.setStoreOffline(true);
                //推送消息类型时，设置true，设备离线时会自动把消息转成辅助通道的通知
                pushRequest.setAndroidRemind(true);
                // 72小时后消息失效, 不会再发送
                String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 72 * 3600 * 1000));
                pushRequest.setExpireTime(expireTime);
                pushRequest.setAndroidExtParameters(extParam);
                // 推送配置：Android。
                // 指定notificaitonchannel id。
                //pushRequest.setAndroidNotificationChannel("zhongzhi_android");
                break;
            default:
                break;
        }
        try {
            PushResponse response = client.getAcsResponse(pushRequest);
            log.info("push response : {}", new Gson().toJson(response));
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
