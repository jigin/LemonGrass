package com.asian5restaurant.lemongrass;

import android.app.Application;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes()
public class DataApplication extends Application {
	@Override
	public void onCreate() {
		// The following line triggers the initialization of ACRA
		super.onCreate();
		
		  ACRA.init(this);
		  ACRAReportSender reportSender = new ACRAReportSender("crashwebqua@gmail.com", "Vision2020");
		  ACRA.getErrorReporter().setReportSender(reportSender);
		 }
	

}
