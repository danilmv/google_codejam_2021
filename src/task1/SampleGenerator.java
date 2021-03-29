package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleGenerator {
    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) numbers.add(i);

        int t = random.nextInt(99) + 1;
        System.out.println(t);

        for (int i = 0; i < t; i++) {
            int length = random.nextInt(98) + 2;
            System.out.println(length);
            int[] array = new int[length];
            List<Integer> copy = new ArrayList<>(numbers);
            for (int j = 0; j < length; j++) {
                array[j] = copy.remove(random.nextInt(length - j));
            }
            for (int j = 0; j < length; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println("");
        }
    }
}
