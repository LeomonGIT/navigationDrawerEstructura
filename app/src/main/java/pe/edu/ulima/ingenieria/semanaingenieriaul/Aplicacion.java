package pe.edu.ulima.ingenieria.semanaingenieriaul;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Leonardo on 06/04/2015.
 */
public class Aplicacion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "76NvBNmyFtuEA57S5rA4kl3Y9Jg773K8HMlKNWbi", "xRas3knNeKiYXE9andF30Id7X1l7ZRPuRCQUIeIl");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
