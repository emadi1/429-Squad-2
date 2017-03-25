package core;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Created by JJ on 3/21/2017.





 */
/**
public class Core {

    private String country, language;

    private Core instance = null;
    private ResourceBundle getMessageBundle = null;

    private Core() {
        country = new String("US");
        language = new String("en");
    }

    public Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }

        return instance;
    }

    public Core getMessageBundle(){
        if(getMessageBundle == null){
            getMessageBundle= new ResourceBundle() {

            }

        }
    }
}*/
