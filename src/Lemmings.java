public class Lemmings {

    Vector2<String, Integer>[][] lemming; // <Tribo do Lemming , Poder do Lemming >

    public Lemmings(){
    }


    public void build(Vector2<String, Integer>[][] lemming_trial) {
    }

    public Vector2<Integer, Integer> solve() {
        int res = 0;
        int nPairs = 0;
        for (int i = 0; i < lemming.length; i++) {
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
        Vector2 <Integer, Integer> output = new Vector2<>(res, nPairs);
        return output;
    }
}
