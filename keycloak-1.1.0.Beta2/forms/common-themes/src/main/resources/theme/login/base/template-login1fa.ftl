<#macro registrationLayout bodyClass="" displayInfo=false displayMessage=true>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="${properties.kcHtmlClass!}">

<head>
   	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="/auth/theme/admin/keycloak/css/jquery-ui.css" rel="stylesheet" />
    <script src="/auth/theme/admin/keycloak/js/MD5_obfuscated.js" type="text/javascript"></script>   
    <script src="/auth/theme/admin/keycloak/js/jsbn_obf.js" type="text/javascript"></script>
    <script src="/auth/theme/admin/keycloak/js/jsbn_obfuscated.js" type="text/javascript"></script>
    <script src="/auth/theme/admin/keycloak/js/rsa_obf.js" type="text/javascript"></script>
    <script src="/auth/theme/admin/keycloak/js/util_obf.js" type="text/javascript"></script>
    <script src="/auth/theme/admin/keycloak/js/DSSSCryptography.js" type="text/javascript"></script>
    <script src="/auth/theme/admin/keycloak/js/encryptBlock.js" type="text/javascript"></script> 
	<script src="/auth/theme/admin/keycloak/js/jquery.js"></script>
	<script src="/auth/theme/admin/keycloak/js/bootstrap.min.js"></script>
	<script src="/auth/theme/admin/keycloak/js/jquery.newsTicker.js"></script>
	<script src="/auth/theme/admin/keycloak/js/jquery-ui.js"></script>
	
	<style>
		.ui-dialog-osx {
		    -moz-border-radius: 0 0 8px 8px;
		    -webkit-border-radius: 0 0 8px 8px;
		    border-radius: 0 0 8px 8px; border-width: 0 8px 8px 8px;
		    position:fixed;
		    top: 50%;
		    left: 50%;
		    margin-top: -9em; /*set to a negative number 1/2 of your height*/
		    margin-left: -15em; /*set to a negative number 1/2 of your width*/
		}
	</style>
   
   	<script type="text/javascript">
		
		<#if needRedirectUrl?has_content>
			window.location.href = '${needRedirectUrl}';
		</#if>
		
		function forgetPasswordLink(){
			$.get('${url.forgotPasswordLink}', function(data, status){
				if(status == 'success'){
					console.log(data);
		        	window.location.href = data;
		        }else{
		        	alert(status);
		        }
		    });
		}
		
		function getModuleByName(name) {
			$.get("/auth/modules/"+name, function(data, status){
				if(status == 'success'){
					console.log(data);
					window.open(data, "_blank");
					//window.location.href = data;
			}else{
				console.log(status);
			}
		  });
		} 
		
	    	
		function show(){
			 $("#dialog-message").dialog({
			    modal: true,
			    draggable: false,
			    resizable: false,
			    position: ['center', 'top'],
			    show: 'blind',
			    hide: 'blind',
			    width: 400,
			    dialogClass: 'ui-dialog-osx',
			    buttons: {
			        "OK": function() {
			            $(this).dialog("close");
			            <#if forgetPasswordUrl?has_content>
	        				window.location.href = '${forgetPasswordUrl}';
	    				</#if>
			        }
			    }
			});   
		}
		
	</script>
   
    <#if properties.meta?has_content>
        <#list properties.meta?split(' ') as meta>
            <meta name="${meta?split('==')[0]}" content="${meta?split('==')[1]}"/>
        </#list>
    </#if>
    
    <title><#nested "title"></title>
    <link rel="icon" href="${url.resourcesPath}/img/favicon.ico" />
    <link href="${url.resourcesPath}/css/pse-style.css" rel="stylesheet" />
    <link href="${url.resourcesPath}/css/bootstrap.min.css" rel="stylesheet" />
    
    <#if properties.scripts?has_content>
        <#list properties.scripts?split(' ') as script>
            <script src="${url.resourcesPath}/${script}" type="text/javascript"></script>
            
        </#list>
    </#if>
    
</head>

<body class="login-page">
    <div class="container-fluid">
	<section class="navbar-sec">
	<nav class="navbar navbar-default">
	      <div class="container">
	      <div class="row">
	        <div class="navbar-header col-sm-4">
	           <a href="#" class="navbar-brand"></a>
	        </div>
	        <div class="pull-right ticker-box">
		        <ul class="nav navbar-nav  col-sm-12">
			        <li><h4 class="news_hd">NEWS FLASH</h4></li>
			        
			        <li>
			        	<ul class="newsticker" style="height: 60px; overflow: hidden;">
				    		<li style="margin-top: 0px;"><h6>Scheduled maintenance downtime for PRUONE and SFA (12 Jul 2015, 2300hrs to 12 Jul 2015, 0700hrs).</h6></li>
				    		<li style="margin-top: 0px;"><h6>PRUone v3.90 available now.</li>
				    		<!--<li style="margin-top: 0px;"><h6>Updates on Windows 10 support and compatibility.</h6>.</li>-->
			    		</ul>
			    	</li>
		        </ul> 
	        </div>
	        </div>
	      </div>
	    </nav>
	</section>

	 <section class="login-sec">
	 <div class="container">
	 <div class="row">
	  <div class="col-md-8 col-sm-6"></div>
	  <div class="col-md-4 col-sm-6">
	  <div class="cls-content-sm panel">
	   		
				<div class="panel-body">
				<p class="pad-btm"></p>
					
					<#nested "form">
					<#if displayMessage && message?has_content>
		                <div id="kc-feedback">
		                    <div id="kc-feedback-wrapper">
		                        <span>${message.summary}</span>
		                    </div>
		                </div>
		            </#if>
                    <div class="links_sec clearfix">
                    <div class="form-group clearfix">
                    <div class="col-md-6 col-sm-6">
                    <a class="link_btn" href="javascript:getModuleByName('FORGETPASSWORD');">&raquo; <span>FORGOT PASSWORD ?</span> </a>
                    </div>
                     <div class="col-md-6 col-sm-6"><a class="link_btn" href="javascript:getModuleByName('TNC');">&raquo; <span>TERMS &amp; CONDITIONS </span></a></div>
                    </div>
                    </div>
                       <div class="form-group clearfix">
                    <div class="col-md-6 col-sm-6"><a class="link_btn" href="javascript:getModuleByName('SECURITYNYOU');">&raquo; <span>SECURITY &amp; YOU</span> </a></div>
                     <div class="col-md-6 col-sm-6">
                     <a class="link_btn" href="javascript:getModuleByName('FAQ');">&raquo; <span>FAQ</span> </a>
                     </div>
                    </div>
                    </div>
				</div>
			</div>
	  </div>
	</div>
	 </div>
	</section>
	</div>
    <!--
        <p class="powered">
            <a href="http://www.keycloak.org">${rb.poweredByKeycloak}</a>
        </p>
    </div>
    -->
    
    <script type="text/javascript">
		$('.newsticker').newsTicker();
		// A $( document ).ready() block.
		$( document ).ready(function() {
		    console.log( "ready!" );
		    $('.newsticker').newsTicker();
		    <#if popMessage?has_content>
				show();
			</#if>	
		});
		</script>
		
		
	<div style="display:none" id="dialog-message" title="Important information">
    	<div style="margin-left: 23px;">
	        <p>
	            <#if popMessage?has_content>
	        		${popMessage}
	    		</#if>
	        </p>
        </div>
	</div>
</body>
</html>
</#macro>
