package util;

public class Pair<T, U> {
    public T left;
    public U right;

    public Pair() {}

    public Pair(T left, U right) {
        this();
        this.left = left;
        this.right = right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        Pair<T, U> p = (Pair<T, U>)obj;
        return this.left.equals(p.left) && this.right.equals(p.right);
    }

    @Override
    public int hashCode() {
        return 255 * this.left.hashCode() + this.right.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format(
            "(%s, %s)",
            this.left.toString(),
            this.right.toString()
        );
    }
}
