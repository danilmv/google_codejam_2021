package task4.MedianSort.v2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static int[] defaultList;

    private static BufferedReader br;
    private static StringTokenizer st;
    private static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = readInt();
        int n = readInt();
        int q = readInt();

        defaultList = new int[n];
        for (int i = 0; i < n; i++) defaultList[i] = i + 1;

        for (int i = 0; i < t; i++) {
            if (beginCase(n) == null)
                break;
        }
    }

    private static List<Integer> beginCase(int n) throws IOException {

        LinkedList<Integer> list = new LinkedList<>();
        int i = 0, j = 0, k = 0;
        int answer = 0;

        int end = 2;
        int middle = -1;

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
                middle = (list.size() + 1) / 2;
                end++;
            } else {

                if (answer == k) {
                    end++;
                    list.add(middle, k);

                    middle = (list.size() + 1) / 2;
                } else if (answer == i) {
                    middle = middle / 2;
                    if (middle == 0) {
                        end++;
                        list.addFirst(k);
                        middle = (list.size() + 1) / 2;
                    }
                } else {
                    middle = (list.size() + middle + 1) / 2;
                    if (middle > list.size() - 1) {
                        end++;
                        list.addLast(k);
                        middle = (list.size() + 1) / 2;
                    }
                }
            }

//            System.out.println(list);

        } while (true);
    }

    private static int ask(int i, int j, int k) throws IOException {
        System.out.println(String.format("%d %d %d", i, j, k));
        System.out.flush();
        return readInt();
    }

    private static int askAll(List<Integer> list) throws IOException {
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
        return readInt();
    }

    private static int readInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private static String nextLine() throws IOException {
        String s = br.readLine();
        if (s == null) {
            exitImmediately();
        }
        st = null;
        return s;
    }

    private static String nextToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(nextLine().trim());
        }
        return st.nextToken();
    }

    private static void exitImmediately() {
        pw.close();
        System.exit(0);
    }
}
