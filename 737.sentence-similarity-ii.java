import java.util.Map;

/*
 * @lc app=leetcode id=737 lang=java
 *
 * [737] Sentence Similarity II
 *
 * https://leetcode.com/problems/sentence-similarity-ii/description/
 *
 * algorithms
 * Medium (43.77%)
 * Likes:    315
 * Dislikes: 18
 * Total Accepted:    24.6K
 * Total Submissions: 56.2K
 * Testcase Example:  '["great","acting","skills"]\n["fine","drama","talent"]\n[["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]'
 *
 * Given two sentences words1, words2 (each represented as an array of
 * strings), and a list of similar word pairs pairs, determine if two sentences
 * are similar.
 * 
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine",
 * "drama", "talent"] are similar, if the similar word pairs are pairs =
 * [["great", "good"], ["fine", "good"], ["acting","drama"],
 * ["skills","talent"]].
 * 
 * Note that the similarity relation is transitive. For example, if "great" and
 * "good" are similar, and "fine" and "good" are similar, then "great" and
 * "fine" are similar.
 * 
 * Similarity is also symmetric. For example, "great" and "fine" being similar
 * is the same as "fine" and "great" being similar.
 * 
 * Also, a word is always similar with itself. For example, the sentences
 * words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though
 * there are no specified similar word pairs.
 * 
 * Finally, sentences can only be similar if they have the same number of
 * words. So a sentence like words1 = ["great"] can never be similar to words2
 * = ["doubleplus","good"].
 * 
 * Note:
 * 
 * 
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * unionFind, union过程没有优化，由于序列是String，比较好的办法是建一个String->Integer的map，unionFind单元照常写
     * 另外一种办法是unionFind的parents写成Map<String,String>，find一个String的时候如果不存在顺便添加上，union正好也要先调用find，可以成图，https://leetcode.com/problems/sentence-similarity-ii/discuss/350797/My-Java-UnionFind-solution-beat-96
     * 时间复杂度 O(P + W); 空间复杂度 O(P)
     * Your runtime beats 86.79 % of java submissions
     * Your memory usage beats 82.86 % of java submissions (44.9 MB)
     * @param words1
     * @param words2
     * @param pairs
     * @return
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;

        Map<String, Integer> map = new HashMap<String, Integer>();
        int num = 0;
        UnionFind dsu = new UnionFind(2*pairs.size());
        // mapping and union to build graph
        for (List<String> pair: pairs) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);
            if (!map.containsKey(s1))   map.put(s1, num++);
            if (!map.containsKey(s2))   map.put(s2, num++);
            dsu.union(map.get(s1), map.get(s2));
        }

        // are similar
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i], w2 = words2[i];
            if (w1.equals(w2)) {
                continue;
            }
            if (!map.containsKey(w1) || !map.containsKey(w2) || dsu.find(map.get(w1)) != dsu.find(map.get(w2))) {
                return false;
            }
            
        }
        return true;
    }
}

class UnionFind {
    private int[] parents;
    private int[] weights;
    public UnionFind(int n) {
        parents = new int[n];
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            weights[i] = 1;
        }
    }
    public int find(int i) {
        if (parents[i] != i) {
            parents[i] = find(parents[i]);
        }
        return parents[i];
    }
    public boolean union(int i, int j) {
        int rooti = find(i);
        int rootj = find(j);
        if (rooti == rootj) {
            return false;
        }
        parents[rooti] = rootj;
        weights[rootj] += weights[rooti];
        
        return true; 
    }
}

