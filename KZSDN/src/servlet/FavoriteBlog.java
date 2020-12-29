package servlet;

import bean.Blog;
import dao.AddFavorite;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/favoriteblog")
public class FavoriteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog b=new Blog();
        b=(Blog)req.getSession().getAttribute("blog");
        int sum=b.getFavorite();
        sum++;
        b.setFavorite(sum);
        String blogid=b.getId();
        String username=req.getParameter("username");
        try {
            boolean f=new AddFavorite().addFavorite(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
