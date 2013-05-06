import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		DBCollection codedCol = stuffDb.getCollection("coded");
		DBObject document;
		
		document = codedCol.findOne();
		System.out.println("find one: " + document);
		
		document = codedCol.findOne(new BasicDBObject("counter",5));
		System.out.println("find counter: " + document);

		DBCursor cursor = codedCol.find();
		while(cursor.hasNext()) {
			document = cursor.next();
			System.out.println("cursor: " + document);
		}
		
		mongoClient.close();
	}
}
