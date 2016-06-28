package controllers;

//imports for forms and basic play things
import play.data.*;
import play.Logger;
import play.mvc.*;

//import files needed in the project
import views.html.*;
import models.*;

//imports for Java things
import java.util.*;

//imports for date
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import for injections
import javax.inject.*;

//imports for actors
import akka.actor.*;
import play.libs.F.*;
import play.mvc.WebSocket;
import play.mvc.LegacyWebSocket;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Application extends Controller {
    

    //redirection of "localhost:9000/" to "localhost:9000/formHello
    public Result index() {
        Logger.debug("Redirection from home to project...");
        return redirect("/testpage");
    }
    

    public Result bootstrapSandstone() {
        int i = 0;
        Tools tools = new Tools();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> dayDatas = new ArrayList<String>(); // used for thermometer
        ArrayList<String> chartDatas = new ArrayList<String>(); // used for temperature
        ArrayList<String> chartDatasHumidity = new ArrayList<String>(); // used for humidity
        List<List<String>> mongoData = new ArrayList<List<String>>(); // contains every datas stored
        ArrayList<String> mongoDataTempRaw = new ArrayList<String>();
        ArrayList<String> mongoDataTempAgr = new ArrayList<String>();

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date dateobj = new Date();
        /*String date = df.format(dateobj);*/
        String date = "2015-25-05"; // keep it static for dev purposes for now

        list = tools.readCsv2("/home/jeremy/dev/java/JavaPlay_BasicTests/public/sources/data2.csv", ";", list);
        dayDatas = tools.getAllDayValues(date, 1, list, dayDatas);
        chartDatas = tools.csvToChartDataLine(list, chartDatas);

        list = new ArrayList<String>(); // re-instanciate it so it is empty. Clear could be used but perf gain is negligible.
        list = tools.readCsv2("/home/jeremy/dev/java/JavaPlay_BasicTests/public/sources/data2-humidity.csv", ";", list);
        chartDatasHumidity = tools.csvToChartDataLine(list, chartDatasHumidity);
        mongoData = tools.jsonToDataList("localhost", 27017, "myDB");

        /*    INDEX:  
            0 = temperature_raw
            1 = temperature_agr
            2 = humidity_raw
            3 = humidity_agr
            4 = wind_raw
            5 = wind_agr
            6 = rain_raw
            7 = rain_agr
            8 = air_raw
            9 = air_agr*/

        mongoDataTempAgr = tools.jsonToDataFormat(mongoData, 4);

        for (i=0; i<mongoDataTempAgr.size(); i++) {
            Logger.debug(""+mongoDataTempAgr.get(i));
        }


        return ok(testpage.render(chartDatas, dayDatas, chartDatasHumidity, mongoData));
    }

    public Result generator() {
        DataGenerators dg = new DataGenerators();
        dg.fullGeneratorMongoDB("myDB", 50, 24, 7);

        return ok(generator.render());
    }
}
