package Assignment5;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Assignment {

    //107. Binary Tree Level Order Traversal II
    public List<List<Integer>> list=new ArrayList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        bfs(root,0);
        Collections.reverse(list);
        return list;
    }
    public void bfs(TreeNode root,int step){
        if(root==null) return;
        if(list.size()<=step) list.add(new ArrayList<>());
        bfs(root.left,step+1);
        bfs(root.right,step+1);
        list.get(step).add(root.val);
    }


    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        while (root != null) {
            List list = new ArrayList<>();
            root = recur(root, list);
            resList.add(list);
        }
        return resList;
    }

    //366. Find Leaves of Binary Tree
    private TreeNode recur(TreeNode root, List<Integer> list) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return null;
        }
        root.left = recur(root.left, list);
        root.right = recur(root.right, list);
        return root;
    }

    //662. Maximum Width of Binary Tree
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int ans=0;
        root.val=0;
        LinkedList<TreeNode> queue=new LinkedList();
        queue.add(root);
        while(queue.size()!=0){
            int size=queue.size();
            int max=queue.getLast().val;
            int min=queue.getFirst().val;
            ans=Math.max(ans,max-min+1);
            for(int i=0;i<size;i++){
                TreeNode t=queue.removeFirst();
                if(t.left!=null){
                    t.left.val=t.val*2+1;
                    queue.add(t.left);
                }
                if(t.right!=null){
                    t.right.val=t.val*2+2;
                    queue.add(t.right);
                }
            }
        }

        return ans;
    }


    //515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null) return list;
        queue.offer(root);
        while(!queue.isEmpty()){
            int len=queue.size();
            int max=queue.peek().val;
            while(len>0){
                TreeNode node=queue.poll();
                if(node.val>max) max=node.val;
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
                len--;
            }
            list.add(max);
        }
        return list;
    }

}
