package controllers;

//imports for forms and basic play things
import play.mvc.*;
import play.data.*;
import play.Logger;

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
/*        for (i=0;i < dayDatas.size();i++ ) {
                    Logger.debug(dayDatas.get(i));
        }*/

        return ok(testpage.render(chartDatas, dayDatas, chartDatasHumidity));
    }

    // getnewvalue (scheduler)
    // tell html thqt new block exists
    // render using ajax ?
}
