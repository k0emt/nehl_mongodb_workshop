import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("XXXX");
		DB stuffDb = mongoClient.getDB("XXXXXX");
		DBCollection codedCol = stuffDb.getCollection("XXXXX");
		DBObject document;
		
		document = codedCol.X();
		System.out.println("find one: " + document);
		
		document = codedCol.X(new BasicDBObject("counter",5));
		System.out.println("find counter: " + document);

		DBCursor cursor = codedCol.X();
		while(cursor.hasNext()) {
			document = cursor.X();
			System.out.println("cursor: " + document);
		}
		
		mongoClient.X();
	}
}
