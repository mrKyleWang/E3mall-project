package cn.e3mall.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;
/**
 * 用户登录拦截器
 * @author wking
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	
	@Value("${SSO_URL}")
	private String SSO_URL;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookie中取token 
		String token = CookieUtils.getCookieValue(request, "e3_token");
		//判断token是否存在
		if(StringUtils.isBlank(token)) {
			//如果token不存在,未登录,跳转到sso登录页面,登录成功后,跳转到当前请求url
			response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
			//拦截
			return false;
		}
		//如果token存在,调用sso系统服务,根据token取用户信息
		E3Result e3Result = tokenService.getUserByToken(token);
		if(e3Result.getStatus()!=200) {
			//取不到,用户登录已过期,需要登录
			response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
			//拦截
			return false;
		}
		//取到用户信息,写入request
		TbUser user = (TbUser) e3Result.getData();
		request.setAttribute("user", user);
		//判断cookie中是否有购物车数据,合并到服务端
		String jsonCartList = CookieUtils.getCookieValue(request, "cart",true);
		if(StringUtils.isNoneBlank(jsonCartList)) {
			//合并购物车
			cartService.mergeCart(user.getId(), JsonUtils.jsonToList(jsonCartList, TbItem.class));
		}
		//放行
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub

	}



}
