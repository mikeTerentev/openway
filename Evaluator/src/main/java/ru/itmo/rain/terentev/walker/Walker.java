package ru.itmo.rain.terentev.walker;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Walker {
    public static void main(String[] args) throws IOException {
        StreamTokenizer reader = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
        int[] dist;
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        reader.nextToken();
        int start = (int) reader.nval - 1;
        reader.nextToken();
        int finish = (int) reader.nval - 1;
        reader.nextToken();
        int n = (int) reader.nval;
        reader.nextToken();
        int m = (int) reader.nval;
        int INF = Integer.MAX_VALUE / 7;
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            reader.nextToken();
            int u = (int) reader.nval - 1;
            reader.nextToken();
            int v = (int) reader.nval - 1;
            g.get(u).add(v);
            g.get(v).add(u);
        }
        dist[start] = 0;
        queue.add(new Pair<>(0, start));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curE = queue.poll();
            int curInd = curE.getValue();
            int l = curE.getKey();
            if (l <= dist[curInd]) {
                for (int i = 0; i < g.get(curInd).size(); i++) {
                    int vertex = g.get(curInd).get(i);
                    if (dist[vertex] > dist[curInd] + 1) {
                        dist[vertex] = dist[curInd] + 1;
                        queue.add(new Pair<>(dist[vertex], vertex));
                    }
                }
            }
        }
        System.out.print(dist[finish]);
    }
}