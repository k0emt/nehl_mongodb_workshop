import java.net.UnknownHostException;
import java.util.regex.Pattern;	// <<---- for RegEx

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindRegularExpressionsDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		DBCollection codedCol = stuffDb.getCollection("coded");
		DBObject document;
		
		DBObject query  = new BasicDBObject("java","powered");
		document = codedCol.findOne(query);
		System.out.println("find one bare: " + document + "\n");

		// Pattern code here

		document = codedCol.findOne(query);
		System.out.println("query: " + query.toString());
		System.out.println("find one first: " + document + "\n");

		// Pattern code here

		document = codedCol.findOne(query);
		System.out.println("query: " + query.toString());
		System.out.println("find one last: " + document);

		mongoClient.close();
	}
}
