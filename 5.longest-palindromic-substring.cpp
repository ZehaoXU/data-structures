/*
 * @lc app=leetcode id=5 lang=cpp
 *
 * [5] Longest Palindromic Substring
 */
#include<string>
#include<vector>
#include<iostream>

using namespace std;
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.length();
        if (n==0)   return s;
        int table[n][n];
        int start = 0, end = 0;
        for (int i=0; i<n; i++) table[i][i] = 1;
        for (int i=0; i<n-1; i++){
            if (s[i+1]==s[i]){
                table[i][i+1] = 1;
                start = i, end = i+1;
            }   
            else    table[i][i+1] = 0;
        }   // initialize table
        
        for(int turn = 0; turn < n-2; turn++){
            for (int i = 0; i < n-2-turn; i++){
                int j = i + 2 + turn;
                if (s[i] == s[j] && table[i+1][j-1] == 1){
                    table[i][j] = 1;
                    if (j-i > end - start)  start = i, end = j;
                }
                else    table[i][j] = 0;
            }
        }
        return s.substr(start, end-start+1);
    }
};

