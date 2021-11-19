import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test implements A,B {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","a");
        System.out.println(list.stream().collect(Collectors.groupingBy(String::toString)));
        System.out.println(list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())));
        Test test = new Test();
        test.print();
    }


    @Override
    public void print() {
        A.super.print();
        B.super.print();
    }
}
