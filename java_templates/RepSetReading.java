import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

public class RepSetReading {
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
			readDocument();
			Thread.sleep(1000);
		}

		mongoClient.close();
	}

	static void readDocument() throws InterruptedException {
		boolean needToReadDocument = true;
		DBObject doc;

		while (needToReadDocument) {

			try {
				doc = testCol.findOne();
				System.out.println("result: " + doc);
				needToReadDocument = false;
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
