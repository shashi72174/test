public interface B extends Super {
    public default void print() {
        System.out.println("print from B");
    }
}
