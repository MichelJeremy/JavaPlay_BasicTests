package models;

import java.util.*;

//imports for csv file
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import play.Logger;

// gives some functionnalities
public class Tools {
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
}