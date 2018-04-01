import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Dao_VerifyCode extends ParentDao{
	public Dao_VerifyCode(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}

	public void verifyCode() throws IOException {
		_response.setContentType("image/jpeg;");
		String content = "";
		content = com.bwf.common.VerifyCode.generateVerifyCode(4);
		Cookie cookie = new Cookie("verifyCode",content);
		cookie.setMaxAge(60);
		_response.addCookie(cookie);
//		HttpSession session = _request.getSession();
//		session.setAttribute("verifyCode", content);
		com.bwf.common.VerifyCode.outputImage(100, 30, _response.getOutputStream(), content);
	}
	
	
	public void checkVC() throws IOException{
		_request.setCharacterEncoding("UTF-8");
		String vc = _request.getParameter("vc");
		String verifyCode = "";
		Cookie[] cookies = _request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("verifyCode")){
				verifyCode=cookie.getValue();
			}
		}		
		System.out.println("vc"+vc);
		System.out.println("verifyCode"+verifyCode);
		if(vc.equalsIgnoreCase(verifyCode)){
			_response.getWriter().print("1");
		}else{
			_response.getWriter().print("0");
		}
	}
}
