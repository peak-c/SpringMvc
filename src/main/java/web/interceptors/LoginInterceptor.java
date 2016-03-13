package web.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录验证拦截器
 * controller执行前调用此方法
 * 返回true表示继续执行，返回false中止执行
 * 这里可以加入登录校验、权限拦截等
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是登录页面则放行
        System.out.println("HandlerInterceptor_URI-----"+request.getRequestURI());
        if(request.getRequestURI().indexOf("login")>0){
            System.out.println("HandlerInterceptor-----login");
            return true;
        }
        //如果是注册页面则放行
        if(request.getRequestURI().indexOf("regist")>=0){
            System.out.println("HandlerInterceptor-----regist");
            return true;
        }
        HttpSession session = request.getSession();
        System.out.println(""+session.getAttribute("student")+"###"+request.getRequestURI());
        //如果用户已登录也放行
//        if(session.getAttribute("student")!=null ) {
        if(session.getAttribute("student")!=null && request.getRequestURI().indexOf("/springMvc/student")>=0){

            System.out.println("HandlerInterceptor-----session"+request.getRequestURI());
            return true;
        }
        //如果用户已登录也放行
        // if(session.getAttribute("admin")!=null){
        if(session.getAttribute("admin")!=null && request.getRequestURI().indexOf("/springMvc/admin")>=0){
            System.out.println("HandlerInterceptor-----session"+request.getRequestURI());
            return true;
        }
        //用户没有登录跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login");
        //request.getRequestDispatcher("/WEB-INF/templates/login.html").forward(request, response);

        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
