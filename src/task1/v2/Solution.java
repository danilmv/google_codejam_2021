package task1.v2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int[] array = new int[100];

    private static int length;

    public static void main(String[] args) {
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            length = scanner.nextInt();
            for (int j = 0; j < length; j++)
                array[j] = scanner.nextInt();

            System.out.println("Case #" + (i + 1) + ": " + getNumOfReverseOperation());
        }
    }

    private static int getNumOfReverseOperation() {
        int result = 0;
        int j;
        int min;
        int nextI;

        for (int i = 0; i < length - 1; i++) {
            j = i;
            min = array[j];
            for (int k = i + 1; k < length; k++) {
                if (array[k] < min) {
                    j = k;
                    min = array[k];
                }
            }
            result += j - i + 1;
            Arrays.sort(array, i, j + 1);
            System.out.println("i = " + i + " j = " + j + " min = " + min + " result = " + result);
            System.out.println(Arrays.toString(array));

            if (j < length - 1 && i < length - 2) {
                nextI = getNextI(i, j);
                result += nextI - i;
                i = nextI;
            } else if (i != j) {
                result += j - i - 1;
                i = j;
            }

            System.out.println("i = " + i + " result = " + result);
        }
        return result;
    }

    private static int getNextI(int i, int j) {
        if (j >= length - 1)
            return j;
        if (array[j] > array[j + 1]) {
            return findPlace(i, j, array[j + 1]) - 1;
        }
        return j + 1;
    }

    private static int findPlace(int start, int end, int value) {
        if (start >= end)
            return start;

        int middle = (end + start) / 2;
        if (array[middle] > value)
            return findPlace(start, middle - 1, value);
        else if (array[middle] < value)
            return findPlace(middle + 1, end, value);

        return 0;
    }
}
