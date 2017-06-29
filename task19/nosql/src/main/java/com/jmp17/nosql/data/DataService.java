package com.jmp17.nosql.data;

public interface DataService {
	
	void fillDataStorage();
	String test();
	
	//query #1
	void reportAvgMsg();
	//query #2
	void reportMaxNewFriendshipsPerMonth();
	//query #3
	void reportMinWatchedVideosByFriendlyUsers();
}
