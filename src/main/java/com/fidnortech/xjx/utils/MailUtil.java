package com.fidnortech.xjx.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MailUtil {

    /**
     * 1. 来源人名
     * 2. 来源内容
     */
    public static final String originalText = "<hr style=\"border: 1px dashed #ef859d2e;margin: 20px 0\">\n" +
            "            <div>\n" +
            "                <div style=\"font-size: 18px;font-weight: bold;color: #C5343E\">\n" +
            "                    %s\n" +
            "                </div>\n" +
            "                <div style=\"margin-top: 6px;font-size: 16px;color: #000000\">\n" +
            "                    <p>\n" +
            "                        %s\n" +
            "                    </p>\n" +
            "                </div>\n" +
            "            </div>";

    /**
     * 发件人
     */
    public static final String replyMail = "你之前的评论收到来自 %s 的回复";
    public static final String commentMail = "你的文章 %s 收到来自 %s 的评论";
    public static final String messageMail = "你收到来自 %s 的留言";
    public static final String loveMail = "你收到来自 %s 的祝福";
    public static final String imMail = "你收到来自 %s 的消息";
    public static final String notificationMail = "你收到来自 %s 的订阅";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 1. 网站名称
     * 2. 邮件类型
     * 3. 发件人
     * 4. 发件内容
     * 5. originalText
     * 6. 网站名称
     */
    private String mailText;

    @PostConstruct
    public void init() {
        this.mailText = "<div style=\"font-family: serif;line-height: 22px;padding: 30px\">\n" +
                "    <div style=\"display: flex;justify-content: center;width: 100%%;max-width: 900px;background-image: url('https://s1.ax1x.com/2022/12/04/zsKgDs.jpg');background-size: cover;border-radius: 10px\"></div>\n" +
                "    <div style=\"margin-top: 20px;display: flex;flex-direction: column;align-items: center\">\n" +
                "        <div style=\"margin: 10px auto 20px;text-align: center\">\n" +
                "            <div style=\"line-height: 32px;font-size: 26px;font-weight: bold;color: #000000\">\n" +
                "                嘿！你在 %s 中收到一条新消息。\n" +
                "            </div>\n" +
                "            <div style=\"font-size: 16px;font-weight: bold;color: rgba(0, 0, 0, 0.19);margin-top: 21px\">\n" +
                "                %s\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div style=\"min-width: 250px;max-width: 800px;min-height: 128px;background: #F7F7F7;border-radius: 10px;padding: 32px\">\n" +
                "            <div>\n" +
                "                <div style=\"font-size: 18px;font-weight: bold;color: #C5343E\">\n" +
                "                    %s\n" +
                "                </div>\n" +
                "                <div style=\"margin-top: 6px;font-size: 16px;color: #000000\">\n" +
                "                    <p>\n" +
                "                        %s\n" +
                "                    </p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            %s\n" +
                "            <a style=\"width: 150px;height: 38px;background: #ef859d38;border-radius: 32px;display: flex;align-items: center;justify-content: center;text-decoration: none;margin: 40px auto 0\"\n" +
                "               href=\"https://fidnor.com\" target=\"_blank\">\n" +
                "                <span style=\"color: #DB214B\">https://fidnor.com</span>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div style=\"margin-top: 20px;font-size: 20px;color: #00000045\">\n" +
                "            备注xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";
    }

    public String getMailText() {
        return mailText;
    }

    private String mailCustom;

    @PostConstruct
    public void initCustom() {
        this.mailCustom = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%%\" bgcolor=\"#EDF9F5\" style=\"font-size: 14px;font-family: Arial;padding-bottom: 40px;\">\n" +
                "        <tr>\n" +
                "            <td height=\"30\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <table border=\"0\" cellpadding=\"8\" cellspacing=\"0\" width=\"600\" bgcolor=\"#FFFFFF\" align=\"center\">\n" +
                "                    <tr>\n" +
                "                        <td bgcolor=\"#23c09d\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table border=\"1\" cellpadding=\"8\" cellspacing=\"0\" width=\"100%%\" align=\"center\" style=\"border-collapse: collapse;border-color: #ebeef5\">\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" align=\"center\" style=\"color: #23c09d;font-weight: 500;font-size: 1.5rem;border-bottom: 1px solid #dcdfe6\">\n" +
                "                                        收到一封来自 %s 的测试样品请求邮件 \n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" style=\"font-size: 1.1rem;color: #1f2f3d\">\n" +
                "                                        <span style=\"border-left: 3px solid #23c09d;padding-left: 20PX;\">\n" +
                "                                            firstname: %s \n" +
                "                                        </span>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" style=\"font-size: 1.1rem;color: #1f2f3d\">\n" +
                "                                        <span style=\"border-left: 3px solid #23c09d;padding-left: 20PX;\">\n" +
                "                                            lastname: %s \n" +
                "                                        </span>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" style=\"font-size: 1.1rem;color: #1f2f3d\">\n" +
                "                                        <span style=\"border-left: 3px solid #23c09d;padding-left: 20PX;\">\n" +
                "                                            email: %s \n" +
                "                                        </span>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" style=\"font-size: 1.1rem;color: #1f2f3d\">\n" +
                "                                        <span style=\"border-left: 3px solid #23c09d;padding-left: 20PX;\">\n" +
                "                                            phone: %s \n" +
                "                                        </span>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td height=\"60\" style=\"font-size: 1.1rem;color: #1f2f3d\">\n" +
                "                                        <span style=\"border-left: 3px solid #23c09d;padding-left: 20PX;\">\n" +
                "                                            message: %s \n" +
                "                                        </span>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>";
    }

    public String getMailCustom() {
        return mailCustom;
    }

    private String successfullySent;

    @PostConstruct
    public void initSuccessfullySent() {
        this.successfullySent = "<table\n" +
                "      border=\"0\"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "      width=\"100%%\"\n" +
                "      bgcolor=\"#EDF9F5\"\n" +
                "      style=\"font-size: 14px; font-family: Arial; padding-bottom: 40px\"\n" +
                "    >\n" +
                "      <tr>\n" +
                "        <td height=\"30\"></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "        <td>\n" +
                "          <table\n" +
                "            border=\"0\"\n" +
                "            cellpadding=\"8\"\n" +
                "            cellspacing=\"0\"\n" +
                "            width=\"600\"\n" +
                "            bgcolor=\"#FFFFFF\"\n" +
                "            align=\"center\"\n" +
                "          >\n" +
                "            <tr>\n" +
                "              <td bgcolor=\"#23c09d\"></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td>\n" +
                "                <table\n" +
                "                  border=\"1\"\n" +
                "                  cellpadding=\"8\"\n" +
                "                  cellspacing=\"0\"\n" +
                "                  width=\"100%%\"\n" +
                "                  align=\"center\"\n" +
                "                  style=\"border-collapse: collapse; border-color: #ebeef5\"\n" +
                "                >\n" +
                "                  <tr>\n" +
                "                    <td\n" +
                "                      height=\"60\"\n" +
                "                      align=\"center\"\n" +
                "                      style=\"\n" +
                "                        color: #23c09d;\n" +
                "                        font-weight: 500;\n" +
                "                        font-size: 1.5rem;\n" +
                "                        border-bottom: 1px solid #dcdfe6;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      收到一封来自FidNor.com的邮件\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td height=\"60\" style=\"font-size: 1.1rem; color: #1f2f3d\">\n" +
                "                      <span\n" +
                "                        style=\"\n" +
                "                          border-left: 3px solid #23c09d;\n" +
                "                          padding-left: 20px;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                      The test sample request email you sent has been received \n" +
                "                      </span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td height=\"60\" style=\"font-size: 1.1rem; color: #1f2f3d\">\n" +
                "                      <span\n" +
                "                        style=\"\n" +
                "                          border-left: 3px solid #23c09d;\n" +
                "                          padding-left: 20px;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                      we will contact you later\n" +
                "                      </span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                  <tr>\n" +
                "                    <td height=\"60\" style=\"font-size: 1.1rem; color: #bbbec2\">\n" +
                "                      <span\n" +
                "                        style=\"\n" +
                "                          border-left: 3px solid #bbc9c6;\n" +
                "                          padding-left: 20px;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                      This email is a system callback, please reply in error\n" +
                "                      </span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>";
    }

    public String getSuccessfullySent() {
        return successfullySent;
    }


    @Async
    public void sendMailMessage(List<String> to, String subject, String text) {
        log.info("发送邮件===================");
        log.info("to：{}", JSON.toJSONString(to));
        log.info("subject：{}", subject);
//        log.info("text：{}", text);
        try {
            //true代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人1或多个
            mimeMessageHelper.setTo(to.toArray(new String[0]));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text, true);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            mailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("发送成功==================");
        } catch (MessagingException e) {
            log.info("发送失败==================");
            log.error(e.getMessage());
        }
    }

    public void sendMailOneMessage(String to, String subject, String text) {
        log.info("发送邮件===================");
        log.info("to：{}", JSON.toJSONString(to));
        log.info("subject：{}", subject);
//        log.info("text：{}", text);
        try {
            //true代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人1或多个
            mimeMessageHelper.setTo(to);
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text, true);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            mailSender.send(mimeMessageHelper.getMimeMessage());

            log.info("发送成功:"+to);
        } catch (Exception e) {
            log.info("发送失败==================");
            log.error(e.getMessage());
        }
    }
}
