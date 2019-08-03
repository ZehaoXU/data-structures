import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=996 lang=java
 *
 * [996] Number of Squareful Arrays
 */
class Solution {
    /**
     * DFS/backtracking，无任何优化，最笨的剪枝方法：如果不是平方数就不加和，重复计算了很多次
     * 优化：抽象为图，每个节点有边相连的条件是是完全平方数，避免重复判断平方
     * 时间复杂度 O()
     * @param A
     * @return
     */
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        int[] visited = new int[A.length];
        
        int count = dfs(visited, new ArrayList<>(), A);
        return count;
    }

    public int dfs(int[] visited, List<Integer> list, int[] A){
        int count = 0;
        if (list.size() == A.length){
            return ++count;
        }

        for (int i = 0; i < A.length; i++) if (visited[i] == 0){
            if (list.size()==0){
                list.add(A[i]);
                visited[i] = 1;
                count += dfs(visited, list, A);
                list.remove(list.size()-1);
                visited[i] = 0;
            }
            else if (isSquareful(list.get(list.size()-1), A[i])
                    && (i == 0 || visited[i-1] == 0 || A[i] != A[i-1])){
                list.add(A[i]);
                visited[i] = 1;
                count += dfs(visited, list, A);
                list.remove(list.size()-1);
                visited[i] = 0;
            }
        }
        return count;
    }

    private boolean isSquareful(int a, int b){
        int i = 1, sum = a+b;
        while (sum > 0){
            sum -= i;
            i += 2;
        }
        return sum==0;
    }
}

