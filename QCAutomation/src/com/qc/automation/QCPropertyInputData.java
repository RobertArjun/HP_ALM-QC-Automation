package com.qc.automation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class QCPropertyInputData {
	private static final Logger logger = Logger
			.getLogger(QCPropertyInputData.class);

	public static HashMap<String, String> getInputData(QCCommonData data) {
		logger.info("Inside getInputData method");
		 Properties prop = loadPropertyFile(data.COMMON_PROPERTY_LOCATION);
		 if(prop != null){
			 prop = loadPropertyFile(prop.getProperty("filter.location"));
			 if(prop !=null){
				 HashMap<String, String> readedValues = new HashMap<>();
					String projectName = prop.getProperty("project.name");
					data.setProjectNames(Arrays.asList(projectName.trim().split(",")));
					logger.debug("Project Name " + data.getProjectNames() + " Size "
							+ data.getProjectNames().size());
					for (int i = 0; i < data.getProjectNames().size(); i++) {
						System.out.println(data.getProjectNames().get(i));
						for (Enumeration<?> e = prop.propertyNames(); e
								.hasMoreElements();) {
							String key = (String) e.nextElement();
							if (key.startsWith(data.getProjectNames().get(i)
									.toLowerCase())) {
								logger.debug("i" + i + " " + key);
								readedValues.put(key, prop.getProperty(key));
							}
						}
					}
					return readedValues;
			 }
		 }
		return null;
	}

	public static Properties loadPropertyFile(String filename) {
		logger.info("Inside loadPropertyFile method");
		File file = new File(filename);
		Properties prop = new Properties();
		try (FileInputStream inputReader = new FileInputStream(file)) {
			prop.load(inputReader);
			return prop;
		} catch (Exception e) {
			logger.error("Incide Exception", e);
			return null;
		}

	}
}
