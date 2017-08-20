<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enroll</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link href="../css/fullcalendar.min.css" rel="stylesheet" />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/moment.min.js"></script>
<script src="../js/fullcalendar.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 16px;
/* 	background-image: url(../images/OV_750x1124.jpg); */
/* 	background-repeat: no-repeat; */
/*     background-attachment: fixed; */
/* 	background-position: 50px -100px;  */
/* 	background-size: cover; */

 	background-image: url(../images/enroll_bg.jpg); 
 	background-size:cover; 
}

#calendar {
	width: 100%;
 	margin: 30px auto; 
 	background-color:#ffffff;
}

.fc-view-container{
	border: 1px solid black;!mportant
}

#section-header{
	margin-top:80px;

}

#section-header-text{
	font-weight:bold;
	font-style:italic;
	z-index: 10;
}


</style>


<script>
	$(document).ready(function() {
		var eventno;
		var trainerid;
		var t_name;
		var locationname;
		var room_title;
		var roomno;
		var title;
		var coursekind;
		var e_status;
		var start;
		var end;
		var enroll
		var charge;

		$('#calendar').fullCalendar({

			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek'
			},
			defaultDate : '2017-08-28',
			allDaySlot: false,
			firstDay:1,
			//slotDuration:"01:00:00",
			minTime: "09:00:00",
			maxTime: "24:00:00",
			aspectRatio: 2.2,
			navLinks : true, // can click day/week names to navigate views
			selectable : true,
			selectHelper : true,
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			//fullcalendar載入後會執行這個程式
			events : "<c:url value="/TestServlet"/>",
			//課程點擊事件
			eventClick : function(event, jsEvent, view) {
				//設定彈出視窗裡面的字
				$("#exampleModalLabel").html('<h3>預約課程: '+event.title+'</h3>');
				$("#trainer").html("教練姓名: " + event.t_name)
				$("#locationname").html("地點: " + event.locationname + "  " + event.room_title);
				$("#start").html("時間: "+moment(event.start).format('YYYY-MM-DD hh:mm')+" 到 "+moment(event.end).format('YYYY-MM-DD hh:mm'));
				$("#coursekind").html("種類: " + event.coursekind);
				$("#charge").html("價格: " + event.charge);
				
				//課程點擊之後彈出的DIV
				$("#myModal").modal();
				
				//先把點擊課程的資訊參數存到全域變數(最上面的那些)，之後會用到
				eventno = event.eventno;
				trainerid = event.trainerid;
				roomno = event.roomno;
				title = event.title;
				coursekind = event.coursekind;
				e_status = event.e_status;
				start = moment(event.start).format('YYYY-MM-DD hh:mm:ss');
				end = moment(event.end).format('YYYY-MM-DD hh:mm:ss');
				enroll = event.enroll;
				charge = event.charge;
				t_name = event.t_name;
				locationname = event.locationname;
				room_title = event.room_title;
			}
		});
		
		
		//點擊"加入購物車"會觸發此function
		$("#shoppingCartDIV").submit(function(event) {
			event.preventDefault();
			
			//用Ajax的Post呼叫後端程式YuiAddShoppingCartEventsServlet
			$.post("<c:url value="/YuiAddShoppingCartEventsServlet"/>", {
				//把點擊到的課程屬性傳到後端程式，後端可用request.getParameter("...")取出
				"eventno" : eventno,
				"trainerid" : trainerid,
				"roomno" : roomno,
				"title" : title,
				"coursekind" : coursekind,
				"e_status" : e_status,
				"start" : start,
				"end" : end,
				"enroll" : enroll,
				"charge" : charge,
				"t_name" : t_name,
				"locationname" : locationname,
				"room_title" : room_title
				
				//呼叫完執行此function
			}, function(data) {
				//data是後端程式的傳回值(使用PrintWriter寫出)
				if(data == "尚未登入"){
					
					//把購物車視窗關閉
					$("#myModal").modal('hide');
					//打開登入視窗   (登入視窗的submit按鈕的觸發function在下面)
					$('#LoginDIV').modal();

				}else{
					//把後端傳來的訊息放到訊息頁面
					$("#msgStr").html(data);
					$.post("<c:url value='/GetShoppingCartSize'/>",{},function(cartSize){
						$("#cartSize").text("購物車("+cartSize+")");
					});
					
					//先關閉Login視窗
					$("#myModal").modal('hide');
					//打開訊息頁面
					$('#msgDIV').modal();
				}
			});
		});
		
		
		//登入視窗如果按下submit之後會觸發此function
		$("#LoginForm").submit(function (){
			event.preventDefault();
			
			//呼叫後端的YuiLoginServletForAjax程式，他會回傳一個json所以使用getJSON呼叫
			$.getJSON("<c:url value="/YuiLoginServletForAjax"/>",
					{"userId" : $("#userId").val(),"pswd": $("#pswd").val()}
			
				//呼叫完執行此function
				, function(json){
					
				//他會回傳一個json，用這個json做一些判斷
				if(json[0].memberLoginOK!=null){
					//登入成功，把訊息放到訊息視窗
					$("#msgStr").html(json[0].memberLoginOK);
					//關閉登入視窗
					$('#LoginDIV').modal('hide');
					//叫出訊息視窗
					$('#msgDIV').modal();
				}else{
					//先把顯示錯誤的欄位清空
					$("#userIdError").html("");
					$("#pswdError").html("");
					$("#LoginErrorMsg").html("");
					
					//把錯誤訊息放到顯示欄位
					$("#userIdError").html(json[0].AccountEmptyError);
					$("#pswdError").html(json[0].PasswordEmptyError);
					$("#LoginErrorMsg").html(json[0].LoginError);
				}
				
			});		
		});


	});
	
	
</script>
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
		<ul class="nav navbar-nav navbar-right">
		<c:if test="${empty memberLoginOK}">
			<li><a href="<c:url value="/login/login.jsp"/>">登入</a></li>
			<li><a href="<c:url value="/signup/signup.jsp"/>">註冊</a></li>	
		</c:if>
		<c:if test="${not empty memberLoginOK}">
			
				<c:if test="${not empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(${ShoppingCart.size})</a><li>
				</c:if>
				<c:if test="${empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(0)</a></li>
				</c:if>
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${memberLoginOK.memberID } !</a>
	              <ul class="dropdown-menu">
	                <li><a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">歷史紀錄</a></li>
	                <li><a href="<c:url value="/membercenter/mytrains.jsp" />">健身歷程</a></li> 
	                <li><a href="<c:url value="/group/create.jsp" />">揪團</a></li>  
	                <li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	              
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
			<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
			</ul>
		</c:if>
	</div><!--/.nav-collapse -->
	</div>
</nav>

	
<section id="section-header">
		<div class="container">	
			<div class="row">
          			<div id="section-header-text"">
            			<h1 style="color:#002147;">Find Your Courses</h1>
      				</div>
			</div>
		</div>
</section>



<section id="section-cal">
	<div class="container">	
		<div class="row" >
				<div id='calendar'></div>
		</div>
		
	</div>
</section>		


<!-- 	購物車視窗 -->
<div class="modal fade" id="myModal">
		<form id="shoppingCartDIV" method="post" action="">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="exampleModalLabel">Modal title</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<table>
							<tr>
								<td id="trainer"  style="font-size: 18px;"></td>
							</tr>
							<tr>
								<td id="locationname" style="font-size: 18px;"></td>
							</tr>
							<tr>
								<td id="start" style="font-size: 18px;"></td>
							</tr>
							<tr>
								<td id="coursekind" style="font-size: 18px;"></td>
							</tr>
							<tr>
								<td id="charge" style="font-size: 18px;"></td>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">加入購物車</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</form>
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
					<H4 id="msgStr"></H4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>

<!-- 登入視窗 -->
<div class="modal fade" id="LoginDIV" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<form id="LoginForm" method="post" action="">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">登入</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       
		          <div class="form-group">
		            <label for="recipient-name" class="form-control-label">帳號</label>
		            <input type="text" class="form-control" name="userId" id="userId">
		            <small><font color="red" size="-1" id="userIdError"></font></small>
		          </div>
		          <div class="form-group">
		            <label for="message-text" class="form-control-label">密碼</label>
		            <input type="password" class="form-control" name="pswd" id="pswd" >
		            <small><font color="red" size="-1" id="pswdError"></font></small>
		          </div>
		          <div class="form-group">
		          	 <small><font color="red" size="-1" id="LoginErrorMsg"></font></small>
		       	  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
		        <button type="submit" class="btn btn-primary">登入</button>
		      </div>
		    </div>
		  </div>
	  </form>
</div>




</body>
</html>