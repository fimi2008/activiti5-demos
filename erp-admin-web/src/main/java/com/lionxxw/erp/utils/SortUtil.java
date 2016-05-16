package com.lionxxw.erp.utils;

import java.util.*;

/**
 * <p>Description: TODO </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/11 上午9:25
 */
public class SortUtil {
    int count = 0;

    public void quicksort(int n[], int left, int right) {
        count++;
        if (left < right) {
            int dp = partition(n, left, right);
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }

    public int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        SortUtil u = new SortUtil();
        int[] n = {1,23,4,32,34,52,23,123,123,52,23,51,5,35,67};
        u.quicksort(n, 0, n.length - 1);
        System.out.println(Arrays.toString(n));
        System.out.println(u.count);

        Collection c = new ArrayList();

        Set<Integer> set1 = new HashSet<Integer>();
        set1.add(5);
        set1.add(4);
        set1.add(3);
        set1.add(2);
        set1.add(1);

        System.out.println(set1.toString());

        Set<String> set2 = new TreeSet<String>();
        set2.add("one");
        set2.add("s");
        set2.add("sgw");
        set2.add("gds");
        set2.add("sgd");

        System.out.println(set2.toString());


        Map map = new HashMap();
        map.put(null, 323);
        System.out.println(map.get(null));

        Map<String, String> emps = new HashMap<String, String>();
        for (int i = 0; i< 10 ; i++){
            emps.put(i+"","admin"+i);
        }

        Set<String> ids = new HashSet<String>(3);
        ids.add("2");
        ids.add("5");
        ids.add("3");

        // 删除ids下员工
       // emps.keySet().removeAll(ids);

        // 保留交集员工
        emps.keySet().retainAll(ids);

        System.out.println(emps.toString());

        Iterator<String> iterator = ids.iterator();
        String last = iterator.next();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (last.compareTo(next) < 0){
                last = next;
            }
        }
        System.out.println("max="+last);
    }
}