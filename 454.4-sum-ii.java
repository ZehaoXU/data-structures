import java.lang.*;
import java.util.*;
/*
 * @lc app=leetcode id=454 lang=java
 *
 * [454] 4Sum II
 *
 * https://leetcode.com/problems/4sum-ii/description/
 *
 * algorithms
 * Medium (51.12%)
 * Likes:    737
 * Dislikes: 60
 * Total Accepted:    72.7K
 * Total Submissions: 142.1K
 * Testcase Example:  '[1,2]\n[-2,-1]\n[-1,2]\n[0,2]'
 *
 * Given four lists A, B, C, D of integer values, compute how many tuples (i,
 * j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * 
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤
 * N ≤ 500. All integers are in the range of -2^28 to 2^28 - 1 and the result
 * is guaranteed to be at most 2^31 - 1.
 * 
 * Example:
 * 
 * 
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 
 * Output:
 * 2
 * 
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 沿用sum系列的hashMap思想，先将AB CD两两分组，然后计算AB中的和以及和的组成方式（count），然后一边计算CD的和一边判断-sum在不在mapAB中！
     * 不用重复计算CD的和，然后再遍历map寻找！
     * 时间复杂度 O(n^2); 空间复杂度 O(n);
     * Your runtime beats 9.14 % of java submissions
     * Your memory usage beats 12 % of java submissions (82.2 MB)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        if (n == 0) return 0;
        // A+B, sum->sumCount
        Map<Integer, Integer> mapAB = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = A[i] + B[j];
                mapAB.put(sum, mapAB.getOrDefault(sum, 0)+1);
            }
        }
        // C+D, sum->sumCount
        // 这一遍可以不要！直接边计算sum，一边判断-sum在不在mapAB中
        Map<Integer, Integer> mapCD = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = C[i] + D[j];
                mapCD.put(sum, mapCD.getOrDefault(sum, 0)+1);
            }
        }
        // find
        int res = 0;
        for (Integer num : mapAB.keySet()) {
            if (mapCD.containsKey(-1 * num)) {
                res += mapAB.get(num) * mapCD.get(-1 * num);
            }
        }

        return res;
    }
}

