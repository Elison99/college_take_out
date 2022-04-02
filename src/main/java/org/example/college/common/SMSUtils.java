package org.example.college.common;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.io.IOException;

public class SMSUtils {

    public static final int VALIDATE_CODE = 1321640;//发送短信验证码
    public static final int ORDER_NOTICE = 1321703;//体检预约成功通知

    public static void sendSMS( String phoneNumber,int templateCode,String code) throws HTTPException, IOException, HTTPException {

        // 短信应用SDK AppID  1400开头
        int appid = 1400640675;
        // 短信应用SDK AppKey
        String appkey = "65292c5feef8a3e2b3ab587adaa45690";
        // 短信模板ID，需要在短信应用中申请
        int templateId = templateCode;
        // 签名，使用的是签名内容，而不是签名ID
        String smsSign = "传至健康";
        //随机生成四位验证码的工具类
//		String code = keyUtil.keyUtils();
//		try {
        //参数，一定要对应短信模板中的参数顺序和个数，
        String[] params = {code};
        //创建ssender对象
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        //发送
        SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");

/*			if(result.result!=0){
//				reStr = "error";
				throw new Exception();
			}
*//*			// 签名参数未提供或者为空时，会使用默认签名发送短信
			HttpSession session = request.getSession();
			//JSONObject存入数据
			JSONObject json = new JSONObject();
			json.put("Code", code);//存入验证码
			json.put("createTime", System.currentTimeMillis());//存入发送短信验证码的时间
			// 将验证码和短信发送时间码存入SESSION
			request.getSession().setAttribute("MsCode", json);*//*
//			reStr = "success";
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}catch (Exception e) {
			// 网络IO错误
			e.printStackTrace();
		}*/

    }


}
