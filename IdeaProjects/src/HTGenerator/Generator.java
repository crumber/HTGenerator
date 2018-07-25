package HTGenerator;

import java.io.*;
import java.util.*;

public class Generator {



    public static void main(String[] args) throws IOException {


        //I create an HashMap to save the triggers and their dimension from Architecture.txt
        HashMap<String,Integer> registersDimension = new HashMap<>();
        ArrayList<String> instructionsType = new ArrayList<>();

        //FileReader and BufferedReader to read registers/instructions from the file that describes the architecture used
        BufferedReader buff = new BufferedReader(new FileReader("Architecture.txt"));

        String arch = buff.readLine(); //save the architecture from the first line of file, it could be useful in future
        String triggerSet = buff.readLine(); //save trigger type (registers or instructions)
        triggerSet = triggerSet.substring(0,triggerSet.length()-1); //to remove colon at the end of triggerType

        String line = buff.readLine();
        String[] currentTrigger = line.split(" ");

        while (!line.equals("Instructions:")){ //read until the end of file

            registersDimension.put(currentTrigger[0],Integer.parseInt(currentTrigger[1]));//add currentTrigger to the ArrayList that contains the triggers
            line = buff.readLine();

            if(line!=null) {

                currentTrigger = line.split(" ");//read next trigger

            }

        }

        line = buff.readLine();


        while (line != null ){ //read until the end of file

            instructionsType.add(line);//add currentTrigger to the ArrayList that contains the triggers
            line = buff.readLine();

        }

        buff.close();

        ArrayList<String> triggers = new ArrayList<>(registersDimension.keySet()); //copy the triggers set to an ArrayList
        Scanner sc = new Scanner(new FileReader("HWT.conf"));
        int trojan = Integer.parseInt(sc.nextLine());
        ArrayList<String> trojanType = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));



        for(String type : trojanType){

            switch(type) {

                case "1" :
                    int trigger = sc.nextInt();
                    CombinatorialRegisterTrigger tr1 = new CombinatorialRegisterTrigger(registersDimension, triggers, arch);
                    Random random = new Random();
                    int maxTrojan = random.nextInt(trojan)+1;
                    trojan = trojan-maxTrojan;
                    tr1.generate(maxTrojan,trigger);
                    break;
                case "2" :

                    String[] parameters = sc.next().split(",");
                    SequentialRegisterTrigger tr2 = new SequentialRegisterTrigger(registersDimension,triggers,arch,Integer.parseInt(parameters[0]));
                    random = new Random();
                    maxTrojan = random.nextInt(trojan)+1;
                    trojan = trojan-maxTrojan;
                    tr2.generate(maxTrojan,Integer.parseInt(parameters[1]));
                    break;


            }
        }

        File file = new File("GeneratedTrigger.txt");

    }
}
