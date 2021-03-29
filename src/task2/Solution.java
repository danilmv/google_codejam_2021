package task2;

import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char symbol = '?';

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int offset = 0;
            int fine = 0;
            char[] line = scanner.nextLine().replace("?", "").toCharArray();
            for (int j = 0; j < line.length - 1; j++) {
                if (line[j] == 'C' && line[j + 1] == 'J')
                    fine += x;
                else if (line[j] == 'J' && line[j + 1] == 'C')
                    fine += y;
            }

            System.out.println("Case #" + (i + 1) + ": " + fine);
        }
    }
}
