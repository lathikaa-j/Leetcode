class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    int nodeCount;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                nodeCount = 0;
                edgeCount = 0;

                dfs(i);

                edgeCount /= 2;

                int expected = nodeCount * (nodeCount - 1) / 2;

                if (edgeCount == expected)
                    ans++;
            }
        }

        return ans;
    }

    private void dfs(int node) {

        visited[node] = true;

        nodeCount++;

        edgeCount += graph[node].size();

        for (int nei : graph[node]) {

            if (!visited[nei])
                dfs(nei);
        }
    }
}