package com.qc.automation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QCBug {
	private int no;
	private String projectName = "";
	private String defectID = "";
	private String assignedName = "";
	private String assingedTo = "";
	private String assignedSystem = "";
	private String teamAssigned = "";
	private String targetRelease = "";
	private String openBy = "";
	private String sevierity = "";
	private String openDate = null;
	private String environment = "";
	private String comments;
	private String description = "";
	private List<QCBug> qcBugList = new ArrayList<>();

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDefectID() {
		return defectID;
	}

	public void setDefectID(String defectID) {
		this.defectID = defectID;
	}

	public String getAssignedName() {
		return assignedName;
	}

	public void setAssignedName(String assignedName) {
		this.assignedName = assignedName;
	}

	public String getAssingedTo() {
		return assingedTo;
	}

	public void setAssingedTo(String assingedTo) {
		this.assingedTo = assingedTo;
	}

	public String getAssignedSystem() {
		return assignedSystem;
	}

	public void setAssignedSystem(String assignedSystem) {
		this.assignedSystem = assignedSystem;
	}

	public String getTeamAssigned() {
		return teamAssigned;
	}

	public void setTeamAssigned(String teamAssigned) {
		this.teamAssigned = teamAssigned;
	}

	public String getTargetRelease() {
		return targetRelease;
	}

	public void setTargetRelease(String targetRelease) {
		this.targetRelease = targetRelease;
	}

	public String getOpenBy() {
		return openBy;
	}

	public void setOpenBy(String openBy) {
		this.openBy = openBy;
	}

	public String getSevierity() {
		return sevierity;
	}

	public void setSevierity(String sevierity) {
		this.sevierity = sevierity;
	}

	public List<QCBug> getQcBugList() {
		return qcBugList;
	}

	public void setQcBugList(List<QCBug> qcBugList) {
		this.qcBugList = qcBugList;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
