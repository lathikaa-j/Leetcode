class Solution {
    static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        ArrayList<Integer> digits = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digits.add(d);
                pos.add(i);
            }
        }
        int m = digits.size();
        long[] prefixSum = new long[m + 1];
        long[] prefixNum = new long[m + 1];
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
        
        for (int i = 0; i < m; i++) {
            prefixSum[i + 1] = prefixSum[i] + digits.get(i);
            prefixNum[i + 1] = (prefixNum[i] * 10 + digits.get(i)) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;
            if (left > right) {
                ans[i] = 0;
                continue;
            }
            long sum = prefixSum[right + 1] - prefixSum[left];
            int len = right - left + 1;
            long number =
                    (prefixNum[right + 1]
                    - prefixNum[left] * pow10[len] % MOD
                    + MOD) % MOD;
            ans[i] = (int)((number * (sum % MOD)) % MOD);
        }
        return ans;
    }
    private int lowerBound(ArrayList<Integer> arr, int target) {

        int l = 0;
        int r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) < target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
    private int upperBound(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) <= target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
