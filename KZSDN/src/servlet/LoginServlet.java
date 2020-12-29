package servlet;

import bean.Blog;
import bean.User;
import dao.*;
import bean.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        //System.out.println("dopost");
        ArrayList<Blog>menublogs=new ArrayList<>();
        menublogs=new GetMenuBlogs().getBlog();

        for(int i=0;i<menublogs.size();i++)
        {
            Blog b1=new Blog();
            b1=menublogs.get(i);
            b1=new GetBlog1().getBlog(b1);
            menublogs.set(i,b1);
        }
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        ArrayList<String>categorys=new ArrayList<>();
        categorys=new GetCategorys().getCategory(username);
        System.out.println("username="+username);
        System.out.println("password="+password);
        String checkstr="sb";
        //数据库通过用户名查询是否密码正确
        Message msg=new Message();
        msg.setUsername(username);
                msg.setPassword(password);
                boolean f= new CheckLogin().checkLogin(msg);
                if(f){//密码正确跳转主页面
                    User user=new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user=new GetUser().getUser(user);
                    if(user.getTouxiang()==null) {
                        user.setTouxiang("images/background2.jpg");
                    }
                    ArrayList<Blog>allblogs=new ArrayList<>();
                    allblogs=new GetBlogs().getBlog();
                    ArrayList<String>allcolumns1=new ArrayList<>();
                    allcolumns1=new GetMenu_Column().getColumn();
                    req.getSession().setAttribute("allcolumns",allcolumns1);
                    req.getSession().setAttribute("allblogs",allblogs);
                    req.getSession().setAttribute("user",user);
                    req.getSession().setAttribute("menu_blogs",menublogs);
                    req.getSession().setAttribute("username",username);
                    req.getSession().setAttribute("categorys",categorys);
                    req.getSession().setAttribute("checkstr",checkstr);
                req.setAttribute("username",username);
                if(username.equals("管理员"))
                {
                    ArrayList<User> users=new ArrayList<>();
                    users=new GetUsers().getUser();
                    req.getSession().setAttribute("users",users);
                    ArrayList<Blog> mblogs=new ArrayList<>();
                    mblogs=new GetBlogs().getBlog();
                    ArrayList<String>allcolumns=new ArrayList<>();
                    allcolumns=new GetMenu_Column().getColumn();
                    req.getSession().setAttribute("allcolumns",allcolumns);
                    req.getSession().setAttribute("manageblogs",mblogs);
                    req.getSession().setAttribute("users",users);
                    req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
                }
                else {
                    req.getRequestDispatcher("/Menu.jsp").forward(req, resp);
                }
                }
                else{//账号或密码错误
                req.setAttribute("username",username);
                req.setAttribute("error","用户名或密码错误，请重新输入！");
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
                }
    }
}
