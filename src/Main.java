import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
    private static final int DIMENSIONS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int nTrials = Integer.parseInt(in.readLine());

        Vector2<Integer, Integer>[] scores_trial = new Vector2[nTrials];
        Lemmings lm = new Lemmings();
        for (int i = 0 ; i < nTrials ; i++){
            Vector2<String, Integer>[][] lemming_trial = new Vector2[nTrials][DIMENSIONS]; // TODO isto nao devia ser ao contrario? [linha][coluna]?
            int nLemmings = Integer.parseInt(in.readLine()); // Numero de lemmings por linha

            for (int d = 0 ; d < DIMENSIONS ; d++) { // Poder e tribo de um lemming
                for (int j = 0; j < nLemmings; j++) {
                    String[] line = in.readLine().split(" ");
                    String type = line[0];
                    int points = Integer.parseInt(line[1]);
                    lemming_trial[i][d] = new Vector2<>(type, points);
                }
            }

            //Solve sub-problem
            lm.build(lemming_trial);
            scores_trial[i] = lm.solve();
        }

        for (int i = 0 ; i < scores_trial.length ; i++){
            Vector2<Integer, Integer> curr = scores_trial[i];
            System.out.println(curr.getFirst() + " " + curr.getSecond());
        }

        in.close();
    }
}
