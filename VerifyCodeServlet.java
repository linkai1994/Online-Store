import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyCodeServlet extends ParentNewServlet{
	public void init(){
		this.className="Dao_VerifyCode";
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		super.doGet(request, response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
