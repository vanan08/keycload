<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.security.Principal" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>

<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<title>PRU-Sales Enterprise</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/slider.css" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<!-- Custom CSS -->
<link href="css/pse-style.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
var stopTimeArray = new Array();
stopTimeArray = [15000, 15000, 15000, 15000];
var stopTime = -1;
$(function() {
	var $autoFun;
	var currentImg = 0;
	autoSlide(0);
 	var imgWrapper = $('.slideshow a img');
 	imgWrapper.hide().filter(':first').show();
	timeIndex = 0;

	$('ul.recentlist li a').click(function() {
	   if (this.className.indexOf('current') == -1) {
			imgWrapper.hide();
			imgWrapper.filter(this.hash).fadeIn(500);
			$('ul.recentlist li a').removeClass('current');
			$(this).addClass('current');
			currentImg = $('ul.recentlist li a').index(this);
			timeIndex = currentImg;
			 
			stopTime = stopTimeArray[timeIndex++];
		}
		
		return false;
	});

	$('ul.recentlist li a').mouseover(function() {
		   $('ul.recentlist li a').eq(this.innerHTML - 1).trigger('click');
	 });

	clearAutoRun($('.slideshow'));

	function clearAutoRun(elem){
		elem.hover(function(){
			clearAuto();
		}, function(){
             autoSlide(1);
           });
        }

	function autoSlide(from){
		if(currentImg == 4) {
  			currentImg = -1;
  			timeIndex = 0;
		}

		if(from != 1 && from != 0) {
			currentImg++;
		}
		$('ul.recentlist li a').eq(currentImg).trigger('click');
		
        if(stopTime == null || stopTime == undefined || stopTime == -1) {
        	stopTime = stopTimeArray[0];
        }
		$autoFun = setTimeout(autoSlide, stopTime);
	}
	function clearAuto(){
		clearTimeout($autoFun);
	}
    });
function showLink(link) {
    if(link == '' || link == 'null') {
        return;
    }
    window.open(link, "_blank"); 
}
</script>
</head>
<%
    Principal userPrincipal = null;
    String username = "";
    String displayName = "";
    if(request.getSession().getAttribute("picketlink.principal")!=null) {
         userPrincipal = (Principal)request.getSession().getAttribute("picketlink.principal");
         username=userPrincipal.getName();
    }

System.out.println("asdasdasdadasdasd");

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    try {
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/KeycloakDS");
        connection = dataSource.getConnection();

        String sql = "select first_name, last_name from user_entity where username=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1,username); 
        rs = statement.executeQuery();

        if(rs.next()) {
           displayName = rs.getString(1) + " " + rs.getString(2);
        }

        if(rs!=null) {
           rs.close();
           rs=null;
        } 
        if(statement!=null) {
           statement.close();
           statement=null;
        } 
    }
    catch(Exception e) {
       e.printStackTrace();
    }
    finally {
       if(rs!=null) {
           rs.close();
           rs=null;
        } 
        if(statement!=null) {
           statement.close();
           statement=null;
        } 
        if(connection!=null) {
          connection.close();
          connection=null;
        }
    }
%>
<body class="landing-page">
<div class="container-fluid">
<section class="navbar-sec">
<nav class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
           <a href="#" class="navbar-brand"></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><%=displayName %></a></li>
        <li><a href="#"> Last Accessed : 14-July-2015 12:30PM</a></li>
        <li><a href="?GLO=true">Logout</a></li>
      </ul>
      </div>
      </div>
    </nav>
</section>
<section class="banner-sec">
 <div class="container">
 <div class="row">
  <div class="col-md-6 col-sm-6 banner-portion">
  <div class="jumbotron banner-panel-height">
   <div class=" clearfix">
  <div class="col-md-6 col-sm-8 col-xs-12"><div class="inner-banner-pro">
   <h4 class="banner-hd">PRU<span>ONE</span></h4>
             <p>Your Point of Sales Toolkit. Access&nbsp;PRUONE from here.</p>
            
             </div></div>
   <div class="col-md-6 col-sm-4">
   
             </div>
      </div>
      <div class=" clearfix">
      <div class="btn-sec-banner">
   <div id="REPMODE" class="col-md-4 col-sm-4 col-xs-4">
   <a id="REPMODE-LINK" class="btn btn-default btn-banner" href="javascript:getModuleByName('REPMODE');" >REP <span>MODE</span></a>
   </div>
    <div id="SALESMODE" class="col-md-4 col-sm-4 col-xs-4">
   <a id="SALESMODE-LINK" class="btn btn-default btn-banner" href="javascript:getModuleByName('SALESMODE');">SALES <span>MODE</span></a>
   </div> 
    <div id="SGSMODE" class="col-md-4 col-sm-4 col-xs-4">
   <a id="SGSMODE-LINK" class="btn btn-default btn-banner" href="javascript:getModuleByName('SGSMODE');">SQS <span>MODE</span></a>
   </div>
   </div>
   </div>
  </div>
   
  </div>
  <div class="col-md-6 col-sm-6">

<div class="slideshow">
		<div>
		    <ul class="recentlist">
		    	<li><a class="current" href="#slide1">1</a></li>
		    
		        <li><a href="#slide2">2</a></li>
		    
		        <li><a href="#slide3">3</a></li>
		    
		        <li><a href="#slide4">4</a></li>
		    
		    </ul>
		</div>
		
		<a onclick="showLink('https://sfa-uact.prudential.com.sg/prusales/sfa/RedirectServlet?path=14\/PRUSelect Vantage Newsltr_Sept.pdf')" href="#"><img width="576" height="291"  id="slide1" src="https://sfa-uact.prudential.com.sg/prusales/sfa/common/jsp/pseimage.jsp?imageName=PSV Newsletter.jpg"/></a>
		
		<a onclick="showLink('http://s3.amazonaws.com/content.whispir.com/public/pacs/index.html')" href="#"><img width="576" height="291"  id="slide2" src="https://sfa-uact.prudential.com.sg/prusales/sfa/common/jsp/pseimage.jsp?imageName=PRUMessage v1.1.0.gif"/></a>
		
		<a onclick="showLink('http://www.prudential.com.sg/corp/prudential_en_sg/solutions/invest/PRUSelect.html')" href="#"><img width="576" height="291"  id="slide3" src="https://sfa-uact.prudential.com.sg/prusales/sfa/common/jsp/pseimage.jsp?imageName=PruSelect Videos.jpg"/></a>
		
		<a onclick="showLink('https://vsglifewurep02.pru.intranet.asia/ipa/sfa/index.html')" href="#"><img width="576" height="291"  id="slide4" src="https://sfa-uact.prudential.com.sg/prusales/sfa/common/jsp/pseimage.jsp?imageName=Laptop.JPG"/></a>
		
	</div>

  </div>
  </div>
  <div class="row">
    <div id="PRURAISE" class="col-md-6 col-sm-6 banner-portion">
    <div class="panel big-ico-list">
    <div id="PRURAISE-LINK" class="ico-por pruraise" onClick="getModuleByName('PRURAISE');">
      
    </div>
    <div class="ico-details"><a class="" href="#">
    <h4 class="ico-hd">PRU<span>RAISe</span></h4></a>
    <p>Your Enquiry portal for post<br/> sales activities</p>
    </div>
    </div>
    </div>
      <div id="EAPPROVAL"  class="col-md-6 col-sm-6">
      <div class="panel big-ico-list">
       <div id="EAPPROVAL-LINK" class="ico-por eapproval">
    </div>
    <div class="ico-details">
    <a class="" href="https://ssopoc.prudential.com.sg/sfa/"><h4 class="ico-hd">e<span>Approval</span></h4></a>
    <p>Find all your Outstanding<br/>  Approvals</p>
    </div>
    </div>
    </div>
</div>
    <div class="row">
    <div id="IFILECLAIMS" class="col-md-6 col-sm-6 banner-portion">
    <div class="panel isuite equal-height">
    <label class="label_name"><span>i</span>Suite</label>
    <div class="clearfix">
    <div class="isuite-listing">
    <div id="IFILECLAIMS-LINK" class="isuite-img ifileclaims" onclick="getModuleByName('IFILECLAIMS');">
    </div>
    <div class="isuite-details">
      <a class="" href="#" ><h4 class="ico-hd-suite">i<span>FileClaims</span></h4></a>
    <p>Your Claim Transactions</p>
    </div>
    </div>
    </div>
    <div id="IDOC" class="clearfix">
       <div class="isuite-listing">
    <div id="IDOC-LINK" class="isuite-img idocs" onclick="getModuleByName('IDOC');">
    </div>
    <div class="isuite-details">
    <a class="" href="#" ><h4 class="ico-hd-suite">i<span>Doc</span></h4></a>
    <p>Your Document Management System</p>
    </div>
    </div>
   
    </div>
    <div id="IACT" class="clearfix">
    <div class="isuite-listing">
    <div id="IACT-LINK" class="isuite-img iacts" onclick="getModuleByName('IACT');">
    </div>
    <div class="isuite-details">
     <a class="iact-img" href="#" > <img src="images/iact-texts.png"/></a>
    <p>Your To-Do List</p>
    </div>
    </div>
    </div>
    </div>
    </div>
     <div class="col-md-6 col-sm-6">
     <div class="panel nxt_award equal-height">
     <label class="label_name"><span>Useful</span>Links</label>
     <div class="clearfix usefullinks-area">
    <ul class="useful_links">
    <li><a href="">View Outstanding Proposal and Outstanding Mid-Term Addition</a></li>
    <li><a href="">Update My PIN</a></li>
    <li><a href="">Prudential Applications Downloads</a></li>
    <!--
    <li><a href="">Training - Courses, Registration and CPD > Courses available for Registration > Register Now</a></li>
    <li><a href="">Online Forms Cabinet (For Bankers only)</a></li>
    <li><a href="">PRUONE Service Desk</a></li>
    -->
    <li><a href="">PruLink Funds and Prudential Unit Trusts > PruLink Fund Prices and Performance Charts</a></li>
    <li><a href="">Prudential Corporate Website</a></li>
    </ul>
    <div class="clearfix">
    <div class="pull-right">
    <a class="btn btn-notice" href="#">Notices &amp; Downloads</a>
    </div>
    </div>
     </div>
    </div>
    </div>
    </div>
     
    </div>
</section>

<div  id="bottom-button">
 <div class="container-fluid">

<div class="row">
    
<div class="col-md-12 col-sm-12 banner-portion">

    <div class="btn-por">
    
    <a href="javascript:void(0);" class="btn double" id="view-more">View More</a>
    <div class="buttons-area clearfix">
    <div class="col-lg-8 col-md-10 col-sm-12 col-xs-12 col-centered">
     <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer pruinfo" href="javascript:getModuleByName('PRUINFO');">PRU<span>INFO</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer qualitybuisness" href="javascript:getModuleByName('QUALITYBUSINESS');">QUALITY<span>BUSINESS</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer prugrade" href="javascript:getModuleByName('PRUGRADE');">PRU<span>GRADE</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer prucoach" href="javascript:getModuleByName('PRUCOACH');">PRU<span>COACH</span></a>
    </div>
    </div>
    </div>
    
    </div>
   
   </div>
    
   </div>
    
    </div>

</div></div>

</div>
<!-- jQuery -->
<script src="js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
  <script src="js/charts/flot/jquery.flot.min.js"></script>
  <script src="js/charts/flot/jquery.flot.resize.js"></script>
  <script src="js/charts/flot/jquery.flot.orderBars.js"></script>
 <script src="js/jquery.matchHeight-min.js"></script>
  <script src="js/jquery.stickyfooter.js"></script>
 
 <script type="text/javascript">

function getModuleByName(name) {
	if($( "#"+name ).hasClass( "disable" )){
		return;
	}
	$.get("/auth/modules/"+name, function(data, status){
                //alert(data);
		if(status == 'success'){
			console.log(data);
			window.open(data, "_blank");
			//window.location.href = data;
	}else{
		console.log(status);
	}
  });
}

function disableModule(name, element_id) {
	 //Check REPMODE
    $.get("/auth/modules/"+name+"/info", function(data, status){
		if(status == 'success'){
			console.log(JSON.stringify(data));
			
			if(data.active){
				$("#"+element_id).show();
			}else{
				$("#"+element_id).hide();
			}
			
			var endDate = data.enddate;
			console.log("End Date: "+endDate);
		    var date = endDate.split("-"); 
		    console.log(date[0]);
		    console.log(date[1]);
		    console.log(date[2]);

		    var newEndate = new Date(date[0],date[1]-1,date[2]);//Date object
		    console.log(newEndate);
			    
		    var currentDate = new Date();

		    if (currentDate > newEndate) {
		        console.log('Disable '+name);
		        $("#"+element_id+"-LINK").addClass( "btn btn-default btn-banner disable" );
		    }
		    
		    
	}else{
		console.log(status);
	}
		
		
  });

}

//document ready
$( document ).ready(function() {
    console.log( "ready!" );
    var modules = ["REPMODE", "SALESMO", "SGSMODE", "PRURAISE"
                   , "EAPPROV", "IFILECLAI", "IDOC", "IACT"
                   , "PRUINFO", "QUALITYB", "PRUGRAD", "PRUCOAC"]; 
   
    $.each(modules, function( index, value ) {
    	disableModule(value);
    });
   
});

 </script>

    <script type="text/javascript">
	
	
	$(window).load(function() {
    $("#bottom-button").stickyFooter();
});

    </script>
    


  
  <script>
  
  $(function() {
    $('.equal-height').matchHeight({
    });
});
    $(function() {
    $('.banner-panel-height').matchHeight({
    });
});
  $(function(){
			 
			  var d2_1 = [
    [10, 15],
    [8, 20]
  ];

  var d2_2 = [
    [8, 10],
    [5, 15]
  ];

 
			 
			 
			  var data2 = [
    {
        label: "SPI OUTFALL : 24,158.91",
        data: d2_1,
        bars: {
            horizontal: true,
            show: true,
            fill: true,
            lineWidth: 0,
            order: 1,
            fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] }
        },
        color: "#ea6044"
    },
    {
        label: "API OUTFALL : 108 ",
        data: d2_2,
        bars: {
            horizontal: true,
            show: true,
            fill: true,
            lineWidth: 1,
            order: 2,
            fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] }
        },
        color: "#ffd479"
    }
  
  ];
			 
		  $("#flot-bar-h").length && $.plot($("#flot-bar-h"), data2, {
      xaxis: {
		  tickLength:0, 
		  ticks:false
          
      },
      yaxis: {
		  tickLength:0,
		  ticks: [
                        [10, "API"],
                        [15, "SPI"],
						[20, "AIPI"]
						
                    ]
		            
      },
      grid: {
          hoverable: true,
          clickable: false,
          borderWidth: 0
      },
      legend: {
          labelBoxBorderColor: "none",
          position: "left"
      },
      series: {
          shadowSize: 1
      }
     
  });	 
			 
			 });
  </script>
<script type="text/javascript">

$('#view-more').click(function() {
			$('.buttons-area').toggle();		
			if($('.btn-por').hasClass('toggle-up')){
				$('.btn-por').removeClass('toggle-up');
			}else{
			   $('.btn-por').addClass('toggle-up');	
			}
							   
});
</script>

</body>
</html>