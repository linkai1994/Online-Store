import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.bwf.bean.User;


public class mainFilter implements Filter{
	
FilterConfig conf=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		conf=filterConfig;
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	//["do","action","vo"]   do
	private static boolean IsContains(String[] resource,String name)
	{
		for(int i=0;i<resource.length;i++)
		{
			if(resource[i].equals(name))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		HttpServletRequest realrequest=(HttpServletRequest)request;
		HttpServletResponseWrapper realresponse=new HttpServletResponseWrapper((HttpServletResponse)response);
		
		String url=realrequest.getRequestURI();
		
		//http://localhost:8080/oa/student/login/name/chenchong/age/24
		
		//oa/student?action=login&name=chenchong&age=24
		
		//http://localhost:8080/oa/js/jquery-1.7.2.min.js
		
		//地址的前面四个元素分别是 域名  站点名  类名   方法名    0   1   2   3
		
		//System.out.println(url);
		
		String[] urlarr=url.split("/");
		
		String urlname=urlarr[urlarr.length-1];
		
		if(urlname.indexOf(".")==-1)
		{
			
			StringBuffer sb=new StringBuffer();
			
			sb.append(urlarr[2]);
			sb.append("?action=");
			sb.append(urlarr[3]);
			
			try
			{
				
				//大于4的是，是除了类名和方法名还有其他的参数
				if(urlarr.length>4)
				{
					for(int i=4;i<urlarr.length;i+=2)
					{
						sb.append("&");
						sb.append(urlarr[i]);
						sb.append("=");
						sb.append(urlarr[i+1]);
					}
				}
				//小于4个元素，是属于不完整路径
				else if(urlarr.length<4)
				{
					realrequest.getRequestDispatcher("erro?message=路径非法").forward(realrequest, realresponse);
				}
			}
			catch(Exception ex)
			{
				realrequest.getRequestDispatcher("erro?message=路径非法").forward(realrequest, realresponse);
			}
			
			
			realrequest.getRequestDispatcher("/"+sb.toString()).forward(realrequest, realresponse);
			
			//System.out.println(sb.toString());
		}
		else
		{
			String[] noFilter=conf.getInitParameter("noFilter").split(";");
			
			String[] extarr=urlname.split("\\.");
			String name=extarr[0];
			String extension=extarr[extarr.length-1];
			
			if(this.IsContains(noFilter, extension))
			{
				chain.doFilter(realrequest, realresponse);
										
			}
			
		}
		
//		//temp.do  jquery-1.7.2.min.js
//		String[] extarr=urlname.split("\\.");
//		String name=extarr[0];
//		String extension=extarr[extarr.length-1];
//		
//		String[] confextarr=conf.getInitParameter("extvalue").split(";");
//
//		String[] noFilter=conf.getInitParameter("noFilter").split(";");
//
//		String[] noSession=conf.getInitParameter("noSession").split(";");
//		
//		if(this.IsContains(noFilter, extension))
//		{
//			chain.doFilter(realrequest, realresponse);
//			
//			return;
//			
//		}
//		
//		
//		if(this.IsContains(confextarr, extension))
//		{
//			//realrequest.getRequestDispatcher(name).forward(realrequest, realresponse);
//			
//			if(this.IsContains(noSession, name))
//			{
//				realrequest.getRequestDispatcher(name).forward(realrequest, realresponse);
//			}
//			else
//			{
//				Student student=(Student)realrequest.getSession().getAttribute("studentinfo");
//				if(student==null)
//				{
//					realrequest.getRequestDispatcher("erro?message=没有登录").forward(realrequest, realresponse);
//				}
//				else
//				{
//					realrequest.getRequestDispatcher(name).forward(realrequest, realresponse);
//				}
//			}
//		}
//		else
//		{
//			realrequest.getRequestDispatcher("erro?message=路径非法").forward(realrequest, realresponse);
//		}
	}

}
