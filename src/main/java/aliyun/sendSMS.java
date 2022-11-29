package aliyun;



import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import java.util.*;


/**
 * 短信测试
 */
@Slf4j
public class sendSMS {
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAI4G5McZReLtqmYs5fqPAQ";
    static final String accessKeySecret = "dijhjtYsB3JKTBnead9igaVqeTL2dU";

    public static SendSmsResponse sendSms(String telephone) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(telephone);
        request.setSignName("新区供热"); //
        request.setTemplateCode("SMS_242925729");  //
        request.setTemplateParam("{\"devname\":\"新华测试01\",\"tagdesc\":\"新华测试01\",\"leveldesc\":\"新华测试01\"}");

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            log.info("短信发送成功！");
        } else {
            log.error("短信发送失败！");
        }
        return sendSmsResponse;
    }



    public static void main(String[] args) throws ClientException {
        SendSmsResponse sendSms =sendSms("19121154736");
        log.info("短信接口返回的数据----------------！");
        log.info("Code=" + sendSms.getCode());
        log.info("Message=" + sendSms.getMessage());
        log.info("RequestId=" + sendSms.getRequestId());
        log.info("RequestId=" + sendSms.getRequestId());
        log.info("BizId=" + sendSms.getBizId());
    }

}
