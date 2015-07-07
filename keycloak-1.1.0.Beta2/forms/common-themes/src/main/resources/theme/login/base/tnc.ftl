<#import "template-tnc.ftl" as layout>
<@layout.registrationLayout; section>
    <#if section = "title">
        PSE-OTP
    <#elseif section = "form">
    	<div class="centre">
		<form id="kc-totp-login-form" action="${url.loginAction}" method="post">	
    		<input id="need_tnc" name="need_tnc" value="N" type="hidden" />
    		<input id="username" name="username" value="${login.username!''}" type="hidden" />
            <input id="password-token" name="password-token" value="${login.passwordToken!''}" type="hidden" />		
    		<input  name="login" style="width:75px" id="kc-login" type="button" onClick="submitForm('Y')" value="OK"/>
    		<input  name="login" style="width:75px" id="kc-login" type="button" onClick="submitForm('N')" value="Cancel"/>
		</form>
		<script>
			function submitForm(accept){
				$("#need_tnc").val(accept);
				$("#kc-totp-login-form").submit();
			}
		</script>
	</div>
    </#if>
</@layout.registrationLayout>