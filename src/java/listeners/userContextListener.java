package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.Admin;

public class userContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Admin user = (Admin)sc.getAttribute("loginDetails");
        sc.setAttribute("message", user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
