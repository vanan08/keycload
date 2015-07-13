<#import "template-totp.ftl" as layout>
<@layout.registrationLayout; section>
    <#if section = "title">
        PSE-OTP
    <#elseif section = "form">
    	<form id="kc-totp-login-form" action="${url.loginAction}" method="post">	
    		<#if mobile?has_content>
	    	    <input id="mobile" name="mobile" value="${mobile}" type="hidden" />
	   		</#if>
    		<input id="username" name="username" value="${login.username!''}" type="hidden" />
            <input id="password-token" name="password-token" value="${login.passwordToken!''}" type="hidden" />					
			<div class="form-group mar-btm15">
				<div class="input-group">
					<input type="password" id="totp" name="totp" placeholder="OTP" class="form-control">
				</div>
			</div>
				<input class="btn btn-danger btn-lg btn-block" name="login" id="kc-login" type="submit" value="SUBMIT"/>
		</form>
    </#if>
</@layout.registrationLayout>