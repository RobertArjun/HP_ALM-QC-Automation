package com.qc.automation;

import qca_clinet.ClassFactory;
import qca_clinet.ITDConnection;



public class QCConnection {
	public static void main(String[] args) {
		// QC url
		String url = "Your QC URL";
		// username for login
		String username = "User Name";
		// password for login
		String password = "Password";
		// domain
		String domain = "Domain Name";
		// project
		String project = "Project Name";
		try {
			System.out.println("Creating Connection Factory..");
			ITDConnection itdc = ClassFactory.createTDConnection();
			System.out.println("Creating Initial Connection..");
			itdc.initConnectionEx(url);
			itdc.connectProjectEx(domain, project, username, password);
			System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
