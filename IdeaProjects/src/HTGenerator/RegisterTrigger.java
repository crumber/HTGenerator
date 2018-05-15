package HTGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RegisterTrigger extends TriggerGenerator{

    private ArrayList<String> registerSet;
    public String architecture;

    public RegisterTrigger(ArrayList<String> triggers, String architecture){

        this.registerSet = new ArrayList<>();
        this.registerSet.addAll(triggers);
        this.architecture = architecture;

    }

    @Override
    public void generate(int trojan, int trigger){

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("GeneratedTrigger.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintWriter printWriter = new PrintWriter(fileWriter);
        Random r1 = new Random();

        for (int i = 1; i <= trojan; i++) {

            Collections.shuffle(this.registerSet); //mischia i registri in modo da prenderne sempre diversi per i trigger
            int nCoppie = r1.nextInt(trigger - 1) + 1; //generatore casuale trigger da 1 a trigger

            for (int k = 0; k < nCoppie; k++) {

                Random r2 = new Random();
                String value = Integer.toHexString(r2.nextInt());
                printWriter.print(i);
                printWriter.print("   " + this.registerSet.get(k));
                printWriter.print("  0x" + value + "\n");

            }

            printWriter.print("\n");

        }

        printWriter.close();

    }
}
