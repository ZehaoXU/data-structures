/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 */
class Solution {
    /**
     * 最基础的字符串匹配，长串指针一次移动一步，用equal方法匹配
     * 时间复杂度 O(nm) or O(n) m是pattern长度，n是text长度；空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 75.31 % of java submissions (37.5 MB)
     * 
     * 其他方法：
     *  1. 变化步长，通过map进行变化
     *  2. Rabin-Karp算法，通过哈希表进行匹配
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle == "")   return 0;
        int index = -1;
        int lenHaystack = haystack.length(), lenNeedle = needle.length();
        for (int i = 0; i <= lenHaystack - lenNeedle; i++){
            if (needle.equals(haystack.substring(i, i + lenNeedle))){
                index = i;
                break;
            }
        }
        return index;
    }
}

