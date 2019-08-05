import java.util.Map;

/*
 * @lc app=leetcode id=952 lang=java
 *
 * [952] Largest Component Size by Common Factor
 *
 * https://leetcode.com/problems/largest-component-size-by-common-factor/description/
 *
 * algorithms
 * Hard (26.50%)
 * Likes:    116
 * Dislikes: 31
 * Total Accepted:    5.1K
 * Total Submissions: 19.3K
 * Testcase Example:  '[4,6,15,35]'
 *
 * Given a non-empty array of unique positive integers A, consider the
 * following graph:
 * 
 * 
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a
 * common factor greater than 1.
 * 
 * 
 * Return the size of the largest connected component in the graph.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [4,6,15,35]
 * Output: 4
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [20,50,9,63]
 * Output: 2
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [2,3,6,7,4,12,21,39]
 * Output: 8
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 100000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * UnionFind，如果直接用辗转相除确定两数公约数，时间复杂度O(n^2*logW)，超时
     * 直接分解因数并记录一个从因数->序号的与映射，这样只要遍历一遍，每个数字可以在sqrt(W)时间内求出所有因数并维护map
     * 时间复杂度 O(N*W) W是最大数字；空间复杂度 O(N + W)
     * Your runtime beats 50.79 % of java submissions
     * Your memory usage beats 35 % of java submissions (51.2 MB)
     * @param A
     * @return
     */
    public int largestComponentSize(int[] A) {
        int n = A.length;
        UnionFind uf = new UnionFind(n);
        // build the map
        // factor to Index. For a newIndex, if factor exits, just union map.get(factor) and newIndex
        Map<Integer, Integer> factorToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = A[i];
            for (int r = 2; r*r <= a; r++) {
                if (a % r == 0) {
                    if (factorToIndex.containsKey(r)) {
                        uf.union(i, factorToIndex.get(r));
                    }
                    else {
                        factorToIndex.put(r, i);
                    }
                    if (factorToIndex.containsKey(a/r)) {
                        uf.union(i, factorToIndex.get(a/r));
                    }
                    else {
                        factorToIndex.put(a/r, i);
                    }
                }
            }
            if (!factorToIndex.containsKey(a)) {
                factorToIndex.put(a, i);
            }
            else {
                uf.union(factorToIndex.get(a), i);
            }
        }
        
        return uf.max;
    }
}

class UnionFind {
    private int[] parents;
    private int[] weights;	// 树的大小，O(logn)
    public int max;
    
    public UnionFind(int n) {
        parents = new int[n];
        weights = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            weights[i] = 1;
            max = 1;
        }
    }
    
    public int find(int i) {
        // 千万注意这里是if 不是while!!!!!
        if (parents[i] != i) {
            parents[i] = find(parents[i]);	// path compress
        }
        return parents[i];
    }
    
    public boolean union(int i, int j) {	// 也可以void
        int rooti = find(i);
        int rootj = find(j);
        if (rooti == rootj) {
            return false;
        }
        // 小树挂大树，实际上这步用处不大
        
        parents[rootj] = rooti;
        weights[rooti] += weights[rootj];
        max = Math.max(max, weights[rooti]);
    
        return true; 
    }
}

