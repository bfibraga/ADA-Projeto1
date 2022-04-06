import utils.Vector2;

import java.util.*;

public class Lemmings {

    //TODO Find a better data structure to get/find specific elements
    private List<Vector2<String, Integer>>[] lemming; // <Tribo do Lemming , Poder do Lemming >
    private Vector2<Integer, Integer>[][] table; //Solving problem table

    public Lemmings(int dimensions){
        this.lemming = new LinkedList[dimensions];
        for (int d = 0 ; d < dimensions ; d++){
            this.lemming[d] = new LinkedList<>();
        }
    }

    public void build(int dimension, Vector2<String, Integer> given_lemming){
        this.lemming[dimension].add(given_lemming);
    }

    /**
     * Creates a new solving table
     */
    public void createTable(){
        this.table = new Vector2[lemming[1].size()+1][lemming[0].size()+1];
    }

    /**
     * Solves this problem returning a pair that contains the maximum possible points with minimum number of pairs
     * @return Vector with 2 components
     */
    public Vector2<Integer, Integer> solve() {
        int res = 0;
        int nPairs = 0;

        //Base case

        //General case

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
        return new Vector2<>(-1, -1);
    }

    public void printLemmings(){
        for (int d = 0 ; d < Main.DIMENSIONS ; d++){
            for (Vector2<String, Integer> curr : lemming[d]) {
                System.out.print("(" + curr.getFirst() + "," + curr.getSecond() + ")");
            }
            System.out.println();
        }
    }

    //DEBUG
    public void printTable(){
        System.out.print("  ");

        for (int l = 0 ; l < table[0].length ; l++){
            System.out.print("  ");
            System.out.print(l);
            System.out.print("  ");
        }
        System.out.println();
        for (int d = 0; d < table.length; d++) {
            System.out.print(d + " ");
            for (int l = 0 ; l < table[0].length ; l++) {
                //Vector2<Integer, Integer> curr = table[d][l];
                System.out.print("(" + d + "," + l + ")");
            }
            System.out.println();
        }
    }

}
