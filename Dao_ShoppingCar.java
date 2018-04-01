import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.bean.ShoppingCar;
import com.bwf.bean.User;

public class Dao_ShoppingCar extends ParentDao {
	public Dao_ShoppingCar(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	public void addCar() throws IOException{
		User user = (User)_request.getSession().getAttribute(com.bwf.common.StaticStr.LoginSessionStr);
		PrintWriter out =_response.getWriter();
		String gsid=_request.getParameter("gid");
		String gsname=_request.getParameter("gname");
		String gsprice=_request.getParameter("gprice");
		String gsqutity=_request.getParameter("gqutity");
		int num = Integer.parseInt(_request.getParameter("num"));
		float price = Float.parseFloat(gsprice);
		float gtprice = Float.parseFloat(_request.getParameter("gtprice"));
		int qutity = Integer.parseInt(gsqutity);
		HttpSession shoppingSession = _request.getSession();
		List<ShoppingCar> list = (List<ShoppingCar>)shoppingSession.getAttribute(com.bwf.common.StaticStr.SCSessionStr);
		if(list==null){
			list = new ArrayList<ShoppingCar>();
		}
		boolean isFound = false;
		for(ShoppingCar sc : list){
			if(sc.getGoodsid().equals(gsid)){
				sc.setGoodsqutity(sc.getGoodsqutity()+num);
				isFound = true;
			}
			sc.setGoodtotoalprice(sc.getGoodsprice()*sc.getGoodsqutity());
		}
		if(!isFound){
			list.add(new ShoppingCar(gsid,gsname,price,num,gtprice));
			shoppingSession.setAttribute(com.bwf.common.StaticStr.SCSessionStr, list);
		}
	}

	public void pay() throws IOException{
		System.out.println("pay from sc");
		_request.setCharacterEncoding("UTF-8");
		String gid = _request.getParameter("gid");
		System.out.println(gid);
		int num = Integer.parseInt(_request.getParameter("num"));
		System.out.println(num);
		User temp = (User)_request.getSession().getAttribute("userInfo");
		String userid = temp.getLoginname();
		String uuid = (String) _request.getSession().getAttribute("uuid");
		
		System.out.println(uuid);
		int result2 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql2
																,gid
																,uuid
																,num);
		int result3 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.DecreaseQuantitySql
																,num
																,gid);
		if(result2*result3>0){
			_response.getWriter().print("ok");
		}else{
			_response.getWriter().print("no");
		}
	}
	
	public void delete() throws IOException, ServletException{
		_request.setCharacterEncoding("UTF-8");
		String gid = _request.getParameter("gid");		
		List<ShoppingCar> list = (List<ShoppingCar>) _request.getSession().getAttribute(com.bwf.common.StaticStr.SCSessionStr);
		String gidd = "";
		for(int i = 0;i < list.size();i++){
			if(list.get(i).getGoodsid().equals(gid)){
				list.remove(i);
			}
		}
		_request.setAttribute("list", list);
	}
	
	public void createUUID() throws IOException, ServletException{
		_request.setCharacterEncoding("UTF-8");
		String uuid = java.util.UUID.randomUUID().toString();
		_request.getSession().setAttribute("uuid", uuid);
		String userid = ((User) _request.getSession().getAttribute(com.bwf.common.StaticStr.LoginSessionStr)).getLoginname();
		Double gtprice = Double.parseDouble(_request.getParameter("gtprice"));
		int result1 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql1
																,uuid
																,userid
																,gtprice);
		if(result1>0){
			_response.getWriter().print("completed");
		}else{
			_response.getWriter().print("failed");
		}
	}

	public void showCar() throws ServletException, IOException
	{
		HttpSession shoppingSession = _request.getSession();
		List<ShoppingCar> list = (List<ShoppingCar>)shoppingSession.getAttribute(com.bwf.common.StaticStr.SCSessionStr);
		if(list==null){
			list = new ArrayList<ShoppingCar>();
		}
		_request.setAttribute("list", list);
		
		_request.getRequestDispatcher("jsp/shoppingcar.jsp").forward(_request, _response);
	}
	
	public void showCar2() throws ServletException, IOException
	{
		HttpSession shoppingSession = _request.getSession();
		List<ShoppingCar> list = (List<ShoppingCar>)shoppingSession.getAttribute(com.bwf.common.StaticStr.SCSessionStr);
		if(list==null){
			list = new ArrayList<ShoppingCar>();
		}
		_request.setAttribute("list", list);
		
		_request.getRequestDispatcher("jsp/shoppingcar2.jsp").forward(_request, _response);
	}
}
