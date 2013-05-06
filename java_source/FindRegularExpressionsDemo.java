import java.net.UnknownHostException;
import java.util.regex.Pattern;

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

		Pattern pattern = Pattern.compile("^p");
		query  = new BasicDBObject("java",pattern);
		document = codedCol.findOne(query);
		System.out.println("query: " + query.toString());
		System.out.println("find one first: " + document + "\n");

		pattern = Pattern.compile("d$");
		query  = new BasicDBObject("java", pattern);
		document = codedCol.findOne(query);
		System.out.println("query: " + query.toString());
		System.out.println("find one last: " + document);

		mongoClient.close();
	}
}
