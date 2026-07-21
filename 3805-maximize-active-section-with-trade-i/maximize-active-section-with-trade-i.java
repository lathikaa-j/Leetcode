class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray())
            if (c == '1')
                ones++;

        String t = "1" + s + "1";

        List<Character> type = new ArrayList<>();
        List<Integer> len = new ArrayList<>();

        int count = 1;
        for (int i = 1; i < t.length(); i++) {
            if (t.charAt(i) == t.charAt(i - 1)) {
                count++;
            } else {
                type.add(t.charAt(i - 1));
                len.add(count);
                count = 1;
            }
        }

        type.add(t.charAt(t.length() - 1));
        len.add(count);

        int gain = 0;

        for (int i = 1; i < type.size() - 1; i++) {
            if (type.get(i - 1) == '0' &&
                type.get(i) == '1' &&
                type.get(i + 1) == '0') {

                gain = Math.max(gain,
                        len.get(i - 1) + len.get(i + 1));
            }
        }

        return ones + gain;
    }
}
