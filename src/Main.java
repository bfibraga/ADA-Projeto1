import utils.Vector2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static final int DIMENSIONS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int nTrials = Integer.parseInt(in.readLine());

        Vector2<Integer, Integer>[] scores_trial = new Vector2[nTrials];
        Lemmings lm;
        for (int i = 0 ; i < nTrials ; i++){
            lm = new Lemmings(DIMENSIONS);
            int[] lemming_length = new int[DIMENSIONS];
            Set<Vector2<Vector2, Vector2>> lemmings = new HashSet<>();
            for (int d = 0 ; d < DIMENSIONS ; d++) { // Poder e tribo de um lemming
                lemming_length[d] = Integer.parseInt(in.readLine()); // Numero de lemmings por linha
                for (int l = 0; l < lemming_length[d] ; l++) {
                    String[] line = in.readLine().split(" ");
                    String type = line[0];
                    int points = Integer.parseInt(line[1]);
                    Vector2<String, Integer> lemming = new Vector2<>(type, points);
                    lm.build(d, lemming);
                }
            }
            //Solve sub-problem


            System.out.println("--------------");
            lm.printLemmings();
            System.out.println("--------------");
            lm.createTable();
            scores_trial[i] = lm.solve();
            lm.printTable();
            System.out.println("--------------");
        }

        for (int i = 0 ; i < scores_trial.length ; i++){
            Vector2<Integer, Integer> curr = scores_trial[i];
            System.out.println(curr.toString());
        }

        in.close();
    }
}
