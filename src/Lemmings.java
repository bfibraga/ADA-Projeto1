import utils.Vector2;


public class Lemmings {

    private Vector2<String, Long>[][] lemming; // <Tribo do Lemming , Poder do Lemming >
    private Vector2<Long, Long>[][] table; //Solving problem table

    @SuppressWarnings("unchecked")
    public Lemmings(int dimensions){
        this.lemming = new Vector2[dimensions][];
    }

    public void build(int dimension, Vector2<String, Long>[] given_lemming){
        this.lemming[dimension] = given_lemming;
    }

    /**
     * Creates a new solving table
     */
    @SuppressWarnings("unchecked")
    public void createTable(){
        int lines = lemming[1] != null ? lemming[1].length+1 : 1;
        int rows = lemming[0] != null ? lemming[0].length+1 : 1;
        this.table = new Vector2[rows][lines];
    }

    /**
     * Solves this problem returning a pair that contains the maximum possible points with minimum number of pairs
     * @return Vector with 2 components
     */
    public Vector2<Long,Long> solve() {
        //Base case
        Vector2<Long, Long> base = new Vector2<>(0L, 0L);
        Vector2<Long, Long> res = base;
        //First Line
        for (int l1 = 0 ; l1 < table[0].length; l1++){
            table[0][l1] = base;
        }

        //First Column
        for (int l2 = 1 ; l2 < table.length; l2++){
            table[l2][0] = base;
        }

        //General cases
        for (int l1 = 1 ; l1 < table[0].length; l1++){
            for (int l2 = 1 ; l2 < table.length; l2++){
                Vector2<String, Long> lemming1 = this.lemming[1][l1-1];
                Vector2<String, Long> lemming2 = this.lemming[0][l2-1];
                String lemming1_type = lemming1.getFirst();
                String lemming2_type = lemming2.getFirst();
                long lemming1_score = lemming1.getSecond();
                long lemming2_score = lemming2.getSecond();
                boolean same_type = lemming1_type.equals(lemming2_type);

                //Best case
                //1: Fall lemming of l1
                //2: Fall lemming of l2
                //3: Fall both lemmings if both are equals
                table[l2][l1] = same_type ?
                        this.bestOption(this.table[l2-1][l1], this.table[l2][l1-1], this.table[l2-1][l1-1], lemming1_score + lemming2_score) :
                        this.bestOption(this.table[l2-1][l1], this.table[l2][l1-1], this.table[l2-1][l1-1], 0L);

                //TODO Fixing
                //Finding the best candidate
                //1: Score is greater than the current result
                //2: If Score is equal
                //  - Number pair is lesser than the current result
                long best_score = res.getFirst();
                long best_nmr_pair = res.getSecond();
                if (table[l2][l1].getFirst() > best_score) {
                    res = table[l2][l1];
                } else {
                    if (table[l2][l1].getFirst() == best_score && table[l2][l1].getSecond() < best_nmr_pair) {
                        res = table[l2][l1];
                    }
                }
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

        return res;
    }

    /**
     * Finds the best option between falling the lemming of first line, second line or both if are the same type
     * @param line_fall
     * @param column_fall
     * @param both_fall
     * @param bonus_score
     * @return
     */
    private Vector2<Long, Long> bestOption(Vector2<Long, Long> line_fall, Vector2<Long, Long> column_fall, Vector2<Long, Long> both_fall, long bonus_score) {
        Vector2<Long, Long> res;

        if (line_fall.getFirst() >= column_fall.getFirst()){
            res = line_fall;
        } else {
            res = column_fall;
        }

        if (both_fall != null && both_fall.getFirst() >= res.getFirst()){
            res = new Vector2<>(both_fall.getFirst() + bonus_score, both_fall.getSecond() + 1L );
        }

        return res;
    }

    //DEBUG
    //TODO Remove this later
    public void printLemmings(){
        for (int d = 0 ; d < Main.DIMENSIONS ; d++){
            if (lemming[d] != null) {
                for (Vector2<String, Long> curr : lemming[d]) {
                    System.out.print(curr.toString());
                }
            }
            System.out.println();
        }
    }

    //DEBUG
    //TODO Remove this later
    public void printTable(){
        System.out.print("  ");

        for (int l1 = 0 ; l1 < table[0].length ; l1++){
            System.out.print("  ");
            System.out.print(l1);
            System.out.print("  ");
        }
        System.out.println();
        for (int l2 = 0 ; l2 < table.length; l2++){
            System.out.print(l2 + " ");
            for (int l1 = 0 ; l1 < table[0].length; l1++){
                Vector2<Long, Long> curr = table[l2][l1];
                System.out.print(curr.toString());
            }
            System.out.println();
        }
    }

}
