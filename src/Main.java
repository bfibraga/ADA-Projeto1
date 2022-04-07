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

        Vector2<Long, Long>[] scores_trial = new Vector2[nTrials];
        Lemmings lm;
        for (int i = 0 ; i < nTrials ; i++){
            int nLemmings;
            lm = new Lemmings(DIMENSIONS);
            for (int d = 0 ; d < DIMENSIONS ; d++) { // Poder e tribo de um lemming
                nLemmings = Integer.parseInt(in.readLine()); // Numero de lemmings por linha
                for (int l = 0; l < nLemmings ; l++) {
                    String[] line = in.readLine().split(" ");
                    String type = line[0];
                    long points = Integer.parseInt(line[1]);
                    Vector2<String, Long> lemming = new Vector2<String, Long>(type, points);
                    lm.build(d, lemming);
                }
            }
            //Solve sub-problem
            /*System.out.println("--------------");
            lm.printLemmings();
            System.out.println("--------------");*/
            lm.createTable();
            scores_trial[i] = lm.solve();
            /*lm.printTable();
            System.out.println("--------------");
            System.out.println(scores_trial[i].toString());*/
        }

        for (Vector2<Long, Long> curr : scores_trial) {
            System.out.println(curr.toString());
        }

        in.close();
    }
}
