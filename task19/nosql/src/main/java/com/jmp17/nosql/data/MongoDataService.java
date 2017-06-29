package com.jmp17.nosql.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDataService implements DataService {

	@Override
	public void fillDataStorage() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("local");
		List<String> existing = new ArrayList<>();
		db.listCollectionNames().forEach((Block<String>) existing::add);
		
		Stream.of("Users", "Messages", "Friendship")
			.filter(s -> !existing.contains(s))
			.forEach(db::createCollection);
		
//		fillUsers(db.getCollection("Users"));
//		fillFriendships(db.getCollection("Friendship"));
//		fillMessages(db.getCollection("Messages"));
		
		client.close();
	}
	
	private void fillUsers(MongoCollection<Document> colle) {
		RandomDataGenerator r = new RandomDataGenerator();
		List<Document> list = new ArrayList<>(600_000);
		for (int i = 0; i < 1_000_001; i++) {
			long joined = System.currentTimeMillis() - 1000L * 3600L * r.nextLong(1L, 24L) * r.nextLong(1L, 365L);
			Date joinedDate = new Date(joined);
			Document doc = new Document()
					.append("name", "user" + i)
					.append("age", r.nextInt(15, 75))
					.append("joined", joinedDate)
					.append("friends", r.nextInt(0, 140));
			List<Document> vids = nextRandomVideos(r);
			if (!vids.isEmpty()) {
				doc.append("videos", vids);
			}
			list.add(doc);
			if (i % 50_000 == 0) {
				System.out.println(i);
				colle.insertMany(list);
				list.clear();
			}
		}
	}
	
	private List<Document> nextRandomVideos(RandomDataGenerator r) {
		int amount = r.nextInt(-25, 50);
		if (amount <= 0) {
			return new ArrayList<>();
		}
		List<Document> list = new ArrayList<>(amount);
		for (int i = 0; i < amount; i++) {
			Document vid = new Document("title", "vid_"+r.nextInt(0, 1_000_000)).append("watchCount", r.nextInt(1, 100));
			list.add(vid);
		}
		return list;
	}
	
	private void fillFriendships(MongoCollection<Document> colle) {
		RandomDataGenerator r = new RandomDataGenerator();
		List<Document> list = new ArrayList<>(600_000);
		for (int i = 0; i < 10_000_001; i++) {
			long sent = System.currentTimeMillis() - 1000L * 3600L * r.nextLong(1L, 24L) * r.nextLong(1L, 365L);
			Date sentDate = new Date(sent);
			Date replyDate = new Date(sent + 1000L * r.nextLong(1L, 100000L));
			Document doc = new Document()
					.append("sender", "user" + r.nextInt(1_000_000, 1_000_000_000))
					.append("recipient", "user" + r.nextInt(1_000_000, 1_000_000_000))
					.append("sent", sentDate)
					.append("replied", replyDate)
					.append("accepted", Boolean.valueOf(r.nextInt(0, 1) == 1));
			list.add(doc);
			if (i % 500_000 == 0) {
				System.out.println(i);
				colle.insertMany(list);
				list.clear();
			}
		}
		
		//friendly users
		list.clear();
		for (int i = 0; i < 10_001; i++) {
			for (int j = 0; j < 501; j++) {
				long sent = System.currentTimeMillis() - 1000L * 3600L * r.nextLong(1L, 24L) * r.nextLong(1L, 365L);
				Date sentDate = new Date(sent);
				Date replyDate = new Date(sent + 1000L * r.nextLong(1L, 100000L));
				Document doc = new Document()
						.append("sender", "user" + i)
						.append("recipient", "user" + (50_000 + j))
						.append("sent", sentDate)
						.append("replied", replyDate)
						.append("accepted", true);
				list.add(doc);
			}
			if (i % 500 == 0) {
				System.out.println(i);
				colle.insertMany(list);
				list.clear();
			}
		}
	}
	
	private void fillMessages(MongoCollection<Document> colle) {
		RandomDataGenerator r = new RandomDataGenerator();
		List<Document> list = new ArrayList<>(600_000);
		for (int i = 0; i < 10_000_001; i++) {
			long sent = System.currentTimeMillis() - 1000L * 3600L * r.nextLong(1L, 24L) * r.nextLong(1L, 365L);
			Date sentDate = new Date(sent);
			Document doc = new Document()
					.append("sender", "user" + r.nextInt(1, 1_000_000))
					.append("recipient", "user" + r.nextInt(1, 1_000_000))
					.append("sent", sentDate)
					.append("text", "msg_" + i);
			list.add(doc);
			if (i % 500_000 == 0) {
				System.out.println(i);
				colle.insertMany(list);
				list.clear();
			}
		}
	}

	@Override
	public String test() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("local");
		db.listCollectionNames().forEach((Block<String>) System.out::println);
		
		client.close();
		return null;
	}

	@Override
	public void reportAvgMsg() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("local");
		MongoCollection<Document> colleMsg = db.getCollection("Messages");
		
		Document groupSum = new Document("$group", 
				new Document()
				.append("_id", 
						new Document()
						.append("month", new Document("$month", "$sent"))
						.append("day", new Document("$dayOfMonth", "$sent"))
						.append("year", new Document("$year", "$sent"))
						.append("weekDay", new Document("$dayOfWeek", "$sent"))
				).append("msgPerDay", new Document("$sum", 1)));
		
		Document groupAvg = new Document("$group", 
				new Document()
				.append("_id", new Document("weekDay", "$_id.weekDay"))
				.append("avg", new Document("$avg", "$msgPerDay")));
		
		List<Document> pipeline = Arrays.asList(groupSum, groupAvg);
		AggregateIterable<Document> result = colleMsg.aggregate(pipeline);
		result.forEach((Block<Document>) System.out::println);
		
		client.close();
	}

	@Override
	public void reportMaxNewFriendshipsPerMonth() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("local");
		MongoCollection<Document> colleFrs = db.getCollection("Friendship");
		
		Document groupSum = new Document("$group", 
				new Document()
				.append("_id", 
						new Document()
						.append("month", new Document("$month", "$replied"))
						.append("year", new Document("$year", "$replied"))
				).append("friendshipsPerMonth", new Document("$sum", 1)));
		
		Document match = new Document("$match", new Document("accepted", true));
		Document sort = new Document("$sort", new Document("friendshipsPerMonth", -1));
		Document limit = new Document("$limit", 1);
		
		List<Document> pipeline = Arrays.asList(match, groupSum, sort, limit);
		AggregateIterable<Document> result = colleFrs.aggregate(pipeline).allowDiskUse(true);
		result.forEach((Block<Document>) System.out::println);
		
		client.close();
	}

	@Override
	public void reportMinWatchedVideosByFriendlyUsers() {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("local");
		MongoCollection<Document> colleUsr = db.getCollection("Users");
		
		Document match = new Document("$match", 
				new Document()
				.append("friends", new Document("$gt", 100))
				.append("videos", new Document("$exists", true))
		);
		
		Document unwind = new Document("$unwind", "$videos");
		
		Document group = new Document("$group", 
				new Document()
				.append("_id", "$name")
				.append("watch", new Document("$sum", "$videos.watchCount")));
		
		
		Document sort = new Document("$sort", new Document("watch", 1));
		Document limit = new Document("$limit", 1);
		
		List<Document> pipeline = Arrays.asList(match, unwind, group, sort, limit);
		AggregateIterable<Document> result = colleUsr.aggregate(pipeline);
		result.forEach((Block<Document>) System.out::println);
		
		client.close();
	}

}
