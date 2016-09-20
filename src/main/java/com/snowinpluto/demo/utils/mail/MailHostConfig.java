package com.snowinpluto.demo.utils.mail;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

/**
 * 邮箱主机（发送方）设置
 */
public class MailHostConfig {

    private String username;    // 邮箱用户名
    private String password;    // 密码

    private String smtpHost;    // SMTP 主机地址
    private Integer smtpPort;   // SMTP 端口号

    private Boolean smtpAuth;   // 是否验证

    /*********************** TLS *************************/
    private Boolean smtpStartTls;   // 是否开启 TLS
    /*********************** TLS *************************/

    /*********************** SSL *************************/
    private Integer smtpSocketFactoryPort;
    private String smtpSocketFactoryClass;
    /*********************** SSL *************************/

    public String getUsername() {
        return username;
    }

    public MailHostConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MailHostConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public MailHostConfig setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
        return this;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public MailHostConfig setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
        return this;
    }

    public Boolean getSmtpAuth() {
        return smtpAuth;
    }

    public MailHostConfig setSmtpAuth(Boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
        return this;
    }

    public Boolean getSmtpStartTls() {
        return smtpStartTls;
    }

    public MailHostConfig setSmtpStartTls(Boolean smtpStartTls) {
        this.smtpStartTls = smtpStartTls;
        return this;
    }

    public Integer getSmtpSocketFactoryPort() {
        return smtpSocketFactoryPort;
    }

    public MailHostConfig setSmtpSocketFactoryPort(Integer smtpSocketFactoryPort) {
        this.smtpSocketFactoryPort = smtpSocketFactoryPort;
        return this;
    }

    public String getSmtpSocketFactoryClass() {
        return smtpSocketFactoryClass;
    }

    public MailHostConfig setSmtpSocketFactoryClass(String smtpSocketFactoryClass) {
        this.smtpSocketFactoryClass = smtpSocketFactoryClass;
        return this;
    }

    /**
     * 验证是否完整有效
     */
    public boolean check() {
        if (Strings.isNullOrEmpty(username) ||
                Strings.isNullOrEmpty(password) ||
                Strings.isNullOrEmpty(smtpHost) ||
                smtpPort == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("username", username)
                          .add("password", password)
                          .add("smtpHost", smtpHost)
                          .add("smtpPort", smtpPort)
                          .add("smtpAuth", smtpAuth)
                          .add("smtpStartTls", smtpStartTls)
                          .add("smtpSocketFactoryPort", smtpSocketFactoryPort)
                          .add("smtpSocketFactoryClass", smtpSocketFactoryClass)
                          .toString();
    }
}
