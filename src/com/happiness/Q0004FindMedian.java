package com.happiness;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。
 * <p>
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
 * <p>
 * 你可以假设nums1和nums2不会同时为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q0004FindMedian {
    /**
     * 时间复杂度要达到log，就要使用二分，类比是找到第k小的数
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0) {
            return findMedianArrays(nums2);
        }

        if (nums2 == null || nums2.length == 0) {
            return findMedianArrays(nums1);
        }

        int total = nums1.length + nums2.length;
        int m1 = total / 2 - 1;
        int m2 = total / 2;
        if (total %2 == 1){
            return getKth(nums1,nums2,total / 2 +1);
        }else {

            return (getKth(nums1, nums2, m1 +1)
                    + getKth(nums1, nums2, m2 +1)) * 0.5;
        }

    }

    private static final double getKth(int[] nums1, int[] nums2, int k) {

        int len1 = nums1.length;
        int len2 = nums2.length;

        int index1 = 0;
        int index2 = 0;

        while (true) {
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }

            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int half = k / 2;
            int newIndex1 = Math.min(len1, half + index1) - 1;
            int newIndex2 = Math.min(len2, half + index2) - 1;

            if (nums1[newIndex1] > nums2[newIndex2]) {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            } else {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }
        }

    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public static double findMedianArrays(int[] nums) {

        if (nums != null && nums.length > 0) {

            if (nums.length % 2 == 0) {

                return (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2.0d;

            } else {
                return nums[(nums.length - 1) / 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {

//        double median = findMedianSortedArrays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, null);
//        System.out.println("median:" + median);
//        median = findMedianSortedArrays(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, null);
//        System.out.println("median:" + median);
        double median = findMedianSortedArrays(new int[]{1,2, 2, 3, 4, 5, 6, 8, 9}, new int[]{2});
        System.out.println("median:" + median);

    }

}
