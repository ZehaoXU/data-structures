import java.util.Map;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=352 lang=java
 *
 * [352] Data Stream as Disjoint Intervals
 *
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
 *
 * algorithms
 * Hard (44.08%)
 * Likes:    199
 * Dislikes: 64
 * Total Accepted:    25.9K
 * Total Submissions: 58.8K
 * Testcase Example:  '["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]\n[[],[1],[],[3],[],[7],[],[2],[],[6],[]]'
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6,
 * ..., then the summary will be:
 * 
 * 
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * 
 * 
 * 
 * 
 * Follow up:
 * 
 * What if there are lots of merges and the number of disjoint intervals are
 * small compared to the data stream's size?
 * 
 */
class SummaryRanges {
    /**
     * 看似并查集，实际并不是，因为并查集底层是数组，这个不行，没告诉输入范围
     * 两个办法：二分查找，找第一个小于val和大于val的intervals，判断并插入，add O(n); TreeMap 找lower和higher，判断interval关系，add O(logn)
     * 本次用treemap实现，建立 interval.start -> interval的映射，每次找val两侧的interval，判断是否能与左右结合
     * TC: add O(logn), get O(k) k-num of intervals; SC O(n)
     * Your runtime beats 44.26 % of java submissions
     * Your memory usage beats 11.11 % of java submissions (55 MB)
     */
    TreeMap<Integer, int[]> tree;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        tree = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (tree.containsKey(val))   return;
        Integer l = tree.lowerKey(val);
        Integer h = tree.higherKey(val);
        if (l != null && tree.get(l)[1] >= val) return;
        
        if (l != null && tree.get(l)[1] == val-1 
                && h != null && h == val+1) {
            int[] temp = new int[]{l, tree.get(h)[1]};
            tree.remove(h);
            tree.put(l, temp);
        }
        else if (h != null && h == val+1) {
            int[] temp = new int[]{val, tree.get(h)[1]};
            tree.remove(h);
            tree.put(val, temp);
        }
        else if (l != null && tree.get(l)[1] == val-1) {
            int[] temp = new int[]{tree.get(l)[0], val};
            tree.put(temp[0], temp);
        }
        else {
            tree.put(val, new int[]{val, val});
        }
    }
    
    public int[][] getIntervals() {
        int size = tree.size();
        int[][] res = new int[size][2];
        int i = 0;
        for (int[] values : tree.values()) {
            res[i++] = values;
        }
        return res;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */

