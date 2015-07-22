package org.keycloak.events;

import org.jboss.logging.Logger;
import org.keycloak.models.ClientModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserSessionModel;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class EventBuilder {

    private static final Logger log = Logger.getLogger(EventBuilder.class);

    private List<EventListenerProvider> listeners;
    private Event event;

    public EventBuilder(List<EventListenerProvider> listeners, RealmModel realm, String ipAddress) {
        this.listeners = listeners;
        this.event = new Event();

        realm(realm);
        ipAddress(ipAddress);
    }

    EventBuilder() {
    }

    public EventBuilder realm(RealmModel realm) {
        event.setRealmId(realm.getId());
        return this;
    }

    public EventBuilder realm(String realmId) {
        event.setRealmId(realmId);
        return this;
    }

    public EventBuilder client(ClientModel client) {
        event.setClientId(client.getClientId());
        return this;
    }

    public EventBuilder client(String clientId) {
        event.setClientId(clientId);
        return this;
    }

    public EventBuilder user(UserModel user) {
        event.setUserId(user.getId());
        return this;
    }

    public EventBuilder user(String userId) {
        event.setUserId(userId);
        return this;
    }

    public EventBuilder session(UserSessionModel session) {
        event.setSessionId(session.getId());
        return this;
    }

    public EventBuilder session(String sessionId) {
        event.setSessionId(sessionId);
        return this;
    }

    public EventBuilder ipAddress(String ipAddress) {
        event.setIpAddress(ipAddress);
        return this;
    }

    public EventBuilder event(EventType e) {
        event.setType(e);
        return this;
    }
    

    public EventBuilder browserType(String browserType) {
    	event.setBrowserType(browserType);
        return this;
	}


	public EventBuilder browserInfomation(String browserInfomation) {
		event.setBrowserInfomation(browserInfomation);
        return this;
	}

	
	public EventBuilder loginDateTimepStamp(Timestamp loginDateTimepStamp) {
		event.setLoginDateTimepStamp(loginDateTimepStamp);
        return this;
	}

	
	public EventBuilder logoutDateTimepStamp(Timestamp logoutDateTimepStamp) {
		event.setLogoutDateTimepStamp(logoutDateTimepStamp);
        return this;
	}


	public EventBuilder otpGenerateDateTime(Timestamp otpGenerateDateTime) {
		event.setOtpGenerateDateTime(otpGenerateDateTime);
        return this;
	}


	public EventBuilder otpGenerated(String otpGenerated) {
		event.setOtpGenerated(otpGenerated);
        return this;
	}

	
	public EventBuilder otpSendDateTime(Timestamp otpSendDateTime) {
		event.setOtpSendDateTime(otpSendDateTime);
        return this;
	}


	public EventBuilder otpReceived(String otpReceived) {
		event.setOtpReceived(otpReceived);
        return this;
	}

	
	public EventBuilder optReceivedDateTime(Timestamp optReceivedDateTime) {
		event.setOptReceivedDateTime(optReceivedDateTime);
        return this;
	}

	public EventBuilder successFlag(String successFlag) {
		event.setSuccessFlag(successFlag);
        return this;
	}

	public EventBuilder failReason(String failReason) {
		event.setFailReason(failReason);
        return this;
	}

	public EventBuilder createdDate(Timestamp createdDate) {
		event.setCreatedDate(createdDate);
        return this;
	}

	public EventBuilder updatedBy(String updatedBy) {
		event.setUpdatedBy(updatedBy);
        return this;
	}

	public EventBuilder updatedDate(Timestamp updatedDate) {
		event.setUpdatedDate(updatedDate);
        return this;
	}

    public EventBuilder detail(String key, String value) {
        if (value == null || value.equals("")) {
            return this;
        }

        if (event.getDetails() == null) {
            event.setDetails(new HashMap<String, String>());
        }
        event.getDetails().put(key, value);
        return this;
    }

    public EventBuilder removeDetail(String key) {
        if (event.getDetails() != null) {
            event.getDetails().remove(key);
        }
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public void success() {
        send();
    }

    public void error(String error) {
        event.setType(EventType.valueOf(event.getType().name() + "_ERROR"));
        event.setError(error);
        send();
    }

    public EventBuilder clone() {
        EventBuilder clone = new EventBuilder();
        clone.listeners = listeners;
        clone.event = event.clone();
        return clone;
    }

    public EventBuilder reset() {
        Event old = event;

        event = new Event();
        event.setRealmId(old.getRealmId());
        event.setIpAddress(old.getIpAddress());
        event.setClientId(old.getClientId());
        event.setUserId(old.getUserId());

        return this;
    }

    private void send() {
        event.setTime(System.currentTimeMillis());
        event.setCreatedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        if (listeners != null) {
            for (EventListenerProvider l : listeners) {
                try {
                    l.onEvent(event);
                } catch (Throwable t) {
                    log.error("Failed to send type to " + l, t);
                }
            }
        }
    }

}
