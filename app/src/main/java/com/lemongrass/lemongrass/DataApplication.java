package com.lemongrass.lemongrass;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(mailTo = "android@webqua.com",
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.crash)
public class DataApplication extends Application{


	@Override
	public void onCreate() {
		// The following line triggers the initialization of ACRA
		super.onCreate();
		
		  ACRA.init(this);/*
		  ACRAReportSender reportSender = new ACRAReportSender("lapomji@gmail.com", "lapo@5453");
		  ACRA.getErrorReporter().setReportSender(reportSender);*/
		 }
	

}
