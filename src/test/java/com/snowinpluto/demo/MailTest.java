package com.snowinpluto.demo;

import com.snowinpluto.demo.utils.mail.MailHostConfig;
import com.snowinpluto.demo.utils.mail.MailUtil;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MailTest {

    @Test
    public void testSend() throws Exception {
        MailHostConfig mailHostConfig = new MailHostConfig();
        mailHostConfig.setUsername("zhongkeqiang@lngtop.com");
        mailHostConfig.setPassword("-a874405828");
        mailHostConfig.setSmtpHost("smtp.lngtop.com");
        mailHostConfig.setSmtpPort(25);
        mailHostConfig.setSmtpAuth(true);

        MailUtil.send(mailHostConfig,
                      "zhongkeqiang@lngtop.com",
                      "505898973@qq.com",
                      "Test标题",
                      "Test内容");
    }
}
