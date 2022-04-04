public class Vector2<X, Y> {
    private X first;
    private Y second;

    public Vector2(X first, Y second){
        this.first = first;
        this.second = second;
    }

    public X getFirst(){
        return first;
    }

    public Y getSecond(){
        return second;
    }
}
