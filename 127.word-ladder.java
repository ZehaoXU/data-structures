import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (24.89%)
 * Likes:    1634
 * Dislikes: 861
 * Total Accepted:    282.2K
 * Total Submissions: 1.1M
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * 
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * Note:
 * 
 * 
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog",
 * return its length 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible
 * transformation.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 隐式图找最短路径，BFS，由于需要记录层数，故多了一层循环，用两个队列交替完成。也可以记录每次q的length，然后只对这段length长度进行poll，可以少一个队列的空间
     * 时间复杂度 O(N*L) N是单词个数，L是长度；空间复杂度 O(N*L)
     * Your runtime beats 39.22 % of java submissions
     * Your memory usage beats 13.14 % of java submissions (45.6 MB)
     * 
     * 优化方法：
     *  双向BFS！
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0)   return 0;
        int L = wordList.get(0).length();
        // build map
        Map<String, List<String>> map = new HashMap<>();
        wordList.forEach(
            word -> {
                for (int i = 0; i < L; i++) {
                    String s = word.substring(0, i) + "*" + word.substring(i+1);
                    if (!map.containsKey(s)) {
                        map.put(s, new ArrayList<>());
                    }
                    map.get(s).add(word);
                }
            }
        );
        // bfs
        int path = 1;
        Queue<String> q = new LinkedList<>();
        Set<String> set = new HashSet<>();
        q.add(beginWord);
        set.add(beginWord);
        Queue<String> temp = new LinkedList<>();
        // level traverse
        do {
            temp = new LinkedList<>();
            path++;
            while (!q.isEmpty()) {
                String cur = q.poll();
                Set<String> neighbors = new HashSet<>();
                for (int i = 0; i < L; i++) {
                    String s = cur.substring(0,i) + "*" + cur.substring(i+1);
                    if (map.containsKey(s))
                        neighbors.addAll(map.get(s));
                }
                for (String neighbor : neighbors) {
                    if (!set.contains(neighbor)) {
                        temp.add(neighbor);
                        set.add(neighbor);
                    }
                }
            }
            q = temp;
        } while (temp.size() != 0 && !set.contains(endWord));

        if (set.contains(endWord))  return path;
        return 0;
    }
}

