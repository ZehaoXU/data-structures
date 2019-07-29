/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 */
class Solution {
    /**
     * 分治法
     * 时间复杂度 O(logn); 空间复杂度 O(logn)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.19 % of java submissions (33.5 MB)
     * 
     * 注意：高度对称，故一路分治！切勿两路分支，否则时间复杂度O(n)
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return solver(x, n);
    }

    public double solver(double x, int n){
        if (n == 0) return 1;
        
        if (n%2 == 0)   return solver(x * x, n/2);
        else    return solver(x * x, n/2) * (n > 0 ? x : 1/x);
    }
}

