package servlet;

import bean.Message;
import dao.GetEmail;
import utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/sendEmail")
public class SendEmail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        send(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        send(req,resp);

    }
    private void send(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Message msg=new Message();
        msg.setUsername(req.getParameter("username"));
        String address = req.getParameter("address");//用户输入的邮箱地址
        StringBuilder addressBuilder;
        if (address.equals("null")) {
            addressBuilder = new StringBuilder();
        }else{
            addressBuilder = new StringBuilder(address);
        }
        if(addressBuilder.length()==0)
        {
           addressBuilder.append(new GetEmail().getEmail(msg));
        }
        String newAddress = addressBuilder.toString();
        if(newAddress.equals("null")){//输入的用户名不存在时，参数传不回去。
            req.setAttribute("username",msg.getUsername());
            req.setAttribute("error","用户名不存在，请重新输入！");
            req.getRequestDispatcher("/RegetPassword.jsp").forward(req,resp);
            return;
        }
        System.out.println("username:"+msg.getUsername());
        System.out.println(newAddress);
        String code = getCode();//生成的验证码
        System.out.println(code);
        SendMail sendMail = new SendMail(newAddress);
        sendMail.setTitle("KZSDN");
        sendMail.setContent("您本次的验证码为：" + code + ",仅限一分钟内输入有效！");
        try {
            //发送验证码
            sendMail.sendMessage();
            //将验证码存入Cookie中
            Cookie emailCode = new Cookie("EmailCode", code);
            emailCode.setMaxAge(60); //设置cookie最大存活时间1分钟
            resp.addCookie(emailCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getCode() {
        int code = new Random().nextInt(9000) + 1000;
        return code + "";
    }
}
