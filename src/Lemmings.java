import utils.Vector2;

import java.util.*;

public class Lemmings {

    //TODO Find a better data structure to get/find specific elements
    private List<Vector2<String, Long>>[] lemming; // <Tribo do Lemming , Poder do Lemming >
    private Vector2<Long, Long>[][] table; //Solving problem table

    public Lemmings(int dimensions){
        this.lemming = new LinkedList[dimensions];
        for (int d = 0 ; d < dimensions ; d++){
            this.lemming[d] = new LinkedList<>();
        }
    }

    public void build(int dimension, Vector2<String, Long> given_lemming){
        this.lemming[dimension].add(given_lemming);
    }

    /**
     * Creates a new solving table
     */
    public void createTable(){
        this.table = new Vector2[lemming[1].size()+1][lemming[0].size()+1];
        for (int l1 = 0 ; l1 <= this.lemming[1].size() ; l1++){
            for (int l2 = 0 ; l2 <= this.lemming[0].size() ; l2++){
                this.table[l1][l2] = new Vector2<Long,Long>((long) -1, (long) -1);
            }
        }
    }

    //


    /**
     * Solves this problem returning a pair that contains the maximum possible points with minimum number of pairs
     * @return Vector with 2 components
     */
    public Vector2<Long,Long> solve() {
        //Base case
        Vector2<Long, Long> base = new Vector2<Long, Long>(0L, 0L);
        Vector2<Long, Long> res = base;
        //First Line
        for (int l1 = 0 ; l1 < table[0].length ; l1++){
            table[0][l1] = base;
        }

        //First Column
        for (int l2 = 1 ; l2 < table.length ; l2++){
            table[l2][0] = base;
        }

        //General cases
        //1: Fall lemming of l1
        //2: Fall lemming of l2
        //3: Fall both lemmings if both are equals
        for (int l1 = 1 ; l1 < table[0].length ; l1++){
            for (int l2 = 1 ; l2 < table.length ; l2++){
                //TODO Terrible time complexity of finding both lemmings
                Vector2<String, Long> lemming1 = this.lemming[0].get(l1-1);
                Vector2<String, Long> lemming2 = this.lemming[1].get(l2-1);
                String lemming1_type = lemming1.getFirst();
                String lemming2_type = lemming2.getFirst();
                long lemming1_score = lemming1.getSecond();
                long lemming2_score = lemming2.getSecond();
                boolean same_type = lemming1_type.equals(lemming2_type);

                //TODO Merge scores with number of pairs
                //Score
                /*
                long one_fall_score = Math.max(this.table[l2-1][l1].getFirst(), this.table[l2][l1-1].getFirst());
                long both_fall_score = Math.max(one_fall_score, this.table[l2-1][l1-1].getFirst() + lemming1_score + lemming2_score);
                long final_res_score = same_type ?
                        Math.max(one_fall_score, this.table[l2-1][l1-1].getFirst() + lemming1_score + lemming2_score) : //Fall both lemmings if both are equals
                        one_fall_score; //Fall one lemming


                //Number Pair formed
                long one_fall_pair = Math.max(this.table[l2-1][l1].getSecond(), this.table[l2][l1-1].getSecond());
                long final_res_pair = same_type ?
                        Math.max(one_fall_pair, this.table[l2-1][l1-1].getSecond()+1) : //Fall both lemmings if both are equals
                        one_fall_pair; //Fall one lemming


                table[l2][l1] = new Vector2<>(final_res_score, final_res_pair);
                */
                table[l2][l1] = same_type ?
                        this.bestOption(this.table[l2-1][l1], this.table[l2][l1-1], this.table[l2-1][l1-1], lemming1_score + lemming2_score) :
                        this.bestOption(this.table[l2-1][l1], this.table[l2][l1-1], null, 0L);

                //TODO Finding best candidate
                /*long best_score = res.getFirst();
                long best_nmr_pair = res.getSecond();
                if (final_res_score > best_score || final_res_pair < best_nmr_pair){
                    res = table[l2][l1];
                }*/
            }
        }

        /*for (int i = 0; i < lemming.length; i++) {
            if (lemming.length == 0)
                return new Vector2<>(0,0);

            if (!lemming[0][0].getFirst().equals(lemming[1][0].getFirst())) { // Os lemmings nao sao da mesma tribo
                res += Math.max(lemming.remove(0,-1), lemming.remove(-1,0)); // Este metodo remove faz com que um dos lemmings salte
                nPairs++;
            }
            else { // Os lemmings sao da mesma tribo
                res += Math.max(Math.max(lemming.remove(0,-1), lemming.remove(-1,0)), lemming.remove(0,0));
                nPairs++;
            }
        }
        Vector2 <Integer, Integer> output = new Vector2<>(res, nPairs);*/

        //table[lemming[1].size()][lemming[0].size()]
        return res;
    }

    /**
     *
     * @param line_fall
     * @param column_fall
     * @param both_fall
     * @param both_score
     * @return
     */
    private Vector2<Long, Long> bestOption(Vector2<Long, Long> line_fall, Vector2<Long, Long> column_fall, Vector2<Long, Long> both_fall, long both_score) {
        Vector2<Long, Long> res = new Vector2<>(0L, 0L);

        if (line_fall.getFirst() >= column_fall.getFirst()){
            res = line_fall;
        } else {
            res = column_fall;
        }

        if (both_fall != null && both_fall.getFirst() >= res.getFirst()){
            res = new Vector2<>(both_fall.getFirst() + both_score, both_fall.getSecond() + 1L );
        }

        return res;
    }

    //DEBUG
    public void printLemmings(){
        for (int d = 0 ; d < Main.DIMENSIONS ; d++){
            for (Vector2<String, Long> curr : lemming[d]) {
                System.out.print(curr.toString());
            }
            System.out.println();
        }
    }

    //DEBUG
    public void printTable(){
        System.out.print("  ");

        for (int l1 = 0 ; l1 < table[0].length ; l1++){
            System.out.print("  ");
            System.out.print(l1);
            System.out.print("  ");
        }
        System.out.println();
        for (int l2 = 0; l2 < table.length ; l2++) {
            System.out.print(l2 + " ");
            for (int l1 = 0 ; l1 < table[0].length ; l1++) {
                Vector2<Long, Long> curr = table[l2][l1];
                System.out.print(curr.toString());
            }
            System.out.println();
        }
    }

}
