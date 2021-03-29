package task4.MedianSort.v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[] defaultList;

    public static void main(String[] args) {
        int t = scanner.nextInt();
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        defaultList = new int[n];
        for (int i = 0; i < n; i++) defaultList[i] = i + 1;

        for (int i = 0; i < t; i++) {
            if (beginCase(n) == null)
                break;
        }
    }

    private static List<Integer> beginCase(int n) {

        LinkedList<Integer> list = new LinkedList<>();
        int i = 0, j = 0, k = 0;
        int answer = 0;

        int end = 2;
        int middle = -1;
        int from = 0;
        int to = 0;

        do {
            if (middle == -1) {
                i = defaultList[0];
                j = defaultList[1];
            } else {
                i = list.get(middle - 1);
                j = list.get(middle);
            }

            if (end < n) {
                k = defaultList[end];
                answer = ask(i, j, k);
            } else {
                answer = askAll(list);
                if (answer == 1) return list;
            }
            if (answer == -1)
                return null;

            if (middle == -1) {
                if (answer != i) {
                    list.add(i);
                    list.add(answer);
                    if (answer != j)
                        list.add(j);
                    else
                        list.add(k);
                } else {
                    list.add(j);
                    list.add(i);
                    list.add(k);
                }
                from = 0;
                to = list.size();
                middle = (to + from + 1) / 2;
                end++;
            } else {

                if (answer == k) {
                    end++;
                    list.add(middle, k);
                    from = 0;
                    to = list.size();

                    middle = (to + from + 1) / 2;
                } else if (answer == i) {
                    to = middle - 1;
                    middle = ( to + from + 1 ) / 2;
                 //   if (middle == from) {
                    if (middle == 0) {
                        end++;
                        list.add(from, k);
                        from = 0;
                        to = list.size();
                        middle = (to + from + 1) / 2;;
                    }
                } else {
                    from = middle;
                    middle = (to + from + 1) / 2;
                  //  if (middle > to - 1) {
                    if (middle > list.size() - 1) {
                        end++;
                        list.add(to, k);
                        from = 0;
                        to = list.size();
                        middle = (to + from + 1) / 2;;
                    }
                }
            }

        } while (true);
    }

    private static int ask(int i, int j, int k) {
        System.out.println(String.format("%d %d %d", i, j, k));
        return scanner.nextInt();
    }

    private static int askAll(List<Integer> list) {
        StringBuilder sb = null;
        for (Integer num : list) {
            if (sb == null) {
                sb = new StringBuilder();
            } else {
                sb.append(" ");
            }
            sb.append(num);
        }
        System.out.println(sb);
        System.out.flush();
        return scanner.nextInt();
    }
}
