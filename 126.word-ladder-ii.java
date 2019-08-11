import java.util.*;
import java.lang.*;

/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 *
 * https://leetcode.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (18.34%)
 * Likes:    1065
 * Dislikes: 197
 * Total Accepted:    129.4K
 * Total Submissions: 703.8K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s) from beginWord to endWord, such
 * that:
 * 
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * Note:
 * 
 * 
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
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
 * Output: []
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
     * 好难，做了两个多小时
     * 和Q127最大的不同是需要记录路径，DFS是一种能记录路径的方法，不过dfs不知道最短路径是多长，遍历所有情况后才能知道，效率低。
     * 想到BFS+DFS的方法，并不是所有路径都需要生成，只要生成遇到target之前的所有路径即可。同时要注意，可能同层不同节点都指向下层同一节点，所以不能遍历一个visited标记一个，要一层一起标记
     * 双向BFS生成图，只用了两个set一个visited，交替进行。个人感觉还是两个栈两个队列好理解
     * 时间复杂度 O(N*L); 空间复杂度 O(N*L)
     * Your runtime beats 91.83 % of java submissions
     * Your memory usage beats 86.54 % of java submissions (43.4 MB)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0)   return null;
        if (!wordList.contains(endWord))    return new ArrayList<>();
        int l = beginWord.length();
        // build the map
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < l; i++) {
                String s = word.substring(0, i) + "*" + word.substring(i+1);
                map.putIfAbsent(s, new ArrayList<>());
                map.get(s).add(word);
            }
        }
        // build the graph
        boolean found = false;
        boolean backward = false;
        Map<String, List<String>> children = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);
        while (!found && !s1.isEmpty() && !s2.isEmpty()) {
            if (s1.size() > s2.size()) {
                Set<String> temp = s1;
                s1 = s2;
                s2 = temp;
                backward = !backward;
            }
            visited.addAll(s1);
            visited.addAll(s2);
            Set<String> s = new HashSet<>();
            for (String word : s1) {
                for (int i = 0; i < l; i++) {
                    String curr = word.substring(0, i) + "*" + word.substring(i+1);
                    if (!map.containsKey(curr)) continue;
                    for (String neighbor : map.get(curr)) {
                        if (s2.contains(neighbor)) {
                            found = true;
                            if (backward) {
                                if (children.containsKey(neighbor)) children.get(neighbor).add(word);
                                else{
                                    children.put(neighbor, new ArrayList<>());
                                    children.get(neighbor).add(word);
                                }
                            }
                            else {
                                if (children.containsKey(word)) children.get(word).add(neighbor);
                                else{
                                    children.put(word, new ArrayList<>());
                                    children.get(word).add(neighbor);
                                }
                            }
                        }
                        else if (!visited.contains(neighbor)) {
                            s.add(neighbor);
                            if (backward) {
                                if (children.containsKey(neighbor)) children.get(neighbor).add(word);
                                else{
                                    children.put(neighbor, new ArrayList<>());
                                    children.get(neighbor).add(word);
                                }
                            }
                            else {
                                if (children.containsKey(word)) children.get(word).add(neighbor);
                                else{
                                    children.put(word, new ArrayList<>());
                                    children.get(word).add(neighbor);
                                }
                            }
                        }
                    }
                }
            }
            s1 = s;
        }

        List<List<String>> res = new ArrayList<>();
        if (found) {
            List<String> path = new ArrayList<>(Arrays.asList(beginWord));
            dfs(beginWord, endWord, children, path, res);
        }
        return res;
    }

    public void dfs(String begin, 
                    String target, 
                    Map<String, List<String>> children, 
                    List<String> path, 
                    List<List<String>> res) {
        if (begin.equals(target)) {
            res.add(new ArrayList<String>(path));
            return;
        }
        if (!children.containsKey(begin))   return;
        for (String neighbor : children.get(begin)) {
            path.add(neighbor);
            dfs(neighbor, target, children, path, res);
            path.remove(path.size()-1);
        }
    }
}

