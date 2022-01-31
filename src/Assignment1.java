//INFO6205 Spring2022
//Name:YujieZhang
//NUID:002952029

import java.util.*;

public class Assignment1 {
    public static void main(String[] args) {

    }

    // 75. Sort Clolrs
    public void sortColors(int[] nums) {
        int low = -1, high = nums.length;
        int current = 0;
        while (current < high) {
            if (nums[current] == 0) {
                swap(nums, current, low + 1);
                low++;
                current++;
            } else if (nums[current] == 1) {
                current++;
            } else if (nums[current] == 2) {
                swap(nums, current, high - 1);
                high--;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


    // 229. Majority Element II
    public List<Integer> majorityElement(int[] nums) {
        Integer candidate1 = null, vote1 = 0;
        Integer candidate2 = null, vote2 = 0;

        for (int num : nums) {
            if (candidate1 == null || vote1 == 0) {
                candidate1 = num;
                vote1++;
            } else if (candidate2 == null || vote2 == 0) {
                candidate2 = num;
                vote2++;
            } else if (candidate1 == num) {
                vote1++;
            } else if (candidate2 == num) {
                vote2++;
            } else {
                vote1--;
                vote2--;
            }
        }

        //verfy the vote;
        vote1 = 0;
        vote2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                vote1++;
            } else if (num == candidate2) {
                vote2++;
            }
        }

        //Output the result;
        List<Integer> result = new ArrayList<>();
        if (vote1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (vote2 > nums.length / 3) {
            result.add(candidate2);
        }
        return result;

    }


    // 274. HIndex
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            i--;
            h++;
        }
        return h;
    }


    //349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] arr = new int[1001];
        for (int i = 0; i < nums1.length; i++) {
            if (arr[nums1[i]] == 0) arr[nums1[i]]++;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (arr[nums2[i]] == 1) {
                list.add(nums2[i]);
                arr[nums2[i]] = 0;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }


    // 658. Find K Closest Elements
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0;
        int high = arr.length - k;
        while (low < high) {
            int mid = (low + high) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = high; i < high + k; i++) {
            res.add(arr[i]);
        }

        return res;
    }

    // 767. Reorganize String
    public String reorganizeString(String s) {
        int len = s.length();
        int[] nums = new int[26];

        for (char c : s.toCharArray())
            nums[c - 'a']+=100;
        for (int i=0; i < 26;i++)
            nums[i]+=i;

        Arrays.parallelSort(nums);

        int t=1;
        char[] ans = new char[len];

        for (int num : nums){
            int ct = num/100;
            char ch = (char) ('a'+num%100);
            for(int i=0; i<ct;i++){
                if (t>=len) t = 0;
                ans[t] = ch;
                t+=2;
            }
        }

        return new String(ans);
    }

    // 791. Custom Sort String
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        StringBuilder ans = new StringBuilder();
        for (char c : order.toCharArray()){
            for (int i = 0; i < cnt[c - 'a']; ++i)
                ans.append(c);
            cnt[c - 'a'] = 0;
        }

        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < cnt[c - 'a']; ++i)
                ans.append(c);

        return ans.toString();
    }

    // 969. Pancake Sorting
    public static List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new ArrayList();
        int l = arr.length;
        while (l > 0) {
            for (int i = 0; i < l; i++) {
                if (arr[i] == l) {
                    flip(arr, i + 1);
                    flip(arr, l);
                    ret.add(i + 1);
                    ret.add(l);
                    break;
                }
            }
            l--;
        }
        return ret;
    }

    public static void flip(int[] arr, int n) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 1636. Sort Array by Increasing Frequency
    public int[] frequencySort(int[] nums) {
        int[] tmp = new int[201];
        for (int n : nums) {
            tmp[n + 100]++;   // +100 for those < 0
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[nums[i] + 100] * 10000 - nums[i] + 100;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = 100 - nums[i] % 10000;
        }

        return nums;

    }

    // 692. Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((s1, s2) -> {
            if (count.get(s1).equals(count.get(s2))) {
                return s2.compareTo(s1);
            } else {
                return count.get(s1) - count.get(s2);
            }
        });
        // 3.依次向堆加入元素。
        for (String s : count.keySet()) {
            minHeap.offer(s);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> res = new ArrayList<String>(k);
        while (minHeap.size() > 0) {
            res.add(minHeap.poll());
        }

        Collections.reverse(res);
        return res;
    }

}
