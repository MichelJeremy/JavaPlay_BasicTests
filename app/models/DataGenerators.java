package models;

import java.util.*;
import java.lang.Object;
import play.Logger;

//mongoDB imports
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.*;
import com.mongodb.client.model.*;
import com.mongodb.client.model.Sorts.*;
import com.mongodb.client.model.Filters.*;

public class DataGenerators {

/*
	Useful to get a new database with new 'randomized' data from scratch.

	this generator will 
		0 - drop target database if it exists. This ensures it is empty.
		1 - create and connect to the database
		2 - create new collections, with raw and aggregated suffixes for each sensor
		3 - insert the data following the function's parameter
		4 - create the aggregations
		5 - remove the data older than a week (yes, it's not optimal since I could 
			just make the aggregation of data older than a week without adding it,
			but it is easier this way)

		Note - this is assuming localhost, default port and no authentification
*/
	public void fullGeneratorMongoDB(String databaseName, int timeSpanDay, int pointsPerDay, int daysDatakept) {

		String[] COLLECTIONS = {
			"S001_Temp_raw",
			"S001_Temp_aggregated",
			"S002_Humi_raw",
			"S002_Humi_aggregated",
			"S003_Wind_raw",
			"S003_Wind_aggregated",
			"S004_Rain_raw",
			"S004_Rain_aggregated",
			"S005_Air_raw",
			"S005_Air_aggregated",
		};

		//value range for each collection
		int MINTEMP = -15;
		int MAXTEMP = 30;
		int MINHUMI = 0;
		int MAXHUMI = 100;
		int MINWINDSPEED = 0;
		int MAXWINDSPEED = 100;
		int MINWINDDIR = 0;
		int MAXWINDIR = 360;
		int MINRAIN = 0;
		int MAXRAIN = 5;
		int MINAIR = 0;
		int MAXAIR = 100;

		/*0 - drop the database, whether it exists or not*/
		MongoClient mongoClient = new MongoClient( "localhost" , 27017);
		MongoDatabase database = mongoClient.getDatabase(databaseName);
		database.drop();


		/*1 - create and connect to the database*/
		database = mongoClient.getDatabase(databaseName);


		/*2 - create new collections, with raw and aggregated suffixes for each sensor*/
		MongoCollection<Document> tempRawCollection = database.getCollection(COLLECTIONS[0]);
		MongoCollection<Document> humiRawCollection = database.getCollection(COLLECTIONS[2]);
		MongoCollection<Document> windRawCollection = database.getCollection(COLLECTIONS[4]);
		MongoCollection<Document> rainRawCollection = database.getCollection(COLLECTIONS[6]);
		MongoCollection<Document> airRawCollection = database.getCollection(COLLECTIONS[8]);
		
		MongoCollection<Document> tempAggregatedCollection = database.getCollection(COLLECTIONS[1]);
		MongoCollection<Document> humiAggregatedCollection = database.getCollection(COLLECTIONS[3]);
		MongoCollection<Document> windAggregatedCollection = database.getCollection(COLLECTIONS[5]);
		MongoCollection<Document> rainAggregatedCollection = database.getCollection(COLLECTIONS[7]);
		MongoCollection<Document> airAggregatedCollection = database.getCollection(COLLECTIONS[9]);


		/*3 - insert the data following the function's parameter*/
		/*3.1 - get generation starting date in milliseconds (epoch)*/
		Calendar now = Calendar.getInstance(); // get current time
		Calendar nextDay = Calendar.getInstance(); // instanciate new calendar with current tine
		nextDay.clear(); // clear the new calendar (which is now 0)
		nextDay.set(
			now.get(Calendar.YEAR),
			now.get(Calendar.MONTH) + 1,
			now.get(Calendar.DAY_OF_MONTH) + 1,
			0,
			0
		); // set the time to next day, midnight
		long nextDayTimestamp = nextDay.getTimeInMillis(); // transform beggining date to tinestamp

		/*3.2 - get milliseconds differentials (between two data point)*/
		int millisecondsOneDay = 86400000; // amount of millis in a day
		long deltaMillis = millisecondsOneDay/pointsPerDay; //delta betweem two values

		/*3.3 - insert data in document, incrementing timestamp each time, timeSpanDay times*/
		//bind raw collections to vars


		

		int numberOfInsert = timeSpanDay * pointsPerDay;
		Random random = new Random();
		//temp insert
		for (long i = 0 ; i < numberOfInsert ; i++) {
			long finalTimestamp = nextDayTimestamp + i * deltaMillis; // every var in this operation MUST be long because java use var type to store result when 
			Document document = new Document("timestamp", finalTimestamp) // prioritizing. ( i * deltaMillis can exceed 32 bits easily on long periods of time)
				.append("temperature", random.nextInt(MAXTEMP - MINTEMP) + MINTEMP)
				.append("unit", "C");

			tempRawCollection.insertOne(document);
		}

		for (long i = 0 ; i < numberOfInsert ; i++) {
			long finalTimestamp = nextDayTimestamp + i * deltaMillis; // every var in this operation MUST be long because java use var type to store result when 
			Document document = new Document("timestamp", finalTimestamp) // prioritizing. ( i * deltaMillis can exceed 32 bits easily on long periods of time)
				.append("humidity", random.nextInt(MAXHUMI - MINHUMI) + MINHUMI)
				.append("unit", "%");

			humiRawCollection.insertOne(document);
		}

		for (long i = 0 ; i < numberOfInsert ; i++) {
			long finalTimestamp = nextDayTimestamp + i * deltaMillis; // every var in this operation MUST be long because java use var type to store result when 
			Document document = new Document("timestamp", finalTimestamp) // prioritizing. ( i * deltaMillis can exceed 32 bits easily on long periods of time)
				.append("windspeed", random.nextInt(MAXWINDSPEED - MINWINDSPEED) + MINWINDSPEED)
				.append("winddirection", random.nextInt(MAXWINDIR - MINWINDDIR) + MINWINDDIR)
				.append("unit1", "kn")
				.append("unit2", "°");

			windRawCollection.insertOne(document);
		}

		for (long i = 0 ; i < numberOfInsert ; i++) {
			long finalTimestamp = nextDayTimestamp + i * deltaMillis; // every var in this operation MUST be long because java use var type to store result when 
			Document document = new Document("timestamp", finalTimestamp) // prioritizing. ( i * deltaMillis can exceed 32 bits easily on long periods of time)
				.append("rain", random.nextInt(MAXRAIN - MINRAIN) + MINRAIN)
				.append("unit", "mm");

			rainRawCollection.insertOne(document);
		}

		for (long i = 0 ; i < numberOfInsert ; i++) {
			long finalTimestamp = nextDayTimestamp + i * deltaMillis; // every var in this operation MUST be long because java use var type to store result when 
			Document document = new Document("timestamp", finalTimestamp) // prioritizing. ( i * deltaMillis can exceed 32 bits easily on long periods of time)
				.append("airquality", random.nextInt(MAXAIR - MINAIR) + MINAIR)
				.append("unit", "%");

			airRawCollection.insertOne(document);
		}


		/*4 - create the aggregations*/
		//bind collections to vars


		//get data older than daysDatakept, and aggregate it
		Document doc = tempRawCollection.find().sort(descending("timestamp")).first();
		Logger.debug(""+doc);
	}
	
}


