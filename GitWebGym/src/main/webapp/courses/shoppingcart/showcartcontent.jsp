<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物清單</title>

<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link href="../../css/bootstrap.min.css" rel="stylesheet" />
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
	
<style>

body{
	font-size: 16px;
}

#section-header{
	margin-top: 100px;
}


footer{
	  margin-top:100px;
	  position: absolute;
	  right: 0;
	  left: 0;
}
.flex-container {
    display: -webkit-flex;
    display: flex;
    width: 100%;
    height: 200px;
    justify-content: center;
}

.flex-item {
/* 	background-color:yellow; */
 	width:200px; 
 	height:200px; 
    margin: 20px;
    border-radius: 20px;
	text-align: center;
}
#flex-item-1{	
  	   	background-image: url(../../images/order-footer-6.jpg);   
     	background-size: cover;
}


#flex-item-2{	
  	   	background-image: url(../../images/order-footer-7.jpg);   
     	background-size: cover;
}

#flex-item-3{	
  	   	background-image: url(../../images/order-footer-8.jpg);   
     	background-size: cover;
}


#flex-item-4{	
  	   	background-image: url(../../images/order-footer-9.jpg);   
     	background-size: cover;
}

#flex-item-5{	
  	   	background-image: url(../../images/order-footer-10.jpg);   
     	background-size: cover;
}
</style>	
	
<script>

	$(document).ready(function() {
		var subtotal = null;
		var id = null;
		getSubTotal();
		loadShoppingCart();
		function getSubTotal(){
			$.post("<c:url value='/getShoppingCartSubTotal'/>",{},function(datas){
				if(datas!=null){
					subtotal = datas;
				}else{
					subtotal = 0;
				}	
			});
		}
		var tb =  $('#ShoppingCartForm>tbody');
		var tbfoot =  $('#ShoppingCartForm>tfoot');
		function loadShoppingCart(){
			getSubTotal();
			$.getJSON("<c:url value='/getShoppingCartItemForJson'/>",{},function(datas){
				
				 var frag1 = $(document.createDocumentFragment());
				 var frag2 = $(document.createDocumentFragment());	
					var Firstcell1 = $("<td colspan='6' align='right'></td>").text("合計金額：");
					var Firstcell2 = $("<td align='right' style='text-align: center;'></td>").text(subtotal+"元");
					var subTotalTr  =$("<tr></tr>").append([Firstcell1,Firstcell2]);
					var Firstcell3 = $("<TD width='260' align='center' colspan='7'></td>").html("<input type='button' class='btn btn-default' value='再次確認' id='showMsgDIV'/>");
					var theSubmitBtnTr  =$("<tr></tr>").append([Firstcell3]);
					frag1.append([subTotalTr,theSubmitBtnTr]);
					tbfoot.html(frag1);
					if(datas[0].noShoppingCart != null){
						 $('.testclass').remove();
					}else{
					    $.each(datas,function(idx,shoppingCart){
						   var cell1 = $("<td style='text-align: center;'></td>").text(shoppingCart.eventno);
						   var cell2 = $("<td style='text-align: center;'></td>").text(shoppingCart.event_title);
						   var cell3 = $("<td style='text-align: center;'></td>").text(shoppingCart.t_name);
						   var cell4 = $("<td style='text-align: center;'></td>").text(shoppingCart.locationname+" "+shoppingCart.room_title);
						   var cell5 = $("<td style='text-align: center;'></td>").text(shoppingCart.eventstart+"到"+shoppingCart.eventend);
						   var cell6 = $("<td style='text-align: center;'></td>").text(shoppingCart.charge);
						   var cell7 = $("<td style='text-align: center;'></td>").html('<Input type="button" class="btn btn-default" value="刪除"/>');
						   var row  =$("<tr class='testclass'></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6,cell7]);
						   frag2.append(row);					   
					   })
					   tb.html(frag2);
					}
			});
		}
		
		tb.on('click','input:nth-child(1)',function(){
			var row = $(this).parents('tr');
		    id = row.children("td:eq(0)").text();
		    $('#msgDIVstg').html("再次刪除課程?");
		    $("#deleteDIV").modal();
		    
		   
		});
		
		$("#deleteCartBtn").click(function(){
			$.post("<c:url value='/YuiUpdateShoppingCartItem'/>",{"cmd":"DEL","eventID":id},function(data){
				if(data=="1"){
					loadShoppingCart();
					 $("#deleteDIV").modal('hide');
				}		
			});    
		});
			   		
		tbfoot.on('click','input:nth-child(1)',function(){
			if(subtotal==0){
				$('#msgStr').html('無購買任何商品，不需結帳');
				$('#msgDIV').modal();
				
			}else{
				$('#msgDIVstg').html("再次確認訂單內容 ? ");
				$("#AddnewOrderDIV").modal();
			}
			
		});
		
		
		$('#submitBtn').click(function(){
			event.preventDefault();
			document.getElementById("orderForm").finalDecision.value = "ORDER";
			$.post("<c:url value='/YuiAddNewOrderServlet'/>",{"finalDecision":"ORDER"},function(data){
				if(data=="訂單產生成功"){
					console.log(data);
					$('#msgStr').html("訂單產生成功，您可以至<a href='<c:url value='/courses/shoppingcart/myorder.jsp'/>'>我的訂單</a>查看您的訂單");
					
					
					loadShoppingCart();
					$("#AddnewOrderDIV").modal('hide');
					$("#msgDIV").modal();
					$("#msgDIVCloseBTN").click(function(){
						document.location.href="<c:url value="/courses/enroll.jsp"/>";
					});
					
				}else{
					
					$('#msgStr').html("發生錯誤，訂單產生失敗，請再試一次");
					$("#AddnewOrderDIV").modal('hide');
					$("#msgDIV").modal();
				}
			});
		});
	});
	
	
	
</script>

<style >

</style>

</head>
<body>

<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
			<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
			<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
			<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
			<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
		</ul>	
		<c:if test="${not empty memberLoginOK}">
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${not empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(${ShoppingCart.size})</a><li>
				</c:if>
				<c:if test="${empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(0)</a></li>
				</c:if>    
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${memberLoginOK.memberID } !</a>
	              <ul class="dropdown-menu">
	                <li><a href="<c:url value="/courses/enroll.jsp"/>">報名課程</a></li>	
	                <li><a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">歷史紀錄</a></li>  
	                <li><a href="<c:url value="/group/create.jsp" />">揪團</a></li>  
	                <li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	              
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
			</ul>
		</c:if>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
<section id="section-header">
	<div class="container">
		<div class="row">
			<TABLE class="table table-hover" border='2' width="820" id="ShoppingCartForm">
				<THEAD>
					<tr>
					<TH style="text-align: center;">課程編號</TH>
					<TH style="text-align: center;">課程名稱</TH>
					<TH style="text-align: center;">教練</TH>
					<TH style="text-align: center;">地點</TH>
					<TH style="text-align: center;">上課日期</TH>
					<TH style="text-align: center;">單價</TH>
					<TH style="text-align: center;">修改</TH>
					</tr>
				</THEAD>
				<tbody>
				</tbody>
				<tfoot>
				</tfoot>
			</TABLE>
		</div>
	</div>
</section>

<footer class="footer">
  <div class="flex-container" >

		<div class="flex-item" id="flex-item-1"></div>
		<div class="flex-item" id="flex-item-2"></div>
		<div class="flex-item" id="flex-item-3"></div>
		<div class="flex-item" id="flex-item-4"></div>
		<div class="flex-item" id="flex-item-5"></div>
  </div>
</footer>


	<!-- 購物車詢問視窗  -->
	<div class="modal fade" id="AddnewOrderDIV" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">訊息</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" style="text-align: center;">
	       	<font id="msgDIVstg" size="5" style='text-align: center;'></font>
	      </div>
	      <div class="modal-footer">
	     	 <button type="button" class="btn btn-primary" id="submitBtn">確定購買</button>
	         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        
	      </div>
	    </div>
	  </div>
	</div>
		
	<!-- 訊息視窗 -->
	<div class="modal fade" id="msgDIV">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">訊息</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<H4 id="msgStr" style='text-align: center;'></H4>
				</div>
				<div class="modal-footer">
					<button type="button" id = "msgDIVCloseBTN" class="btn btn-secondary"
						data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 刪除視窗 -->
	<div class="modal fade" id="deleteDIV">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">訊息</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<H4 id="msgStr" style='text-align: center;'>確定要刪除課程?</H4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="deleteCartBtn">刪除課程</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<form id="deleteForm">
		<input type="hidden" name="a" />
		<input type="hidden" name="cmd" />
		<input type="hidden" name="eventID" />
		
	</form>
	
	<form id="orderForm">
		<input type="hidden" name="finalDecision" />
		
	</form>
	


	
</body>
</html>