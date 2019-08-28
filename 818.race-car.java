import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/*
 * @lc app=leetcode id=818 lang=java
 *
 * [818] Race Car
 *
 * https://leetcode.com/problems/race-car/description/
 *
 * algorithms
 * Hard (35.84%)
 * Likes:    305
 * Dislikes: 36
 * Total Accepted:    11.4K
 * Total Submissions: 31.8K
 * Testcase Example:  '3'
 *
 * Your car starts at position 0 and speed +1 on an infinite number line.
 * (Your car can go into negative positions.)
 * 
 * Your car drives automatically according to a sequence of instructions A
 * (accelerate) and R (reverse).
 * 
 * When you get an instruction "A", your car does the following: position +=
 * speed, speed *= 2.
 * 
 * When you get an instruction "R", your car does the following: if your speed
 * is positive then speed = -1 , otherwise speed = 1.  (Your position stays the
 * same.)
 * 
 * For example, after commands "AAR", your car goes to positions 0->1->3->3,
 * and your speed goes to 1->2->4->-1.
 * 
 * Now for some target position, say the length of the shortest sequence of
 * instructions to get there.
 * 
 * 
 * Example 1:
 * Input: 
 * target = 3
 * Output: 2
 * Explanation: 
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * 
 * 
 * 
 * Example 2:
 * Input: 
 * target = 6
 * Output: 5
 * Explanation: 
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= target <= 10000.
 * 
 * 
 */
class Solution {
    /**
     * BFS，相当于最短路径，每次两种选择：A or R，需要剪枝否则超时
     * 两个剪枝手段：用set保证同一位置同一速度值计算一次；加速时不走负数pos，不走超过target*2 （target 肯定在2的两个指数之间）
     * TC O(2^S) s是要走几步；SC O(2^S)
     * Your runtime beats 8.86 % of java submissions
     * Your memory usage beats 33.33 % of java submissions (88.8 MB)
     * 
     * 其他方法：
     *  记忆化递归，DP。不过不太好理解
     * @param target
     * @return
     */
    public int racecar(int target) {
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.offer(new Pair<>(0, 1));
        set.add("0_1");
        set.add("0_-1");
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> pair = q.poll();
                int pos = pair.getKey();
                int speed = pair.getValue();
                // A
                int pos1 = pos + speed;
                int speed1 = speed * 2;
                Pair<Integer, Integer> pair1 = new Pair<>(pos1, speed1);
                if (pos1 == target) return step+1;
                String state1 = String.valueOf(pos1) + "_" + String.valueOf(speed1);
                if (!set.contains(state1)) {
                    if (pos1 > 0 && pos1 < 2*target) {
                        set.add(state1);
                        q.offer(pair1);
                    }
                }
                // R
                int pos2 = pos;
                int speed2 = speed>0 ? -1 : 1;
                String state2 = String.valueOf(pos2) + "_" + String.valueOf(speed2);
                Pair<Integer, Integer> pair2 = new Pair<>(pos2, speed2);
                if (!set.contains(state2)) {
                    set.add(state2);
                    q.offer(pair2);
                }
            }
            step++;
        }
        return -1;
    }
}

