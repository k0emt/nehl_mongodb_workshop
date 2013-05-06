import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RemoveDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		DBCollection codedCol = stuffDb.getCollection("coded");

		viewQuery(codedCol, "initial: ");
		
		// create matchQuery
		// remove document
		
		viewQuery(codedCol, "removed: ");
		System.out.println("done!");
		mongoClient.close();
	}

	private static void viewQuery(DBCollection codedCol, String label) {
		DBObject document;
		DBObject query = new BasicDBObject("updatedemo",true);
		DBCursor cursor = codedCol.find(query);
		while (cursor.hasNext()) {
			document = cursor.next();
			System.out.println(label + document);
		}
	}
}
