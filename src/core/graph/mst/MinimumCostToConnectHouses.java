package core.graph.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a 2D array houses[][], consisting of n 2D coordinates {x, y} where each coordinate represents the location of each house, the task is to find the minimum cost to connect all the houses of the city.
 * The cost of connecting two houses is the Manhattan Distance between the two points (xi, yi) and (xj, yj) i.e., |xi – xj| + |yi – yj|, where |p| denotes the absolute value of p.
 */
public class MinimumCostToConnectHouses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // expected 7.
        System.out.println(solution.prim(new int[][] {
                {0, 0},
                {1, 1},
                {1, 3},
                {3, 0}
        }));
        System.out.println(solution.krustal(new int[][] {
                {0, 0},
                {1, 1},
                {1, 3},
                {3, 0}
        }));
    }

    private static class Solution {

        public int krustal(int[][] houses) {
            int n = houses.length;

            // construct the costs arrays.
            List<int[]> costs = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if (i != j) {
                        costs.add(new int[] { i, j, getCost(houses, i, j) });
                    }
                }
            }

            // sort the costs in non-descending order
            costs.sort((a, b) -> Integer.compare(a[2], b[2]));

            // build UnionFind that used to indicate if both two houses are visited.
            UnionFind uf = new UnionFind(n);

            int minCost = 0;
            for(int i = 0, count = 0; i < costs.size() && count < n - 1; i++) {
                int u = costs.get(i)[0];
                int v = costs.get(i)[1];
                int cost = costs.get(i)[2];

                // return true if (u, v) are not connected yet.
                if (uf.union(u, v)) {
                    minCost += cost;
                    count++;
                }
            }

            return minCost;
        }

        public int prim(int[][] houses) {
            int n = houses.length;

            boolean[] visited = new boolean[n];
            visited[0] = true;

            // <u, v, distance>
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
            for (int i = 1; i < n; i++) {
                pq.offer(new int[] { 0, i, getCost(houses, 0, i)});
            }

            int count = 0;
            int minCost = 0;
            while (count < n - 1) {
                int[] edge = pq.poll();
                int i = edge[1];
                int weight = edge[2];
                if (!visited[i]) {
                    count++; // find an minimum edge
                    visited[i] = true; // marked the connected house as visited.
                    minCost += weight;

                    // Move all houses that connected to the ith house into the priority queue.
                    for (int j = 0; j < n; j++) {
                        if (!visited[j]) {
                            pq.offer(new int[] {i, j, getCost(houses, i, j)});
                        }
                    }
                }
            }
            return minCost;
        }

        int getCost(int[][] houses, int i, int j) {
            return Math.abs(houses[i][0] - houses[j][0]) + Math.abs(houses[i][1] - houses[j][1]);
        }
    }

    static class UnionFind {
        int[] root;
        int[] rank;
        UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for(int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        boolean union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                if (rank[rx] > rank[ry]) {
                    root[ry] = rx;
                } else if (rank[rx] < rank[ry]) {
                    root[rx] = ry;
                } else {
                    root[ry] = rx;
                    rank[rx]++;
                }
                return true;
            }
            return false;
        }
    }

}
