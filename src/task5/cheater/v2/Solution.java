package task5.cheater.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Solution {
    private static final int NUM_PARTICIPANTS = 100;
    private static final int NUM_TASKS = 10000;
    private static int p;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Case>> futures = new ArrayList<>();

        Case[] cases = readInput();
        for (Case c : cases)
            futures.add(service.submit(c));

        System.out.println("");

        for (Future<Case> future : futures) {
            Case c = future.get();
            System.out.println(String.format("Case #%d: %d", c.getCaseNum(), c.getCheater() + 1));
        }
        service.shutdown();
    }

    private static Case[] readInput() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        p = scanner.nextInt();
        Case[] cases = new Case[t];

        for (int i = 0; i < t; i++) {
            cases[i] = new Case(i + 1);
            for (int j = 0; j < NUM_PARTICIPANTS; j++) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    j--;
                    continue;
                }
                cases[i].addData(line);
            }
        }
        return cases;
    }

    static class Case implements Callable<Case> {
        int caseNum;

        byte[][] data = new byte[NUM_PARTICIPANTS][NUM_TASKS];
        int cheater;
        int curLine = 0;

        double[] skills = new double[NUM_PARTICIPANTS];
        double[] difficulties = new double[NUM_TASKS];
        int[] results = new int[NUM_PARTICIPANTS];
        double[] calculations = new double[NUM_PARTICIPANTS];

        public Case(int caseNum) {
            this.caseNum = caseNum;
        }

        public void addData(String line) {
            if (line.isEmpty()) return;

            char[] chars = line.toCharArray();
            for (int i = 0; i < line.length() && i < NUM_TASKS; i++)
                data[curLine][i] = (byte) (chars[i] == '1' ? 1 : 0);
            curLine++;
        }

        @Override
        public Case call() throws Exception {
            for (int i = 0; i < NUM_PARTICIPANTS; i++) {
//                if (i == 58) continue;
                for (int j = 0; j < NUM_TASKS; j++) {
                    results[i] += data[i][j];
                    difficulties[j] += data[i][j];
                }
                skills[i] = results[i] / (double) NUM_TASKS * 6 - 3;
            }

            for (int j = 0; j < NUM_TASKS; j++) {
                difficulties[j] = difficulties[j] / NUM_PARTICIPANTS * 6 - 3;
            }

            for (int i = 0; i < NUM_PARTICIPANTS; i++) {
                for (int j = 0; j < NUM_TASKS; j++) {
                    calculations[i] += f(skills[i], difficulties[j]);
                }
            }

            cheater = 0;
            double max = results[cheater] - calculations[cheater];

            for (int i = 1; i < NUM_PARTICIPANTS; i++) {
//                if (skills[i] < 0.5) continue;

                double dif = results[i] - calculations[i];
                if (dif > max) {
                    max = dif;
                    cheater = i;
                }
                System.out.println(String.format("%d:\tskill = %.3f \tresults = %d \tcalcs = %.3f\tdif = %.3f\tcheater = %d", i, skills[i], results[i], calculations[i], dif, cheater));
            }

            return this;
        }

        private double f(double s, double q) {
            return 1 / (1 + Math.exp(q - s));
        }

        public int getCaseNum() {
            return caseNum;
        }

        public int getCheater() {
            return cheater;
        }
    }
}
