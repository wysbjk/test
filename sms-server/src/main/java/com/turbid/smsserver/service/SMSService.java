package com.turbid.smsserver.service;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.turbid.basicapi.entity.BJSON;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SMSService {

    // 短信应用SDK AppID
    @Value("${com.turbid.sms.appid}")
    private int appid; // 1400开头

    // 短信应用SDK AppKey
    @Value("${com.turbid.sms.appkey}")
    private String appkey;

    // 签名
    @Value("${com.turbid.sms.smsSign}")
    private String smsSign;

    /**
     * 通用验证码
     * @return
     */
    public BJSON sendSMS(String mobile,String code) throws InterruptedException {
        BJSON bjson=new BJSON();
        // 短信模板ID，需要在短信应用中申请
        int templateId = 235406; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        SmsSingleSenderResult result=null;
        try {
            String[] params = {code};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.sendWithParam("86", mobile,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            bjson.setStatus(200);
            bjson.setMassages("发送成功");
            bjson.setData(code);
        } catch (HTTPException e) {
            bjson.setStatus(-1);
            bjson.setMassages("发送失败");
            bjson.setData(null);
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            bjson.setStatus(-1);
            bjson.setMassages("发送失败");
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            bjson.setStatus(-1);
            bjson.setMassages("发送失败");
            // 网络IO错误
            e.printStackTrace();
        }
        return bjson;
    }

    /**
     * 自定义短线
     * @param phone
     * @param params
     * @param templateId
     * @return
     */
    public  int sendSMS(String phone,String[] params,int templateId){
        SmsSingleSenderResult result=null;
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.sendWithParam("86", phone,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return result.getResponse().statusCode;
    }

    /**
     * 多人群发
     * @param phoneNumbers
     * @param params
     * @param templateId
     * @return
     */
    public int sendSMS(String[] phoneNumbers,String[] params,int templateId){
        SmsMultiSenderResult result=null;
        try {
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return result.getResponse().statusCode;
    }



}
