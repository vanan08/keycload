package org.keycloak.events;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class Event {

    private long time;

    private EventType type;

    private String realmId;

    private String clientId;

    private String userId;

    private String sessionId;

    private String ipAddress;

    private String error;
    
    private String browserType;
    
    private String browserInfomation;

    private Timestamp loginDateTimepStamp;
    
    private Timestamp logoutDateTimepStamp;
    
    private Timestamp otpGenerateDateTime;
    
    private String otpGenerated;
    
    private Timestamp otpSendDateTime;
    
    private String otpReceived;
    
    private Timestamp optReceivedDateTime;

    private String successFlag;
    
    private String failReason;
    
    private Timestamp createdDate;
    
    private String updatedBy;
    
    private Timestamp updatedDate;

    
    
    private Map<String, String> details;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
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

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
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

	public Event clone() {
        Event clone = new Event();
        clone.time = time;
        clone.type = type;
        clone.realmId = realmId;
        clone.clientId = clientId;
        clone.userId = userId;
        clone.sessionId = sessionId;
        clone.ipAddress = ipAddress;
        clone.error = error;
        clone.details = details != null ? new HashMap<String, String>(details) : null;
        return clone;
    }

}
