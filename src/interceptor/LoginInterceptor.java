package interceptor;

import model.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.common.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_KEY_PROXY);
        if (admin != null || session.getAttribute(Constant.SESSION_KEY_SUPERVISOR_ACCOUNT_INFO) != null) {
            return true;
        }
        //System.out.println("当前session已失效，请重新登陆");
        response.sendError(401, "当前session已失效，请重新登陆");
        return false;
    }

}
