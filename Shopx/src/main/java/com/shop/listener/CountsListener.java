package com.shop.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//监听器实现监控访问量
@WebListener
public class CountsListener implements HttpSessionListener {
    //有session创建就使访问人数加一
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer visited = (Integer) context.getAttribute("visited");
        if (visited == null) {
            visited = 1;
        } else {
            visited++;
        }
        context.setAttribute("visited", visited);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        ServletContext context = se.getSession().getServletContext();
        Integer visited = (Integer) context.getAttribute("visited");
        visited--;
        context.setAttribute("visited", visited);

    }
}
