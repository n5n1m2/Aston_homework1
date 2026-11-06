import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        CustomMap<String, String> map = new CustomHashMap<>();
        String test = "test";
        String val = "val";
        String newTest = new String("Test");

        map.put(test, val);
        System.out.println(map.get(test)); //val
        System.out.println(map.get(newTest));//null
        map.remove(test);
        System.out.println(map.get(test));//null

        CustomMap<ClassForCollisions, String> map1 = new CustomHashMap<>();
        List<ClassForCollisions> collisions = IntStream.rangeClosed(1, 10)
                .mapToObj((num) -> new ClassForCollisions(Integer.toString(num)))
                .toList();

        for (ClassForCollisions collision : collisions) {
            map1.put(collision, "test" + collision.getField());
        }

        for (ClassForCollisions collision : collisions) {
            System.out.println(map1.get(collision));//test1-10
        }
    }
}