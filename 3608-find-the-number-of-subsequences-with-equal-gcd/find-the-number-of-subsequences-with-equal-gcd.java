class Solution {
    int MOD = 1000000007;
    int[][][] dp;
    int[] nums;
    int n;
    
    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;

        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        dp = new int[n + 1][max + 1][max + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= max; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return dfs(0, 0, 0);
    }

    private int dfs(int idx, int g1, int g2) {

        if (idx == n) {
            return (g1 != 0 && g1 == g2) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1)
            return dp[idx][g1][g2];

        long ans = 0;

        ans += dfs(idx + 1, g1, g2);
        ans += dfs(idx + 1, gcd(g1, nums[idx]), g2);
        ans += dfs(idx + 1, g1, gcd(g2, nums[idx]));
        ans %= MOD;

        return dp[idx][g1][g2] = (int) ans;
    }

    private int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
