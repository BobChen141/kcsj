package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class SendMail {

    private String address;//收件人邮箱地址
    private String title;//邮件标题
    private String content;//邮件内容

    public SendMail(String address) {
        this.address = address;
    }

    //设置邮件标题
    public void setTitle(String title) {
        this.title = title;
    }
    //设置邮件内容
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 发送邮件
     */
    public void sendMessage() throws Exception {

        Properties prop = new Properties();

        //设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        //发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");

        //开启SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码
        ts.connect("smtp.qq.com", "2462478392", "gerrsagbqnsxecae");
        // 创建邮件
        Message message = createMail(session);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();

    }

    /**
     * 创建一封邮件
     */
    private MimeMessage createMail(Session session) throws Exception {

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 指明邮件的发件人
        message.setFrom(new InternetAddress("2462478392@qq.com"));

        // 指明邮件的收件人，如果发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));

        // 邮件的标题
        message.setSubject(title);

        // 邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");

        // 返回创建好的邮件对象
        return message;
    }

}