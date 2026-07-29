class Solution {

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            halfLen += half[i];
            if ((cnt[i] & 1) == 1) mid = (char) ('a' + i);
        }

        if (countWays(half, halfLen, k) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1, k);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    // Counts distinct permutations, capped at limit
    private long countWays(int[] freq, int len, int limit) {

        long ans = 1;
        int remaining = len;

        for (int i = 0; i < 26; i++) {

            int f = freq[i];

            if (f == 0) continue;

            ans *= combLimited(remaining, f, limit, ans);

            if (ans >= limit)
                return limit;

            remaining -= f;
        }

        return ans;
    }

    // Computes C(n,r), stopping once result exceeds limit/current
    private long combLimited(int n, int r, int limit, long current) {

        if (r > n) return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res > limit)
                return limit;
        }

        return res;
    }
}