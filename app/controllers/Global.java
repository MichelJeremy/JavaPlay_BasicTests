// imports for akka mechanisms
import play.libs.Akka;
import play.Application;
import play.GlobalSettings;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;
import akka.actor.*;
import actors.*;

import models.*;
import play.Logger;
import controllers.*;


public class Global extends GlobalSettings {
	int i = 0;
	@Override
    public void onStart(Application application) {

        Akka.system().scheduler().schedule(
            Duration.create(0, TimeUnit.SECONDS),
            Duration.create(5, TimeUnit.SECONDS),
            new Runnable() {
                @Override
                public void run() {
                    Logger.debug("blbl"+i);
                    i++;
                }
            },
            Akka.system().dispatcher()
        );
    }
}