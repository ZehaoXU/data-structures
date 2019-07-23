#
# @lc app=leetcode id=11 lang=python
#
# [11] Container With Most Water
#
class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        i, j = 0, len(height)-1
        result = 0
        while (i < j):
            width = j-i
            if height[i] > height[j]:
                result = max(result, height[j]*width)
                j -= 1
            else:
                result = max(result, height[i]*width)
                i += 1
        
        return result


