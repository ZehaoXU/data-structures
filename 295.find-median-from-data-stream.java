import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=295 lang=java
 *
 * [295] Find Median from Data Stream
 *
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (37.94%)
 * Likes:    1349
 * Dislikes: 27
 * Total Accepted:    122.7K
 * Total Submissions: 322.5K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n[[],[1],[2],[],[3],[]]'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * For example,
 * 
 * [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 * 
 * 
 * 
 * 
 * Follow up:
 * 
 * 
 * If all integer numbers from the stream are between 0 and 100, how would you
 * optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how
 * would you optimize it?
 * 
 * 
 */
class MedianFinder {
    /**
     * 能想到binary search找插入位置/ quick select找中位数 TC O(n)
     * 更好的方法是两个堆，或者self-balancing BST
     * 这次用俩个堆实现，因为找中位数并不需要两边排序，用两个堆，最大堆装小的一半元素，最小堆装大的一半元素，如果两个堆元素个数差不多，堆顶元素就是中位数
     * 时间复杂度 O(logn) + O(1); SC O(n)
     * Your runtime beats 45.72 % of java submissions
     * Your memory usage beats 91.53 % of java submissions (63.1 MB)
     */
    private PriorityQueue<Integer> small;   // maxHeap
    private PriorityQueue<Integer> large;   // minHeap

    /** initialize your data structure here. */
    public MedianFinder() {
        large = new PriorityQueue<>();
        small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                return j - i;
            }
        });
    }
    
    public void addNum(int num) {
        if (small.isEmpty()) {
            small.offer(num);
            return;
        }
        if (num > small.peek()) {
            large.offer(num);
            if (small.size() < large.size()) {
                int temp = large.poll();
                small.offer(temp);
            }
        }
        else {
            small.offer(num);
            if (small.size() > large.size() + 1) {
                int temp = small.poll();
                large.offer(temp);    
            }
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size())
            return small.peek();
        else return 0.5*(small.peek()+large.peek());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

