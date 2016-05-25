package controllers;

//imports for forms and basic play things
import play.mvc.*;
import play.data.*;
import play.Logger;
import javax.inject.Inject;

//import files needed in the project
import views.html.*;
import models.*;

//imports for Java things
import java.util.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Application extends Controller {
    
    
    // Create and inject a form
    @Inject public FormFactory formFactory;


    //redirection of "localhost:9000/" to "localhost:9000/formHello
    public Result index() {
        Logger.debug("Redirection from home to /formHello...");
        return redirect("/formHello");
    }
    
    //logs appear in the terminal console
    public Result hello() {
        Logger.debug("Done!");
        // initiate form using the injection of FormFactory. The form is defined by app/models/User.java
        Form<User> userForm = formFactory.form(User.class);
        Logger.debug("Form initiated.");
    
        //Map<String,String> anyData = new HashMap();
        //anyData.put("email", "john.doe@gmail.com");
        //anyData.put("password", "secret.jpg");
        //Logger.debug("Data inserted into Hashmap.");
        //User user = userForm.bind(anyData).get();
        //Logger.debug("Data bound to userForm");
        return ok(main.render(userForm));
    }
    
    public Result loginSubmit() {
        // Create a new User form and bind the result within
        Form<User> userForm2 = formFactory.form(User.class).bindFromRequest();
        User user = userForm2.get();
        
        // print email & password in the console
        Logger.info(user.getEmail());
        Logger.info(user.getPassword());
        
        // load email & pass into an hashmap
        Map<String, String> userInfo = new HashMap();
        userInfo.put("email",user.getEmail());
        userInfo.put("password",user.getPassword());
        
        // Create an instance of Tools & an arraylist
        Tools tools = new Tools();
        ArrayList<String> list = new ArrayList<String>();
        
        // Read CVS file & send it into ArrayList list
        list = tools.readCsv("/home/jeremy/JavaPlay_BasicTests/public/sources/data.csv", ";", list);
        Logger.info(list.size()+" results found in CVS file");
        int i = 0;
        while (i< list.size()) {
            //Logger.debug(list.get(i));
            i++;
        }
        //renders graph.render with the hashmap
        return ok(graph.render(userInfo, list));
    }

    public Result viewGraph() {
        // Create an instance of Tools & an arraylist
        Tools tools = new Tools();
        ArrayList<String> list = new ArrayList<String>();
        // Read csv file
        list = tools.readCsv("/home/jeremy/JavaPlay_BasicTests/public/sources/data.csv", ";", list);
        // send data to html page & render it
        return ok(viewGraph.render(list));
    }
    
    public Result viewGraphv2() {
        return TODO;
    }
}
