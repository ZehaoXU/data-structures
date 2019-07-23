/*
 * @lc app=leetcode id=3 lang=cpp
 *
 * [3] Longest Substring Without Repeating Characters
 */
#include<unordered_map>
#include<iostream>
#include<vector>

using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_map<char, int> map;
        int max_length = 0;
        for (int i=0, j=0; j < s.length(); j++)
        {
            if (map.find(s[j]) != map.end()){
                i = max(i, map[s[j]] + 1);
            }
            map[s[j]] = j;
            max_length = max(max_length, j-i+1);
        }
        return max_length;
    }
};

