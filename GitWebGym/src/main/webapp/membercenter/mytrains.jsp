<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="subTitle" value="個人訓練" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${subTitle}</title>
	<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
	<link rel="stylesheet" href="../css/bootstrap.min.css"></link>
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<script src="../js/jquery.min.js"></script>
<script>
 $(function(){
	 //讀取訓練資料
	
	
	 
	 $("#buttonUpdate").css("background-color","black")
	 $("#buttonUpdate").css("color","white")
	 
	 $("#buttonAdd").css("background-color","#1C5403")
	 $("#buttonAdd").css("color","white")
 	 loadTrains();	 
	 function loadTrains(){	 		
		 $.getJSON('<c:url value="/selectdata.do"/>',{},function(datas){	
			 console.log("datas="+datas)
			 $('#traintable>tbody').empty()
			 $.each(datas,function(idx,traindata){
				 console.log(traindata.trainday);
				 console.log(traindata.traintitle);
				 console.log(traindata.trainset);	
				 console.log(traindata.trainweight);
	
				 
				var cell1=$("<td></td>").text(traindata.trainday.split(" ")[0]);
			    var cell2=$("<td></td>").text(traindata.traintitle);
		        var cell3=$("<td></td>").text(traindata.trainset);
		        var cell4=$("<td></td>").text(traindata.trainweight);
		        var cell5=$("<td></td>").html('<button type="button" class="btn "><span class="glyphicon glyphicon-remove"></span></button> <button type="button" class="btn "><span class="glyphicon glyphicon-pencil"></span></button>')
	            var row=$("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);		        
		        $('#traintable>tbody').append(row);		        
			 })	
			 	 $("#traintable>tbody>tr button:nth-child(1)").css("background-color","black")
			 	  $("#traintable>tbody>tr button:nth-child(1)").css("color","white")
			 
		 })	 
	 }
	 //新增功能
	 $('#buttonAdd').click(function(){
		 var datas = $('form[name="myForm"]').serialize();
	     console.log($('form[name="myForm"]').serializeArray());
	     $.post('<c:url value="/insertdata.do"/>',datas,function(data){
	    	 alert("Create a Train Detail Succefully!");
	    	 loadTrains();
	    	 $('#traintitle').val('');
			 $('#trainday').val('');
			 $('#trainset').val('');
			 $('#trainweight').val('');	
	    	 
	     })
		 
	 })
	 
	 //刪除功能
	 $("#traintable>tbody").on('click','tr button:nth-child(1)',function(){
		    var deletetitle = $(this).parents('tr').find('td:nth-child(2)').text(); 
		    var deleteday = $(this).parents('tr').find('td:nth-child(1)').text();
		    $.post('<c:url value="/deletedata.do"/>',{traintitle:deletetitle,trainday:deleteday},function(data){
		    	alert("Delete a Train Detail Succefully!");
		    	loadTrains();
		    })
		 
		 })

	 
	 $("#traintable>tbody").on('click','tr button:nth-child(2)',function(){ 
			//編輯紐功能設定		
// 			if($(this).attr('class')=="btn btn-info"){ 
// 				console.log($(this).attr('class')) 
	 			var trainday = $(this).parents('tr').find('td:nth-child(1)').text(); 
				var traintitle = $(this).parents('tr').find('td:nth-child(2)').text(); 
				var trainset = $(this).parents('tr').find('td:nth-child(3)').text(); 
				var trainweight = $(this).parents('tr').find('td:nth-child(4)').text(); 
				
				$("#oldtraintitle").val(traintitle); 
				
	 			$("#oldtrainday").val(trainday); 
	 			$("#traintitle").val(traintitle); 
				$("#trainset").val(trainset); 
				$("#trainweight").val(trainweight); 		 
	 	}) 
	 	//更新項目
	 	$('#buttonUpdate').click(function(){
	 	
	 		var datas = $('form[name="myForm"]').serialize();
	 		$.post('<c:url value="/updatedata.do"/>',datas,function(data){
	 			alert("Update a Train Detail Succefully!");
	 			loadTrains();
	 			   $('#oldtraintitle').val('');
	 			   $('#traintitle').val('');
				   $('#oldtrainday').val('');
				   $('#trainset').val('');
				   $('#trainweight').val('');	 			
	 		})	 
	 	})	 	
 })
 
</script>
<style>
body{
	background-color:#5073AB;
	background: linear-gradient(to bottom right, #5073AB, #87A9D6);
	color:#ffffff;
	font-size: 16px;
}

h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}

#section-header{
	background-color:#002147;
	padding:20px;
	margin-top: 70px;
}

#section-mytrain{
	margin-top: 5px;
}


#traintable th{
	font-family: 'Open Sans', sans-serif;
}



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
		<ul class="nav navbar-nav navbar-right">
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
	                <li><a href="<c:url value="/courses/enroll.jsp"/>">報名課程</a></li>	
	                <li><a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">歷史紀錄</a></li>  
	                <li><a href="<c:url value="/group/create.jsp" />">揪團</a></li>  
	                <li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	              
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>		
			</c:if>	
		</ul>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>


<section id="section-header">
	<div class="container">	
		<div class="row">
			 <div style="display:inline-block; width:70%; height:100px;" >
				<h1>My Self-Training Records</h1> 
				<p style="font-style: italic;">Success isn’t given, it is earned.</p>
				<p style="font-style: italic;">On the track, on the field, in the gym. With blood, sweat and the occasional tear.</p>
			</div>
			<div style="display:inline-block; width:180px; height:180px;" >
				<img width="180" height="180" class="img-circle" src="${pageContext.request.contextPath}/misc/showmemberphoto?id=${memberLoginOK.memberID}">
			</div>
		</div>	
	</div>
</section>

<section id="section-mytrain">
	<div class="container">
		<div class="row">
	 		<form name="myForm">
			   	<table id='edittable' class="table">
			   	<tbody>
			   		<tr>
       					<td><input type="date" style="width:140px" class="form-control input-lg" name="udDay" id="trainday" placeholder="日期"></td>
       					<td><input type="text" style="width:120px" class="form-control input-lg" name="udTitle" id="traintitle" placeholder="項目"></td>                
	
       					<td><input type="text" style="width:110px" class="form-control input-lg" id="trainset" name="udSet" placeholder="組數"></td>      
       					<td><input type="text" style="width:120px" class="form-control input-lg" id="trainweight" name="udWeight" placeholder="重量"></td> 

             			<td><button id="buttonAdd" type="button" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-ok"></span></button>
             			<button id="buttonUpdate" class="btn btn-success btn-lg"><span class="glyphicon glyphicon-edit"></span></button></td>
             		</tr>
             	</tbody>
				</table>	
						<input type='hidden' id="oldtraintitle" name="oldtraintitle"/>
             			<input type="hidden" name="oldtrainday" id="oldtrainday"/>
         	</form>
		</div>
		<div class="row">
				<table id='traintable' class="table">
					<thead>
						<tr>
							<th width='150'>Training Date</th>
							<th width='140'>Title</th>
							<th width='120'>Set</th>
							<th width='120'>Weight</th>		
							<th width='120'>Manage</th>			
						</tr>
					</thead>
					<tbody>			
					</tbody>	
				</table>

		<form>
			<input type="hidden" name="traintitle"/>
			<input type="hidden" name="trainday"/>     	
		</form>
		
		</div>
	</div>
</section>
 	


<script src="../js/jquery-ui.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
 
</body>
</html>