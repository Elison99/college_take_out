package org.example.college.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.college.common.BaseContext;
import org.example.college.common.Result;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查询用户登录状态
 */
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //向下转型
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取请求的uri
        String uri = httpServletRequest.getRequestURI();

        log.info("拦截到请求: {}",uri);

        //放行的请求路径
        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };

        //判断是否需要处理
        boolean check = check(urls, uri);

        //3、如果不需要处理，则直接放行
        if (check){
            log.info("本次请求{}不需要处理",uri);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //判断登录状态，如果已经登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",httpServletRequest.getSession().getAttribute("employee"));

            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setThreadLocalId(empId);

            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //4-2、判断登录状态，如果已登录，则直接放行
        if(httpServletRequest.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",httpServletRequest.getSession().getAttribute("user"));

            Long userId = (Long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setThreadLocalId(userId);

            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        log.info("用户未登录");

        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配,检查本次请求是否需要放行
     * @param urls
     * @param RequestUri
     * @return
     */
    public boolean check(String[] urls,String RequestUri){
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, RequestUri);
            if (match)
                return true;
        }
        return false;
    }
}
