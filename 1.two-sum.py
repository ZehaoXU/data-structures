#
# @lc app=leetcode id=1 lang=python3
#
# [1] Two Sum
#
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dic = {}
        for i in range(len(nums)):
            item = target - nums[i]
            if item in dic:
                return [dic[item], i]
            dic[nums[i]] = i 
        

