import java.io.IOException;
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

import com.bwf.bean.Goods;
import com.bwf.bean.User;

public class Dao_Goods extends ParentDao{

	public Dao_Goods(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	public void showGoods() throws SQLException, ServletException, IOException{
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowGoodSql);
		ResultSet rs=(ResultSet)objects[2];
		List<Goods> list=new ArrayList<Goods>();
		Goods goods=null;
		while(rs.next()){
			goods=new Goods(rs.getString("uuid")
							,rs.getString("name")
							,rs.getFloat("price")
							,rs.getInt("qutity")
							,rs.getString("images")
							,rs.getDate("createtime")
							,rs.getInt("typeid"));
				list.add(goods);
			HttpSession session = _request.getSession();
			session.setAttribute(com.bwf.common.StaticStr.GoodsSessionStr,goods);
		}
		com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
		_request.setAttribute("goodslist", list);
		_request.getRequestDispatcher("/jsp/showgoods.jsp").forward(_request, _response);
	}
	
	public void details() throws SQLException, ServletException, IOException{
		String gname = _request.getParameter("name");
		System.out.println(gname);
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowGoodSql);
		ResultSet rs=(ResultSet)objects[2];
		List<Goods> list=new ArrayList<Goods>();
		Goods goods=null;
		while(rs.next()){
			if(rs.getString("name").equals(gname)){
				goods=new Goods(rs.getString("uuid")
								,rs.getString("name")
								,rs.getFloat("price")
								,rs.getInt("qutity")
								,rs.getString("images")
								,rs.getDate("createtime")
								,rs.getInt("typeid"));
					list.add(goods);
				HttpSession session = _request.getSession();
				session.setAttribute(com.bwf.common.StaticStr.DetailSessionStr,goods);
			}
		}
		com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
		_request.getSession().setAttribute("detaillist", list);
		_request.getSession().setAttribute("gname", gname);
		System.out.println("ok");
		_request.getRequestDispatcher("/jsp/goodsDetail.jsp").forward(_request, _response);
	}
	
	public void back2details() throws SQLException, ServletException, IOException{
		String gname = (String) _request.getSession().getAttribute("gname");
		System.out.println(gname);
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowGoodSql);
		ResultSet rs=(ResultSet)objects[2];
		List<Goods> list=new ArrayList<Goods>();
		Goods goods=null;
		while(rs.next()){
			if(rs.getString("name").equals(gname)){
				goods=new Goods(rs.getString("uuid")
								,rs.getString("name")
								,rs.getFloat("price")
								,rs.getInt("qutity")
								,rs.getString("images")
								,rs.getDate("createtime")
								,rs.getInt("typeid"));
					list.add(goods);
				HttpSession session = _request.getSession();
				session.setAttribute(com.bwf.common.StaticStr.DetailSessionStr,goods);
			}
		}
		com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
		_request.getSession().setAttribute("detaillist", list);
		_request.getSession().setAttribute("gname", gname);
		System.out.println("ok");
		_request.getRequestDispatcher("/jsp/goodsDetail.jsp").forward(_request, _response);
	}
	
	public void pay() throws IOException, SQLException, ServletException{
		System.out.println("pay1");
		_request.setCharacterEncoding("UTF-8");
		String gid = _request.getParameter("gid");		
		String gprice = _request.getParameter("gprice");
		Double gtprice = Double.parseDouble(_request.getParameter("gtprice"));
		int num = Integer.parseInt(_request.getParameter("num"));
		User temp = (User)_request.getSession().getAttribute("userInfo");
		String userid = temp.getLoginname();
		String uuid = java.util.UUID.randomUUID().toString();
		int result1 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql1
																,uuid
																,userid
																,gtprice);
		int result2 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.PaySql2
																,gid
																,uuid
																,num);
		int result3 =com.bwf.model.dao.SqlOpr.executeNoneQuery(com.bwf.common.StaticStr.DecreaseQuantitySql
																,num
																,gid);
		refresh();
		if(result1*result2*result3>0){
			_response.getWriter().print("1");
		}else{
			_response.getWriter().print("0");
		}
	}
	
	public void refresh() throws SQLException{
		String gname = (String) _request.getSession().getAttribute("gname");
		Object[] objects=com.bwf.model.dao.SqlOpr.executeQuery(com.bwf.common.StaticStr.ShowGoodSql);
		ResultSet rs=(ResultSet)objects[2];
		List<Goods> list=new ArrayList<Goods>();
		Goods goods=null;
		while(rs.next()){
			if(rs.getString("name").equals(gname)){
				goods=new Goods(rs.getString("uuid")
								,rs.getString("name")
								,rs.getFloat("price")
								,rs.getInt("qutity")
								,rs.getString("images")
								,rs.getDate("createtime")
								,rs.getInt("typeid"));
					list.add(goods);
				HttpSession session = _request.getSession();
				session.setAttribute(com.bwf.common.StaticStr.DetailSessionStr,goods);
			}
		}
		com.bwf.model.dao.SqlOpr.Free((Connection)objects[0],(Statement)objects[1], rs);
		_request.getSession().setAttribute("detaillist", list);
	}
}
