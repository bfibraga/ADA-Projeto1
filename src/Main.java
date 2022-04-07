import utils.Vector2;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static final int DIMENSIONS = 2;

    public static void main(String[] args) throws IOException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        int nTrials = Integer.parseInt(in.readLine());

        Vector2<Long, Long>[] scores_trial = new Vector2[nTrials];
        Lemmings lm;
        for (int i = 0 ; i < nTrials ; i++){
            int nLemmings;
            lm = new Lemmings(DIMENSIONS);

            for (int d = 0 ; d < DIMENSIONS ; d++) { // Poder e tribo de um lemming
                nLemmings = Integer.parseInt(in.readLine()); // Numero de lemmings por linha
                Vector2<String, Long>[] lemmings = new Vector2[nLemmings];
                for (int l = 0; l < nLemmings ; l++) {
                    String[] line = in.readLine().split(" ");
                    String type = line[0];
                    long points = Long.parseLong(line[1]);
                    lemmings[l] = new Vector2<>(type, points);
                    lm.build(d, lemmings);
                }
            }
            //Solve sub-problem
            /*System.out.println("--------------");
            lm.printLemmings();
            System.out.println("--------------");*/
            lm.createTable();
            out.write(lm.solve().toString());
            out.newLine();
            /*lm.printTable();
            System.out.println("--------------");*/
        }
        Timestamp late = new Timestamp(System.currentTimeMillis());

        /*for (Vector2<Long, Long> curr : scores_trial) {
            System.out.println(curr.toString());
        }*/

        out.newLine();
        out.write(String.valueOf(late.getTime()-now.getTime()));
        in.close();
        out.close();
    }
}
