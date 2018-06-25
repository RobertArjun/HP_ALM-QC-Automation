package com.qc.automation;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class SheduledExeuteJobs extends TimerTask {
	private static final Logger logger = Logger
			.getLogger(SheduledExeuteJobs.class);
	private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;

	// private final static int ONE_DAY = 1;
	private final static int TWO_AM = 16;
	private final static int ZERO_MINUTES = 05;
	private final static int SECONDS = 00;

	public static void main(String args[]) {
		startTask();

	}
	
	@Override
	public void run() {
		QCConnectionV1 connection = new QCConnectionV1();
		connection.getConnection();
		logger.info("Successfully connected");
		System.exit(0);

	}

	private static Date getTomorrowMorning2AM() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, TWO_AM);
		cal.set(Calendar.MINUTE, ZERO_MINUTES);
		cal.set(Calendar.SECOND, SECONDS);
		return cal.getTime();
	}

	// call this method from your servlet init method
	public static void startTask() {
		SheduledExeuteJobs task = new SheduledExeuteJobs();
		Timer timer = new Timer();
		timer.schedule(task, getTomorrowMorning2AM(), ONCE_PER_DAY);
	}

	

}