package com.jmp17.nosql.run;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jmp17.nosql.data.DataService;
import com.jmp17.nosql.data.MongoDataService;

public class SocialNetworkDemo {
	
	private DataService mongo = new MongoDataService();

	public static void main(String[] args) {
		Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);
		
		SocialNetworkDemo demo = new SocialNetworkDemo();
		demo.test();
	}
	
	public void test() {
//		mongo.fillDataStorage();
//		mongo.test();
		System.out.println("-------------------------------------");
		mongo.reportAvgMsg();
		System.out.println("-------------------------------------");
		mongo.reportMaxNewFriendshipsPerMonth();
		System.out.println("-------------------------------------");
		mongo.reportMinWatchedVideosByFriendlyUsers();

		System.out.println("-------------------------------------");
	}
}
