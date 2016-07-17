package controllers;

import java.util.*;

//imports for csv file
import java.io.File;
import java.io.FileNotFoundException;


//imports for date
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Projections.*;
import play.Logger;

// gives some functionnalities
public class Tools {
    
    // act reader, takes \n into account
    public ArrayList<String> readCsv2(String csvPath, String delimiter, ArrayList<String> list) {
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(csvPath));

            scanner.useDelimiter(String.format("%s|\\n",delimiter));
            while(scanner.hasNext()) {
                i++;
                list.add(scanner.next());
            }
            scanner.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> csvToChartDataLine(ArrayList<String> list, ArrayList<String> chartData) {
        // First, we grab the number of sensors, that we store in the first field of the arraylist
        // Then we store the push arrays
        int i, j;
        boolean alreadyExists = false;

        ArrayList<Integer> ids = new ArrayList<Integer>();
        int id;
        int value;
        String dateMinus;
        String newDate;
        String newDateSplit;
        String time;

        StringBuilder sb = new StringBuilder();

        // var initialisation
        i = 0;
        j = 0;
        id = 0;
        value = 0;
        newDate = "";
        dateMinus = "";
        chartData.add("0");
        // goes through every members of the data arraylist
        for (i=0; i<list.size(); i++) {
            // checks every 4 members, except the very first (0) which is the column header  (= every Ids)
            if (i%4 == 0 && i != 0) {
                // checks every members of sensor ids
                for (j=0; j<ids.size(); j++) {
                    // if the list members exists in listofsensorsId, check next value
                    if(Integer.parseInt(list.get(i)) == ids.get(j)) {
                        alreadyExists = true;
                    }
                }
                // if the data list member didn't exist in the sensor list, add it in and then update 
                if (alreadyExists == false) {
                    ids.add(Integer.parseInt(list.get(i)));
                    chartData.set(0, (""+(Integer.parseInt(chartData.get(0))+1)));
                } else {
                    // Finally, we reset already exist to test the next value
                    alreadyExists = false;
                }
                // if we are at the second qudruplet iteration of the for loop, there is data to push to charData array
                if (i >= 8 && i%4 == 0) {
                    // construct the push
                    sb.append("chartData").append(""+id).append(".push({date: \"").append(newDate).append("\", tonsOfBananas: ").append(""+value).append("});");
                }
                // now that we are not going to erase previous data, write new ones
                    id = Integer.parseInt(list.get(i));
            }
            if (i%4 == 1 && i != 1) {
                dateMinus = list.get(i);
            } else if (i%4 == 2 && i != 2) {
                time = list.get(i).substring(0, 5);
                newDate = dateMinus + " " + time;
            } else if (i%4 == 3&& i != 3) {
                value = Integer.parseInt(list.get(i));
            }

        }
        chartData.add(sb.toString());
        return chartData;
    }



    //this method is going to return the data stored in a list
    public List<List<String>> jsonToDataList(String dbHost, int port, String dbName) {
/*      List[0][x] = temp
        List[1][x] = tempAgr
        List[2][x] = humidity
        List[3][x] = humidityAgr
        List[4][x] = windspeed
        List[5][x] = windspeedAgr
        List[6][x] = winddirection
        List[7][x] = winddirectionAgr
        List[8][x] = rain
        List[9][x] = rainAgr
        List[10][x] = air
        List[11][x] = airAgr*/

        List<List<String>> dataList = new ArrayList<List<String>>();

        String[] COLLECTIONS = {
            "S001_Temp_raw",
            "S001_Temp_aggregated",
            "S002_Humi_raw",
            "S002_Humi_aggregated",
            "S003_WindSpeed_raw",
            "S003_WindSpeed_aggregated",
            "S004_WindDirection_raw",
            "S004_WindDirection_aggregated",
            "S005_Rain_raw",
            "S005_Rain_aggregated",
            "S006_Air_raw",
            "S006_Air_aggregated",
        };


        MongoClient mongoClient = new MongoClient(dbHost , port);
        MongoDatabase database = mongoClient.getDatabase(dbName);

        MongoCollection<Document> tempRawCollection = database.getCollection(COLLECTIONS[0]);
        MongoCollection<Document> humiRawCollection = database.getCollection(COLLECTIONS[2]);
        MongoCollection<Document> windSRawCollection = database.getCollection(COLLECTIONS[4]);
        MongoCollection<Document> windDRawCollection = database.getCollection(COLLECTIONS[6]);
        MongoCollection<Document> rainRawCollection = database.getCollection(COLLECTIONS[8]);
        MongoCollection<Document> airRawCollection = database.getCollection(COLLECTIONS[10]);
        
        MongoCollection<Document> tempAggregatedCollection = database.getCollection(COLLECTIONS[1]);
        MongoCollection<Document> humiAggregatedCollection = database.getCollection(COLLECTIONS[3]);
        MongoCollection<Document> windSAggregatedCollection = database.getCollection(COLLECTIONS[5]);
        MongoCollection<Document> windDAggregatedCollection = database.getCollection(COLLECTIONS[7]);
        MongoCollection<Document> rainAggregatedCollection = database.getCollection(COLLECTIONS[9]);
        MongoCollection<Document> airAggregatedCollection = database.getCollection(COLLECTIONS[11]);

        int i;

        // get database values
        for (i = 0; i < COLLECTIONS.length; i++) {
            List<String> list = new ArrayList<String>();
            dataList.add(list);
            MongoCursor<Document> cursor = database
                .getCollection(COLLECTIONS[i])
                .find()
                .projection(excludeId())
                .sort(descending("timestamp"))
                .iterator();

            try {
                while (cursor.hasNext()) {
                    Document cursorNext = cursor.next();
                    switch (i) {
                        //raw temperature
                        case 0: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("temperature").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;

                        //raw humidity
                        case 2: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("humidity").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;

                        //raw wind speed
                        case 4: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("windspeed").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;

                        case 6: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("winddirection").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;                               

                        //raw rain
                        case 8: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("rain").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;

                        //raw air
                        case 10: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("airquality").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;

                        //every cases left are the agregations
                        default: dataList.get(i).add(cursorNext.get("timestamp").toString());
                                dataList.get(i).add(cursorNext.get("min").toString());
                                dataList.get(i).add(cursorNext.get("max").toString());
                                dataList.get(i).add(cursorNext.get("average").toString());
                                dataList.get(i).add(cursorNext.get("unit").toString());
                                break;
                    }
                }
            } catch (Exception e) {
                Logger.error("An unknown error occured when fetching result from MongoDB");
            } finally {
                cursor.close();
            }
        }
        return dataList;
    }

    //give the function the json data list obtained with jsonToDataList and the index of the data you need (see below)
    //return one dimension array for javascript easier processing
/*    INDEX:  0 = temperature_raw
            1 = temperature_agr
            2 = humidity_raw
            3 = humidity_agr
            4 = windS_raw
            5 = windS_agr
            6 = windD_raw
            7 = windD_agr
            8 = rain_raw
            9 = rain_agr
            10 = air_raw
            11 = air_agr*/
    public ArrayList<String> jsonToDataFormat(List<List<String>> jsonDataList, int index) {
        ArrayList<String> jsonPushList = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        String timestamp= "", value = "", unit = "", value2 = "", unit2 = "", min = "", max = "", min2 = "", max2 = "", average = "", average2 = "";
        //raw collections
        if (index%2 == 0) {
            for (int i=0; i<jsonDataList.get(index).size(); i++) {
                if (i%3 == 0) timestamp = jsonDataList.get(index).get(i);
                if (i%3 == 1) value = jsonDataList.get(index).get(i);
                if (i%3 == 2) {
                    unit = jsonDataList.get(index).get(i);
                    sb.append(".push({timestamp: ")
                    .append(timestamp)
                    .append(", value: ")
                    .append(value)
                    .append(", unit: ")
                    .append("\""+unit+"\"")
                    .append("});");
                    jsonPushList.add(sb.toString());
                    sb.setLength(0); //reset the string builder
                }
            }
        }

        //agr collections
        if (index%2 == 1) {
            for (int i=0; i<jsonDataList.get(index).size(); i++) {
                if (i%5 == 0) timestamp = jsonDataList.get(index).get(i);
                if (i%5 == 1) min = jsonDataList.get(index).get(i);
                if (i%5 == 2) max = jsonDataList.get(index).get(i);
                if (i%5 == 3) average = jsonDataList.get(index).get(i);
                if (i%5 == 4) {
                    unit = jsonDataList.get(index).get(i);
                    sb.append(".push({timestamp: ")
                    .append(timestamp)
                    .append(", min: ")
                    .append(min)
                    .append(", max: ")
                    .append(max)
                    .append(", average: ")
                    .append(average)
                    .append("});");
                    jsonPushList.add(sb.toString());
                    sb.setLength(0); //reset the string builder
                }
            }
        }

        return jsonPushList;
    }




    // give the function the day and the sensorId and it will return every values associated with this day
    public ArrayList<String> getAllDayValues(String date, int sensorID, ArrayList<String> list, ArrayList<String> output) {
        /*list:
            - first column: sensorId
            - second column: date
            - third column: time
            - fourth column: value*/
        for(int i = 4; i < list.size(); i++) {
            // check sensor ID
            if ((i%4 == 0) && (Integer.parseInt(list.get(i)) == sensorID)) {
                // if sensor id is the same, jump to date column
                i++;
                if (list.get(i).equals(date)) {
                    i+=2;
                    // if date is also the same, jump to value column and add it to the array
                    output.add(list.get(i) + "|" + list.get(i-1));
                }
            }
        }
        return output;
    }

    //overrides the previous method to work with json
    //takes the list of list<String> generated with jsonToDataList, and the index of the collection wished
/*  INDEX:  0 = temperature_raw
            2 = humidity_raw
            4 = wind_raw
            6 = rain_raw
            8 = air_raw*/
    public ArrayList<Object> getAllDayValues(List<List<String>> mongoData, int collectionIndex) {
        ArrayList<Object> stats = new ArrayList<Object>();
        long lastTimestamp = 0,
            dayLimitTimestamp = 0,
            nextTimestamp = 0,
            minTime = 0,
            maxTime = 0,
            currentTime = 0,
            minTimeWS = 0,
            maxTimeWS = 0,
            currentTimeWS = 0,
            minTimeWD = 0,
            maxTimeWD = 0,
            currentTimeWD = 0;

        int i = 0,
            min = 0,
            max = 0,
            current = 0,
            currentWS = 0,
            minWS = 0,
            maxWS = 0,
            currentWD = 0,
            minWD = 0,
            maxWD = 0;

        float average = 0,
            averageWS = 0,
            averageWD = 0;

        lastTimestamp = Long.parseLong(mongoData.get(collectionIndex).get(0));
        Calendar dayLimit = Calendar.getInstance();
        dayLimit.setTimeInMillis(lastTimestamp);
        dayLimit.set(
            dayLimit.get(Calendar.YEAR),
            dayLimit.get(Calendar.MONTH),
            dayLimit.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0
        ); // get time at the beginning of the day

        if (collectionIndex%2 == 0) { //raws
            current = Integer.parseInt(mongoData.get(collectionIndex).get(1));
            currentTime = lastTimestamp;
            dayLimitTimestamp = dayLimit.getTimeInMillis(); // get time in milliseconds
            nextTimestamp = lastTimestamp; // for while loop and var name comprehension
            while (nextTimestamp >= dayLimitTimestamp) {
                if (i == 0) { // cursor on first entry (init timestamps)
                    minTime = maxTime =  Long.parseLong(mongoData.get(collectionIndex).get(i));
                    nextTimestamp = Long.parseLong(mongoData.get(collectionIndex).get(i+3));
                } else if (i == 1) { // cursor on second entry (init values)
                    min = max = Integer.parseInt(mongoData.get(collectionIndex).get(i)); 
                    average += Integer.parseInt(mongoData.get(collectionIndex).get(i));
                } else if (i%3 == 1) { //cursor on every seconds entries (except the first second)
                    average += Integer.parseInt(mongoData.get(collectionIndex).get(i)); // add value to average
                    nextTimestamp = Long.parseLong(mongoData.get(collectionIndex).get(i+2));
                    if (Integer.parseInt(mongoData.get(collectionIndex).get(i)) > max) { //case where value gt max
                        max = Integer.parseInt(mongoData.get(collectionIndex).get(i)); // replace max with new value
                        maxTime = Long.parseLong(mongoData.get(collectionIndex).get(i-1)); // get time associated with this value
                    } else if (Integer.parseInt(mongoData.get(collectionIndex).get(i)) < min) { // case where value lt min
                        min = Integer.parseInt(mongoData.get(collectionIndex).get(i)); // replace min with new value
                        minTime = Long.parseLong(mongoData.get(collectionIndex).get(i-1)); // get time associated with this value
                    }
                }
                i++;
            }
            average /= ((i+1)/3); //divides average with the number of iterations divided by the amount of "loop entries"
                                // because when i is incremented, it only went through one entry
                                // while a full data is composed of 3 entries here
                                // thus i is iterated three times as much as there are data
            stats.add(min);
            stats.add(minTime);
            stats.add(max);
            stats.add(maxTime);
            stats.add(average);
            stats.add(current);
            stats.add(currentTime);
        }
        
        return stats;
    }


}