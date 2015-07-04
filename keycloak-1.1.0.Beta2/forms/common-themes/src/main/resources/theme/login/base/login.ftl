<#import "template-login1fa.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section = "title">
        <#if client.application>
             ${rb.loginTitle} ${realm.name}
        <#elseif client.oauthClient>
             ${realm.name} ${rb.loginOauthTitle}
        </#if>
        
    <#elseif section = "header">
        <script src="/auth/theme/admin/keycloak/js/encryptBlock.js" type="text/javascript"></script>        
    <#elseif section = "form">
        <#if realm.password>
        	<form id="kc-form-login" action="${url.loginAction}" method="post">
	             <input type="hidden" name="randomKey" id="randomKey" value="${randomKey}"/>
	             <input type="hidden" name="randomKey" id="publicKey" value="${publicKey}"/>
	             <input type="hidden" name="exponent" id="exponent" value="${exponent}"/>
	             <input type="hidden" name="encryptedBlock" id="encryptedBlock" value=""/>
	        	<div class="form-group">
					<div class="input-group">
						<input id="username" name="username" value="${login.username!''}" type="text" autofocus placeholder="USERNAME" class="form-control">
					</div>
				</div>
				<div class="form-group mar-btm15">
					<div class="input-group">
						<input id="password" name="password" type="password" placeholder="PASSWORD" class="form-control">
					</div>
				</div>
				
        		<input class="btn btn-danger btn-lg btn-block" name="login" id="kc-login" type="submit" onclick="setBlock();"  value="LOGIN"/>
            </form>
        <#elseif realm.social>
            <div id="kc-social-providers">
                <ul>
                    <#list social.providers as p>
                        <li><a href="${p.loginUrl}" class="zocial ${p.id}"> <span class="text">${p.name}</span></a></li>
                    </#list>
                </ul>
            </div>
        </#if>
    <#elseif section = "info" >
        <#if realm.password && realm.registrationAllowed>
            <div id="kc-registration">
                <span>${rb.noAccount} <a href="${url.registrationUrl}">${rb.register}</a></span>
            </div>
        </#if>

        <#if realm.password && social.providers??>
            <div id="kc-social-providers">
                <ul>
                    <#list social.providers as p>
                        <li><a href="${p.loginUrl}" class="zocial ${p.id}"> <span class="text">${p.name}</span></a></li>
                    </#list>
                </ul>
            </div>
        </#if>
    </#if>
</@layout.registrationLayout>
