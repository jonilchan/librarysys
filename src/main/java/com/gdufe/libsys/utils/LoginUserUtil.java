package com.gdufe.libsys.utils;



import javax.servlet.http.HttpServletRequest;

/**
 * 在Cookie中获取用户id工具类
 */
public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     * @param request
     * @return
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userId");
        if (userIdString.equals("") || userIdString == null) {
            return 0;
        }
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }
}
