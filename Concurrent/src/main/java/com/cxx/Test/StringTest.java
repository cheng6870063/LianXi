package com.cxx.Test;

public class StringTest {
    public static void main(String[] args) {
        String str1 = "ff hhh jj kkk hu";
        String str2 = "jj";
        int n = 1;
        String result = check(str1,str2,n);
        System.out.println(result);
    }

    static String check(String str1,String str2 ,int n){
        String result = "";
        boolean status = str1.contains(str2);
        if(status){
            int start = str1.indexOf(str2) + 1 - n;
            if(start < 0){
                start = 0;
            }
            int  end   = start + str2.length() - 1 + n;
            if(end > str1.length() - 1){
                end = str1.length() - 1;
            }
            result = str1.substring(start,end);
        }
        return result;
    }
}
