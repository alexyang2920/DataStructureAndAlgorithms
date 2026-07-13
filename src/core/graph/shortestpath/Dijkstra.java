package core.graph.shortestpath;

import java.util.*;

/**
 * Given an undirected, weighted graph with V vertices numbered from 0 to V-1 and E edges, represented by 2d array edges[][], where edges[i]=[u, v, w] represents the edge between the nodes u and v having w edge weight.
 * You have to find the shortest distance of all the vertices from the source vertex src, and return an array of integers where the ith element denotes the shortest distance between ith node and source vertex src.
 * Note: The Graph is connected and doesn't contain any negative weight edge.
 * It is guaranteed that all the shortest distance will fit in a 32-bit integer.
 */
public class Dijkstra {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] dist = solution.dijkstra(3, new int[][] {
                {0, 1, 1},
                {1, 2, 3},
                {0, 2, 6}
        }, 2);

        for(int distance : dist) {
            System.out.println(distance);
        }
    }


    /**
     * Time complexity:
     *  - Graph construction: O(V + E)
     *  - Main loop: O(E*log(E))
     * Space complexity: O(V + E)
     */
    private static class Solution {
        public int[] dijkstra(int n, int[][] edges, int src) {
            // 1. build graph
            List<List<int[]>> graph = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                graph.get(u).add(new int[] { v, w });
                graph.get(v).add(new int[] { u, w });
            }

            // 2. Cache the minimum distance between src and all nodes.
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // 3. build min-heap priority queue
            PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
            q.offer(new Node(src, 0));

            boolean[] visited = new boolean[n];

            while(!q.isEmpty()) {
                Node node = q.poll();
                int u = node.id;
                if (visited[u]) {
                    continue;
                }

                visited[u] = true;

                for(int[] edge : graph.get(u)) {
                    int v = edge[0], w = edge[1];
                    if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        q.offer(new Node(v, dist[v]));
                    }
                }
            }

            return dist;
        }

        static class Node {
            int id;
            int distance;

            Node(int id, int distance) {
                this.id = id;
                this.distance = distance;
            }
        }
    }
}
