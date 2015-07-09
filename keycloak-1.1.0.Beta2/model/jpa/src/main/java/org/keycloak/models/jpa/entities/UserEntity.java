package org.keycloak.models.jpa.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.keycloak.models.utils.KeycloakModelUtils;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@NamedQueries({
        @NamedQuery(name="getAllUsersByRealm", query="select u from UserEntity u where u.realmId = :realmId order by u.username"),
        @NamedQuery(name="searchForUser", query="select u from UserEntity u where u.realmId = :realmId and ( lower(u.username) like :search or lower(concat(u.firstName, ' ', u.lastName)) like :search or u.email like :search ) order by u.username"),
        @NamedQuery(name="getRealmUserById", query="select u from UserEntity u where u.id = :id and u.realmId = :realmId"),
        @NamedQuery(name="getUserByUsername", query="select u from UserEntity u where u.username = :username"),
        @NamedQuery(name="getRealmUserByUsername", query="select u from UserEntity u where u.username = :username and u.realmId = :realmId"),
        @NamedQuery(name="getRealmUserByEmail", query="select u from UserEntity u where u.email = :email and u.realmId = :realmId"),
        @NamedQuery(name="getRealmUserByLastName", query="select u from UserEntity u where u.lastName = :lastName and u.realmId = :realmId"),
        @NamedQuery(name="getRealmUserByFirstLastName", query="select u from UserEntity u where u.firstName = :first and u.lastName = :last and u.realmId = :realmId"),
        @NamedQuery(name="getRealmUserCount", query="select count(u) from UserEntity u where u.realmId = :realmId"),
        @NamedQuery(name="deleteUsersByRealm", query="delete from UserEntity u where u.realmId = :realmId"),
        @NamedQuery(name="deleteUsersByRealmAndLink", query="delete from UserEntity u where u.realmId = :realmId and u.federationLink=:link")
})
@Entity
@Table(name="USER_ENTITY", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "REALM_ID", "USERNAME" }),
        @UniqueConstraint(columnNames = { "REALM_ID", "EMAIL_CONSTRAINT" })
})
public class UserEntity {
	@Id
	@Column(name = "ID", length = 36)
	protected String id;

	@Column(name = "USERNAME")
	protected String username;
	
	@Column(name = "FIRST_NAME")
	protected String firstName;
	
	@Column(name = "LAST_NAME")
	protected String lastName;
	
	@Column(name = "EMAIL")
	protected String email;
	
	@Column(name = "ENABLED")
	protected boolean enabled;
	
	@Column(name = "TOTP")
	protected boolean totp;
	
	@Column(name = "EMAIL_VERIFIED")
	protected boolean emailVerified;

	@Column(name = "MOBILE")
	protected String mobile;

	@Column(name = "CUSTOM_USER_TYPE_ID", length = 36)
	protected String customUserTypeId;

	@Column(name = "CUSTOM_USER_SUBTYPE_ID", length = 36)
	protected String customUserSubTypeId;

	@Column(name = "NEED2FA")
	protected String need2FA;

	@Column(name = "NEEDTNC")
	protected String needTNC;

	@Column(name = "ACCOUNT_STATUS", length = 10)
	protected String accountStatus;

	@Column(name = "AGENT_CODE", length = 20)
	protected String agentCode;

	@Column(name = "AGENCY", length = 20)
	protected String agency;

	// Hack just to workaround the fact that on MS-SQL you can't have unique
	// constraint with multiple NULL values TODO: Find better solution (like
	// unique index with 'where' but that's proprietary)
	@Column(name = "EMAIL_CONSTRAINT")
	protected String emailConstraint = KeycloakModelUtils.generateId();

	@Column(name = "REALM_ID")
	protected String realmId;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
	protected Collection<UserAttributeEntity> attributes = new ArrayList<UserAttributeEntity>();

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user")
	protected Collection<UserRequiredActionEntity> requiredActions = new ArrayList<UserRequiredActionEntity>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy="user")
    protected Collection<CredentialEntity> credentials = new ArrayList<CredentialEntity>();
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy="user")
    protected Collection<CustomUserEntity> customUsers = new ArrayList<CustomUserEntity>();

	@Column(name = "federation_link")
	protected String federationLink;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
        this.email = email;
        this.emailConstraint = email != null ? email : KeycloakModelUtils.generateId();
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmailConstraint() {
		return emailConstraint;
	}

	public void setEmailConstraint(String emailConstraint) {
		this.emailConstraint = emailConstraint;
	}

	public boolean isTotp() {
		return totp;
	}

	public void setTotp(boolean totp) {
		this.totp = totp;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Collection<UserAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(Collection<UserAttributeEntity> attributes) {
		this.attributes = attributes;
	}

	public Collection<UserRequiredActionEntity> getRequiredActions() {
		return requiredActions;
	}

    public void setRequiredActions(Collection<UserRequiredActionEntity> requiredActions) {
        this.requiredActions = requiredActions;
    }

	public String getRealmId() {
		return realmId;
	}

	public void setRealmId(String realmId) {
		this.realmId = realmId;
	}

	public Collection<CredentialEntity> getCredentials() {
		return credentials;
	}

	public void setCredentials(Collection<CredentialEntity> credentials) {
		this.credentials = credentials;
	}

	public String getFederationLink() {
		return federationLink;
	}

	public void setFederationLink(String federationLink) {
		this.federationLink = federationLink;
	}
	
	public Collection<CustomUserEntity> getCustomUsers() {
		return customUsers;
	}
    
    public void setCustomUsers(Collection<CustomUserEntity> customUsers) {
		this.customUsers = customUsers;
	}

	public String getCustomUserTypeId() {
		return customUserTypeId;
	}

	public void setCustomUserTypeId(String customUserTypeId) {
		this.customUserTypeId = customUserTypeId;
	}

	public String getCustomUserSubTypeId() {
		return customUserSubTypeId;
	}

	public void setCustomUserSubTypeId(String customUserSubTypeId) {
		this.customUserSubTypeId = customUserSubTypeId;
	}

	public String getNeed2FA() {
		return need2FA;
	}

	public void setNeed2FA(String need2fa) {
		need2FA = need2fa;
	}

	public String getNeedTNC() {
		return needTNC;
	}

	public void setNeedTNC(String needTNC) {
		this.needTNC = needTNC;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

}
