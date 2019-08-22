import java.util.*;

/*
 * @lc app=leetcode id=346 lang=java
 *
 * [346] Moving Average from Data Stream
 *
 * https://leetcode.com/problems/moving-average-from-data-stream/description/
 *
 * algorithms
 * Easy (67.01%)
 * Likes:    330
 * Dislikes: 37
 * Total Accepted:    84.4K
 * Total Submissions: 125.8K
 * Testcase Example:  '["MovingAverage","next","next","next","next"]\n[[3],[1],[10],[3],[5]]'
 *
 * Given a stream of integers and a window size, calculate the moving average
 * of all integers in the sliding window.
 * 
 * Example:
 * 
 * 
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 
 * 
 * 
 * 
 */
class MovingAverage {
    /**
     * 数据流，不断添加元素求一定窗口的avg，滑动窗口只需要根据两边元素以及previous result计算下一个result即可(replacing the oldest element with the new entry)，不需要每次对窗口内全部元素重新计算。采用队列数据结构，只维护窗口大小的队列
     * TC O(k+n) k窗口大小，n是next操作次数; SC O(k)
     * Your runtime beats 95.58 % of java submissions
     * Your memory usage beats 78.13 % of java submissions (41.9 MB)
     * 
     * 其他数据结构：
     *  可以用size长度的array！相当于循环队列，用index进行模操作标记oldest元素
     */
    private Deque<Integer> _q;
    private int _size;
    private double _avg;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        _size = size;
        _q = new ArrayDeque<>();
    }
    
    public double next(int val) {
        _q.offer(val);
        if (_q.size() > _size) {
            int prev = _q.poll();
            _avg += ((double)(val - prev))/_size;
        }
        else {
            _avg = (_avg*(_q.size()-1) + val) / _q.size();
        }
        return _avg;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

