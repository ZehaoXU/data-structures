#
# @lc app=leetcode id=13 lang=python
#
# [13] Roman to Integer
#
class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        dic = {"I":1, "V":5, "X":10, "L":50, "C":100, "D":500, "M":1000}
        result, i = 0, 0
        for i in range(len(s)):
            if i == len(s)-1:
                result += dic[s[i]]
            else: 
                if dic[s[i]] < dic[s[i+1]]:
                    result -= dic[s[i]]
                else:
                    result += dic[s[i]]
        return result
            
