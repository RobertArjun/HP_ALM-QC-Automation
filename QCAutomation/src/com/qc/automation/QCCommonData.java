package com.qc.automation;

import java.util.ArrayList;
import java.util.List;

public class QCCommonData {
	private String username = "";
	private String password = "";
	private String domain = "";
	private String project = "";
	private String url = "";
	
	private List<String> projectNames = new ArrayList<>();
	public final String BG_PROJECT = "BG_PROJECT"; //Assigned to Version
	public final String BG_STATUS = "BG_STATUS";
	public final String BG_BUG_ID= "BG_BUG_ID";
	public final String BG_DESCRIPTION = "BG_DESCRIPTION";
	public final String BG_SEVERITY = "BG_SEVERITY";
	public final String BG_USER_15= "BG_USER_15"; //Assigned Name
	public final String BG_RESPONSIBLE= "BG_RESPONSIBLE"; //Assigned To
	public final String BG_USER_68 = "BG_USER_68"; //Assigned to System
	public final String BG_ATTACHMENT = "BG_ATTACHMENT";
	public final String BG_DETECTION_DATE = "BG_DETECTION_DATE"; //Date Opened
	public final String BG_USER_08 = "BG_USER_08"; //Defect Type
	public final String BG_USER_25 = "BG_USER_25"; //Team Assigned
	public final String BG_SUBJECT = "BG_SUBJECT";
	public final String BG_USER_33 = "BG_USER_33"; //Target Release
	public final String BG_TARGET_REL = "BG_TARGET_REL"; //Target Release
	public final String BG_USER_14 = "BG_USER_14"; //Opened by Name
	public final String BG_USER_24 = "BG_USER_24"; //Opened by Team
	public final String BG_ENVIRONMENT = "BG_ENVIRONMENT"; //Detected on Environment
	public final String BG_DEV_COMMENTS = "BG_DEV_COMMENTS"; //Comments
	public final String COMMON_PROPERTY_LOCATION = "C:/Users/RV5035825/Desktop/QC_Defect/Filter/common.properties"; //Comments
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getProject() {
		return project;
	}

	public List<String> getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(List<String> projectNames) {
		this.projectNames = projectNames;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setProject(String project) {
		this.project = project;
	}

	/*public void setProject(String project) {
		this.project = project;
	}

	public static String getUrl() {
		return URL;
	}*/
	

}
