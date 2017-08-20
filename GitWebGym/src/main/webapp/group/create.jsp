<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${loginidentity=='trainer' }">
	

</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>揪團</title>
<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link  href="../css/bootstrap.min.css" rel="stylesheet"></link>
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" href="../css/font-awesome.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<style>

body{
	
  	font-size: 16px;  
}
h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}


#section-header{
	margin-top: 70px;

}
#div-content table{
	background-color: #404764;
	color:#ffffff;
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
  	   	background-image: url(../images/order-footer-1.jpg);   
     	background-size: cover;
}


#flex-item-2{	
  	   	background-image: url(../images/order-footer-3.jpg);   
     	background-size: cover;
}

#flex-item-3{	
  	   	background-image: url(../images/order-footer-5.jpg);   
     	background-size: cover;
}


#flex-item-4{	
  	   	background-image: url(../images/order-footer-7.jpg);   
     	background-size: cover;
}

#flex-item-5{	
  	   	background-image: url(../images/order-footer-9.jpg);   
     	background-size: cover;
}


</style>
<script>
$(document).ready(function(){
	
	var contextPath = "${pageContext.request.contextPath}"
	var memberid= "${memberLoginOK.memberID}"
	loadAllGroups()
	
	$("#title,#buttonUpdate").hide()
	
	
	
	// 刪除自己的團
	$('#showallgroups>tbody').on('click','tr button:button:nth-child(2)',function(){
			  
		var id = $(this).parents('tr').find('td:nth-child(1)').text(); 
		$.get(contextPath+'/group/deletemygroup',{groupid:id,memberid:memberid},function(data){
			console.log("delete ok! "+data)
			loadAllGroups()
		})
		
	})
	
	// 編輯所有團
	$('#showallgroups>tbody').on('click','tr button:button:nth-child(1)',function(){
		if(! ($(this).prop('name')=='joinbutton'))	{
			var groupid = $(this).parents('tr').find('td:nth-child(1)').text(); 
			var creator = $(this).parents('tr').find('td:nth-child(2)').text(); 
			var title = $(this).parents('tr').find('td:nth-child(3)').text(); 
			var joins = $(this).parents('tr').find('td:nth-child(4)').text(); 
			
			$("#title,#buttonUpdate").show()	
		   $('#groupid').val(groupid).next('span').text(groupid)
		   $('#creator').val(creator).next('span').text(creator)
		   $('#title').val(title)
		   $('#title').prop("display","inline")
		   $('#joins').val(joins).next('span').text(joins)
		   
		}
	})
	
	// 更新團
	$('#buttonUpdate').click(function(){
		   var datas = $('form[name="editform"]').serialize();
	    	$.post(contextPath+'/group/editmygroup',datas,function(data){
	    		 $('#groupid').val('').next('span').text('')
				 $('#creator').val('').next('span').text('')
				 $('#title').val('')
				 $('#joins').val('').next('span').text('')
				 
				 $("#title,#buttonUpdate").hide()
	    		 loadAllGroups()

	    	})
	})
	
	
	function joinOrLeaveGroupCallback (){
		var id = $(this).parents('tr').find('td:nth-child(1)').text(); 
		
		if($(this).find('span').hasClass('glyphicon-minus')){
			$.post(contextPath+'/group/leavegroup',{groupid:id,memberid:memberid},function(msg){
				console.log(msg)
				loadAllGroups()
			})
		}else{
			$.post(contextPath+'/group/joingroup',{groupid:id,memberid:memberid},function(msg){
				if(msg == "already in group"){
					alert("already in group")
				}
				loadAllGroups()
			})
		}

	}
	
	function loadAllGroups(){
		$.get(contextPath+'/showallgroups',{},function(groups){
			
			var memberid = "${memberLoginOK.memberID}"
			var mygroups = groups.filter(function(group){return (group.creator == memberid)})
			var othergroups = groups.filter(function(group){return (group.creator != memberid)})
			var tb = $('#showallgroups>tbody');
			tb.empty();

				
	        $.each(mygroups, dynamicAppendTable)
	        $.each(othergroups, dynamicAppendTable)
	        
	        // 加團與退團 button click listener
			$('button[name="joinbutton"]').click(joinOrLeaveGroupCallback)
		})
	}
	
	
	function dynamicAppendTable (idx,group) {   
		var docFrag = $(document.createDocumentFragment());
		var tb = $('#showallgroups>tbody');
		var is_in_group = "n"
		var cell1 = $('<td></td>').text(group.groupid);
        var cell2 = $('<td></td>').text(group.creator);
        var cell3 = $('<td></td>').text(group.title);
        var cell4 = $('<td></td>').text(group.joins.substring(0,group.joins.length-1));

		var joiners = group.joins.split(",")
		joiners.map(function(member){
			if(member==memberid){
				is_in_group = "y"
			}
		})          
        if(memberid != ""){
            if(group.creator == memberid){
            	var cell5 = $('<td></td>').html('<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button>');
            }else{
            	if(is_in_group == 'y'){
                	var cell5 = $('<td></td>').html('<button type="button" class="btn btn-default" name="joinbutton"><span class="glyphicon glyphicon-minus"></span></button>');
            	}else{
                	var cell5 = $('<td></td>').html('<button type="button" class="btn btn-primary" name="joinbutton"><span class="glyphicon glyphicon-plus"></span></button>');
            	}
            }
            
            var row = $('<tr></tr>').append([cell1, cell2, cell3, cell4, cell5]);
            docFrag.append(row);
    		tb.append(docFrag);
            
        }else{
        	// if guest, don't append manage button
            var row = $('<tr></tr>').append([cell1, cell2, cell3, cell4]);
            docFrag.append(row);
    		tb.append(docFrag);
        }

        

	}
})

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
				<c:if test="${empty memberLoginOK && empty trainerLoginOK}">
					<li><a href="<c:url value="/login/login.jsp"/>">登入</a></li>
					<li><a href="<c:url value="/signup/signup.jsp"/>">註冊</a></li>	
				</c:if> 
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
	                <li><a href="<c:url value="/courses/enroll.jsp"/>">報名課程</a></li>	
	                <li><a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">歷史紀錄</a></li> 
	                <li><a href="<c:url value="/membercenter/mytrains.jsp" />">健身歷程</a></li>  
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
	<div class="row" >

		<H1>You Can Find Friends   <i class="fa fa-bullhorn" aria-hidden="true"></i></H1>
		${creategroupmsg }

		<hr>
	</div>
	
</div>	
</section>
<section id="section-content"> 
<div class="container">
	<div class="row" id="div-content">
<%-- 	<c:if test="${not empty memberLoginOK } "> --%>
		<form class="form-inline" action="<c:url value="/group/creategroup"/>" method="POST" id="showgroups">
		  <input type="text" name="memberid" value="${memberLoginOK.memberID }" hidden="hidden">
		  <label class="sr-only" for="groupname">Group Name</label>
		  <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 input-lg" name="newgroupid" id="newgroupid" placeholder="大家來打球...">
		
		  <label class="sr-only" for="groupcontent">Say Something!</label>
		  <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 input-lg" name="newgrouptitle" id="newgrouptitle" placeholder="下午五點新生球場! ">
		
		  <input type="text" name="action" value="create" hidden="hidden">
		  <button type="submit" class="btn btn-primary">Create New Group</button>
		</form>
<%-- 	</c:if> --%>
		<br>
		<h1>You Can Join Friends   <i class="fa fa-bicycle" aria-hidden="true"></i></h1>
		<hr>
		<table id="showallgroups" class="table">
			<thead>
				<th>主題</th>
				<th>揪團者</th>
				<th>時間地點</th>
				<th>還有誰有興趣</th>
				<c:if test="${not empty memberLoginOK }">
				<th>管理</th>
				</c:if>
			</thead>
			<tbody>
			</tbody>
			<tfoot>
            	<tr>
               	<form name="editform">
                    <td><input type="hidden" class="form-control" id="groupid" name="groupid" placeholder="團名"><span></span></td>
                    <td><input type=hidden class="form-control" id="creator" name="creator" placeholder="創團者"><span></span></td>
                    <td><input type="text"  class="form-control" id="title" name="title" placeholder="標題" ></td>
                    <td><input type="hidden" class="form-control" id="joins" name="joins" placeholder="加入者"><span></span></td>
                    <td><button id="buttonUpdate" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span></button></td>
               </form>
               </tr>
       		 </tfoot>
		</table>
		</div>
		<div class="row" id="div-buttom">
		
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

</body>
</html>
