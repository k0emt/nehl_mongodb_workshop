import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class RepSetWriting {
	static DBCollection testCol;
	static int SECONDS_TO_RUN = 60;

	public static void main(String[] args) throws UnknownHostException,
			InterruptedException {

		List<ServerAddress> repSetAddresses = new ArrayList<ServerAddress>();
		repSetAddresses.add(new ServerAddress("127.0.0.1", 27001));
		repSetAddresses.add(new ServerAddress("127.0.0.1", 27002));
		repSetAddresses.add(new ServerAddress("127.0.0.1", 27003));

		Mongo mongoClient;
		
		System.out.println("connecting to replica set");
		
		mongoClient = new MongoClient(repSetAddresses);
		// mongoClient.setWriteConcern(WriteConcern.MAJORITY);

		DB stuffDb = mongoClient.getDB("test");
		testCol = stuffDb.getCollection("testdata");

		System.out.println("have collection");

		for (int counter = 0; counter < SECONDS_TO_RUN; counter++) {
			System.out.print(counter + " ");
			insertDocument();
			Thread.sleep(1000);
		}
		
		mongoClient.close();
	}

	static void insertDocument() throws InterruptedException {
		boolean needToInsertDocument = true;
		ObjectId myObjectId = new ObjectId();
		WriteResult result;

		BasicDBObjectBuilder docBuilder = new BasicDBObjectBuilder();
		docBuilder.add("_id", myObjectId);
		docBuilder.add("ts", new Date());
		docBuilder.add("data", "stuff");

		while (needToInsertDocument) {

			try {
				result = testCol
						.insert(docBuilder.get(), WriteConcern.MAJORITY);
				System.out.println("result: " + result.toString());
				System.out.println("last error: "
						+ result.getLastError().toString() + "\n");
				needToInsertDocument = false;
			} catch (MongoException.Network men) {
				System.out.println("*** caught network exception!");
				Thread.sleep(2000);
			} catch (Exception ex) {
				System.out.println("*** caught exception: " + ex.getClass());
				Thread.sleep(2000);
			}

		}
	}
}
