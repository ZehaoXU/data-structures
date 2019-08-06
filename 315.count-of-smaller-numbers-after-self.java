import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
 *
 * algorithms
 * Hard (38.76%)
 * Likes:    1241
 * Dislikes: 58
 * Total Accepted:    82.8K
 * Total Submissions: 213.5K
 * Testcase Example:  '[5,2,6,1]'
 *
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * Example:
 * 
 * 
 * Input: [5,2,6,1]
 * Output: [2,1,1,0] 
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * 
 */

class SolutionBIT {
    /**
     * BIT，从后向前看，从后依次添加元素，然后直接query，这种update/query频繁的做法，想到用BIT数据结构优化时间。用map将num和rank对应上，再用一个rank.size()+1的BIT去查询，节点数字已出现的<=它的个数，每次update(rank,1) query(rank-1)
     * 时间复杂度 O(n*logn)；空间复杂度 O(K) K是unique number个数
     * Your runtime beats 54.1 % of java submissions
     * Your memory usage beats 97.48 % of java submissions (39.2 MB)
     * 
     * 其他方法：
     *  1. merge sort，假装排nums，实际排nums中元素的index
     *  2. BST
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        // nums[i] -> rank
        int rank = 1;
        Map<Integer, Integer> numToRankMap = new HashMap<>();
        for (int num : sorted) {
            if (!numToRankMap.containsKey(num))
                numToRankMap.put(num, rank++);
        }
        // init a BIT
        BinaryIndexTree bit = new BinaryIndexTree(numToRankMap.size());
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length-1; i >= 0; i--) {
            int index = numToRankMap.get(nums[i]); // start from 1
            bit.update(index, 1);
            int sum = bit.query(index-1);
            res.add(sum);
        }
        
        Collections.reverse(res);
        return res;
    }

    class BinaryIndexTree {
        int[] sum;
        public BinaryIndexTree(int n) {
            sum = new int[n+1];
        }
        public void update(int i, int delta) {
            while (i < sum.length) {
                sum[i] += delta;
                i += i & -i;
            }
        }
        public int query(int i) {
            int res = 0;
            while (i > 0) {
                res += sum[i];
                i -= i & -i;
            }
            return res;
        }
    }
}

