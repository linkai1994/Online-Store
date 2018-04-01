<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bwf.bean.Goods" %>
<%@ page import="com.bwf.bean.User" %>
<%List<Goods> list=(List<Goods>)request.getSession().getAttribute("detaillist"); %>
<%User user=(User)request.getSession().getAttribute("userInfo"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><%= list.get(0).getName()%></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script src="/oa/js/jquery-1.7.2.min.js"></script>
  <style type="text/css">
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
	#goodshow{
		margin:30px auto;
		width:600px;
		height:250px;
		text-align:left;
		border:3px solid grey;
		padding:5px;
	}
	#goodshow span{
		font-size:27px;
		font-family:黑体;
	}
	#goodshow a{
		font-size:27px;
		font-family:黑体;
		margin:5px 0 0 0;
	}
	#goodshow input{
		font-size:25px;
		font-family:黑体;
		height:30px;
		width:50px;
	}
	#goodshow img{
		margin:0 50px 0 0;
		width:250px;
		height:250px;
		float:left;
	}
  	th{		
		border:solid 1px #003399;
		width:100px;
	}	
	td{		
		border:solid 1px #003399
	}
	#img{
		float:left;
	}
	body{
		background:rgba(0,0,0,0.2);
	}
	#imgspan{
		display:none;
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
		<a style="font-family:黑体" href="/oa/goods/showGoods">返回主界面</a>
		|   <a style="font-family:黑体" href="/oa/shoppingcar/showCar2">查看购物车</a>
		|   <a href="/oa/user/showOrder">查看购买记录</a>
	</div>
</div>
  <div id="goodshow">
	  <%for(Goods g:list){ %>
		<div id="divclass1">
			<div><a class="detail" href="#">
				<%String[] imgs = g.getImages().split("/"); %>
				<img class="img" src="/oa/image/<%=imgs[0]%>"/>
				<span id="imgspan"><%=g.getImages() %></span>
			</a></div>
	  		<span>商品编号:</span><span name="uuid"><%=g.getUuid() %></span><br>
	  		<span>商品名称:</span><span name="name"><%=g.getName() %></span><br>
	  		<span>商品价格:</span><span name="price"><%=g.getPrice() %></span><br>
	  		<span>仓库储量:</span><span name="quantity"><%=g.getQutity() %></span><br>
	  		<span>商品类别:</span><span name="typeid"><%=g.getTypeid() %></span> <br>
	  		<span>购买数量:</span><input type="text" value="<%=1 %>" name="gunm"/><br>
	  		<a name="add" href="#" >加入购物车</a><br>
	  		<a name="pay" href="#">立即付款</a><br>
	  <%} %>
</div>
</body>
  <script>
  var div=$("#goodshow");
	//在table中找到所有超链接 条件属性name的值edit
	var add=div.find("a[name='add']");
	var pay = div.find("a[name='pay']");
	
	add.click(function(){
	 	var tr = $(this).parent();
		var textarr = tr.find("input[type='text']");
		var total = Number(tr.find("span[name='price']").text())*Number(textarr.eq(0).val());
		$.ajax({		
			type:"POST",
			url:"/oa/shoppingcar/addCar",
			data:{gid:tr.find("span[name='uuid']").text(),
					gname:tr.find("span[name='name']").text(),
					gprice:tr.find("span[name='price']").text(),
					gqutity:tr.find("span[name='quantity']").text(),
					num:textarr.eq(0).val(),
					gtprice:total},
			success:function(result){
				alert("添加成功");
			}			
		 });	   
	});
	
	pay.click(function(){
		var tr = $(this).parent().parent();
		var textarr = tr.find("input[type='text']");
		var total = Number(tr.find("span[name='price']").text())*Number(textarr.eq(0).val());
		//alert(tr.find("span[name='uuid']").text()+"--"+$("tr>td:first").text());
		$.ajax({
			type:"post",
			url:"/oa/goods/pay",
			data:{gid:tr.find("span[name='uuid']").text(),
					gname:tr.find("span[name='name']").text(),
					gprice:tr.find("span[name='price']").text(),
					gqutity:tr.find("span[name='quantity']").text(),
					num:textarr.eq(0).val(),
					gtprice:total},
			success:function(result){
					alert("购买成功");
					window.location.reload();
			}
		});
	});
	
	var imgSrc = $("#imgspan").text();
	var singleSrc = new Array();
	singleSrc = imgSrc.split("/");
	var img = $(".img");
	img.click(function(){
	 	var xxx = $(this);
	 	var index = 0;
		for(var i = 0;i < singleSrc.length;i++){
			if("/oa/image/"+singleSrc[i]==xxx.attr("src")){
					index=i;
			}
		}
		xxx.attr("src","/oa/image/"+singleSrc[(index+1)%singleSrc.length]);
	})
	</script>
</html>
