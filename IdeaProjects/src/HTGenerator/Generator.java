package HTGenerator;

import java.io.*;
import java.util.*;

public class Generator {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        //ask to user how much trojan want to generate
        System.out.println("Insert number of Trojan: ");
        int trojan = sc.nextInt();

        //ask to user max number of triggers
        System.out.println("Insert max number of Triggers: ");
        int trigger = sc.nextInt();

        //I create an HashMap to save the triggers and their dimension from Architecture.txt
        HashMap<String,Integer> triggersDimension = new HashMap<>();

        //FileReader and BufferedReader to read registers/instructions from the file that describes the architecture used
        BufferedReader buff = new BufferedReader(new FileReader("Architecture.txt"));

        String arch = buff.readLine(); //save the architecture from the first line of file, it could be useful in future
        String triggerType = buff.readLine(); //save trigger type (registers or instructions)
        triggerType = triggerType.substring(0,triggerType.length()-1); //to remove colon at the end of triggerType

        String line = buff.readLine();
        String[] currentTrigger = line.split(" ");

        while (line != null){ //read until the end of file

            triggersDimension.put(currentTrigger[0],Integer.parseInt(currentTrigger[1]));//add currentTrigger to the ArrayList that contains the triggers
            line = buff.readLine();

            if(line!=null) {

                currentTrigger = line.split(" ");//read next trigger

            }

        }

        buff.close();

        ArrayList<String> triggers = new ArrayList<>(triggersDimension.keySet()); //copy the triggers set to an ArrayList

        //I exploit java inheritance to manage different types of triggers in a future development of this program
        if(triggerType.equals("Registers")){

            RegisterTrigger tr = new RegisterTrigger(triggersDimension, triggers, arch); //constructor with triggers set and architecture type
            tr.generate(trojan,trigger);//method that generate random triggers with the inputs of the user

        }else if(triggerType.equals("Instructions")){

            InstructionTrigger tr = new InstructionTrigger(triggersDimension, triggers, arch);
            tr.generate(trojan,trigger);

        }
    }
}
