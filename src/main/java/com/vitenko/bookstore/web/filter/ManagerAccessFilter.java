package com.vitenko.bookstore.web.filter;

import com.vitenko.bookstore.data.entity.User;
import com.vitenko.bookstore.service.dto.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerAccessFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Object user = req.getSession().getAttribute("user");
        if (user != null) {
            UserDto userDto = (UserDto) user;
            StringBuffer requestURL = req.getRequestURL();
            if (requestURL.indexOf("user/show") > -1) {
                if (userDto.getRole() != User.Role.MANAGER && userDto.getRole() != User.Role.ADMIN) {
                    if (Long.parseLong(req.getParameter("id")) != (userDto.getId())) {
                        redirect(req, res);
                        return;
                    }
                }
            } else if (requestURL.indexOf("order") > -1 || requestURL.indexOf("all") > -1) {
                if (userDto.getRole() != User.Role.MANAGER && userDto.getRole() != User.Role.ADMIN) {
                    redirect(req, res);
                    return;
                }
            }
        }
        chain.doFilter(req, res);
    }

    private void redirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setAttribute("message", "Sorry, you are forbidden to view this content");
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.sendRedirect("/login?message=Sorry, you are forbidden to view this content. " +
                "You can try logging in as user with access to this content.");
    }
}
