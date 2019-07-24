import java.util.ArrayList;

/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */
class Solution {
    /**
     * 用String实现一个简单栈来实现括号匹配
     * 运行时间太长，only beats 5.06%
     * @param s
     * @return
     */
    /*public boolean isValid(String s) {
        String stack = "";
        String left = "([{", right = ")]}", pairs = "(){}[]";
        while (s.length() != 0){
            String next = s.substring(0, 1);
            // 遇到左括号，入栈
            if (left.contains(next)){
                stack += next;
                s = s.substring(1);
            }
            // 遇到右括号，判断stack目前是否为空
            else if (stack.length() != 0){
                String pop = stack.substring(stack.length()-1);
                String pair = pop + next;
                if (!pairs.contains(pair))  return false;
                stack = stack.substring(0, stack.length()-1);
                s = s.substring(1);
            }
            // 遇到右括号且stack空
            else return false;
        }
        if (stack.length() == 0)    return true;
        else return false;
    }
    */

    /**
     * 实际上Java自带Stack数据结构，并使用hashMap更好的处理括号匹配以及左右括号查找
     * runtime beats 60.88 %
     */
    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings;
    
    // Initialize hash map with mappings. This simply makes the code easier to read.
    public Solution() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }
    
    public boolean isValid(String s) {
    
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();
    
        for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
    
        // If the current character is a closing bracket.
        if (this.mappings.containsKey(c)) {
    
            // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
            char topElement = stack.empty() ? '#' : stack.pop();
    
            // If the mapping for this bracket doesn't match the stack's top element, return false.
            if (topElement != this.mappings.get(c)) {
            return false;
            }
        } else {
            // If it was an opening bracket, push to the stack.
            stack.push(c);
        }
        }
    
        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}

