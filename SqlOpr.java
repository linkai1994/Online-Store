import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.bwf.bean.User;

public class SqlOpr {
	
	private static final String url="jdbc:mysql://localhost:3306/store";
	private static final String uid="root";
	private static final String pass="";
	private static final String driver="com.mysql.jdbc.Driver";
	
	static {
		try{
			//加载驱动
			//反射 条件  包名.类名  com.mysql.jdbc.Driver
			Class.forName(driver);	
		}catch(Exception ex){
			
		}
	}	
	public static Connection GetConn(){
		Connection conn=null;
		try{
			conn=DriverManager.getConnection(url,uid,pass);
		}catch(Exception ex){
			
		}		
		return conn;
	}	
	public  static void Free(Connection conn,Statement st,ResultSet rs){
		try{			
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex){
			
		}		
	}

	/**
	* 操作增、删、改
	* @param sql
	* @return int
	*/
	public static int executeNoneQuery(String SqlStr){
		int result=0;
		Connection conn=null;		
		Statement stmt=null;		
		try{			
			conn=GetConn();
			stmt=conn.createStatement();
			result=stmt.executeUpdate(SqlStr);
		}catch(Exception ex){
			Free(conn,stmt,null);
		}finally{
			Free(conn,stmt,null);
		}
		return result;
	}
	/**
	* 参数化操作增、删、改
	* @param sql
	* @param Objects
	* @return int
	*/
	public static int executeNoneQuery(String SqlStr,Object...objects){
		int result=0;		
		Connection conn=null;		
		PreparedStatement pstmt=null;		
		try{
			conn=GetConn();			
			pstmt=conn.prepareStatement(SqlStr);		
			for(int i=0;i<objects.length;i++){
				pstmt.setObject(i+1, objects[i]);
			}
			result=pstmt.executeUpdate();
		}catch(Exception ex){
			Free(conn,pstmt,null);
		}		
		return result;
	}

	/**
	* 操作查询
	* @param sql
	* @return Result结果集
	*/
	public static Object[] executeQuery(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		      conn = GetConn();
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		 } catch (SQLException err) {
		      err.printStackTrace();
		      Free(conn,stmt,rs);
		 }
		 return new Object[]{conn,stmt,rs};
	}
		
	/**
	* 参数化操作查询
	* @param sql
	* @param Objects
	* @return Result结果集
	 * @throws SQLException 
	*/
	public static Object[] executeQuery(String sql, Object... obj) throws SQLException {		
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 	conn = GetConn();
			 	pstmt = conn.prepareStatement(sql);
		        for (int i = 0; i < obj.length; i++) {		        	
		        	pstmt.setObject(i + 1, obj[i]);
		         }
		         rs = pstmt.executeQuery();		         
		  } catch (SQLException err) {
		     err.printStackTrace();
		     Free(conn,pstmt,rs);
		 }
//		 if(rs.next()){
//			 System.out.println(123+rs.getString("loginname"));
//		 }
		 return new Object[]{conn,pstmt,rs};
	}
	
	public static LinkedList<User> executeQuery1(String sql, Object... obj) throws SQLException {		
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 LinkedList<User> uList = null;
		 try {
			 	conn = GetConn();
			 	pstmt = conn.prepareStatement(sql);
		        for (int i = 0; i < obj.length; i++) {		        	
		        	pstmt.setObject(i + 1, obj[i]);
		         }
		         rs = pstmt.executeQuery();	
		         if(rs.next()){
		        	 User user = new User(rs.getString("uuid")
								,rs.getString("loginname")
								,rs.getString("loginpass")
								,rs.getString("headimg")
								,rs.getDate("createtime"));
		        	 uList.add(user);
		         }
		  } catch (SQLException err) {
		     err.printStackTrace();
		     Free(conn,pstmt,rs);
		 }
		 return uList;
	}
	/**
	* 判断记录是否存在
	*
	* @param sql
	* @return Boolean
	*/
	public static Boolean isExist(String sql) {	
		ResultSet rs = null;		
		Object[] objects=null;		
		try {			
			objects=executeQuery(sql);			
			rs = (ResultSet)objects[2];
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException err) {
			err.printStackTrace();
			Free((Connection)objects[0],(Statement)objects[1],rs);
			return false;
		} finally {
			Free((Connection)objects[0],(Statement)objects[1],rs);
		}
	}		  
	/**
	* 判断记录是否存在
	*
	* @param sql
	* @return Boolean
	*/
	public static Boolean isExist(String sql, Object... obj) {
		ResultSet rs = null;		
		Object[] objects=null;		
		try {			
			objects=executeQuery(sql);
			rs =(ResultSet)objects[2];
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException err) {
			err.printStackTrace();
			return false;
		} finally {
			Free((Connection)objects[0],(Statement)objects[1],rs);
		}
	}

	/**
	* 获取查询记录的总行数
	*
	* @param sql
	* @return int
	*/
	public static int getCount(String sql) {
		int result = 0;
		ResultSet rs = null;
		Object[] objects=null;
		try {			
			objects=executeQuery(sql);
			rs =(ResultSet)objects[2];
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			Free((Connection)objects[0],(Statement)objects[1],rs);
		}
		return result;
	}

	/**
	* 获取查询记录的总行数
	*
	* @param sql
	* @param obj
	* @return int
	*/
	public static int getCount(String sql, Object... obj) {
		int result = 0;
		ResultSet rs = null;
		Object[] objects=null;
		try {
			objects=executeQuery(sql);
			rs =(ResultSet)objects[2];
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			Free((Connection)objects[0],(Statement)objects[1],rs);
		}
		return result;
	}
}
