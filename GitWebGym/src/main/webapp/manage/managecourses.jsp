<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Courses</title>

<link href='../css/jquery.qtip.min.css' rel='stylesheet' />
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	var identity ="all"
	var location="all"
	var kind="all"
	var trainersearch="all"
	var contextPath = "${pageContext.request.contextPath}"
	
	
	loadAllCourse()
	
	$("#managecourse").on('submit',function(e){
		var that = $(this);
	})
	
	
	$('#showallcourses>tbody').on('click','tr button',function(){
			  
		var id = $(this).parents('tr').find('td:nth-child(1)').text(); // 取得產品 id
		console.log("id="+id)
	})
	
	
	
	
	function loadAllCourse(){
		
		$.get(contextPath+'/json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch,{},function(mycourses){
			
			var docFrag = $(document.createDocumentFragment());
			var tb = $('#showallcourses>tbody');
			tb.empty();
			
	        $.each(mycourses, function (idx,course) {
	        	
	        		var cell1 = $('<td></td>').text(course.id);
	                var cell2 = $('<td></td>').text(course.title);
	                var cell3 = $('<td></td>').text(course.trainerId);
	                var cell4 = $('<td></td>').text(course.start);
	                var cell5 = $('<td></td>').text(course.end);
	                var cell6 = $('<td></td>').text(course.enroll);
	                var cell7 = $('<td></td>').text(course.resourceId);	
	                var cell8 = $('<td></td>');	
	                var cell9 = $('<input type="text" name="e_status">').attr('value',course.e_status)
	                cell8.append(cell9)
	                var cell10 = $('<td></td>').html('<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span></button>');

	                var row = $('<tr></tr>').append([cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell10]);
	                docFrag.append(row);
	        	tb.append(docFrag);
	        })
		})
	}  
	

	
})

</script>
</head>
<body>

	<section>
	<div class="container">
		<div class="row"> 
		<form action="#" method="POST" id="managecourse">
			<input type="text" name="action" value="edit" hidden="hidden">
			<input type="text" name="eventid" hidden="hidden">
			<input type="text" name="title" hidden="hidden">
			<input type="text" name="resourceId" hidden="hidden">
			<input type="text" name="trainerId" hidden="hidden">
			<input type="text" name="start" hidden="hidden">
			<input type="text" name="end" hidden="hidden">
			<input type="text" name="coursekind" hidden="hidden">
			<input type="text" name="enroll" hidden="hidden">
			<input type="text" name="charge" hidden="hidden">
			<input type="text" name="multiple" hidden="hidden">
		
			<table id="showallcourses" class="table">
				<thead>
					<th>課程編號</th>
					<th>名稱</th>
					<th>開課教練</th>
					<th>開始時間</th>
					<th>結束時間</th>
					<th>報名人數</th>
					<th>地點</th>
					<th>是否上架</th>
					<th>上/下架</th>
				</thead>
				<tbody>
					
				</tbody>

			</table>
		</form>
		</div>
	</div>
	</section>

</body>
</html>