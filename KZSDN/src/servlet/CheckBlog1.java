package servlet;

import bean.Blog;
import bean.Comments;
import dao.GetBlog1;
import dao.GetComments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checkblog1")
public class CheckBlog1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog=new Blog();
        blog.setId(req.getParameter("check_id"));
        blog=new GetBlog1().getBlog(blog);
        ArrayList<Comments> comments=new ArrayList<>();
        comments=new GetComments().getComments(blog.getId());
        for(int i=0;i<comments.size();i++)
        {
            ArrayList<Comments>cmts=new ArrayList<>();
            String superid=comments.get(i).getCommentid();
            for(int j=0;j<comments.size();j++)
            {
                if(j==i)
                    j++;
                if(j==comments.size())
                    break;
                if(comments.get(j).getSuperid()!=null) {
                    Comments cmt = comments.get(j);
                    if (superid.equals(cmt.getSuperid())) {
                        cmts.add(cmt);
                    }
                }
            }
            comments.get(i).setCommentlist(cmts);
        }
        req.getSession().setAttribute("comments",comments);
        req.getSession().setAttribute("blog", blog);
        req.getRequestDispatcher("/ReadBlog1.jsp").forward(req,resp);
    }
}
