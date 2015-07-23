package org.keycloak.events.jpa;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
@Entity
@Table(name="EVENT_ENTITY")
public class EventEntity {

    @Id
    @Column(name="ID", length = 36)
    private String id;

    
    @Column(name="TIME")
    private long time;

    @Column(name="TYPE")
    private String type;

    @Column(name="REALM_ID")
    private String realmId;

    @Column(name="CLIENT_ID")
    private String clientId;

    @Column(name="USER_ID", length = 36)
    private String userId;

    @Column(name="SESSION_ID", length = 255)
    private String sessionId;

    @Column(name="IP_ADDRESS")
    private String ipAddress;

    @Column(name="ERROR")
    private String error;
    
    @Column(name="BROWSER_TYPE", length = 50)
    private String browserType;
    
    @Column(name="BROWSER_INFORMATION", length = 200)
    private String browserInfomation;

    @Column(name="LOGIN_DATE_TIMP_STAMP")
    private Timestamp loginDateTimepStamp;
    
    @Column(name="LOGOUT_DATE_TIMP_STAMP")
    private Timestamp logoutDateTimepStamp;
    
    @Column(name="OTP_GENERATED_DATE_TIME")
    private Timestamp otpGenerateDateTime;
    
    @Column(name="OTP_GENERATED", length = 200)
    private String otpGenerated;
    
    @Column(name="OTP_SEND_DATE_TIME")
    private Timestamp otpSendDateTime;
    
    @Column(name="RESULT_CODE", length = 200)
    private String otpReceived;
    
    @Column(name="RESULT_CODE_DATE_TIME")
    private Timestamp optReceivedDateTime;

    @Column(name="SUCCESS_FLAG", length = 1)
    private String successFlag;
    
    @Column(name="FAIL_REASON", length = 1)
    private String failReason;
    
    @Column(name="CREATED_DATE")
    private Timestamp createdDate;
    
    @Column(name="UPDATED_BY", length = 36)
    private String updatedBy;
    
    @Column(name="UPDATED_DATE")
    private Timestamp updatedDate;
   
    @Column(name="DETAILS_JSON", length = 2550)
    private String detailsJson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetailsJson() {
        return detailsJson;
    }

    public void setDetailsJson(String detailsJson) {
        this.detailsJson = detailsJson;
    }

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getBrowserInfomation() {
		return browserInfomation;
	}

	public void setBrowserInfomation(String browserInfomation) {
		this.browserInfomation = browserInfomation;
	}

	public Timestamp getLoginDateTimepStamp() {
		return loginDateTimepStamp;
	}

	public void setLoginDateTimepStamp(Timestamp loginDateTimepStamp) {
		this.loginDateTimepStamp = loginDateTimepStamp;
	}

	public Timestamp getLogoutDateTimepStamp() {
		return logoutDateTimepStamp;
	}

	public void setLogoutDateTimepStamp(Timestamp logoutDateTimepStamp) {
		this.logoutDateTimepStamp = logoutDateTimepStamp;
	}

	public Timestamp getOtpGenerateDateTime() {
		return otpGenerateDateTime;
	}

	public void setOtpGenerateDateTime(Timestamp otpGenerateDateTime) {
		this.otpGenerateDateTime = otpGenerateDateTime;
	}

	public String getOtpGenerated() {
		return otpGenerated;
	}

	public void setOtpGenerated(String otpGenerated) {
		this.otpGenerated = otpGenerated;
	}

	public Timestamp getOtpSendDateTime() {
		return otpSendDateTime;
	}

	public void setOtpSendDateTime(Timestamp otpSendDateTime) {
		this.otpSendDateTime = otpSendDateTime;
	}

	public String getOtpReceived() {
		return otpReceived;
	}

	public void setOtpReceived(String otpReceived) {
		this.otpReceived = otpReceived;
	}

	public Timestamp getOptReceivedDateTime() {
		return optReceivedDateTime;
	}

	public void setOptReceivedDateTime(Timestamp optReceivedDateTime) {
		this.optReceivedDateTime = optReceivedDateTime;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}
