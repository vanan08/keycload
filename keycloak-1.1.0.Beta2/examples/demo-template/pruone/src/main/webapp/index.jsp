<%@page import="com.prudential.ModuleService"%>
<%@page import="org.keycloak.representations.idm.ModuleRepresentation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- Custom CSS -->
<link href="css/pse-style.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<%
	ModuleRepresentation reqmode = ModuleService.getModuleByApp("REPMODE");
	ModuleRepresentation salesmo = ModuleService.getModuleByApp("SALESMO");
	ModuleRepresentation sgsmode = ModuleService.getModuleByApp("SGSMODE");
	ModuleRepresentation pruraise = ModuleService.getModuleByApp("PRURAISE");
	ModuleRepresentation ifileclai = ModuleService.getModuleByApp("IFILECLAI");
	ModuleRepresentation idoc = ModuleService.getModuleByApp("IDOC");
	ModuleRepresentation iact = ModuleService.getModuleByApp("IACT");
	ModuleRepresentation pruinfo = ModuleService.getModuleByApp("PRUINFO");
	ModuleRepresentation qualityb = ModuleService.getModuleByApp("QUALITYB");
	ModuleRepresentation prugrad = ModuleService.getModuleByApp("PRUGRAD");
	ModuleRepresentation prucoac = ModuleService.getModuleByApp("PRUCOAC");
	ModuleRepresentation logout = ModuleService.getModuleByApp("LOGOUT");
	ModuleRepresentation eapprov = ModuleService.getModuleByApp("EAPPROV");
%>
</head>
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
        <li><a href="#">John Malkovich</a></li>
        <li><a href="<%=(logout == null ? "#" : logout.getFullpath()) %>">Logout</a></li>
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
   <div class="col-md-4 col-sm-4 col-xs-4">
   <a class="btn btn-default btn-banner" href="<%=(reqmode == null ? "#" : reqmode.getFullpath()) %>" >REP <span>MODE</span></a>
   </div>
    <div class="col-md-4 col-sm-4 col-xs-4">
   <a class="btn btn-default btn-banner" href="<%=(salesmo == null ? "#" : salesmo.getFullpath()) %>">SALES <span>MODE</span></a>
   </div> 
    <div class="col-md-4 col-sm-4 col-xs-4">
   <a class="btn btn-default btn-banner" href="<%=(sgsmode == null ? "#" : sgsmode.getFullpath()) %>">SQS <span>MODE</span></a>
   </div>
   </div>
   </div>
  </div>
   
  </div>
  <div class="col-md-6 col-sm-6">
  <div class="panel irequest banner-panel-height">
  
   
  </div>
  </div>
  </div>
  <div class="row">
    <div class="col-md-6 col-sm-6 banner-portion">
    <div class="panel big-ico-list">
    <div class="ico-por pruraise">
      
    </div>
    <div class="ico-details"><a class="" href="<%=(pruraise == null ? "#" : pruraise.getFullpath()) %>">
    <h4 class="ico-hd">PRU<span>RAISe</span></h4></a>
    <p>Your Enquiry portal for post<br/> sales activities</p>
    </div>
    </div>
    </div>
      <div class="col-md-6 col-sm-6">
      <div class="panel big-ico-list">
       <div class="ico-por eapproval">
    </div>
    <div class="ico-details">
    <a class="" href="<%=(eapprov == null ? "#" : eapprov.getFullpath()) %>"><h4 class="ico-hd">e<span>Approval</span></h4></a>
    <p>Find all your Outstanding<br/>  Approvals</p>
    </div>
    </div>
    </div>
</div>
    <div class="row">
    <div class="col-md-6 col-sm-6 banner-portion">
    <div class="panel isuite equal-height">
    <label class="label_name"><span>i</span>Suite</label>
    <div class="clearfix">
    <div class="isuite-listing">
    <div class="isuite-img ifileclaims">
    </div>
    <div class="isuite-details">
      <a class="" href="<%=(ifileclai == null ? "#" : ifileclai.getFullpath()) %>" ><h4 class="ico-hd-suite">i<span>FileClaims</span></h4></a>
    <p>Your Claim Transactions</p>
    </div>
    </div>
    </div>
    <div class="clearfix">
       <div class="isuite-listing">
    <div class="isuite-img idocs">
    </div>
    <div class="isuite-details">
    <a class="" href="<%=(idoc == null ? "#" : idoc.getFullpath()) %>" ><h4 class="ico-hd-suite">i<span>Doc</span></h4></a>
    <p>Your Document Management System</p>
    </div>
    </div>
   
    </div>
    <div class="clearfix">
    <div class="isuite-listing">
    <div class="isuite-img iacts">
    </div>
    <div class="isuite-details">
     <a class="iact-img" href="<%=(iact == null ? "#" : iact.getFullpath()) %>" > <img src="images/iact-texts.png"/></a>
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
    <li><a href="">Nullam lacinia nibh sit amet est luctus</a></li>
    <li><a href="">Nullam lacinia nibh sit amet est luctus</a></li>
    <li><a href="">Nullam lacinia nibh sit amet est luctus</a></li>
    <li><a href="">Nullam lacinia nibh sit amet est luctus</a></li>
    
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
     <a class="btn-footer pruinfo" href="<%=(pruinfo == null ? "#" : pruinfo.getFullpath()) %>">PRU<span>INFO</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer qualitybuisness" href="<%=(qualityb == null ? "#" : qualityb.getFullpath()) %>">QUALITY<span>BUISNESS</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer prugrade" href="<%=(prugrad == null ? "#" : prugrad.getFullpath()) %>">PRU<span>GRADE</span></a>
    </div>
      <div class="col-md-3 col-sm-3 col-xs-6">
     <a class="btn-footer prucoach" href="<%=(prucoac == null ? "#" : prucoac.getFullpath()) %>">PRU<span>COACH</span></a>
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