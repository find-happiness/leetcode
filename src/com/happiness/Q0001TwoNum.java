package com.happiness;

import java.util.HashMap;

public class Q0001TwoNum {

    public int[] twoSum(int[] nums, int target) {

        //key:num,value:index
        HashMap<Integer, Integer> mapNums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int diff = target - nums[i];

            if (mapNums.containsKey(diff)) {
                return new int[]{i, mapNums.get(diff)};
            } else {
                mapNums.put(nums[i], i);
            }

        }


        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 13;
        Q0001TwoNum twoNum = new Q0001TwoNum();
        int[] indexs = twoNum.twoSum(nums, target);
        if (indexs != null) {
            System.out.println("indexs:" + indexs[0] + "  "+ indexs[1]);
        }
    }
}
