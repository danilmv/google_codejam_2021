package task1.v1;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);
    private static Sample[] samples;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readInput();
        for (int i = 0; i < samples.length; i++) {
            System.out.println(samples[i].getDescription() + ": " + solution.getNumOfReverseOperation(samples[i]));
        }
    }

    private void readInput() {
        int t = scanner.nextInt();
        samples = new Sample[t];
        for (int i = 0; i < t; i++) {
            samples[i] = new Sample(scanner.nextInt(), "Case #" + (i + 1));
            for (int j = 0; j < samples[i].getLength(); j++)
                samples[i].add(scanner.nextInt());
        }
    }

    private int getNumOfReverseOperation(Sample sample) {
        int result = 0;
        int j = -1;
        int min = 0;

        for (int i = 0; i < sample.getLength() - 1; i++) {
            j = i;
            min = sample.getNumber(j);
            for (int k = i + 1; k < sample.getLength(); k++) {
                if (sample.getNumber(k) < min) {
                    j = k;
                    min = sample.getNumber(k);
                }
            }
            result += j - i + 1;
            sample.reverse(i, j);
            if (j - i > 1) {
                result += j - i - 1;
            }
            i = j + 1;
        }
        return result;
    }

    static class Sample {
        String description;
        int length;
        int[] numbers;

        private int position;

        public Sample(int length, String description) {
            this.description = description;
            this.length = length;
            if (length > 0) numbers = new int[length];
        }

        public String getDescription() {
            return description;
        }

        public int getLength() {
            return length;
        }

        public void add(Integer num) {
            numbers[position++] = num;
        }

        public int[] getNumbers() {
            return numbers;
        }

        public Integer getNumber(int position) {
            if (position < 0 || position > length)
                return null;
            return numbers[position];
        }

        public void reverse(int i, int j) {
            Arrays.sort(numbers, i, j + 1);
        }

    }
}
