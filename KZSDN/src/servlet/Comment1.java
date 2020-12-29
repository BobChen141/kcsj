package servlet;

import bean.Category;
import bean.Comments;
import dao.AddComment1;
import dao.GetComments;
import utils.GetID;
import utils.GetTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/comment1")
public class Comment1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        Comments cmt=new Comments();
        cmt.setRate(1);
        String id= GetID.main(null);
        cmt.setCommentid(id);
        cmt.setTime(GetTime.getTime());
        String comment=req.getParameter("textarea1");
        String superid=req.getParameter("superid");
        String supername=req.getParameter("supername");
        String username=(String)req.getSession().getAttribute("username");
        cmt.setBlogid(req.getParameter("cgetid"));
        cmt.setUsername(username);
        cmt.setComment(comment);
        cmt.setSuperid(superid);
        cmt.setSupername(supername);
        try {
            boolean f= new AddComment1().addcomment(cmt);
            if(f)
            {
                ArrayList<Comments> comments=new ArrayList<>();
                comments=new GetComments().getComments(cmt.getBlogid());
                for(int i=0;i<comments.size();i++)
                {
                    ArrayList<Comments>cmts=new ArrayList<>();
                    String superid1=comments.get(i).getCommentid();
                    for(int j=0;j<comments.size();j++)
                    {
                        if(j==i)
                            j++;
                        if(j==comments.size())
                            break;
                        if(comments.get(j).getSuperid()!=null) {
                            Comments cmt1 = comments.get(j);
                            if (superid1.equals(cmt1.getSuperid())) {
                                cmts.add(cmt1);
                            }
                        }
                    }
                    comments.get(i).setCommentlist(cmts);
                }
                req.getSession().setAttribute("comments",comments);
                req.getRequestDispatcher("/ReadBlog.jsp").forward(req,resp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
