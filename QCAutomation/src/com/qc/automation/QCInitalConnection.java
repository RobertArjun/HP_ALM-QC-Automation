package com.qc.automation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import qca_clinet.ClassFactory;
import qca_clinet.IBug;
import qca_clinet.IBugFactory;
import qca_clinet.IList;
import qca_clinet.ITDConnection;
import qca_clinet.ITDFilter;

import com4j.Com4jObject;
import com4j.ComException;

public class QCInitalConnection {
	private static final Logger logger = Logger
			.getLogger(QCInitalConnection.class);
	private ITDConnection connection;
	private DateFormat format = new SimpleDateFormat(
			"E MMM dd HH:mm:ss zzz yyyy");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	QCCommonData commonData = new QCCommonData();
	Properties prop = null;

	public QCInitalConnection() {
		logger.info(">>>>>>>>>> Constructor Calling <<<<<<<<<<<");
		prop = QCPropertyInputData
				.loadPropertyFile(commonData.COMMON_PROPERTY_LOCATION);
		if (prop != null) {
			commonData.setDomain(prop.getProperty("domain").toString().trim());
			commonData.setPassword(prop.getProperty("password").toString()
					.trim());
			commonData.setUsername(prop.getProperty("username").toString()
					.trim());
			commonData
					.setProject(prop.getProperty("project").toString().trim());
			commonData.setUrl(prop.getProperty("url").toString().trim());
			logger.debug("Initial values added");
		} else {
			logger.debug("not loaded");
		}
	}

	public void doInit() {
		logger.info(">>>>>>>>>> Entering DoInit Method <<<<<<<<<<<");
		try {
			connection = ClassFactory.createTDConnection();
			connection.initConnectionEx(commonData.getUrl());
			logger.debug(connection.connected() + " Connected");
			connection.connectProjectEx(commonData.getDomain(),
					commonData.getProject(), commonData.getUsername(),
					commonData.getPassword());
			logger.debug(connection.connected() + " Logged In !!!");
			// Get the DB name
			logger.debug("Db Name " + connection.dbName());

			getBugList();
			// getBugDetailsByBugID();

		} catch (ComException e) {
			connection.disconnectProject();
			e.printStackTrace();
		}
	}

/*	private void getBugDetailsByBugID() {
		// find defect based on defect id
		IBugFactory bugfactory = connection.bugFactory().queryInterface(
				IBugFactory.class);
		IBug bug = bugfactory.item(454368).queryInterface(IBug.class);
		logger.debug(bug.assignedTo());

	}*/

	private void getBugList() {
		IBugFactory bugfactory = connection.bugFactory().queryInterface(
				IBugFactory.class);
		IList buglist = null;
		ITDFilter filter = null;
		List<QCBug> defects = null;
		Map<String, String> initialPropertyData = null;
		QCGenerateExcel generate = new QCGenerateExcel();
		try {
			initialPropertyData = QCPropertyInputData.getInputData(commonData);
			logger.debug("Inside getBugList >> initialPropertyData >> "
					+ initialPropertyData + "Size"
					+ commonData.getProjectNames().size());

			if (initialPropertyData != null) {
				defects = new ArrayList<>();
				int count = 0;
				for (int i = 0; i < commonData.getProjectNames().size(); i++) {
					Iterator<Map.Entry<String, String>> keyitr = initialPropertyData
							.entrySet().iterator();
					filter = bugfactory.filter()
							.queryInterface(ITDFilter.class);
					filter.filter(commonData.BG_PROJECT, commonData
							.getProjectNames().get(i).trim().toUpperCase()
							+ "*");
					while (keyitr.hasNext()) {
						Map.Entry<String, String> entry = keyitr.next();
						if (entry.getKey().startsWith(
								commonData.getProjectNames().get(i)
										.toLowerCase())) {
							if (entry.getKey().contains("status")) {
								filter.filter(commonData.BG_STATUS,
										entry.getValue());
								keyitr.remove();
							}
							if (entry.getKey().contains("assigned")) {
								filter.filter(commonData.BG_USER_25,
										entry.getValue());
								keyitr.remove();
							}

						}
					}

					buglist = filter.newList();
					Iterator itr = buglist.iterator();
					logger.debug(commonData
							.getProjectNames().get(i).trim().toUpperCase() + " Total Bug List :" + buglist.count());
					while (itr.hasNext()) {
						Com4jObject comobj = (Com4jObject) itr.next();
						IBug bug = comobj.queryInterface(IBug.class);
						count++;

						QCBug qcbug = null;
						qcbug = setTheBugValues(bug, qcbug, count);
						defects.add(qcbug);
					}
				}
				logger.debug("Calling Generate Excel method Calling >>");
				generate.createExcel(defects, prop);
			}

		} catch (Exception e) {
			e.printStackTrace();
			connection.disconnectProject();
		}

	}

	private QCBug setTheBugValues(IBug bug, QCBug qcbug, int count)
			throws ParseException {
		qcbug = new QCBug();
		qcbug.setNo(count);
		if (bug.field("BG_BUG_ID") != null) {
			qcbug.setDefectID(bug.field("BG_BUG_ID").toString());
		}
		if (bug.field("BG_USER_15") != null) {
			qcbug.setAssignedName(bug.field("BG_USER_15").toString());
		}
		if (bug.field("BG_USER_68") != null) {
			qcbug.setAssignedSystem(bug.field("BG_USER_68").toString());
		}
		if (bug.field("BG_RESPONSIBLE") != null) {
			qcbug.setAssingedTo(bug.field("BG_RESPONSIBLE").toString());
		}
		if (bug.field("BG_USER_14") != null) {
			qcbug.setOpenBy(bug.field("BG_USER_14").toString());
		}
		if (bug.field("BG_DETECTION_DATE") != null) {
			Date date = format
					.parse((bug.field("BG_DETECTION_DATE").toString()));
			qcbug.setOpenDate(formatter.format(date));
		}
		if (bug.field("BG_PROJECT") != null) {
			qcbug.setProjectName(bug.field("BG_PROJECT").toString());
		}
		if (bug.field("BG_SEVERITY") != null) {
			qcbug.setSevierity(bug.field("BG_SEVERITY").toString());
		}
		if (bug.field("BG_USER_33") != null) {
			qcbug.setTargetRelease(bug.field("BG_USER_33").toString());
		}
		if (bug.field("BG_USER_25") != null) {
			qcbug.setTeamAssigned(bug.field("BG_USER_25").toString());
		}
		if (bug.field("BG_DEV_COMMENTS") != null) {
			qcbug.setComments(bug.field("BG_DEV_COMMENTS").toString());
		}
		if (bug.field("BG_ENVIRONMENT") != null) {
			qcbug.setEnvironment(bug.field("BG_ENVIRONMENT").toString());
		}
		if (bug.field("BG_DESCRIPTION") != null) {
			// qcbug.setDescription(bug.field("BG_DESCRIPTION").toString());
			String dataDescription = bug.field("BG_DESCRIPTION").toString();
			dataDescription = dataDescription.replaceAll("<br>", "\n")
					.replaceAll("&nbsp;", " ").replaceAll("&amp;", "&");
			/*
			 * .replaceAll("&lt;", "<") .replaceAll("&gt;", ">");
			 */
			dataDescription = Jsoup.clean(dataDescription, Whitelist.none())
					.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
			qcbug.setDescription(dataDescription);
			// logger.debug(Jsoup.clean(bug.field("BG_DESCRIPTION").toString(),
			// Whitelist.none()));
		}
		return qcbug;
	}

	public void disconnect() {
		logger.debug(">>>>>>>>>> Entering DoInit Method <<<<<<<<<<<");
		connection.disconnectProject();
	}

}
