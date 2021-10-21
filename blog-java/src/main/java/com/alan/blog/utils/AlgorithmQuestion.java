package com.alan.blog.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * <p>算法题解</p>
 *
 * @author Alan Huang
 * @version v1.0.0
 * @className AlgorithmQuestion.java
 * @date 2021/10/20-11:18
 * @email cmrhyq@163.com
 */
@Slf4j
public class AlgorithmQuestion {

//    public static void main(String[] args) {
//        int[] list = new int[9];
//        list[0]=-999;
//        list[1]=-253;
//        list[2]=-108;
//        list[3]=-1;
//        list[4]=128;
//        list[5]=364;
//        list[6]=669;
//        list[8]=999;
//        log.info("所查找的值的下标为:"+search(list,364));
//    }
    /**
     * 二分查找
     *
     * @param nums 待查找的数组
     * @param target 要查找的值
     * @return 返回查找到的数组的下标或者-1
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 搜索插入位置 - 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * @param nums 待查找的数组
     * @param target 要查找的值
     * @return 返回查找到的数组的下标或它将会被按顺序插入的位置
     */
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        // 定义一个区间 左边是0 右边是数组长度减一
        int left = 0;
        int right = n - 1;
        // 当left==right时 区间以然有效
        while(left<=right){
            // 防止溢出
            int mid = left + (right-left) /2;
            // 判断target是在左区间还是右区间
            if(nums[mid]>target){
                // target是在左区间 so [left,mid - 1]
                right = mid -1;
            } else if(nums[mid]<target){
                // target是在右区间 so [mid + 1, right]
                left = mid +1;
            } else{
                // 找到目标值，等于数组中某一个元素 so return mid
                return mid;
            }
        }
        // 没有找到目标值，按题目要求，返回它将会被按顺序插入的位置 so return nums.length + 1
        return right+1;
    }
}
