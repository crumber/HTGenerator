package HTGenerator;

import java.io.*;
import java.util.*;

public class RegisterTrigger extends TriggerGenerator{

    private Map<String,Integer> registerSetDimension;
    private List<String> registerSet;
    private String architecture;

    public RegisterTrigger(Map<String,Integer> triggersDimension,List<String> triggers, String architecture){

        this.registerSetDimension = new HashMap<>();
        this.registerSetDimension = triggersDimension; //copy hashMap
        this.registerSet= new ArrayList<>();
        this.registerSet.addAll(triggers);//copy register triggers to registerSet field
        this.architecture = architecture;

    }

    @Override
    public void generate(int trojan, int trigger) throws FileNotFoundException {

        File file = new File("GeneratedTrigger.txt"); //create the output file that will contain

        PrintWriter printWriter = new PrintWriter(file); //Prints formatted representations of objects to a text-output stream
        Random r1 = new Random();//class to generate random integers

        //loop on number of trojan that you want to generate
        for (int i = 1; i <= trojan; i++) {

            Collections.shuffle(this.registerSet); //shuffle registers to take always different triggers
            int nCouples = r1.nextInt(trigger) + 1; //random generator of triggers from 0 to "trigger"(user input)

            //loop to generate a random value for every register trigger
            for (int k = 0; k < nCouples; k++) {

                Random r2 = new Random();
                String value;

                //control on dimension of the actual register, the value is get from the HashMap using the String that represents the actual register
                if(this.registerSetDimension.get(this.registerSet.get(k)).equals(32)) {

                    value = Integer.toHexString(r2.nextInt()); //convert random integers to hexadecimal
                    printWriter.print(i);//print the number of trojan in the file
                    printWriter.print("   " + this.registerSet.get(k));//print the register
                    printWriter.print("  0x" + value + "\n");//and its trigger value

                }else if(this.registerSetDimension.get(this.registerSet.get(k)).equals(16)){

                    value = Integer.toHexString((short) r2.nextInt(Short.MAX_VALUE+1)); //generate a random 16-bit value
                    printWriter.print(i);//print the number of trojan in the file
                    printWriter.print("   " + this.registerSet.get(k));//print the register
                    printWriter.print("  0x" + value + "\n");//and its trigger value

                }

            }

            printWriter.print("\n");

        }

        printWriter.close();

    }

}
