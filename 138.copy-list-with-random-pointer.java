import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.sun.corba.se.impl.orbutil.graph.Node;

/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (28.09%)
 * Likes:    1745
 * Dislikes: 462
 * Total Accepted:    265.1K
 * Total Submissions: 941.1K
 * Testcase Example:  '{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}'
 *
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input:
 * 
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * 
 * Explanation:
 * Node 1's value is 1, both of its next and random pointer points to Node 2.
 * Node 2's value is 2, its next pointer points to null and its random pointer
 * points to itself.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You must return the copy of the given head as a reference to the cloned
 * list.
 * 
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    /**
     * 把链表当成图，和Q133很像，BFS搜索
     * 处理一个节点可以分两步，1.如果没见过，先把new Node，此时可以不用包含节点信息 2.处理到某个节点，添加节点的val next等信息
     * 核心是用一个HashMap存储点信息，各种边的关系全在map中引用。map不仅可以是val->newNode, 也可以oldNode->newNode。
     * 本题由于是链表，单向就能到头，所以其实不需要q
     * 时间复杂度 O(n); 空间复杂度 O(n) map；可以优化到空间复杂度 O(1) 每个原节点后面生成一个新节点
     * 
     */
    public Node copyRandomList(Node head) {
        if (head == null)   return null;

        Queue<Node> q = new LinkedList<>();
        q.add(head);
        Map<Integer, Node> map = new HashMap<>();
        // empty node, add info during the loop
        map.put(head.val, new Node());

        while (!q.isEmpty()) {
            Node cur = q.poll();
            Node copy = map.get(cur.val);
            copy.val = cur.val;

            if (cur.next != null) {
                if (!map.containsKey(cur.next.val)) {
                    q.add(cur.next);
                    map.put(cur.next.val, new Node());
                }
                copy.next = map.get(cur.next.val);
            }
            else {
                copy.next = null;
            }

            if (cur.random != null) {
                if (!map.containsKey(cur.random.val)) {
                    q.add(cur.random);
                    map.put(cur.random.val, new Node());
                    copy.random = map.get(cur.random.val);
                }
                copy.random = map.get(cur.random.val);
            }
            else {
                copy.random = null;
            }
        }
        return map.get(head.val);
    }
}

