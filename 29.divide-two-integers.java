/*
 * @lc app=leetcode id=29 lang=java
 *
 * [29] Divide Two Integers
 */
class Solution {
    /**
     * 目前的方法比较丑陋，原计划被除数倍增，结果超时；于是改用除数的2的幂次方倍方法，一定能在31步递归之内得到结果（因为数据范围是[-2^31 ~ 2^31-1]）
     * 一开始divident & divisor都转换成负数，因为负数范围比正数大，但后来还是迫不得已用了long类型
     * 时间复杂度 O(logn) n表示被除数大小
     * 
     * 其他方法：位运算，通过除数左移k位（乘以2^k）实现，O(logn)。https://leetcode.com/problems/divide-two-integers/discuss/333577/java-beats-100
     */
    int res = 0;
    int num;
    int div;
    
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) 
            return Integer.MAX_VALUE;
        int flag = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        num = (dividend < 0) ? dividend : -dividend;
        div = (divisor < 0) ? divisor : -divisor;
        
        solve(div, 1);
        return res * flag;
    }

    public void solve(long divisor, long times){
        if (num > divisor) return;
        solve(divisor + divisor, times + times);
        if (num <= divisor){
            num -= divisor;
            res += times;
        }
    }
}

