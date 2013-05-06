import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FinderBuildAndLimitsDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		DBCollection codedCol = stuffDb.getCollection("coded");
		DBObject document;
		
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
		builder.add("counter", 5);
		builder.add("looped", true);
		DBObject query = builder.get();

		document = codedCol.findOne(query);
		System.out.println("built query: " + query.toString());
		System.out.println("find built: " + document + "\n");

		
		document = codedCol.findOne(new BasicDBObject("counter",9));
		System.out.println("find counter: " + document + "\n");

		DBCursor cursor = codedCol.find().limit(4);
		while(cursor.hasNext()) {
			document = cursor.next();
			System.out.println("cursor: " + document);
		}
		
		mongoClient.close();
	}
}
