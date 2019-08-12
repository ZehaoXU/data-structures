import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=675 lang=java
 *
 * [675] Cut Off Trees for Golf Event
 *
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 *
 * algorithms
 * Hard (31.16%)
 * Likes:    308
 * Dislikes: 180
 * Total Accepted:    18.1K
 * Total Submissions: 57.7K
 * Testcase Example:  '[[1,2,3],[0,0,4],[7,6,5]]'
 *
 * You are asked to cut off trees in a forest for a golf event. The forest is
 * represented as a non-negative 2D map, in this map:
 * 
 * 
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through,
 * and this positive number represents the tree's height.
 * 
 * 
 * 
 * 
 * You are asked to cut off all the trees in this forest in the order of tree's
 * height - always cut off the tree with lowest height first. And after
 * cutting, the original place has the tree will become a grass (value 1).
 * 
 * You will start from the point (0, 0) and you should output the minimum steps
 * you need to walk to cut off all the trees. If you can't cut off all the
 * trees, output -1 in that situation.
 * 
 * You are guaranteed that no two trees have the same height and there is at
 * least one tree needs to be cut off.
 * 
 * Example 1:
 * 
 * 
 * Input: 
 * [
 * ⁠[1,2,3],
 * ⁠[0,0,4],
 * ⁠[7,6,5]
 * ]
 * Output: 6
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 
 * [
 * ⁠[1,2,3],
 * ⁠[0,0,0],
 * ⁠[7,6,5]
 * ]
 * Output: -1
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 
 * [
 * ⁠[2,3,4],
 * ⁠[0,0,5],
 * ⁠[8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree
 * in (0,0) directly without walking.
 * 
 * 
 * 
 * 
 * Hint: size of the given matrix will not exceed 50x50.
 * 
 */
class Solution {
    /**
     * BFS
     * 程序构架：按照树的大小砍树，所以先对 [高度，row，col] 数组按照高度排序排序。每次用BFS查找start_r,start_c 到target_r,target_c
     * 时间复杂度 O(MN*MN) 一共要砍树 O(MN)，BFS计算当前到target O(MN) ；空间复杂度 O(MN)
     * Your runtime beats 7.61 % of java submissions
     * Your memory usage beats 20 % of java submissions (46.2 MB)
     * 
     * 注意：
     *  本题双向BFS反而更慢，因为维数并不是很大，这种不是纯考BFS的题，直接单向即可
     * @param forest
     * @return
     */
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    trees.add(new int[]{height, i, j});
                }
            }
        }
        Collections.sort(trees, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));

        int sr = 0, sc = 0;
        int res = 0;
        for (int[] tree : trees) {
            int tr = tree[1];
            int tc = tree[2];
            int steps = bfs(sr, sc, tr, tc, forest);
            if (steps == -1)    return -1;
            res += steps;
            sr = tr;
            sc = tc;
        }
        return res;
    }

    private int bfs(int sr, int sc, int tr, int tc, List<List<Integer>> forest) {
        if (sr == tr && sc == tc)   return 0;
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int n = forest.get(0).size();
        int m = forest.size();
        int[][] visited = new int[m][n];
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        s1.add(sr*n+sc);
        s2.add(tr*n+tc);
        visited[sr][sc] = 1;
        visited[tr][tc] = 1;
        
        int steps = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.size() > s2.size()) {
                Set<Integer> temp = s1;
                s1 = s2;
                s2 = temp;
            }
            steps++;
            Set<Integer> temp = new HashSet<>();
            for (Integer pos : s1) {
                int row = pos/n;
                int col = pos%n;
                for (int[] dir : dirs) {
                    int x = row + dir[0];
                    int y = col + dir[1];
                    if (x<0 || x>=m || y<0 || y>=n || forest.get(x).get(y)==0)
                        continue;
                    if (s2.contains(x*n+y))
                        return steps;
                    if (visited[x][y] == 0) {
                        visited[x][y] = 1;
                        temp.add(x*n+y);
                    }
                }
            }
            s1 = temp;
        }
        return -1;
    }
}

