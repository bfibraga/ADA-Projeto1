package utils;

/**
 * @author Bruno Braga 57747
 * @author Goncalo Prata 52912
 * @param <X> First component's generic type
 * @param <Y> Second component's generic type
 */
public class Vector2<X, Y> {
    private X first;
    private Y second;

    public Vector2(X first, Y second){
        this.first = first;
        this.second = second;
    }

    /**
     * Gets first component of this vector
     * @return first
     */
    public X getFirst(){
        return first;
    }

    /**
     * Gets second component of this vector
     * @return second
     */
    public Y getSecond(){
        return second;
    }

    public String toString(){
        return this.getFirst() + " " + this.getSecond();
    }
}
