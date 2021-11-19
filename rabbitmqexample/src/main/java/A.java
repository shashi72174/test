public interface A extends Super {
    public default void print() {
        System.out.println("print from A");
    }
}
