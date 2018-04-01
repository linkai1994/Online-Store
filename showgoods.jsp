<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bwf.bean.Goods" %>
<%@ page import="com.bwf.bean.User" %>
<%List<Goods> list=(List<Goods>)request.getAttribute("goodslist"); %>
<%User user=(User)request.getSession().getAttribute("userInfo"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<script src="/oa/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
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
	.divclass1 a{
		float:right;
	}
	#goodshow img{
		width:150px;
		height:150px;
	}
	#goodshow{		
		margin:0 auto;
		width:900px;
		padding:20px;
	}	
	#divclass1{		
		margin:50px 30px 0px 35px;
		float:left;
	}	
	.img{
		border:3px solid grey;
	}
	body{
		background:rgba(0,0,0,0.2);
	}
	
</style>	
</head>
<body >
<div class="heading">
	<div class="restart">
		<img class="potrait" style="width:50px;height:50px" src="/oa/image/upload/<%=user.getHeadimg() %>"/>
		<div class="xx" style="margin:-20px 0 0 10px">
			<p style="font-family:黑体"><%=user.getLoginname() %></p>
			<a style="font-family:黑体" href="/oa/user/loginShow">注销</a>
		</div>
	</div>
	<div class="sth">
		<a style="font-family:黑体" href="/oa/shoppingcar/showCar">购物车</a>
		|   <a style="font-family:黑体" href="/oa/user/showOrder">查看购买记录</a>
	</div>
</div>
<div id="goodshow">
	<%for(Goods g:list){ %>
		<div id="divclass1">
			<div>
			<%String[] imgs = g.getImages().split("/"); %>
			<a class="detail" href="#"><img class="img" src="/oa/image/<%=imgs[0]%>"/></a></div>
			<div style="font-family:黑体;font-size:18px" name="name"><%=g.getName() %></div>
			<div style="color:yellow;font-family:Times;font-weight:bold;font-size:22px">￥<%=g.getPrice() %></div>
		</div>
	<%} %>
</div>
<div id="bottom">
	<image src="/oa/image/baidu.png"/ style="margin:20px 0 -10px -8px">
</div>
</body>
<script>
	$(".detail").click(function(){
		var de = $(this);
		//alert(de.parent().parent().find("div[name='name']").html());
		$.ajax({
			type:"post",
			url:"/oa/goods/details",
			data:{name:de.parent().parent().find("div[name='name']").html()},
			success:function(result){
				window.location.href="http://localhost:8080/oa/jsp/goodsDetail.jsp";
			}
		});
	});
	$(".img").mouseover(function(){
		$(this).attr("style","border:3px solid yellow");
	});
	$(".img").mouseout(function(){
		$(this).attr("style","border:3px solid grey");
	});
	
</script>
</html>
