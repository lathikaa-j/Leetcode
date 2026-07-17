class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);
        int[] freq = new int[max + 1];

        for (int x : nums)
            freq[x]++;

        long[] exact = new long[max + 1];
        for (int d = max; d >= 1; d--) {

            long count = 0;
            for (int m = d; m <= max; m += d)
                count += freq[m];

            exact[d] = count * (count - 1) / 2;
            for (int m = d + d; m <= max; m += d)
                exact[d] -= exact[m];
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long k = queries[i] + 1;

            int l = 1;
            int r = max;

            while (l < r) {
                int mid = (l + r) / 2;

                if (prefix[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}
