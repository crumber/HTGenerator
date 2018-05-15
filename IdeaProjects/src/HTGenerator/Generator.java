package HTGenerator;
import java.io.*;
import java.util.*;

public class Generator {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire numero massimo Trigger: ");
        int trigger = sc.nextInt();

        System.out.println("Inserire numero Trojan: ");
        int trojan = sc.nextInt();

        ArrayList<String> triggers= new ArrayList<>();

        //FileReader e BufferedReader per leggere i registri dal file che descrive l'architettura utilizzata
        BufferedReader buff = new BufferedReader(new FileReader("Architecture.txt"));

        /*
        Loop per aggiungere i registri della Cpu all arraylist;
        La prima riga dell'Architecture.txt viene saltata perchè descrive il tipo di architettura
        che in questa versione del programma non è necessario sapere dato che si è lavorato esclusivamente sull'armv6,
        potrà comunque essere utilizzata in futuro;
         */

        String arch = buff.readLine();
        String triggerType = buff.readLine();
        triggerType = triggerType.substring(0,triggerType.length()-1);// tolgo i due punti finali
        String reg = buff.readLine();

        while (reg!=null){

            triggers.add(reg);
            reg = buff.readLine();

        }

        buff.close();

        if(triggerType.equals("Registers")){

            RegisterTrigger tr = new RegisterTrigger(triggers, arch);
            tr.generate(trigger,trojan);

        }else if(triggerType.equals("Instructions")){

            InstructionTrigger tr = new InstructionTrigger(triggers, arch);
            tr.generate(trigger,trojan);

        }
    }
}
