#
# @lc app=leetcode id=9 lang=python
#
# [9] Palindrome Number
#
class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """

        return str(x) == str(x)[::-1]

