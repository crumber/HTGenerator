package HTGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//classe per la gestione dell' instrction set acquisito da file

public class InstructionTrigger extends TriggerGenerator {

    private Map<String,Integer> instructionSetDimension;
    private List<String> instructions;
    private String architecture;

    public InstructionTrigger(Map<String,Integer> triggersDimension, List<String> triggers, String architecture){

        this.instructionSetDimension = new HashMap<>();
        this.instructionSetDimension = triggersDimension;
        this.instructions= new ArrayList<>();
        this.instructions.addAll(triggers);
        this.architecture = architecture;

    }

    @Override
    public void generate(int trojan, int trigger){

        //Not implemented because it was not in the specifications of this project

    }


}
