import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=703 lang=java
 *
 * [703] Kth Largest Element in a Stream
 *
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 *
 * algorithms
 * Easy (46.79%)
 * Likes:    361
 * Dislikes: 177
 * Total Accepted:    40.5K
 * Total Submissions: 86.5K
 * Testcase Example:  '["KthLargest","add","add","add","add","add"]\n[[3,[4,5,8,2]],[3],[5],[10],[9],[4]]'
 *
 * Design a class to find the kth largest element in a stream. Note that it is
 * the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Your KthLargest class will have a constructor which accepts an integer k and
 * an integer array nums, which contains initial elements from the stream. For
 * each call to the method KthLargest.add, return the element representing the
 * kth largest element in the stream.
 * 
 * Example:
 * 
 * 
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 
 * 
 * Note: 
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 * 
 */
class KthLargestMinHeap {
    /**
     * 最小堆，找第k大的元素，注意是从最大开始往前找k个，不是从最小开始
     * TC 建堆 O(n*logn) add O(logn); SC O(n)
     * Your runtime beats 15.53 % of java submissions
     * Your memory usage beats 43.33 % of java submissions (47.9 MB)
     * 
     * 其他方法：
     *  排序序列中添加/查找，想到BST，但是treeset不能有重复元素，所以需要自己实现BST，由于不是balanced，所以可能比最小堆差一点
     */
    private PriorityQueue<Integer> q;
    private final int k;
    public KthLargest(int k, int[] nums) {
        q = new PriorityQueue<>();
        this.k = k;
        for (int i : nums) {
            q.add(i);
            if (q.size() > k) {
                q.poll();
            }
        }
    }
    
    public int add(int val) {
        q.add(val);
        while (q.size() > k)
            q.poll();
        return q.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

