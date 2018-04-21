package HTGenerator;
import java.io.*;
import java.util.*;

public class Generator {

    public static void main(String[] args){



        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire numero massimo Trigger: ");
        int trigger = sc.nextInt();

        System.out.println("Inserire numero Trojan: ");
        int trojan = sc.nextInt();

        Random r1= new Random();//generatore casuale trigger da 1 a T

        ArrayList<String> registers= new ArrayList<String>();

        /*
        Loop per aggiungere i registri della Cpu all arraylist
         */
        for (int i=0; i<13; i++){

            registers.add("r" + i);

        }

        registers.add("sp");
        registers.add("lr");
        registers.add("pc");
        registers.add("cpsr");

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("GeneratedTrigger.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i = 1; i <= trojan; i++) {

                int t;
                t = r1.nextInt(trigger - 1) + 1;

                Collections.shuffle(registers); //mischia i registri in modo da prenderne sempre diversi per i trigger
                Random r2 = new Random(); //generatore casuale numero coppie trigger

                for (int j = 0; j < t; j++) {

                    int nCoppie = r2.nextInt(17 - 1) + 1;

                    for (int k = 0; k < nCoppie; k++) {

                        Random r3 = new Random();
                        String value = Integer.toHexString(r3.nextInt());
                        printWriter.print(j + 1);
                        printWriter.print("   " + registers.get(k));
                        printWriter.print("  0x" + value + "\n");

                        /*
                        stampare su file il registro registers.get(k) più il valore casuale che deve contenere in esadecimale
                         */

                    }

                }


            }
            printWriter.close();




    }
}
