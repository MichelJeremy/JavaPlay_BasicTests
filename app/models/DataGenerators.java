package models;

import java.util.*;
import play.Logger;

//mongoDB imports
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

public class DataGenerators {

/*
	Useful to get a new database with new 'randomized' data from scratch.

	this generator will 
		0 - drop target database if it exists. This ensures it is empty.
		1 - create and connect to the database
		2 - create new collections, with raw and aggregated suffixes for each sensor
		3 - insert the data following the function's parameter
		4 - create the aggregations
		5 - remove the data older than a week (yes, it;s not optimal since I could 
			just make the aggregation of data older than a week without adding it,
			but it is easier this way)

		Note - this is assuming localhost, default port and no authentification
*/
	public void fullGeneratorMongoDB(String dabataseName) {

		
		/*0 - drop the database*/
		// look for existence
		MongoClient mongoClient = new MongoClient( "localhost" , 27017);
		MongoDatabase database = mongoClient.getDatabase("mydb");
		database.createCollection("test");
		
		
		List<String> dbs = mongoClient.getDatabaseNames();
		for(String db : dbs){
			Logger.debug(""+db);
		}


	}
}