/*
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 */
class Solution {
    /**
     * 每层双指针遍历得到下一层
     * 时间复杂度 O(n^2)
     * Your runtime beats 14.57 % of java submissions
     * Your memory usage beats 48.05 % of java submissions (36 MB)
     * 
     * 提升空间利用率（因为String是不可变对象，每次+都分配一块新空间，指向新String）：使用StringBuilder or StringBuffer
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++){
            String temp = res;
            int left = 0, right = 0;
            res = "";
            while (right < temp.length()){
                if (temp.charAt(left) == temp.charAt(right)){
                    right++;
                }
                else {
                    res += String.valueOf(right-left) + String.valueOf(temp.charAt(left));
                    left = right;
                    right++;
                }
            }
            right -= 1;
            res += String.valueOf(right-left + 1);
            res += String.valueOf(temp.charAt(left));
        }
        return res;
    }
}
