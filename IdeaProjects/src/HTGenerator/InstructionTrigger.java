package HTGenerator;

import java.util.ArrayList;


//classe per la gestione dell' instrction set acquisito da file

public class InstructionTrigger extends TriggerGenerator {

    public ArrayList<String> instructionSet;
    public String architecture;

    public InstructionTrigger(ArrayList<String> triggers, String architecture){

        this.instructionSet = new ArrayList<>();
        this.instructionSet.addAll(triggers);
        this.architecture = architecture;

    }

    @Override
    public void generate(int trojan, int trigger){}


}
