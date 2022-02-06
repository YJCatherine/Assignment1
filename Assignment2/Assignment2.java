//INFO6205 Spring2022
//Name:YujieZhang
//NUID:002952029

import java.util.*;

public class Assignment2 {
    public static void main(String[] args){

    }

    // 35.Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int n = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                n = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n;
    }


    // 540.Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high){
            int mid = low + (high - low) / 2;
            if(mid % 2 == 1){
                mid--;
            }
            if (nums[mid] == nums[mid + 1]){
                low = mid + 2;
            }else {
                high = mid;
            }
        }
        return nums[low];
    }

    // 153.Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high){
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[high]){
                high = mid;
            }else {
                low = mid + 1;
            }
        }
        return nums[low];
    }


    // 253.Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;

        int sp = 0;
        int ep = 0;
        while (sp < n){
            if(start[sp] >= end[ep]){
                rooms -=1 ;
                ep += 1;
            }

            rooms +=1;
            sp +=1;
        }

        return rooms;
    }

    // 347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[k];

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] list = new ArrayList[nums.length + 1];
        for (int num : hashMap.keySet()) {
            int i = hashMap.get(num);
            if (list[i] == null) {
                list[i] = new ArrayList<>();
            }
            list[i].add(num);
        }
        int i = 0, t, j;
        for (t = nums.length; t > 0; --t) {
            if (list[t] != null) {
                for (j = 0; j < list[t].size() && i < k; ++j) {
                    result[i++] = list[t].get(j);
                }
            }
        }
        return result;
    }


    // 16. 3Sum Closest
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int minGap = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i) {
            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                int gap = Math.abs(sum - target);
                if (gap < minGap) {
                    result = sum;
                    minGap = gap;
                }

                if (sum < target) {
                    low++;
                } else {
                    high--;
                }

            }
        }
        return result;
    }

    // 57.Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]){
            list.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[0]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(new int[]{newInterval[0], newInterval[1]});

        while (i < intervals.length){
            list.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        int[][] result = new int[list.size()][2];
        for (i = 0; i < list.size(); i++){
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];
        }

        return result;
    }

    // 435. Non-overlapping Intervals
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(o1, o2) -> (o1[0] - o2[0]));

        int result = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++){
            if(end <= intervals[i][0])
                end = intervals[i][1];
            else {
                end = Math.min(end, intervals[i][1]);
                result++;
            }
        }
        return result;
    }

    // 986. Interval List Intersections
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length){
            int i0 = firstList[i][0];
            int i1 = firstList[i][1];
            int j0 = secondList[j][0];
            int j1 = secondList[j][1];

            if(j0 > i1) i++;
            else if (j1 < i0) j++;
            else {
                int[] interval = new int[2];
                interval[0] = Math.max(i0, j0);
                interval[1] = Math.min(i1, j1);
                result.add(interval);
                if (i1 > j1) j++;
                if (j1 >= i1) i++;
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    // 18. 4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>>res = new ArrayList<>();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++){
            if(a > 0 && nums[a] == nums[a-1]) continue;
            for (int b = a+1; b < nums.length - 2; b++){
                if (b > a+1 && nums[b] == nums[b-1]) continue;
                int c = b+1;
                int d = nums.length - 1;
                while (c < d){
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target){
                        c++;
                        while (c < d && nums[c] == nums[c-1]) c++;
                    }else if(sum > target){
                        d--;
                        while (c < d && nums[d] == nums[d+1]) d--;
                    }else {
                        res.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        c++;
                        d--;
                        while (c < d && nums[c] == nums[c-1]) c++;
                        while (c < d && nums[d] == nums[d+1]) d--;
                    }
                }
            }
        }

        return res;
    }


}
