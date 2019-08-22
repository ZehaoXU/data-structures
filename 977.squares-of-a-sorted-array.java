import java.util.Arrays;

/*
 * @lc app=leetcode id=977 lang=java
 *
 * [977] Squares of a Sorted Array
 *
 * https://leetcode.com/problems/squares-of-a-sorted-array/description/
 *
 * algorithms
 * Easy (71.84%)
 * Likes:    433
 * Dislikes: 47
 * Total Accepted:    97.4K
 * Total Submissions: 135.5K
 * Testcase Example:  '[-4,-1,0,3,10]'
 *
 * Given an array of integers A sorted in non-decreasing order, return an array
 * of the squares of each number, also in sorted non-decreasing order.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 * 
 * 
 * 
 */
class SolutionMergeSort {
    /**
     * 先求出平方，然后归并排序的方法
     * 归并排序是一个双指针，建议一开始传相同大小的备用数组进去，避免每次merge都新建
     * 时间复杂度 O(n*logn); SC O(n)
     * Your runtime beats 11.93 % of java submissions
     * Your memory usage beats 96.34 % of java submissions (40.7 MB)
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = A[i]*A[i];
        }
        mergeSort(res, 0, res.length-1);
        return res;
    }
    private void mergeSort(int[] res, int l, int r) {
        if (l == r) return;
        int m = l + (r-l)/2;
        mergeSort(res, l, m);
        mergeSort(res, m+1, r);
        merge(res, l, m, r);
    }
    private void merge(int[] res, int l, int m, int r) {
        int[] list1 = Arrays.copyOfRange(res, l, m+1);
        int[] list2 = Arrays.copyOfRange(res, m+1, r+1);
        int i = 0, j = 0, index = l;
        while (i < list1.length && j < list2.length) {
            if (list1[i] < list2[j]) {
                res[index++] = list1[i++];
            }
            else {
                res[index++] = list2[j++];
            }
        }
        if (i == list1.length) {
            while (j < list2.length)
                res[index++] = list2[j++];
        }
        else if (j == list2.length) {
            while (i < list1.length)
                res[index++] = list1[i++];
        }
    }
}

class Solution {
    /**
     * 双指针，找到正负分解线，两侧数字都是平方增序排列，相当于merge two sorted array
     * TC O(n); SC O(n)
     * Your runtime beats 69.03 % of java submissions
     * Your memory usage beats 96.34 % of java submissions (40.9 MB)
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int i = 0;
        for (; i < A.length; i++) {
            if (A[i] >= 0)   break;
        }
        int j = i-1, index = 0;
        while (j >= 0 && i < A.length) {
            if (Math.abs(A[i]) < Math.abs(A[j])) {
                res[index++] = A[i] * A[i];
                i++;
            }
            else {
                res[index++] = A[j] * A[j];
                j--;
            }
        }
        while (j >= 0) {
            res[index++] = A[j] * A[j];
            j--;
        }
        while (i < A.length) {
            res[index++] = A[i] * A[i];
            i++;
        }
        return res;
    }
}

