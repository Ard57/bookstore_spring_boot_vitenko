package com.vitenko.bookstore.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/order/*", "/logout", "/cart/purchase"})
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            req.setAttribute("message", "Please, log in to view this content");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.sendRedirect("/login?message=Please, log in to view this content.");
            return;
        }
        chain.doFilter(req, res);
    }
}
