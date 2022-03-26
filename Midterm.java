import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Midterm {
    //Question1:
    public List<String> missingRange(int []nums, int lower, int upper){
        List<String> ans = new LinkedList<>();
        int low = lower;
        int up = upper;
        boolean overrange = false;
        for(int i = 0; i < nums.length; i++){
            if(low < nums[i]){
                ans.add(getRange(low, nums[i] - 1));
            }
            low = nums[i] + 1;
            if(low == Integer.MIN_VALUE){
                overrange = true;
                break;
            }
        }

        if(low <= up && overrange == false){
            ans.add(getRange(low, up));
        }
        return ans;
    }
    private String getRange(int a, int b){
        if(a == b){
            return a+"";
        }else{
            return a + "->" + b;
        }
    }
    //Question2:
    public ListNode addNumbers(ListNode l1, ListNode l2){
        int carry = 0;
        ListNode ans = new ListNode(0);
        ListNode head = ans;

        while (l1 != null || l2 != null || carry != 0){
            int sum = carry;
            sum += l1 != null ? l1.val : 0;
            sum += l2 != null ? l2.val : 0;
            ans.next = new ListNode(sum % 10);
            carry = sum/10;
            ans = ans.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return head.next;
    }


    //Question3:
    int index = 0;

    public TreeNode buildTree(int[] preorder, int[]inorder){
        TreeNode root = constructTree(preorder, inorder,0, preorder.length);
        return root;
    }

    private TreeNode constructTree(int[]preorder, int[]inorder, int l, int r){
        if(l >= r) return null;
        int parentIndex = findParentIndex(preorder[index++], l, r, inorder);
        TreeNode node = new TreeNode(inorder[parentIndex]);
        node.left = constructTree(preorder,inorder, l, parentIndex);
        node.right = constructTree(preorder,inorder, parentIndex + 1, r);
        return node;
    }

    private int findParentIndex(int par,int l, int r, int[]inorder){
        int parIndex = l;
        for(int i = l; i < r; i++){
            if(inorder[i] == par){
                parIndex = i;
                break;
            }
        }
        return parIndex;
    }

    //Question4:
    public int[][] merge(int[][] intervals){
        Arrays.sort(intervals,(i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++){
            int[] temp = new int[2];
            temp[0] = intervals[i][0];
            temp[1] = intervals[i][1];

            while (i < intervals.length - 1 && temp[1] >= intervals[i+1][0]){
                temp[1] = Math.max(temp[1], intervals[i+1][1]);
                i++;
            }

            result.add(temp);
        }

        return  result.toArray(new int[result.size()][]);
    }





}
