package com.snowinpluto.demo.utils.mail;

import com.google.common.base.Strings;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 邮箱工具
 */
public class MailUtil {

    /**
     * 发送邮件
     * @param mailHostConfig 邮箱主机配置
     * @param messageFrom 消息发件人
     * @param messageTo 消息收件人
     * @param messageSubject 消息标题
     * @param messageText 消息内容
     * @return 是否发送成功
     */
    public static boolean send(final MailHostConfig mailHostConfig,
                               String messageFrom,
                               String messageTo,
                               String messageSubject,
                               String messageText) {
        // 参数验证
        checkNotNull(mailHostConfig);
        checkArgument(mailHostConfig.check());

        checkNotNull(messageFrom);
        checkNotNull(messageTo);
        checkNotNull(messageSubject);
        checkNotNull(messageText);

        // 创建 Session
        Session session = createSession(mailHostConfig);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(messageFrom));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(messageTo));
            message.setSubject(messageSubject);
            message.setText(messageText);

            // 发送消息
            Transport.send(message);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 创建 Session
     * @param mailHostConfig 邮箱主机配置
     * @return 创建的 Session
     */
    private static Session createSession(final MailHostConfig mailHostConfig) {
        // 转换 Properties
        Properties props = parseProperties(mailHostConfig);

        // 创建 Session
        return Session.getDefaultInstance(props,
                                   new Authenticator() {
                                       @Override
                                       protected PasswordAuthentication getPasswordAuthentication() {
                                           return new PasswordAuthentication(mailHostConfig.getUsername(), mailHostConfig.getPassword());
                                       }
                                   });
    }

    /**
     * 转换 Properties
     * @param mailHostConfig 邮箱主机配置
     * @return 转换后的 Properties
     */
    private static Properties parseProperties(MailHostConfig mailHostConfig) {
        // 组建 Properties
        Properties props = new Properties();
        props.put("mail.smtp.host", mailHostConfig.getSmtpHost());
        props.put("mail.smtp.port", String.valueOf(mailHostConfig.getSmtpPort()));
        props.put("mail.smtp.auth", String.valueOf(mailHostConfig.getSmtpAuth()));

        // TLS
        if (mailHostConfig.getSmtpStartTls() != null) {
            props.put("mail.smtp.starttls.enable", String.valueOf(mailHostConfig.getSmtpStartTls()));
        }

        // SSL
        if (mailHostConfig.getSmtpSocketFactoryPort() != null &&
                !Strings.isNullOrEmpty(mailHostConfig.getSmtpSocketFactoryClass())) {
            props.put("mail.smtp.socketFactory.port", String.valueOf(mailHostConfig.getSmtpSocketFactoryPort()));
            props.put("mail.smtp.socketFactory.class", mailHostConfig.getSmtpSocketFactoryClass());
        }
        return props;
    }
}
