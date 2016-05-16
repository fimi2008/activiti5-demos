package com.lionxxw.erp.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description: 集合排序 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/11 下午4:10
 */
public class ShuffleUtil {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1;i <= 49; i++){
            nums.add(i);
        }
        System.out.println(nums.toString());
        Collections.shuffle(nums);
        System.out.println(nums.toString());
        List<Integer> sub = nums.subList(0, 10);
        Collections.sort(sub);
        System.out.println(sub);
    }
}
