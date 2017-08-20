<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses Calendar</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link href='css/fullcalendar.min.css' rel='stylesheet' />
<link href='css/jquery.qtip.min.css' rel='stylesheet' />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/jquery-ui.min.css" rel="stylesheet" />
<link href="css/jquery-ui.theme.min.css" rel="stylesheet" />
<link href="css/jquery-ui.structure.min.css" rel="stylesheet" />
<link href="css/jquery-ui-timepicker-addon.min.css" rel="stylesheet" />
<link href="css/scheduler.min.css" rel="stylesheet" />
<link href="css/bootstrap-select.min.css" rel="stylesheet"  />

<script src="js/jquery.min.js"></script>
<script src='js/jquery.qtip.min.js'></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery-ui-timepicker-addon.min.js"></script>
<script src="js/jquery-ui-sliderAccess.js"></script>
<script src='js/moment.min.js'></script>
<script src='js/fullcalendar.min.js'></script>
<script src="js/scheduler.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-select.min.js"></script>


<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
/*  	background-color: #002147;  */
}


#calendar {
	width: 80%;
 	margin: 30px auto; 
 	background-color:#ffffff;
}


#icons {
	margin: 0;
	padding: 0;
}

#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

.form-check-label{
 	margin-right: 70px; 
}

.backtoindex{
/* 	color:#ffffff; */
}


</style>



<script>
	$(document).ready(function() {
		
		
		var identity ="member"
		var location="all"
		var kind="all"
		var trainersearch="all"
				
		// Dynamic update events according to memebr's location and kind selection 
		$("#kindselct,#gymselect,#trainerselect").change(function(){
			
			kind = $("#kindselct option:selected").val()
			location = $("#gymselect option:selected").val()
			trainersearch = $("#trainerselect option:selected").val()
			
			$.get('json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch,{},function(result){
				
				$('#calendar').fullCalendar('removeEvents')			
 				$('#calendar').fullCalendar('addEventSource',result)
 				$('#calendar').fullCalendar('rerenderEvents')
			})
			
			$.get('resources?location='+location,{},function(result){
				$('#calendar').fullCalendar('removeReource')
				$('#calendar').fullCalendar('addResource',result)
				$('#calendar').fullCalendar('refetchResources')
			})
		})
		
		
		// Form submit handler for Create and Update Events
		$('#newevent').on('submit', function(e) {
			var that = $(this);
			$("#coursekind").val($("input[name='checkkind']:checked").val()) // put checkbox group selected value to coursekind
			console.log("Form Submit, method=" + that.attr('method')+" ,action=" + that.attr('action')+" ,serialize=" + $(that).serialize())
			$.ajax({
				type : 'POST',
				url : 'do',
				data : $(that).serialize(),
	
			}).done(function(data) {
				console.log("success");
				
				if(data=='edit success'){
					$("#successalert").fadeTo(2000, 500).slideUp(500, function(){
			               $("#successalert").slideUp(500);
	                });   
				}
				$('#calendar').fullCalendar('removeEvents')
  				$('#calendar').fullCalendar('addEventSource','json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch)
  				$('#calendar').fullCalendar('rerenderEvents')

			}).fail(function() {
				console.log("Posting failed.");
			});
			
			e.preventDefault();
		});
		
		$("#newevent").submit(function() {
			$('#trainerModal').modal('hide')
		});
		
		// Click handler for Delete Event
		$("#delsubmit").click(function(){
			$("#action").val("delete")
		})
		
		// Get all trainer options 
		$.get('trainers',{},function(trainers){	
			$("#trainerselect").append('<option value="all">師資</option>');
			trainers.map(function(trainer){
				$("#trainerselect").append('<option value=' + trainer.trainerid + '>' + trainer.trainerid + '</option>');
			})
			
			$("#trainerselect option:selected").val("all");
		})
		
		
		// Get all gym options 
		$.get('gyms',{},function(gyms){	
			$("#gymselect").append('<option value="all">訓練場館</option>');
			gyms.map(function(gym){

				$("#gymselect").append('<option value=' + gym.locationno + '>' + gym.locationname + '</option>');
			})
			
			$("#gymselect option:selected").val("all");
		})

		$('#calendar').fullCalendar({
			
			schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
			defaultView: 'month',
			allDaySlot: false,
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek'
			},
			defaultDate : '2017-08-30',
			firstDay:1,
			//slotDuration:"01:00:00",
			minTime: "09:00:00",
			maxTime: "24:00:00",
			timeFormat: 'H',
			aspectRatio: 2.2,
			displayEventEnd :true,
			selectable : true,
			unselectAuto : true,
			navLinks : true, // can click day/week names to navigate views
			editable : true,
			eventLimit : true, // allow "more" link when too many events	
			resourceGroupField: 'gym',
			resourceAreaWidth: '35%',
			resourceColumns: [
				{
					labelText: '教室',
					field: 'title'
				}
			],
			resources: 'resources?location='+location,
			//events : 'json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch,
			eventSources: [
				{	id:1,
					url:'json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch
				}
			],
			
			//Triggered when a new date-range is rendered, or when the view type switches.
			viewRender: function(view,element){
				console.log("ViewRender :: location="+location+" ,kind="+kind+" ,trainersearch="+trainersearch)
						
				$.get('json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch,{},function(result){
				
					$('#calendar').fullCalendar('removeEventSource',1)	
					$('#calendar').fullCalendar('removeEvents')
  					$('#calendar').fullCalendar('addEventSource',result)
  					$('#calendar').fullCalendar('rerenderEvents')
 					
 				})

				// Customize slot's height
// 				var slatHeight = $("#calendar").find('.fc-slats').height();
// 				var height = $('#calendar').find('.fc-time-grid-container').height();
// 				console.log("slatHeight="+slatHeight+" ,height"+height)
// 				$('#calendar').find('.fc-widget-content').css('height',(height/15-1)+'px');	
			},
			
			// Create New Event 
			select: function( start, end, jsEvent, view){
				console.log("---\nDay Select")

				const oneday = 24*60*60*1000
				var correct_end =  end-oneday
				
				if(view.name=="month"){
					
					console.log('Month view, start='+start.format()+" ,end="+end.format())
					var _year = new Date(correct_end).getFullYear()
					var _month = ((new Date(correct_end).getMonth()+1)<10)?("0"+(new Date(correct_end).getMonth()+1)):(new Date(correct_end).getMonth()+1)
					var _day = new Date(correct_end).getDate()
					end = _year+"-"+_month+"-"+_day
					
					$("#start,#end").datetimepicker('destroy')
					$("#start,#end").timepicker({
						timeOnly:true,
						//dateFormat: "yy-mm-dd",
					    timeFormat:  "HH:mm",
					    showMinute: false,
					    hour:9
					})

					$('input:checkbox').prop('checked',false)
					$("#startdiv").css('display','inline')
					$("#enddiv").css('display','inline')
					$("#start").val("")
					$("#end").val("")
					$("#multiple").val((start.format()+","+end)) 
					$('#modalTitle').html("新增課程 :  "+"( 課程期間 : " +start.format()+" 到 "+end+" )")
				}
				
				if(view.name=="agendaWeek"){
					console.log('agendaWeek view, start='+start.format()+" ,end="+end.format())
					$('input:checkbox').prop('checked',false)
					$("#startdiv").css('display','none')
					$("#enddiv").css('display','none')
					$("#start").val(start.format())
					$("#end").val(end.format())
					$("#multiple").val((start.format()+","+end.format())) 
					$('#modalTitle').html("新增課程 :  "+"( 課程時間 : " +start.format("YYYY-MM-DD HH:mm")+" 到 "+end.format("YYYY-MM-DD HH:mm")+" )")
				}
				

				// select date duration
				$("#title").val("")
				$("#charge").val("")
				$("#addsubmit").html('New')
				$("#delsubmit").css('display','none')
				$("#action").val("add")
				
				
				// got ajax rsults to be room options 
				$.get('resources?location='+location,{},function(data){	
					rooms = data.map(function(room){
					return (room.id+":"+room.title)})		
					getRoom(rooms);
				})
				
				$('#trainerModal').modal();
			},
			
			// Update Event Method - 1 (agendaWeek View Only)
			eventResize:function( event, delta, revertFunc, jsEvent, ui, view ) { 		
				console.log("---\neventResize")
				console.log("id="+event.id+", title="+event.title+", trainerId="+event.trainerId+", resourceId="+event.resourceId+
						  	", coursekind="+event.coursekind+", start="+event.start.format()+", end="+event.end.format())
						  	
				if(view.name=="agendaWeek"){
					updateEventMethod(event)
				}
			},
			
			// Update Event Method - 2 (agendaWeek View and Month View) 
			eventDrop: function( event, delta, revertFunc, jsEvent, ui, view ) { 
				console.log("---\neventDrop")
				updateEventMethod(event)
			},
			
			// Update Event Method - 3
			eventClick : function(event, jsEvent, view) {
				console.log("---\neventClick")
				console.log("id="+event.id+", title="+event.title+", trainerId="+event.trainerId+", resourceId="+event.resourceId+
						  	", coursekind="+event.coursekind+", start="+event.start+", end="+event.end)
					
				var _start=_end =null
				_start = getStartEnd(event.start,event.end).split(",")[0]
				_end = getStartEnd(event.start,event.end).split(",")[1]
				//  Data sent to Server 
				$("#action").val("edit")				
				$("#eventid").val(event.id)
				$("#title").val(event.title);	
				$("#start").val(_start)
				$("#end").val(_end)	
				$("#trainerId").val(event.trainerId)
				$("#coursekind").val(event.coursekind)
				$("#charge").val(event.charge)	
				$("#e_status").val(event.e_status)
				$("#enroll").val(event.enroll)
				
				// Form UI
				$("#start,#end").timepicker('destroy')
				$("#start,#end").datetimepicker({
					dateFormat: "yy-mm-dd",
				    timeFormat:  "HH:mm",
				    showMinute: false,
				});
				$('#modalTitle').html(event.title);
				$("#addsubmit").html('Update')
				$("#delsubmit").css('display','inline')
				$("#startdiv").css('display','inline')
				$("#enddiv").css('display','inline')
				$('input:checkbox').prop('checked',false)
				$(":checkbox[value='"+event.coursekind+"']").prop('checked', true);

				
				$.get('resources?location='+location,{},function(data){				
					rooms = data.map(function(room){return (room.id+":"+room.title)})		
					getRoom(rooms,event.resourceId);
				})
				
				$("#trainerModal").modal()
			},
			
		}); // End of fullcalendar 
		
		$("#successalert").hide();
		
		// coursekind checkbox group
		$("input:checkbox").on('click', function() {
			  
			  var $box = $(this);
			  if ($box.is(":checked")) {
			    var group = "input:checkbox[name='" + $box.attr("name") + "']";
			    $(group).prop("checked", false);
			    $box.prop("checked", true);
			  } else {
			    $box.prop("checked", false);
			  }
		});
		
		// invoked in eventDrop and eventResize 
		function updateEventMethod(event){
			console.log("id="+event.id+", title="+event.title+", trainerId="+event.trainerId+", resourceId="+event.resourceId+
				  	", coursekind="+event.coursekind+", start="+event.start.format()+", end="+event.end.format())
				  	
			var _start=_end =null
			var result= getStartEnd(event.start,event.end)
			_start = result.split(",")[0]
			_end = result.split(",")[1]
			//  Data sent to Server 
			$("#action").val("edit")
			$("#eventid").val(event.id)
			$("#title").val(event.title);	
			$("#start").val(_start)
			$("#end").val(_end)	
			$("#trainerId").val(event.trainerId)
			$("#coursekind").val(event.coursekind)
			$("#charge").val(event.charge)	
			$("#e_status").val(event.e_status)
			$("#enroll").val(event.enroll)
			
			var group = "input:checkbox[name='checkkind']";
	    	$(group).prop("checked", false);
			$(":checkbox[value='"+event.coursekind+"']").prop('checked', true);
			$("#coursekind").val($("input[name='checkkind']:checked").val()) // put checkbox group selected value to coursekind

			// roomid to sever 
			$('#room option').remove()
			$("#room").append('<option value=' + event.resourceId + ' selected="selected">' + event.resourceId + '</option>');
			
			var test = $("#newevent")
			console.log("method=" + test.attr('method')+" ,action=" + test.attr('action')+" ,serialize=" + $(test).serialize())					
			$.ajax({
				type : 'POST',
				url : 'do',
				data : $(test).serialize(),
	
			}).done(function(data) {
				console.log("success"); 
				
				$.get('json?identity='+identity+"&location="+location+"&kind="+kind+"&trainersearch="+trainersearch,{},function(result){
					
					$('#calendar').fullCalendar('removeEventSource',1)	
					$('#calendar').fullCalendar('removeEvents')
  					$('#calendar').fullCalendar('addEventSource',result)
  					$('#calendar').fullCalendar('rerenderEvents')

 				})
			}).fail(function(data) {
				console.log("Posting failed.");
			});	
		}
		
		function getStartEnd(start,end){
			
			var d=_year=_month=_day=_hour=_start=_end = offset =null
			
			d= new Date()			
			offset = d.getTimezoneOffset()*(-1)*60*1000 //TimezoneOffset in milliseconds
			d = new Date((start-offset))
			_year= d.getFullYear()
			_month = d.getMonth()+1
			_day = d.getDate()
			_hour = ((d.getHours())<10)?("0"+(d.getHours())):(d.getHours());
			_start = _year+"-"+_month+"-"+_day+" "+_hour+":00"
			
			
			d= new Date((end-offset))
			_year= d.getFullYear()
			_month = d.getMonth()+1
			_day = d.getDate()
			_hour = ((d.getHours())<10)?("0"+(d.getHours())):(d.getHours());
			_end = _year+"-"+_month+"-"+_day+" "+_hour+":00"
			
			return (_start+","+_end)
		}
		
		// Generate room options in form, Executed in dayClick callback
		function getRoom(data,selected) {
			$('#room option').remove()
			
			var res = (data+"").split(",")	
			
			// index = event's resourceid, therest = the rest resources
			var index = res.filter(function(resource){return (resource.split(":")[0] == selected)})	+""  // convert index to string, so that can use split()
			var therest = res.filter(function(resource,selected){return !((resource).startsWith(selected))})

							
			// Selected options = event's resourceid
			if(!(selected==null)){
				$("#room").append('<option value=' + index.split(":")[0] + '>' + index.split(":")[1] + '</option>');
				therest.map(function(room){
				   		$("#room").append('<option value=' + room.split(":")[0] + '>' + room.split(":")[1] + '</option>');
			   	})
			   	$("#room option:selected").val(index.split(":")[0])
			
			}else{
				res.map(function(room,index,array){
				   		let roomno = room.split(":")[0]
				   		let roomtitle = room.split(":")[1]
				   		$("#room").append('<option value=' + roomno + '>' + roomtitle + '</option>');
			   	})
				$("#room option:selected").val(res[0]);
			}
		}
	});

</script>
</head>
<body>

	<div class="container">	
	
		<div class="row" id="calalert">
			<div class="col-md-4">
			${trainerLoginOK.trainerID}"
				<a class="backtoindex" href="<c:url value="/index.jsp"/>">回首頁</a>
				
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-4"></div>
		</div>
	
		<div class="row" id="calheader">
				<div class="col-md-4">
					<!-- Alert after doing CUD	 -->
					
					<select id="kindselct" class=" form-control">
						<option value="all" selected="selected">訓練種類</option>
						<option value="aerobic">心肺有氧</option>
						<option value="weighttrain">重量訓練</option>
						<option value="cycle">飛輪</option>
						<option value="other">其他</option>
					</select>
				</div>
				<div class="col-md-4">
					<select id="gymselect" class="form-control"></select>
				</div>
				<div class="col-md-4">
					<select id="trainerselect" class="form-control"></select>
				</div>
		</div>		
	</div>
	
	<div class="full-width" id="calcontent">
		<div id='calendar'></div>
	</div>
	
	
	<!-- Modal -->
	<div id="trainerModal" class="modal fade">
	<form id="newevent" action="do" method="POST">
		<div class="modal-dialog" role="dialog">
			<div class="modal-content">
				<input type="text" id="action" name="action" style="display:none" >
				<input type="text" id="eventid" name="eventid" style="display:none" >
				<input type="text" id="trainerId" name="trainerId" style="display:none" >
				<input type="text" id="coursekind" name="coursekind" style="display:none" >
				<input type="text" id="e_status" name="e_status" style="display:none" >
				<input type="text" id="enroll" name="enroll" style="display:none" >
				<input type="text" id="multiple" name="multiple" style="display:none" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span> <span class="sr-only">close</span>
					</button>
					<h4 id="modalTitle" class="modal-title"></h4>
				</div>
				<div id="modalBody" class="modal-body">
					<div class="form-group">
						<label for="title">課程名稱</label>
						<input type="text" class="form-control" name="title" id="title">
					</div>
					<div class="form-group" id="startdiv">
						<label for="start">開始時間</label>
						<input type="text" class="form-control" name="start" id="start">
					</div>
					<div class="form-group" id="enddiv">
						<label for="end">結束時間</label>
						<input type="text" class="form-control" name="end" id="end">
					</div>
					<div class="form-group">
						<label for="room">上課教室</label>
						<select  class="form-control" id="room" name="room"></select>
					</div>
					<div class="form-check form-check-inline">
  						<label class="form-check-label">
    						<input class="form-check-input kindform" name="checkkind" type="checkbox" id="kind1" value="aerobic"> 心肺有氧
 						</label>
 						<label class="form-check-label">
    						<input class="form-check-input kindform" name="checkkind" type="checkbox" id="kind2" value="weighttrain"> 重量訓練
  						</label>
  						<label class="form-check-label">
  						  <input class="form-check-input kindform" name="checkkind" type="checkbox" id="kind3" value="cycle" > 飛輪
  						</label>
  						<label class="form-check-label">
  						  <input class="form-check-input kindform" name="checkkind" type="checkbox" id="kind4" value="other" > 其他
  						</label>
					</div>

					<div class="form-group">
						<label for="charge">課程費用</label>
						<input type="text" class="form-control" name="charge" id="charge">
					</div>
				</div>
				<div id="modalFooter" class="modal-footer">
					<div>
						<button type="submit" class="btn btn-danger" id="delsubmit" style="display: none;">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary" id="addsubmit">New</button>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>	
	<!-- End of Modal -->



</body>
</html>