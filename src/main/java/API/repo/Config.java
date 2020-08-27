package API.repo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
@PropertySource("globalconf.properties")
public class Config {

	private String parttenDate;
	
	private String parttenTime;
	
	private String remoteurl;

	public String getParttenDate() {
		return parttenDate;
	}

	public void setParttenDate(String parttenDate) {
		this.parttenDate = parttenDate;
	}

	public String getParttenTime() {
		return parttenTime;
	}

	public void setParttenTime(String parttenTime) {
		this.parttenTime = parttenTime;
	}

	public String getRemoteurl() {
		return remoteurl;
	}

	public void setRemoteurl(String remoteurl) {
		this.remoteurl = remoteurl;
	}
}
