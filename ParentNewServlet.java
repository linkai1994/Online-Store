import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParentNewServlet extends HttpServlet{
	private static final String packageName="com.bwf.model.dao.";
	protected String className;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			//先通过包名和类名来获得类型
			Class<?> targetClass=Class.forName(packageName+className);
			//声明构造函数所带的参数类型
			Constructor construc=targetClass.getConstructor(HttpServletRequest.class,HttpServletResponse.class);
			Method m = targetClass.getMethod(action);
			m.invoke(construc.newInstance(request,response));			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
}
