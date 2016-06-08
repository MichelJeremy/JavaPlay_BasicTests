package models;

import java.util.*;

//imports for csv file
import java.io.File;
import java.io.FileNotFoundException;


//imports for date
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import play.Logger;

// gives some functionnalities
public class Tools {

    //old reader, shouldn't be used
    public ArrayList<String> readCsv(String csvPath, String delimiter, ArrayList<String> list) {
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(csvPath));
            scanner.useDelimiter(delimiter);
            while(scanner.hasNext()) {
                i++;
                if (i%2 != 0) {
                    scanner.next();
                } else {
                    list.add(scanner.next());
                }
            }
        scanner.close();
        return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
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

    // give the function the day and the sensorId and it will return every values associated with this day
    public ArrayList<String> getAllDayValues(String date, int sensorID, ArrayList<String> list) {
        /*list:
            - first column: sensorId
            - second column: date
            - third column: time
            - fourth column: value*/
        for(int i = 4; i <list.size(); i++) {
            // check sensor ID
            if ((i%4 == 0) && (Integer.parseInt(list.get(i)) == sensorID)) {
                // if sensor id is the same, jump to date column
                i++;
                if (""+list.get(i) == date) {
                    i+=2;
                    // if date is also the same, jump to value column and add it to the array
                    list.add(list.get(i));
                }
            }
        }
        return list;
    }
}