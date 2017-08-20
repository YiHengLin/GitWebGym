<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
	<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link href='../css/jquery.qtip.min.css' rel='stylesheet' />
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <script src='../js/jquery.min.js'></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<script>
	$(document).ready(function(){
		
		// 一鍵登入
		$("#auto").click(function(){
			
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();

			if(dd<10) {
			    dd = '0'+dd
			} 

			if(mm<10) {
			    mm = '0'+mm
			} 

			today = yyyy+ '-' + mm + '-' + dd;
			
			$("#trainerid").val("Arnold")
			$("#t_password").val("123456789")
			$("#t_name").val("阿諾")
			$("#t_id_number").val("A123456789")
			$("#male").prop("checked",true)
			$("#t_bday").val("1985-03-02")
			$("#t_mail").val("arnold@gmail.com")
			$("#t_mobile").val("0919123456")
			$("#t_tel").val("02-1234-5678")
			$("#t_address").val("台北市大安區")
			$("#t_register").val(today)
			$("#is_blacklist").val("n")
			$("#t_field").val("體態評估,減重規劃")
			$("#t_exp").val("AASFP亞洲體適能高級私人教練")
			$("#t_licence").val("FISAF FCC核心課程認證")
			$("#t_identity").val($(":checked").val())
			console.log("value="+$(":checked").val())
			console.log("today="+today)
		})
		
		
// // 		coursekind checkbox group
// 		$("input:checkbox").on('click', function() {
// 			  var $box = $(this);
// 			  if ($box.is(":checked")) {
// 			    var group = "input:checkbox[name='" + $box.attr("name") + "']";
// 			    $(group).prop("checked", false);
// 			    $box.prop("checked", true);
			    
// 			  } else {
// 			    $box.prop("checked", false);
// 			  }
			  
			  
// 		});
		
		$("#istrainer,#ismember").change(function(){
			
			if($(this).prop('id')=='istrainer'){
				console.log("istrainer")
				$("#divfield").show()
				$("#divexp").show()
				$("#divliscence").show()
			}else{
				console.log("ismember")
				$("#divfield").hide()
				$("#divexp").hide()
				$("#divliscence").hide()
			}
		})

	})
	
	
	function chooseOne(cb){
		//先取得同name的chekcBox的集合物件
		var obj = document.getElementsByName("checkbox");
		for (i=0; i<obj.length; i++){
			//判斷obj集合中的i元素是否為cb，若否則表示未被點選
			if (obj[i]!=cb)	obj[i].checked = false;
			//若是 但原先未被勾選 則變成勾選；反之 則變為未勾選
			else	obj[i].checked = cb.checked;
			//若要至少勾選一個的話，則把上面那行else拿掉，換用下面那行
			//else obj[i].checked = true;
		}
	}
	
	

</script>
 <style>
body{
 	background-image: url(../images/signup_bg.jpg);  
   	background-size:cover;   
	
}

h1{
	font-family: 'Montserrat', sans-serif;
	
}
 
.navbar-header {
    float: left;
    padding: 15px;
    text-align: center;
    width: 100%;
}
.navbar-brand {float:none;}

#section-content{
	margin-top: 100px;
} 
 
    #inputEmail,
    #inputPassword {
        margin-top: 3px;
        margin-bottom: 3px;
    }
    


.checkbox {
    float: left;
}

form {
	padding :20px 20px;
		background-color:#ffffff;
/* 	background-color:#5073AB; */
/* 	background: linear-gradient(to bottom right, #5073AB, #87A9D6); */
	
		
    width: 70%;
    margin: auto;
        border-radius: 20px;
}

#header {
    margin-top: 10px;
    margin-bottom: 10px;
}

label{
	font-size: 16px;

}
#error{
	color:red;
}
    </style>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
			</div>
		</div>
	</nav>
	
<section id="section-content">
 <div class="container">
 		<div class="col-md-offset-5 col-md-6" id="header">
        	<h1 style="font-size: 50px;	">Join Us</h1>
        </div>
        <form class="form-horizontal" action="<c:url value="/signup/SignUpServlet"/>" method="POST"  enctype="multipart/form-data">
            <input type="text" name="is_blacklist" id="is_blacklist" hidden="hidden">
            <input type="text" name="t_register" id="t_register" hidden="hidden">
           	<input type="text" name="t_identity" id="t_identity" hidden="hidden">
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
	                        <label>
	                            <input type="checkbox" name="checkbox" id="istrainer" value="trainer" onclick="chooseOne(this)" checked> 教練
	                        </label>
	                        <label>
	                            <input type="checkbox" name="checkbox" id="ismember" value="member" onclick="chooseOne(this)" > 會員
	                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="t_mail" class="col-sm-2 control-label">電子信箱</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="t_mail" name="t_mail" placeholder="Email">
                	<div id="error">${ErrorMsg.email}</div>
                </div>
            </div>
            <div class="form-group">
                <label for="t_password" class="col-sm-2 control-label">登入密碼</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="t_password" name="t_password" placeholder="Password">
                	<div id="error">${ErrorMsg.password}</div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="t_gender" id="male" value="male"> Male
                        </label>
                        <label>
                            <input type="checkbox" name="t_gender" id="female" value="female"> Female
                        </label>
                    </div>
                </div>
            </div>
  			<div class="form-group">
                <label for="trainerid" class="col-sm-2 control-label">帳號</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="trainerid" name="trainerid" placeholder="ID">
                	<div id="error">${ErrorMsg.id}</div>
                </div>
            </div>
  			<div class="form-group">
                <label for="t_name" class="col-sm-2 control-label">真實姓名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_name" name="t_name" placeholder="Name">
                	<div id="error">${ErrorMsg.name}</div>
                </div>
            </div>
            <div class="form-group">
                <label for="t_bday" class="col-sm-2 control-label">生日</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_bday" name="t_bday" placeholder="yyyy-MM-dd">
                	<div id="error">${ErrorMsg.date}</div>
                </div>
            </div>
            <div class="form-group">
                <label for="t_id_number" class="col-sm-2 control-label">身分證</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_id_number" name="t_id_number" placeholder="身分證字號">
               		<div id="error">${ErrorMsg.idNumber}</div>
                </div>
            </div>
             <div class="form-group">
                <label for="t_address" class="col-sm-2 control-label">住址</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_address" name="t_address" placeholder="住址">
                	<div id="error">${ErrorMsg.address}</div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="t_mobile" class="col-sm-2 control-label">手機號碼</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_mobile" name="t_mobile" placeholder="mobile">
                	<div id="error">${ErrorMsg.phone}</div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="t_tel" class="col-sm-2 control-label">住家電話</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_tel" name="t_tel" placeholder="tel">
                </div>
            </div>
 
             <div class="form-group" id="divfield">
                <label for="t_field" class="col-sm-2 control-label">專業領域</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_field" name="t_field" placeholder="專業領域">
               		<div id="error">${ErrorMsg.Field}</div>
                </div>
            </div>
            <div class="form-group" id="divexp">
                <label for="t_exp" class="col-sm-2 control-label">經歷</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_exp" name="t_exp" placeholder="經歷">
                	<div id="error">${ErrorMsg.Exp}</div>
                </div>
            </div>
            
            <div class="form-group" id="divliscence">
                <label for="t_licence" class="col-sm-2 control-label">證照</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="t_licence" name="t_licence" placeholder="證照">
                	<div id="error">${ErrorMsg.Lice}</div>
                </div>
            </div>
            <div class="form-group">
    			<label for="t_photo" class="col-sm-2 control-label">個人照</label>
    			<div class="col-sm-10">
    				<input type="file" class="form-control-file" id="t_photo" name="t_photo" accept="image/*" >
				</div>
  			</div>
            
            
            
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                    
                </div>
                <div class="col-sm-offset-2 col-sm-10">
                	<button id="auto" type="button" class="btn btn-info">一鍵輸入</button>
                	<button type="reset" class="btn btn-default">取消</button>
                    <button type="submit" class="btn btn-primary">Sign up</button>
                </div>
            </div>
        </form>
    </div>
    <!-- /container -->
</section>
</body>
</html>