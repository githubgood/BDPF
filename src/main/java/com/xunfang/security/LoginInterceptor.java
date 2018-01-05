package com.xunfang.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xunfang.bdpf.system.competence.entity.Competence;

/**
 * 
 * @ClassName LoginInterceptor
 * @Description: 登录拦截器类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午8:49:34
 * @version V1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//模块名称
	private String entry;
	
	//uri集合
	private String[] uriPatterns;
	
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	public void setUriPatterns(String[] uriPatterns) {
		this.uriPatterns = uriPatterns;
	}

	@Override
	/** 
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
     */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	/** 
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操 
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像， 
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor 
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。 
     */  
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	/** 
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
     */ 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = super.preHandle(request, response, handler);
		if (flag && uriPatterns != null) {
			boolean passing = false;
			//请求的URI地址
			String uri = request.getRequestURI();
			//站点的根路径
			String ctx = request.getContextPath();
			//进行遍历，过滤掉不以站点根路径开头的URI
			for (String uriPattern : uriPatterns) {
				String checkPattern = ctx + uriPattern;
				if (!passing && uri.startsWith(checkPattern)) {
					passing = true;
					break;
				}
			}
			//如果满足条件
			if (!passing) {
				//如果userid为空，则重定向到指定的路径
				HttpSession session = request.getSession();
				Object user = session.getAttribute("loginuser");
				if (user == null) {
					response.sendRedirect(ctx + entry);
					return false;
				}else{//权限判断,如果当前登录的用户没有当前菜单的访问权限，则进行页面友好提示
					List<Competence> menulist  = (List<Competence>)session.getAttribute("menulist");
					if(menulist==null){
						response.sendRedirect(ctx + entry);
						return false;
					}else{
							if(iscompetence(uri, menulist)){
								return true;
							}else{
								response.sendRedirect(ctx + "/login/nopermission");
								return false;
							}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	  * @Title: iscompetence
	  * @Description: 根据当前请求的URI判断该用户是否有该菜单的权限
	  *  @param uri String 当前请求的URI
	  *  @param menulist List<Competence>  当前用户的所有权限
	  *  @return  boolean 返回类型
	  * @throws
	 */
	public  boolean iscompetence(String uri,List<Competence> menulist){
		for (Competence competence : menulist) {
			if(uri.indexOf(competence.getReqMapping())>0||uri.indexOf("datasite")>0||uri.indexOf("datasource")>0
					||uri.indexOf("resourceAssembly")>0||uri.indexOf("experiment")>0||uri.indexOf("/user/to_updpassword")>0){
				return true;
			}else if(competence.getReqMapping().equals("/assembly")){//只要拥有机器学习的权限，则拥有以下权限
				if(uri.indexOf("sourcetarget")>0||uri.indexOf("datapretreatment")>0||uri.indexOf("featureengineering")>0
						||uri.indexOf("machinelearning")>0||uri.indexOf("statisticalanalysis")>0||uri.indexOf("textanalysis")>0){
					return true;
				}
			}
		}
		return false;
	}
}