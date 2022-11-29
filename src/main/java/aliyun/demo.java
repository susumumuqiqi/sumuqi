package aliyun;

import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

public class demo {


    static final String accessKeyId = "LTAI4G5McZReLtqmYs5fqPAQ";
    static final String accessKeySecret = "dijhjtYsB3JKTBnead9igaVqeTL2dU";

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)//控制台获取
                .setAccessKeySecret(accessKeySecret);//控制台获取
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    // 调用接口AddSmsTemplate申请短信模板
    public static AddSmsTemplateResponseBody addSmsTemplate(Integer TemplateType, String TemplateName, String TemplateContent, String Remark) {
        try {
            com.aliyun.dysmsapi20170525.Client client = demo.createClient(accessKeyId, accessKeySecret);
            AddSmsTemplateRequest templateRequest = new AddSmsTemplateRequest();
            templateRequest.setTemplateType(TemplateType)
                    .setTemplateName(TemplateName)
                    .setTemplateContent(TemplateContent)
                    .setRemark(Remark);
            AddSmsTemplateResponse addSmsResponse = client.addSmsTemplate(templateRequest);
            return addSmsResponse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //调用接口QuerySmsTemplate查询短信模板的审核状态
    //审核备注:
    //1.如果审核状态为审核通过或审核中，参数Reason显示为“无审核备注”。
    //2.如果审核状态为审核未通过，参数Reason显示审核的具体原因。
    public static QuerySmsTemplateResponseBody querySmsTemplate(String TemplateCode) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = demo.createClient(accessKeyId, accessKeySecret);
        QuerySmsTemplateRequest templateRequest = new QuerySmsTemplateRequest();
        templateRequest.setTemplateCode(TemplateCode);
        try {
            QuerySmsTemplateResponse querySmsResponse = client.querySmsTemplate(templateRequest);
            return querySmsResponse.getBody();
        } catch (ServerException e) {
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    //调用接口ModifySmsTemplate修改未通过审核的短信模板
    public static ModifySmsTemplateResponseBody modifySmsTemplate(Integer TemplateType, String TemplateName, String TemplateContent, String Remark, String TemplateCode) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = demo.createClient(accessKeyId, accessKeySecret);
        ModifySmsTemplateRequest templateRequest = new ModifySmsTemplateRequest();

        templateRequest.setTemplateType(TemplateType)
                .setTemplateName(TemplateName)
                .setTemplateContent(TemplateContent)
                .setTemplateCode(TemplateCode)
                .setRemark(Remark);
        try {
            ModifySmsTemplateResponse modifySmsResponse = client.modifySmsTemplate(templateRequest);
            return modifySmsResponse.getBody();
        } catch (ServerException e) {
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 调用接口DeleteSmsTemplate删除短信模板
    public static DeleteSmsTemplateResponseBody deleteSmsTemplate(String TemplateCode) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = demo.createClient(accessKeyId, accessKeySecret);
        DeleteSmsTemplateRequest templateRequest = new DeleteSmsTemplateRequest();

        templateRequest.setTemplateCode(TemplateCode);
        try {
            DeleteSmsTemplateResponse deleteSmsTemplateResponse = client.deleteSmsTemplate(templateRequest);
            return deleteSmsTemplateResponse.getBody();
        } catch (ServerException e) {
            e.printStackTrace();
            return null;
        } catch (ClientException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static QuerySmsTemplateListResponseBody QuerySmsTemplateList(Integer PageIndex, Integer PageSize) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = demo.createClient(accessKeyId, accessKeySecret);

        QuerySmsTemplateListRequest templateListRequest = new QuerySmsTemplateListRequest();
        templateListRequest.setPageSize(PageIndex);
        templateListRequest.setPageSize(PageSize);

        try {
            QuerySmsTemplateListResponse querySmsTemplate = client.querySmsTemplateList(templateListRequest);
            return querySmsTemplate.body;
        } catch (ServerException e) {
            e.printStackTrace();

        } catch (ClientException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        QuerySmsTemplateListResponseBody querySmsTemplateListResponseBody = QuerySmsTemplateList(1, 50);
        for (QuerySmsTemplateListResponseBody.QuerySmsTemplateListResponseBodySmsTemplateList template : querySmsTemplateListResponseBody.smsTemplateList) {
            System.out.println(template.templateContent+"\t["+template.templateCode+","+template.getTemplateName()+","+template.getTemplateType()+","+template.getAuditStatus()+"]");
        }
    }

}
