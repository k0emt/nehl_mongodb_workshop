import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UpdateDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		DBCollection codedCol = stuffDb.getCollection("coded");

		DBObject document;
		for (int i = 0; i < 5; i++) {

			// create a document, use put and insert it into collection

		}
		viewQuery(codedCol, "initial: ");
		
		// create match query and update doc
		DBObject matchQuery = ;

		DBObject updateDoc = ;

		codedCol.update(matchQuery, updateDoc);	// <<-- thee update!

		viewQuery(codedCol, "single: ");
		
		matchQuery = new BasicDBObject("updatedemo",true);
		DBObject addedData = // get fancy with a $set!
		
		System.out.println("query: " + matchQuery);
		System.out.println("added: " + addedData);
		
		codedCol.update(matchQuery, addedData, false, true);
		viewQuery(codedCol, "added: ");
		
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
