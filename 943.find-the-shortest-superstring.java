import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.lang.*;

/*
 * @lc app=leetcode id=943 lang=java
 *
 * [943] Find the Shortest Superstring
 */
class Solution {
    /**
     * 抽象为旅行商问题，不需要绕环，动态规划，太难了
     * 时间复杂度 O(n^2*2^n); 空间复杂度 O(n*2^n)
     * @param A
     * @return
     */
    public String shortestSuperstring(String[] A) {
        // 存放i+j之后增加的距离
        int[][] graph = calculator(A);
        int n = A.length;
        int[][] dp = new int[1<<n][n];
        int[][] parent = new int[1<<n][n];
        
        for (int s = 1; s < (1<<n); s++){
            Arrays.fill(dp[s], Integer.MAX_VALUE);
            for (int i = 0; i < n && (1<<i) <= s; i++){
                if ((s & (1<<i)) == 0)    continue;
                int prev = s - (1<<i);
                if (prev == 0)  dp[s][i] = A[i].length();
                else{
                    for (int j = 0; j < n; j++){
                        if ((prev & (1<<j)) == 0)   continue;
                        if (dp[prev][j] + graph[j][i] < dp[s][i]){
                            dp[s][i] = dp[prev][j] + graph[j][i];
                            parent[s][i] = j;
                        }
                    }
                }
            }
        }
        // find smallest superstring tail
        int minLen = Integer.MAX_VALUE;
        int cur = -1;
        for (int i = 0; i < n; i++){
            if (dp[(1<<n)-1][i] < minLen){
                minLen = dp[(1<<n)-1][i];
                cur = i;
            }
        }
        
        // backtrack to get the superstring
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int s = (1<<n) - 1;
        while (s > 0){
            stack.push(cur);
            int temp = s;
            s -= (1<<cur);
            cur = parent[temp][cur];
        }
        cur = stack.pop();
        sb.append(A[cur]);
        while(!stack.isEmpty()){
            int next = stack.pop();
            sb.append(A[next].substring(A[next].length()-graph[cur][next]));
            cur = next;
        }
        return sb.toString();
    }

    private int[][] calculator(String[] A){
        // dp 存放i到j增加的距离
        int[][] dp = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < A.length; j++){
                if (j == i){
                    dp[i][j] = 0;
                    continue;
                }
                int minLen = Math.min(A[i].length(), A[j].length());
                for (int k = minLen; k > 0; k--){
                    if (A[j].startsWith(A[i].substring(A[i].length()-k))){
                        dp[i][j] = A[j].length() - k;
                        break;
                    }
                }
                if (dp[i][j] == 0)  dp[i][j] = A[j].length();
            }
        }
        return dp;
    }
}

