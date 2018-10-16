package com.unisinsight.framework.uuv.common.interceptor;

import com.unisinsight.framework.uuv.common.utils.EscapeUtils;
import com.unisinsight.framework.uuv.common.utils.StringUtils;
import com.unisinsight.framework.uuv.common.utils.User;
import com.unisinsight.framework.uuv.common.utils.UserHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authorization)){
            return true;
        }

        authorization = EscapeUtils.unescape(EscapeUtils.unescape(authorization));

        String[] array = authorization.split("&");
        User user = new User();
        for (String line : array){
            String[] keyValue = line.split(":");
            if (keyValue.length < 2){
                continue;
            }
            if (keyValue[0].equals("usercode")){
                user.setUserCode(keyValue[1]);
            }

            if (keyValue[0].equals("username")){
                user.setUserName(keyValue[1]);
            }
        }

        UserHandler.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        UserHandler.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
