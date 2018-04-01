<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bwf.bean.OrderDetails" %>
<%@ page import="com.bwf.bean.User" %>
<% List<OrderDetails> list=(List<OrderDetails>)request.getAttribute("orderDetailsList"); %>
<%User user=(User)request.getSession().getAttribute("userInfo"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>details</title>
     <style type="text/css">
	
	.divclass{
		margin:30px 0 30px 0;
	}
	
	table{
		margin:0 auto;
		border:1px solid;
	}
	th{
		
		border:solid 1px #003399;
		width:100px;
	}
	
	td{
		
		border:solid 1px #003399;
		color:blue;
	}
	body{
		background:rgba(0,0,0,0.2);
	}
	.heading{
		height:70px;
		background:url("/oa/image/cctv.png") no-repeat left;
		border-bottom:3px solid brown;
	}
	.restart{
		float:right;
		border:2px solid ;
		padding:5px;
	}
	.xx{
		float:left;
	}
	.potrait{
		float:left;
	}
	.sth{
		float:right;
		font-weight:bold;
		padding:0 5px 0 0;
	}
	.base{
		margin:50px auto;
	}
	.pageclass{
		margin:10px auto;
		width:600px;
		text-align:center;
	}
	.page{
		margin:10px auto;;
		border:1px solid;
		text-align:center;
		width:20px;
	}
</style>
<script src="/oa/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<div class="heading">
	<div class="restart">
		<img class="potrait" style="width:50px;height:50px" src="/oa/image/upload/<%=user.getHeadimg() %>"/>
		<div class="xx" style="margin:-20px 0 0 10px">
			<p style="font-family:黑体"><%=user.getLoginname() %></p>
			<a style="font-family:黑体" href="/oa/user/loginShow">注销</a>
		</div>
	</div>
	<div class="sth">
		<a href="/oa/goods/showGoods">返回主页</a>
		|   <a href="/oa/user/showOrder">返回订单界面</a>
	</div>
</div>
<div class="base">
 	<h1 style="text-align:center">订单详情</h1>
	<table id="tb">
		<tr>
	       	<!-- <th>订单详情编号</th> -->
	        <th>订单编号</th>
		   	<th>商品编号</th>
		   	<th>商品名称</th>
	       	<th>商品订单数量</th>
	      	<!-- <th>购买成功</th> -->
		</tr>
		<%for(int i = 0;i < list.size();i++){ %>
			<%OrderDetails s = new OrderDetails();s = list.get(i); %>
			<tr>
				<%-- <td><span><%=s.getId() %></span><input type="text" value="<%=s.getId() %>" /></td> --%>
				<td><span><%=s.getOid() %></span><input type="text" value="<%=s.getOid() %>" /></td>
				<td><span><%=s.getGoodsid() %></span><input type="text" value="<%=s.getGoodsid() %>" /></td>
				<td><span><%=s.getName() %></span><input type="text" value="<%=s.getName() %>" /></td>
				<td><span><%=s.getShopquantity() %></span><input type="text" value="<%=s.getShopquantity() %>" /></td>
				<!--   <td><a name="end" href="#">购买完成</a> -->
			</tr>
		<%} %>
	</table>
	<div class="pageclass">
		<%for(int i = 0;i < list.size();i+=5){ %>
		 	<button type="button" class="page" name="page<%=i/5 %>"><%=i/5+1%></button>
		<%} %>
 	</div>
</div>
<script>
     var input = $("input");
     input.hide();
     
     var page = $(".page");
    	var max = 5;
    	page.eq(0).attr("style","color:grey");
    	page.eq(0).attr("disabled",true);
    	$(".page").click(function(){
    		var current = $(this);
    		current.attr("style","color:grey");
    		current.attr("disabled",true);
    		for(var i = 0;i < page.length;i++){
    			if(page.eq(i).attr("name")!=current.attr("name")){
    				page.eq(i).attr("style","color:black");
    				page.eq(i).attr("disabled",false);
    			}
    		}
    		for(var i = 1;i < trarr.length;i++){
	    		if(i>(Number(current.text())-1)*5 && i <= (Number(current.text()))*5){
	    			trarr.eq(i).show();
	    		}else{
	    			trarr.eq(i).hide();
	    		}
	    	}
    	});
    	
    	var trarr = tb.find("tr");
    	for(var i = 6;i < trarr.length;i++){
    		trarr.eq(i).hide();
    	}
</script>
</body>
</html>
