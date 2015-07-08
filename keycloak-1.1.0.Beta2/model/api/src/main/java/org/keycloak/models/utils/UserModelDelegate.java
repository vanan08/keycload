package org.keycloak.models.utils;

import org.keycloak.models.ApplicationModel;
import org.keycloak.models.CustomUserModel;
import org.keycloak.models.ModuleModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserCredentialValueModel;
import org.keycloak.models.UserModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class UserModelDelegate implements UserModel {
    protected UserModel delegate;

    public UserModelDelegate(UserModel delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public String getUsername() {
        return delegate.getUsername();
    }

    @Override
    public void setUsername(String username) {
        delegate.setUsername(username);
    }

    @Override
    public boolean isEnabled() {
        return delegate.isEnabled();
    }

    @Override
    public boolean isTotp() {
        return delegate.isTotp();
    }

    @Override
    public void setEnabled(boolean enabled) {
        delegate.setEnabled(enabled);
    }

    @Override
    public void setAttribute(String name, String value) {
        delegate.setAttribute(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        delegate.removeAttribute(name);
    }

    @Override
    public String getAttribute(String name) {
        return delegate.getAttribute(name);
    }

    @Override
    public Map<String, String> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Set<RequiredAction> getRequiredActions() {
        return delegate.getRequiredActions();
    }

    @Override
    public void addRequiredAction(RequiredAction action) {
        delegate.addRequiredAction(action);
    }

    @Override
    public void removeRequiredAction(RequiredAction action) {
        delegate.removeRequiredAction(action);
    }

    @Override
    public String getFirstName() {
        return delegate.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        delegate.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return delegate.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        delegate.setLastName(lastName);
    }

    @Override
    public String getEmail() {
        return delegate.getEmail();
    }

    @Override
    public void setEmail(String email) {
        delegate.setEmail(email);
    }

    @Override
    public boolean isEmailVerified() {
        return delegate.isEmailVerified();
    }

    @Override
    public void setEmailVerified(boolean verified) {
        delegate.setEmailVerified(verified);
    }

    @Override
    public void setTotp(boolean totp) {
        delegate.setTotp(totp);
    }

    @Override
    public void updateCredential(UserCredentialModel cred) {
        delegate.updateCredential(cred);
    }

    @Override
    public List<UserCredentialValueModel> getCredentialsDirectly() {
        return delegate.getCredentialsDirectly();
    }

    @Override
    public void updateCredentialDirectly(UserCredentialValueModel cred) {
        delegate.updateCredentialDirectly(cred);
    }

    @Override
    public Set<RoleModel> getRealmRoleMappings() {
        return delegate.getRealmRoleMappings();
    }

    @Override
    public Set<RoleModel> getApplicationRoleMappings(ApplicationModel app) {
        return delegate.getApplicationRoleMappings(app);
    }

    @Override
    public boolean hasRole(RoleModel role) {
        return delegate.hasRole(role);
    }

    @Override
    public void grantRole(RoleModel role) {
        delegate.grantRole(role);
    }

    @Override
    public Set<RoleModel> getRoleMappings() {
        return delegate.getRoleMappings();
    }

    @Override
    public void deleteRoleMapping(RoleModel role) {
        delegate.deleteRoleMapping(role);
    }

    @Override
    public String getFederationLink() {
        return delegate.getFederationLink();
    }

    @Override
    public void setFederationLink(String link) {
        delegate.setFederationLink(link);
    }

	@Override
	public Set<RoleModel> getModuleRoleMappings(ModuleModel module) {
		return delegate.getModuleRoleMappings(module);
	}

	@Override
	public String getMobile() {
		return delegate.getMobile();
	}

	@Override
	public void setMobile(String mobile) {
		delegate.setMobile(mobile);
	}

	@Override
	public List<CustomUserModel> getCustomUsers() {
		return delegate.getCustomUsers();
	}

	@Override
	public void addCustomUser(CustomUserModel customUserModel) {
		delegate.addCustomUser(customUserModel);
	}

	@Override
	public void updateCustomUser(CustomUserModel customUserModel) {
		delegate.updateCustomUser(customUserModel);
	}
	
	@Override
	public String getCustomUserTypeId() {
		return delegate.getCustomUserTypeId();
	}

	@Override
	public void setCustomUserTypeId(String customUserTypeId) {
		delegate.setCustomUserTypeId(customUserTypeId);	
	}

	@Override
	public String getCustomUserSubTypeId() {
		return delegate.getCustomUserSubTypeId();
	}

	@Override
	public void setCustomUserSubTypeId(String customUserSubTypeId) {
		delegate.setCustomUserSubTypeId(customUserSubTypeId);
	}

	@Override
	public String getNeed2FA() {
		return delegate.getNeed2FA();
	}

	@Override
	public void setNeed2FA(String need2fa) {
		delegate.setNeed2FA(need2fa);
	}

	@Override
	public String getNeedTNC() {
		return delegate.getNeedTNC();
	}

	@Override
	public void setNeedTNC(String needTNC) {
		delegate.setNeedTNC(needTNC);		
	}

	@Override
	public String getAccountStatus() {
		return delegate.getAccountStatus();
	}

	@Override
	public void setAccountStatus(String accountStatus) {
		delegate.setAccountStatus(accountStatus);
	}

	@Override
	public String getAgentCode() {
		return delegate.getAgentCode();
	}

	@Override
	public void setAgentCode(String agentCode) {
		delegate.setAgentCode(agentCode);
	}

	@Override
	public String getAgency() {
		return delegate.getAgency();
	}

	@Override
	public void setAgency(String agency) {
		delegate.setAgency(agency);
	}
}
