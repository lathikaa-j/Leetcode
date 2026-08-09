class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suf = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            suf[i] = m - 1 - j;
        }

        int[] ans = new int[m];

        int pos = 0;
        int ansIndex = 0;
        boolean usedMismatch = false;

        for (j = 0; j < m; j++) {

            boolean found = false;

            while (pos < n) {

                if (word1.charAt(pos) == word2.charAt(j)) {
                    ans[ansIndex++] = pos;
                    pos++;
                    found = true;
                    break;
                }
                if (!usedMismatch && suf[pos + 1] >= m - j - 1) {
                    ans[ansIndex++] = pos;
                    pos++;
                    usedMismatch = true;
                    found = true;
                    break;
                }

                pos++;
            }

            if (!found) {
                return new int[0];
            }
        }

        return ans;
    }
}