/*
 * @lc app=leetcode id=8 lang=cpp
 *
 * [8] String to Integer (atoi)
 */
#include<string>
using namespace std;

class Solution {
public:
    int myAtoi(string str) {
        string num = "0123456789";
        int flag = 1;
        long int result = 0;
        bool first_int = true;

        for (int i = 0; i < str.length(); i++){
            if (str[i] == ' ')   continue;
            if (str[i] == '-'){
                flag = -1;
                continue;
            }
            if (num.find(str[i] != string::npos)){
                first_int = false;
                result = result*10 + int(str[i]) - 48;
                continue;
            }
            if (num.find(str[i] == string::npos) && first_int == false){
                break;
            }
        }
        result = result * flag;
        if (result > INT32_MAX) result = INT32_MAX;
        if (result < INT32_MIN) result = INT32_MIN;
        return result;
    }
};

