package com.qc.automation;

import org.apache.log4j.Logger;

public class QCConnectionV1 {
	private static final Logger logger = Logger.getLogger(QCConnectionV1.class);

	public void getConnection() {
		logger.debug("Entering Main Method .. ");
		QCInitalConnection initial = new QCInitalConnection();
		initial.doInit();
		logger.debug("Project Successfully Conneted..");
		initial.disconnect();
		logger.debug("Project Successfully DisConneted..");
	}
	
	  public static void main(String[] args) {
	 logger.debug("Entering Main Method .. "); QCInitalConnection initial= new
	 QCInitalConnection(); initial.doInit();
	 logger.debug("Project Successfully Conneted.."); initial.disconnect();
	 logger.debug("Project Successfully DisConneted.."); }
	 
}
