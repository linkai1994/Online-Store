public class StaticStr {
	public static final String LoginSql="select uuid,loginname,loginpass,headimg,createtime from user where loginname=? and loginpass=?";
	public static final String CheckSql="select uuid,loginname,loginpass,headimg,createtime from user where loginname=?";
	public static final String LoginSessionStr="userInfo";
	public static final String GoodsSessionStr="goodsinfo";
	public static final String DetailSessionStr="goodsDetail";
	public static final String ShowGoodSql="select uuid,name,price,qutity,images,createtime,typeid from goods";
	public static final String RegisterSql = "INSERT INTO user VALUES(?,?,?,?,CURTIME(),?)";
	public static final String AddToCarSql = "INSERT INTO shoppingcar VALUES(?,?,?,?,?,CURTIME(),?)";
	public static final String DeleteDealSql1 = "DELETE FROM dealdetails WHERE id=?";
	public static final String DeleteDealSql2 = "DELETE FROM deal WHERE uuid=?";
	public static final String ShowOrderSql = "SELECT uuid,userid,totalcost,createtime FROM deal WHERE userid=? ORDER BY createtime";
	public static final String PaySql1 = "INSERT INTO deal VALUES(?,?,?,CURTIME())";
	public static final String PaySql2 = "INSERT INTO dealdetails(goodsid,oid,shopquantity) VALUES(?,?,?)";
	public static final String DecreaseQuantitySql = "UPDATE goods SET qutity=(qutity-?) WHERE uuid=?";
	public static final String ShowOrderDetailsSql = "SELECT d.oid,d.goodsid,g.name,d.shopquantity FROM dealdetails d,goods g WHERE d.oid=? AND d.goodsid=g.uuid";
	public static final String shoppingcarStr="INSERT INTO shoppingcar(goodsid,goodsname,goodsprice,goodsqutity,goodtotoalprice) VALUES(?,?,?,?,?)";
	public static final String usershoppingStr="select goodsid,goodsname,goodsprice,goodsqutity,goodtotoalprice from shoppingcar";
	public static final String SCSessionStr = "SCInfo";
}
