package com.netease.test;

import java.util.Scanner;

/**
 * @Author Muyuxi
 * @Date 2019/3/10
 * @Describtion
 */
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //初始化数据
        int n = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0;i<n;i++) {
            height[i] = sc.nextInt();
        }
        int[][] dp = new int[2][n+1];
        for (int i=0;i<2;i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        dp[1][0] = 0;
        dp[0][1] = 0;
        dp[1][1] = height[0];

        for (int k=2;k<=n;k++) {
            dp[0][k] = Math.min(dp[1][k-1],dp[1][k-2]);
            dp[1][k] = Math.min(dp[0][k-1]+height[k-1],dp[1][k-1]+height[k-1]);
        }
        System.out.println(Math.min(dp[0][n],dp[1][n]));

    }

}
