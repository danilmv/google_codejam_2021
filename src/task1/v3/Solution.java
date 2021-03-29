package task1.v3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Solution {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Solution solution = new Solution();
        Sample[] samples = solution.readInput();
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Sample>> futures = new ArrayList<>();

        for (int i = 0; i < samples.length; i++) {
            futures.add(executor.submit(samples[i]));
        }

        for (Future<Sample> future : futures) {
            Sample sample = future.get();
            System.out.println(String.format("Case #%d: %d", sample.getCaseNum(), sample.getResult()));
        }

        executor.shutdown();
    }

    private Sample[] readInput() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        Sample[] samples = new Sample[t];
        for (int i = 0; i < t; i++) {
            samples[i] = new Sample(scanner.nextInt(), i + 1);
            for (int j = 0; j < samples[i].getLength(); j++)
                samples[i].add(scanner.nextInt());
        }
        return samples;
    }

    static class Sample implements Callable<Sample> {
        int caseNum;
        int length;
        int[] numbers;
        int result = -1;

        private int position;

        public Sample(int length, int caseNum) {
            this.caseNum = caseNum;
            this.length = length;
            if (length > 0) numbers = new int[length];
        }

        public int getCaseNum() {
            return caseNum;
        }

        public int getLength() {
            return length;
        }

        public void add(Integer num) {
            numbers[position++] = num;
        }

        public void reverse(int i, int j) {
            Arrays.sort(numbers, i, j + 1);
        }

        public int getResult() {
            return result;
        }

        public int[] getNumbers() {
            return numbers;
        }

        @Override
        public Sample call() throws Exception {
            return getNumOfReverseOperation();
        }

        public Sample getNumOfReverseOperation() {
            int j;
            int min;

            if (length > 0)
                result = 0;

            for (int i = 0; i < length - 1; i++) {
                j = i;
                min = numbers[j];
                for (int k = i + 1; k < length; k++) {
                    if (min == i + 1)
                        break;
                    if (numbers[k] < min) {
                        j = k;
                        min = numbers[k];
                    }
                }
                result += j - i + 1;
                reverse(i, j);
            }

            return this;
        }
    }
}
