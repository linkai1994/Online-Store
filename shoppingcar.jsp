<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bwf.bean.ShoppingCar" %>
<%@ page import="com.bwf.bean.User" %>
<%List<ShoppingCar> list=(List<ShoppingCar>)request.getAttribute("list"); %>
<%User user=(User)request.getSession().getAttribute("userInfo"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物车</title>
<script src="/oa/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
	.things{
		margin:70px auto;
		width:600px;
		text-align:center;
		border:3px solid grey;
		padding:5px;
	}
	.things a{
		float:right;
		margin:10px;
	}
	th{		
		border:solid 1px #003399;
		width:100px;
	}	
	td{		
		border:solid 1px #003399
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
	body{
		background:rgba(0,0,0,0.2);
	}
</style>	
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
		<a href="/oa/goods/showGoods">返回商品页</a>
	</div>
</div>
	<div class="things">
		<%if(list.size()==0){ %>
			<h1>您的购物车为空</h1>
		<%}else{ %>
			<table id="tbl">
				<tr>
					<th>是否选中</th>
			    	<th>商品ID</th>
			    	<th>商品名称</th>
			        <th>商品价格</th>
			        <th>购买数量</th>
			        <th>金额</th> 
			        <th>总金额</th>      
			    </tr>
			    <form action="pay" method="post" accept-charset="utf-8">
				<%for(int i = 0;i < list.size();i++){ %>
				  	<%ShoppingCar sc = new ShoppingCar(); %>
				  	<%sc = list.get(i); %>
				   	<%if(i == 0){ %>
					  	<tr>
					  		<td><input type="checkbox" class="cb"/></td>
					  		<td><span name="gid"><%=sc.getGoodsid() %></span></td>
					  		<td><span><%=sc.getGoodsname() %></span></td>
					  		<td><span><%=sc.getGoodsprice() %></span></td>
					  		<td><span><%=sc.getGoodsqutity() %></span></td>
					  		<td><span name="tprice1"><%=sc.getGoodtotoalprice() %></span></td>
					  		<td name="total">0</td>
					  	</tr>
				  	<%}else{ %>
					  	<tr>
					  		<td><input type="checkbox" class="cb"/></td>
					  		<td><span name="gid"><%=sc.getGoodsid() %></span></td>
					  		<td><span name="name"><%=sc.getGoodsname() %></span></td>
					  		<td><span name="price"><%=sc.getGoodsprice() %></span></td>
					  		<td><span name="num"><%=sc.getGoodsqutity() %></span></td>
					  		<td><span name="tprice1"><%=sc.getGoodtotoalprice() %></span></td>
					  	</tr>
				  	<%} %>	
				<%} %>
				</form>
			</table>
		<%}%>
		<div class="options">
			<a id="delete" href="#">付款</a>
		</div>
	</div>
		
	<script>
		var trr = $("#tbl").find("tr");
		var row = trr.length-1;
		$("#tbl").find("td[name='total']").attr("rowspan",row);
			
		var cb = $(".cb");
		$("#delete").hide();
		
		function showButton(){
			for(var i = 0;i<cb.length;i++){
				if(cb.eq(i).attr("checked")){
					$("#delete").show();
					return;
				}
			}
			$("#delete").hide();	
		}
		
		$(".cb").change(function(){
			var tprice = parseFloat($(this).parent().parent().find("span[name='tprice1']").text());
			showButton();
			if($(this).attr("checked")){
				$("#tbl").find("td[name='total']").html(parseFloat($("#tbl").find("td[name='total']").html())+tprice);
			}else{
				$("#tbl").find("td[name='total']").html(parseFloat($("#tbl").find("td[name='total']").html())-tprice);
			}
		});
		$("#delete").click(function(){
			createUUID();
			for(var i = 0;i < row;i++){
				var temptr = trr.eq(i+1);
				if(temptr.find("input[type='checkbox']").attr("checked")){
					pay(temptr);
					deleteSC(temptr);
					alert("购买成功！！！");
				}
			}
		});
		function deleteSC(sender){
			var temp = $(sender);
			$.ajax({
				type:"post",
				url:"/oa/shoppingcar/delete",
				data:{gid:temp.find("span[name='gid']").text()},
				success:function(){
					window.location.reload();
				}
			});
		}
		function createUUID(){
			$.ajax({
				type:"post",
				url:"/oa/shoppingcar/createUUID",
				data:{gtprice:$("#tbl").find("td[name='total']").html()},
				success:function(result){
				}
			});
		}
		function pay(sender){
			var temp = $(sender);
			$.ajax({
				type:"post",
				url:"/oa/shoppingcar/pay",
				data:{gid:temp.find("span[name='gid']").text()
					  ,num:temp.find("span[name='num']").text()},
				success:function(result){
				}
			});
		}
	</script>
</body>
</html>
