package com.bee.master.adapter.email;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

import static com.alibaba.fastjson.JSON.toJSONString;

@Slf4j
@Setter
@Component("aliyun")
@ConfigurationProperties(prefix = "aliyun.email")
public class AliyunEmailService implements EmailService {
    @NotBlank
    private String regionId;

    @NotBlank
    private String accessKey;

    @NotBlank
    private String secret;

    @NotBlank
    private String account;

    @NotBlank
    private String alias;

    @Override
    public void send(EmailMessage message) {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKey, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName(account);
            request.setFromAlias(alias);
            request.setAddressType(1);
            request.setReplyToAddress(false);
            request.setToAddress(message.getReceipt());
            request.setSubject(message.getSubject());
            request.setHtmlBody(message.getBody());
            request.setClickTrace("1");
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            log.debug("Email send response is " + toJSONString(httpResponse));
        } catch (ClientException e) {
            log.error("Error occur when send email with error code " + e.getErrCode(), e);
            throw EmailException.sendFailed("email_send_failed", e.getErrCode());
        }
    }
}
