<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.bwf.bean.User" %>
<%@ page import="com.bwf.bean.Order" %>
<% List<Order> list=(List<Order>)request.getAttribute("orderlist"); %>
<%User user=(User)request.getSession().getAttribute("userInfo"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><%=user.getLoginname() %>的订单</title>
<style type="text/css">v
	.divclass{		
		margin:30px 0 30px 0;
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
	#index{
		text-align:center;
		color:green;
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
	body{
		background:rgba(0,0,0,0.2);
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
		<a id="back" href="/oa/goods/showGoods">返回主页</a>
	</div>
</div>
 <%if(list.size()==0){ %>
	<h1 style="margin:0 auto;">您的订单为空</h1>
 <%}else{ %>
 	<div class="base">
 		<h1 style="text-align:center">订单</h1>
	 	<table id="tb">
	 		<tr>
		 		<th>序号</th>
		    	<th>订单号</th>
		        <th>用户账号</th>
		        <th>商品总价</th>
		        <th>创建时间</th>
		        <th>查看商品详情</th>
			</tr>
	
			<%for(int i = 0;i < list.size();i++){ %>
			  	<%Order s = new Order();s = list.get(i); %>
			  	<tr>
					<form action="orderDetails" method="post">
			  			<td id="index"><%=i+1 %></td>
						<td><span><%=s.getUuid() %></span><input name="uuid" type="text" value="<%=s.getUuid() %>" /></td>
						<td><span><%=s.getUserid() %></span><input type="text" value="<%=s.getUserid() %>" /></td>
						<td><span><%=s.getTotalcost() %></span><input type="text" value="<%=s.getTotalcost() %>" /></td>
						<td><span><%=s.getCreatetime() %></span><input type="text" value="<%=s.getCreatetime() %>" /></td>
			    		<td><input type="submit" name="details" class="details" value="查看详情"/></a>
					</form>
				</tr>
			<%} %>
		</table>
		<div class="pageclass">
		 	<%for(int i = 0;i < list.size();i+=5){ %>
		 		<button type="button" class="page" name="page<%=i/5 %>"><%=i/5+1%></button>
		 	<%} %>
		<%} %>
 		</div>
	</div>
    <br>
    <script>
    	var tb = $("#tb");
    	var input = tb.find("input[type='text']");
    	input.hide();
    	$(".details").attr("style","width:100px");
    	
    	
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
