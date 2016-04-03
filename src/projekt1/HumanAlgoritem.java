package projekt1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Lojze on 3.4.2016.
 */
public class HumanAlgoritem implements Algoritem {

    Random rand;
    Queue<Integer> poteze;
    int[][] input;

    public HumanAlgoritem(){
        poteze = new LinkedList<>();
        rand = new Random();
        try {
            input = preberiPoteze();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int naslednjaPoteza() {
        if(poteze.isEmpty())
            dodajPoteze();
        return poteze.poll();
    }

    @Override
    public void nasprotnikovaPoteza(int poteza) {

    }

    @Override
    public void ponastavi() {

    }

    public void dodajPoteze(){
        int i = rand.nextInt(input.length);
        int igra[] = input[i];
        int startI = rand.nextInt(80);
        int endI = Math.min(igra.length-1, startI+rand.nextInt(30)+20);
        for(int j=startI; j<=endI; j++){
            poteze.add(igra[j]);
        }
    }

    public int[][] preberiPoteze() throws IOException {
        FileReader fileReader = new FileReader("rezultati.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<int[]> lines = new ArrayList<>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String numbers[] = line.split(" ");
            int n[] = new int[numbers.length];
            for(int i=0; i<numbers.length; i++){
                n[i] = Integer.parseInt(numbers[i]);
            }
            lines.add(n);
        }
        bufferedReader.close();
        return lines.toArray(new int[lines.size()][]);
    }
}
