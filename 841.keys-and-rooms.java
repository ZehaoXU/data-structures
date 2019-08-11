import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=841 lang=java
 *
 * [841] Keys and Rooms
 *
 * https://leetcode.com/problems/keys-and-rooms/description/
 *
 * algorithms
 * Medium (60.81%)
 * Likes:    526
 * Dislikes: 42
 * Total Accepted:    39.5K
 * Total Submissions: 64.9K
 * Testcase Example:  '[[1],[2],[3],[]]'
 *
 * There are N rooms and you start in room 0.  Each room has a distinct number
 * in 0, 1, 2, ..., N-1, and each room may have some keys to access the next
 * room. 
 * 
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j]
 * is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j]
 * = v opens the room with number v.
 * 
 * Initially, all the rooms start locked (except for room 0). 
 * 
 * You can walk back and forth between rooms freely.
 * 
 * Return true if and only if you can enter every room.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:  
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return
 * true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 * 
 * 
 */
class Solution {
    /**
     * 不能UnionFind！因为建图时不是从0-n线性遍历建图，而是有哪个key就去哪个房间，有的房间虽然有key但是根本去不到！有的房间在前面，但是很后面的房间才有它的钥匙，需要先跳过再回头找。这种UF无法实现
     * 简单BFS即可
     * shijianfuzadu O(N); 空间复杂度 O(N)
     * Your runtime beats 56.11 % of java submissions
     * Your memory usage beats 75.86 % of java submissions (43 MB)
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int room = q.poll();
            if (visited.contains(room)) continue;
            visited.add(room);
            List<Integer> keys = rooms.get(room);
            q.addAll(keys);
        }
        return visited.size() == rooms.size();
    }
}

