package utils;

/**
 * @author Bruno Braga 57747
 * @author Goncalo Prata 52912
 * @param <X>
 * @param <Y>
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

    /**
     * Sets first component of this vector with new value
     * @param new_first - New value for the first component
     */
    public void setFirst(X new_first) {
        this.first = new_first;
    }

    /**
     * Sets second component of this vector with new value
     * @param new_second - New value for the second component
     */
    public void setSecond(Y new_second) {
        this.second = new_second;
    }

    public String toString(){
        return "(" + this.getFirst() + "," + this.getSecond() + ")";
    }
}
