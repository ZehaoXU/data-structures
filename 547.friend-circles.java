import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=547 lang=java
 *
 * [547] Friend Circles
 */
class Solution {
    /**
     * UnionFind，基本默写模板就行，先union建图，然后find找几个树
     * 时间复杂度 O(n^2); 空间复杂度 O(n);
     * Your runtime beats 54.7 % of java submissions
     * Your memory usage beats 46.84 % of java submissions (44.2 MB)
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int n = M.length;
        // build graph
        UnionFind students = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (M[i][j] == 1) {
                    students.union(i, j);
                }
            }
        }
        // find circles
        Set<Integer> circles = new HashSet<>();
        for (int i = 0; i < n; i++) {
            circles.add(students.find(i));
        }
        return circles.size();
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
    // if union return true, else no need to union return false
    public boolean union(int i, int j) {
        int rooti = find(i);
        int rootj = find(j);
        if (rooti == rootj) {
            return false;
        }
        if (weights[rooti] <= weights[rootj]) {
            parents[rooti] = rootj;
            weights[rootj] += weights[rooti];
        }
        else {
            parents[rootj] = rooti;
            weights[rooti] += weights[rootj];
        }
        return true;
    }
}

