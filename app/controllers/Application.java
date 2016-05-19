package controllers;

import play.mvc.*;
import play.data.*;
import java.*;
import java.util.*;
import play.Logger;
import javax.inject.Inject;

import views.html.*;
import models.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Application extends Controller {
    
    // Create and inject a form
    @Inject public FormFactory formFactory;


    public Result index() {
        Logger.debug("Redirection from home to /formHello...");
        return redirect("/formHello");
    }
    
    public Result hello() {
        Logger.debug("Done!");
        Form<User> userForm = formFactory.form(User.class);
        Logger.debug("Form initiated.");
    
        Map<String,String> anyData = new HashMap();
        anyData.put("email", "john.doe@gmail.com");
        anyData.put("password", "secret.jpg");
        Logger.debug("Data inserted into Hashmap.");
        User user = userForm.bind(anyData).get();
        Logger.debug("Data bound to userForm");
        return ok(main.render(userForm));
    }
    
    public Result loginSubmit() {
        Form<User> userForm2 = formFactory.form(User.class).bindFromRequest();
        //Unfinished there
        Logger.info("username : "+userForm2.get());
        return redirect("/formHello");
    }

}
