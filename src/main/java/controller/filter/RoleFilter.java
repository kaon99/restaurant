package controller.filter;

/**
 * * @author Yaroslav
 *  * @version 1.0
 *  */

import controller.util.PageResourseManager;
import model.entity.User;
import model.entity.types.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.AttributesResourseManager;
import controller.util.CommandUtil;

import java.io.IOException;

import java.util.Objects;

public class RoleFilter implements Filter {
    private Logger logger = Logger.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Do Filter");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(AttributesResourseManager.getProperty("parameter.user"));
        String path = req.getRequestURI();
        if (path.contains("admin")) {
            if (user.getRole().equals(Role.ADMIN.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourseManager.getProperty("page_not_permissions"));
                return;
            }
        } else if (path.contains("client")) {
            if (Objects.nonNull(user.getEmail()) && user.getRole().equals(Role.CLIENT.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect(PageResourseManager.getProperty("page_not_permissions"));

                return;
            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }


    @Override
    public void destroy() {

    }
}
