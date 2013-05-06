/*
 * do you need to use -classpath with your javac and java calls?
 * 
 * javac InsertDemo.java -classpath .:./mongo-java-driver-2.11.1.jar
 * java -classpath .:./mongo-java-driver-2.11.1.jar InsertDemo
 */
import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertDemo {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost");
		DB stuffDb = mongoClient.getDB("workshop");
		
		DBCollection codedCol = stuffDb.getCollection("coded");

		// create and insert document here

		// check for doc with {"java":"powered"}

		for(int i=0; i < 30; i++) {
			// create and insert documents here
		}
		
		// check for docs with looped
		
		document = new BasicDBObject("myid",true);
		ObjectId myId = new ObjectId();
		document.put("_id", myId);
		System.out.println("my id: " + myId);
		codedCol.insert(document);
		// check for document with the given ObjectID
		
		mongoClient.close();
	}
	// now check the database :^)
}

