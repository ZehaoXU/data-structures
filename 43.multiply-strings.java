import java.lang.String;;
import sun.security.util.Length;
/*
 * @lc app=leetcode id=43 lang=java
 *
 * [43] Multiply Strings
 */
class Solution {
    /**
     * 两种乘法：平行乘法（某数一位乘以另一个数，然后移位相加结果）和竖式乘法（m*n最多结果m+n位，每一位都由固定位数字乘积求和得到）
     * 本次使用竖式乘法，外层遍历m+n，内层遍历该位的所有乘积对，很晕，写一写自己晕了；如果两数分别遍历可能清晰一点
     * 时间复杂度 O(mn) 两个数每一位都要相乘；空间复杂度 O(1)
     * Your runtime beats 97.44 % of java submissions
     * Your memory usage beats 99.86 % of java submissions (36.7 MB)
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        char[] bigger = num1.length() >= num2.length() ? num1.toCharArray() : num2.toCharArray();
        char[] smaller = num1.length() < num2.length() ? num1.toCharArray() : num2.toCharArray();
        if (smaller[0] == '0')  return "0";
        int[] resInt = new int[bigger.length + smaller.length];

        int carry = 0;
        for (int index = resInt.length-1; index >0; index--){
            int temp = 0;
            for (int i = 0; i < smaller.length && i<=index-1; i++){
                if (index-i-1 > bigger.length-1)    continue;
                int j = index - i - 1;
                temp += (smaller[i]-'0') * (bigger[j]-'0');
            }
            carry = temp / 10;
            int result = resInt[index] + temp % 10;
            while (result > 9){
                carry += result / 10;
                result %= 10;
            }
            resInt[index] = result;
            int carryIndex = index - 1;
            resInt[carryIndex] = carry;
        }
        StringBuilder res = new StringBuilder();
        if (resInt[0] != 0) res.append(String.valueOf(resInt[0]));
        for (int i = 1; i < resInt.length; i++){
            res.append(String.valueOf(resInt[i]));
        }
        return res.toString();
    }
}

