package HTGenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;

//this abstract class is helpful for a future use with different type of triggers as input
public abstract class TriggerGenerator {

    ArrayList<String> triggerSet ;

    public abstract void generate(int trojan, int trigger) throws FileNotFoundException;
}
