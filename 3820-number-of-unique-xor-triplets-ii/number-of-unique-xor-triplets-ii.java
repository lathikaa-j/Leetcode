class Solution {
    public int uniqueXorTriplets(int[] nums) {

        int LIMIT = 2048;

        boolean[][] dp = new boolean[4][LIMIT];
        dp[0][0] = true;

        for (int x : nums) {

            boolean[][] next = new boolean[4][LIMIT];

            for (int c = 0; c <= 3; c++) {
                for (int v = 0; v < LIMIT; v++) {

                    if (!dp[c][v]) continue;

                    next[c][v] = true;

                    if (c < 3)
                        next[c + 1][v ^ x] = true;

                    if (c < 2)
                        next[c + 2][v] = true; 

                    if (c < 1)
                        next[c + 3][v ^ x] = true; 
                }
            }

            dp = next;
        }

        int ans = 0;

        for (boolean b : dp[3])
            if (b) ans++;

        return ans;
    }
}