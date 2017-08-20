<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Temmujin Fitness</title>

	<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
	<!-- stylesheets css -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/magnific-popup.css">

	<link rel="stylesheet" href="css/animate.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<link rel="stylesheet" href="css/nivo-lightbox.css">
	<link rel="stylesheet" href="css/nivo_themes/default/default.css">

	<link rel="stylesheet" href="css/hover-min.css">
	<link rel="stylesheet" href="css/flexslider.css">

	<link rel="stylesheet" href="css/style.css">

	<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600' rel='stylesheet' type='text/css'>
	<link href="https://fonts.googleapis.com/css?family=Courgette|Radley" rel="stylesheet">


<style>

#feature{
	background: url(images/index-bg-2.jpg) no-repeat top  fixed;
}
#feature img{
	opacity: 0.1;
}
footer{
	margin-top:50px;
	padding-top:10px;
	padding-bottom: 10px;
	background-color: #455370;
}

div#footer-div {
    display: table;
    width: 100%;
    table-layout: fixed;    /* For cells of equal size */
}
div#footer-div span {
	color:#ffffff;
    display: table-cell;
    text-align: center;
}

div.feature-icon span{
	background-color: #97B6B8;
}

</style>
</head>
<body id="top" data-spy="scroll" data-target=".navbar-collapse" data-offset="50">	
	
<!-- Preloader section -->
<div class="preloader">
	<div class="sk-spinner sk-spinner-pulse"></div>
</div>

<!-- <section id="home" class="parallax-section"> -->
<!--   <div class="gradient-overlay"></div> -->
<!--     <div class="container"> -->
<!--       <div class="row"> -->
<!--           <div class="col-md-offset-2 col-md-8 col-sm-12"> -->
<!--               <h1 class="wow fadeInUp" data-wow-delay="0.6s">Temmujin Fitness</h1> -->
<!--               <p class="wow fadeInUp" data-wow-delay="1.0s">Fitness, Fun, Community</p> -->
<!--               <p class="wow fadeInUp" data-wow-delay="1.0s">千錘百鍊女王</p> -->
<!--               <a href="#feature" class="wow fadeInUp btn btn-default hvr-bounce-to-top smoothScroll" data-wow-delay="1.3s">Discover Now</a> -->
<!--           </div> -->
<!--       </div> -->
<!--     </div> -->
<!-- </section> -->

<!-- Navigation section -->
<div class="navbar navbar-default navbar-static-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
    </div>
    <div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
        		<li><a href="#about" class="smoothScroll">About</a></li>
				<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
				<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
				<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
				<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
				<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
			</ul>	
		
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty memberLoginOK && empty trainerLoginOK}">
					<li><a href="<c:url value="/login/login.jsp"/>">登入</a></li>
					<li><a href="<c:url value="/signup/signup.jsp"/>">註冊</a></li>	
				</c:if>
				<c:if test="${not empty memberLoginOK || not empty trainerLoginOK}">
					<c:if test="${loginidentity=='member' }">
						<li><a href="<c:url value="/membercenter/membercenter.jsp"/>">Hi ${memberLoginOK.memberID } !</a></li>	
					 </c:if>
					 <c:if test="${loginidentity=='trainer' }">	
						<li class="dropdown">
			              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${trainerLoginOK.trainerID } !</a>
			              <ul class="dropdown-menu">
			                <li><a href="<c:url value="/trainercenter/opencourse.jsp"/>">New Course</a></li>
			                <li><a href="<c:url value="/blog/postarticle.jsp"/>">Share Ideas</a></li>
			              </ul>
			            </li>
					 </c:if>		
					<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>
				</c:if>
			</ul>	
    </div>

  </div>
</div>

<!-- Feature section -->
<section id="feature" class="parallax-section" >
  <div class="container">
    <div class="row">

      <div class="col-md-offset-2 col-md-10 col-sm-offset-1 col-sm-10">
          <div class="wow fadeInUp section-title" data-wow-delay="0.6s">
            <h1 style="font-family: 'Radley', serif;color:#ffffff;font-size:80px;font-weight: bold;">Why Choose Us?</h1>
            <h3 style="font-family: 'Radley', serif;color:#ffffff;font-weight: bold;">YOUR FITNESS DESTINATION</h23>
          </div>
      </div>

      <div class="clearfix"></div>

      <div class="col-md-4 col-sm-6 wow fadeInUp" 	>
        <div class="feature-thumb">
          <div class="feature-icon">
             <span><i class="fa fa-trophy"></i></span>
          </div>
          <h3>EVERY FIELDS</h3>
          <p>COACHES IN EVERY FIELDS PROVIDE YOU ALL YOU NEED </p>
          <p>選擇專業的運動教練 </p>
        </div>
      </div>

      <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-delay="0.6s">
        <div class="feature-thumb">
          <div class="feature-icon">
            <span><i class="fa fa-building"></i></span>
          </div>
          <h3>EVERY GYMS</h3>
          <p>JUST FIND THE GYM NEARS YOU, AND STARTED TO SWEAT</p>
          <p>選擇你喜歡的運動場館</p>
        </div>
      </div>

      <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-delay="0.9s">
        <div class="feature-thumb">
          <div class="feature-icon">
            <span><i class="fa fa-users"></i></span>
          </div>
           <h3>EVERY KINDS</h3>
           <p>CYCLE,MMA,WEIGHTRAINING,TRX GO FIND MORE!</p>
           <p>選擇你喜歡的運動</p>
        </div>
      </div>

    </div>
  </div>
</section>


<!-- About section -->
<section id="about" class="parallax-section">
	<div class="container">
		<div class="row">

      <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10">
          <div class="wow fadeInUp section-title" data-wow-delay="0.3s">
            <h2>About Us</h2>
            <h4>we care about you </h4>
          </div>
      </div>

      <div class="clearfix"></div>
      

      <div class="wow fadeInUp col-md-6 col-sm-8" data-wow-delay="0.5s">

        <!-- flexslider -->
        <div class="flexslider">
          <ul class="slides">

            <li>
              <img src="images/slide-img1.jpg" alt="Flexslider">
            </li>
            <li>
              <img src="images/slide-img2.jpg" alt="Flexslider">
            </li>
            <li>
              <img src="images/slide-img3.jpg" alt="Flexslider">
            </li>

          </ul>
        </div>

         <p>One-to-One Persernal Cources / Group Cources / Join Groups</p>
      </div>

       <div class="wow fadeInUp col-md-6 col-sm-16" data-wow-delay="0.9s">
        	<h2>GOAL</h2>
         	<p>Temmujin Fitness是個整合了各地優質健身場館及各路運動教練的運動分享平台
				承諾以草原民族千錘百鍊的勇猛及真誠的分享為經營理念
				秉持健康訓練的初衷
				創造出一個專業X客製X社交X分享的全新訓練型態</p>
			<hr>
			<p>平台以專業的場地及教練為主要核心
				不管您是想更精進自己健身表現的沙場老將
				抑或是目前還沒有健身經驗的潛力新秀 
				想信您能在這裡持續不斷地創造出更進步的自己</p>
      </div>

		</div>
	</div>
</section>



<!-- Menu section -->
<section id="course" class="parallax-section" >
  <div class="container">
    <div class="row">
      <c:if test="${loginidentity=='member' }">
      	<a href="<c:url value="/courses/enroll.jsp"/>">
      </c:if>
      <c:if test="${loginidentity=='trainer' }">
      	<a href="<c:url value="/trainercenter/opencourse.jsp"/>">
      </c:if>
      <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10" style="text-align: center;">
         <div class="wow fadeInUp section-title" data-wow-delay="0.3s">
            <h2>Courses</h2>
            <h4>Find your favorite</h4>
        </div>
      </div>
	  </a>
      <div class="clearfix"></div>

      <div class="col-md-6 col-sm-12">
        <div class="media wow fadeInUp" data-wow-delay="0.6s">
          <div class="media-object pull-left">
            <img src="images/gallery-img1.jpg" class="img-responsive" alt="Food Menu">
          </div>
          
	          <div class="media-body">
	            <h3 class="media-heading">TRX Traing</h3>
	            <p>Strength Core and Full-body Workout</p>
	          </div>
         
        </div> 

        <div class="media wow fadeInUp" data-wow-delay="0.9s">
          <div class="media-object pull-left">
            <img src="images/gallery-img2.jpg" class="img-responsive" alt="Food Menu">
          </div>
          <div class="media-body">
            <h3 class="media-heading">MMA Traing</h3>
            <p>Mixed martial arts</p>
          </div>
        </div>

        <div class="media wow fadeInUp" data-wow-delay="1.2s">
          <div class="media-object pull-left">
            <img src="images/gallery-img3.jpg" class="img-responsive" alt="Food Menu">
          </div>
          <div class="media-body">
            <h3 class="media-heading">Yoga</h3>
            <p>Peace yor mind</p>
          </div>
        </div>
      </div>

      <div class="col-md-6 col-sm-12">
        <div class="media wow fadeInUp" data-wow-delay="1s">
          <div class="media-object pull-left">
            <img src="images/gallery-img4.jpg" class="img-responsive" alt="Food Menu">
          </div>
          <div class="media-body">
            <h3 class="media-heading">Weight Training</h3>
            <p>One-to-One Training</p>
          </div>
        </div>

        <div class="media wow fadeInUp" data-wow-delay="1.3s">
          <div class="media-object pull-left">
            <img src="images/gallery-img5.jpg" class="img-responsive" alt="Food Menu">
          </div>
          <div class="media-body">
            <h3 class="media-heading">Strength Training</h3>
            <p>Stringthen explosive power </p>
          </div>
        </div>

        <div class="media wow fadeInUp" data-wow-delay="1.6s">
          <div class="media-object pull-left">
            <img src="images/gallery-img6.jpg" class="img-responsive" alt="Food Menu">
          </div>
          <div class="media-body">
            <h3 class="media-heading">Cycle</h3>
            <p>Rock with Music</p>
          </div>
        </div>
      </div>

    </div>
  </div>
</section>

<!-- Team section -->
<section id="coach" class="parallax-section">
  <div class="container">
    <div class="row">

      <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10" style="text-align: center;margin-top: 100px;">      
         	<div class="wow fadeInUp section-title" data-wow-delay="0.3s">
            <a href="<c:url value="/guide/trainerguide.jsp"/>"> <h2>Meet Our Coaches</h2></a>
            <h4>We Offer You Best Training Guide</h4>
       		 </div>
       	
      </div>

      <div class="clearfix"></div>

      <div class="col-md-3 col-sm-6 wow fadeInUp" data-wow-delay="0.4s">
          <div class="team-thumb">
            <img src="images/trainer1.jpg" class="img-responsive" alt="Team">  
                <div class="team-des">
                  <h3>Emma Stone</h3>
                  <h4>AEROBIC</h4>
                </div>
          </div>
      </div>

      <div class="col-md-3 col-sm-6 wow fadeInUp" data-wow-delay="0.6s">
          <div class="team-thumb">
            <img src="images/trainer2.jpg" class="img-responsive" alt="Team">  
              <div class="team-des">
                <h3>The Rock</h3>
                <h4>FITNESS</h4>
              </div>
          </div>
      </div>

      <div class="col-md-3 col-sm-6 wow fadeInUp" data-wow-delay="0.9s">
          <div class="team-thumb">
            <img src="images/trainer3.jpg" class="img-responsive" alt="Team">  
              <div class="team-des">
                <h3>佐佐木希</h3>
                <h4>CYCLE</h4>
              </div>
          </div>
      </div>

      <div class="col-md-3 col-sm-6 wow fadeInUp" data-wow-delay="0.9s">
          <div class="team-thumb">
            <img src="images/trainer4.jpg" class="img-responsive" alt="Team">  
              <div class="team-des">
                <h3>Hulk</h3>
                <h4>WEIGHT TRAINING</h4>
              </div>
          </div>
      </div>

      <div class="clearfix"></div>
      <div class="clearfix"></div>


    </div>
  </div>
</section>


<!-- Footer section -->
    <footer class="footer">
      <div class="container" id="footer-div">
      	<span>Contat Us</span>
      	<span>Site Map</span>
    	<span>Terms & Conditions</span>
    	<span>Privacy Policy</span>
        <span><i class="fa fa-copyright" aria-hidden="true"></i> 2017 Temmujin Fitness</span>
      </div>
    </footer>

<!-- Copyright section -->
<!-- <section id="copyright"> -->
<!--   <div class="container"> -->
<!--     <div class="row"> -->
<!--       <div class="col-md-8 col-sm-8 col-xs-8"> -->
<!--         <p>Copyright <i class="fa fa-copyright" aria-hidden="true"></i> 2017 Temmujin Fitness</p> -->
<!--       </div>   -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->
	
	<!-- javscript js -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.backstretch.min.js"></script>
	<script src="js/isotope.js"></script>
	<script src="js/imagesloaded.min.js"></script>
	<script src="js/nivo-lightbox.min.js"></script>
	<script src="js/jquery.flexslider-min.js"></script>
	<script src="js/jquery.parallax.js"></script>
	<script src="js/smoothscroll.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/custom.js"></script>
</body>
</html>