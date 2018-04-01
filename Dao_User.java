import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwf.bean.Order;
import com.bwf.bean.OrderDetails;
import com.bwf.bean.User;
import com.bwf.common.Md5;
import com.bwf.common.Upload;

public class Dao_User extends ParentDao{
	public Dao_User(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);		
	}
	public void loginShow() throws ServletException, IOException{
		_request.getRequestDispatcher("html/login.html").forward(_request, _response);
	}
	public void login() throws SQLException, ServletException, IOException{
		//验证参数   java正则表达式
		Md5 md5=new Md5();
		String uname = _request.getParameter("loginname").trim();		
		String upass=md5.GetMD5Code(uname+_request.getParameter("loginpass"));
		//返回的是一个Object[]{conn,pstmt,rs};
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.LoginSql,uname,upass);
		ResultSet rs=(ResultSet)objects[2];
		User user=null;
		if(rs.next()){
			user=new User(rs.getString("uuid")
							,rs.getString("loginname")
							,rs.getString("loginpass")
							,rs.getString("headimg")
							,rs.getDate("createtime"));
			 HttpSession session =_request.getSession();
			 session.setAttribute(com.bwf.common.StaticStr.LoginSessionStr, user);
			 _request.getRequestDispatcher("/goods?action=showGoods").forward(_request, _response);
		}else{
			 _request.getRequestDispatcher("/user?action=loginShow").forward(_request, _response);
		}	
	}
	
	public void check() throws IOException, SQLException{
		String uname = _request.getParameter("loginname").trim();
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.CheckSql,uname);
		ResultSet rs=(ResultSet)objects[2];
		User user=null;
		if(rs.next()){
			_response.getWriter().print("0");
		}else{
			_response.getWriter().print("1");
	}
		
	}
	
	public void register() throws SQLException, ServletException, IOException{
		//验证参数   java正则表达式
		com.bwf.common.Upload upload=new Upload();
		upload.uploadcontent(_request);
		Map<String,String> requestMap=upload.getRequetvalues();
		Md5 md5=new Md5();
		_request.setCharacterEncoding("UTF-8");
		String uname = requestMap.get("loginname").trim();		
		String upass=md5.GetMD5Code(uname+requestMap.get("loginpass")).trim();
		String imgPath = requestMap.get("file");
		String province = requestMap.get("p").replace("请选择省份", "");
		String city = requestMap.get("c").replace("请选择城市", "");
		String district = requestMap.get("d").replace("请选择区县", "");
		String location = "";
		if(city.contains(province)){
			location = city + district;
		}else{
			location = province + city + district;
		}
		int result =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.RegisterSql
																,java.util.UUID.randomUUID().toString()
																,uname
																,upass
																,upload.getFilename().get(0)
																,location);
		if(result>0){
			loginShow();
		}
	}
	
	public void registerShow() throws SQLException, ServletException, IOException{
		_request.getRequestDispatcher("html/register.html").forward(_request, _response);
	}
	
	public void showOrder() throws ServletException, IOException, SQLException {
		User user = (User) _request.getSession().getAttribute("userInfo");
		Object[] objects = null;
		List<Order> list = new ArrayList<Order>();
		objects = com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowOrderSql,user.getLoginname());
		ResultSet rs = (ResultSet) objects[2];
		while (rs.next()) {
			if (rs.getString("userid").equals(user.getLoginname())) {
				Order tempOrder = new Order(rs.getString("uuid"), rs.getString("userid"), rs.getFloat("totalcost"),
						rs.getTimestamp("createtime"));
				list.add(tempOrder);
			}
		}
		if(list.size()>0){
			com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
			System.out.println("then1");
			_request.setAttribute("orderlist", list);
			
			_request.getRequestDispatcher("jsp/order.jsp").forward(_request, _response);
		}else{
			_request.setAttribute("orderlist", list);
			_request.getRequestDispatcher("jsp/order.jsp").forward(_request, _response);
		}
	}
	
	public void orderDetails() throws SQLException, ServletException, IOException{
		_request.setCharacterEncoding("UTF-8");
		String uuid = _request.getParameter("uuid");
		Object[] objects = null;
		List<OrderDetails> list = new ArrayList<OrderDetails>();
		objects = com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowOrderDetailsSql,uuid);
		ResultSet rs = (ResultSet) objects[2];
		while (rs.next()) {
				OrderDetails tempOrder = new OrderDetails(rs.getString("goodsid"), rs.getString("oid"), rs.getInt("shopquantity"),rs.getString("name"));
				list.add(tempOrder);
		}
		if(list.size()>0){
			com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
			System.out.println("then2");
			_request.setAttribute("orderDetailsList", list);
			_request.getRequestDispatcher("jsp/orderDetails.jsp").forward(_request, _response);
		}else{
			System.out.println("no");
		}
	}
	
	public void pay() throws IOException{
		_request.setCharacterEncoding("UTF-8");
		String gid = _request.getParameter("gid");		
		String gname = _request.getParameter("gname");
		String gprice = _request.getParameter("gprice");
		int gquantity = Integer.parseInt(_request.getParameter("gqutity"));
		Double gtprice = Double.parseDouble(_request.getParameter("gtprice"));
		int num = Integer.parseInt(_request.getParameter("num"));
		User temp = (User)_request.getSession().getAttribute("userInfo");
		String userid = temp.getLoginname();
		String uuid = java.util.UUID.randomUUID().toString();
		int quantity = gquantity;
		int result1 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql1
																,uuid
																,userid
																,gtprice);
		int result2 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql2
																,gid
																,uuid
																,num);
		int result3 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.DecreaseQuantitySql
																,quantity-num
																,gid);
		if(result1*result2*result3>0){
			_response.getWriter().print("1");
		}else{
			_response.getWriter().print("0");
		}
	}
}
