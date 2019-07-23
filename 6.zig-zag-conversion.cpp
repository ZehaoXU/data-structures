/*
 * @lc app=leetcode id=6 lang=cpp
 *
 * [6] ZigZag Conversion
 */

#include<vector> 
#include<string>
#include<iostream>
using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1)   return s;
        vector<string> result(numRows);
        bool goDown = false;
        int num = 0;
        for (char c : s){
            result[num] += c;
            if (num == 0 || num == numRows-1)   goDown = !goDown;
            num += goDown ? 1 : -1;
        }
        string output;
        for (string row : result)   output += row;
        return output;
        
    }
};

