/*
 * @lc app=leetcode id=7 lang=cpp
 *
 * [7] Reverse Integer
 */
#include<iostream>
#include<vector>
#include<string>

using namespace std;

class Solution {
public:
    int reverse(int x) {
        int result = 0;
        while (x != 0){
            int pop = x%10;
            x = x/10;
            if (result > (pow(2,31)-1)/10 || (result == (pow(2,31)-1)/10 && pop > 7))  return 0;
            if (result < (pow(2,31))/(-10) || (result == (pow(2,31))/(-10) && pop < -8)) return 0;

            result = result * 10 + pop;
        }
        return result;        
    }
};

