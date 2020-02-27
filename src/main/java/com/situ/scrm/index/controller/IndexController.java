/**
 * @Company:中享思途   
 * @Title:IndexController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午3:23:18     
 */
package com.situ.scrm.index.controller;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.ConfigUtils;

/**
 * @ClassName:IndexController
 * @Description:(Index的Controller层)
 */
@RestController
public class IndexController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX = "index";
	private static final String PAGE_LOGIN = "login";

	@Autowired
	private UserService userService;
	
	@GetMapping(path = { "/gologin" })
	public ModelAndView goLog(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}

	/**
	 * @Title: goIndex
	 * @Description:(进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(path = { "/index", "/" })
	public ModelAndView goLogin(ModelAndView modelAndView,HttpServletRequest request, HttpSession session,
			@CookieValue(value = ConfigUtils.COOKIE_NAME, required = false) String remember) {
		String userCode = "";
		if (remember != null) {
			String[] array = remember.split(":");
			userCode = array[0];
			Long rowId = Long.parseLong(array[1]);
//			使用usercode和id到数据库中查询用户实例
			User user = userService.findUserByCodeAndId(userCode, rowId);
			if (user != null) {// 判断有这个用户
//				满足用户登录状态
//				有登陆用户将用户信息放入session
				session.setAttribute(ConfigUtils.LOGIN_USER, user);
				userService.doUpdateLogin(user);
			}
		}
		if (session.getAttribute(ConfigUtils.LOGIN_USER) != null) {// 判断为登录状态
			modelAndView.addObject("authResourceList", userService.findAuthResourceList(userCode,session));
			modelAndView.setViewName(PAGE_INDEX);
		} else {// 判断为非登陆状态
			modelAndView.setViewName(PAGE_LOGIN);
		}
		return modelAndView;
	}

	/**
	 * 
	 * @Title: goIndex
	 * @Description:(登录成功进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(path = { "/success/{login}/{remember}" })
	public ModelAndView goIndex(ModelAndView modelAndView, @PathVariable String login, @PathVariable String remember,
			HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		User findUser = userService.findUserByCode(login);
		if (remember.equals("undefined")) {
			remember = "0";
		}
//		判断是否需要自动登录
		if (Integer.parseInt(remember) == 1 && findUser != null) {
//			不建议将用户CODE和用户密码写入到Cookie中
			String value = findUser.getUserCode() + ":" + findUser.getRowId();
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_NAME, value);
//			设置最长记录时间为7天
			cookie.setMaxAge(60 * 60 * 24 * 7);
//			设置cookie的路径
			cookie.setPath(request.getContextPath() + "/");
//			写出Cookie
			response.addCookie(cookie);
		} else {// 否则不需要自动登录
//			将Cookie删除掉
			Cookie cookie = new Cookie(ConfigUtils.COOKIE_NAME, "");
//			将相同名称的Cookie的最大登录时间设置成0并且写出，就是删除Cookie
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}
		modelAndView.addObject("authResourceList", userService.findAuthResourceList(findUser.getUserCode(),session));
		modelAndView.setViewName(PAGE_INDEX);
		modelAndView.addObject(ConfigUtils.LOGIN_USER, findUser);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: login
	 * @Description:(登录查找用户)
	 * @param user
	 * @return
	 */
	@GetMapping(path = { "/login" })
	public Integer login(User user) {
		User userFind = userService.login(user);
		if (userFind == null) {
			return 0;
		}
		return 1;
	}
	
	/**
	 * @退出用户登录状态
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/loginoutbuyer")
	public ModelAndView loginOutBuyer(ModelAndView modelAndView,HttpSession session) {
		session.removeAttribute(ConfigUtils.LOGIN_USER);
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}
}
