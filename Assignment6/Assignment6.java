package Assignment6;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Assignment6 {

    //437. Path Sum III
    public int pathSum(TreeNode root, int sum) {  //第一个递归：先序，递归每一个节点
        if(root==null)
            return 0;
        int res=findSum(root,sum);   //第二个递归：用于计算以当前节点为根节点的路径和为sum的总和
        res+=pathSum(root.left,sum);
        res+=pathSum(root.right,sum);
        return res;
    }

    public int findSum(TreeNode root,int sum){  //用于计算以当前节点为根节点的路径和为sum的总和
        if(root==null)
            return 0;
        int res=0;
        if(root.val==sum)
            res++;
        res+=findSum(root.left,sum-root.val);
        res+=findSum(root.right,sum-root.val);
        return res;
    }

    //236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q){return root;}

        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);

        if(left==null && right==null){return null;}
        if(right==null){return left;}
        if(left==null){return right;}
        return root;
    }


    //687. Longest Univalue Path
    public int longestUnivaluePath(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root, root.val);
        return max;
    }

    int max = 0;

    private int dfs(TreeNode root, int pre){
        if (root == null){
            return 0;
        }
        boolean flag = true;
        if (pre != root.val){
            flag = false;
        }
        int l = dfs(root.left, root.val);
        int r = dfs(root.right, root.val);
        max = Math.max(max, l + r);
        if (flag){
            return 1 + Math.max(l, r);
        }else{
            return 0;
        }
    }

    //297. Serialize and Deserialize Binary Tree
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                ans.append('n');
                ans.append(',');
            } else {
                ans.append(node.val);
                ans.append(',');
                q.add(node.left);
                q.add(node.right);
            }

        }
        ans.delete(ans.length() - 1, ans.length());
        return ans.toString();
    }

    private TreeNode charToNode(String ch) {
        if (ch.equals("n")) return null;
        else {
            int val = Integer.parseInt(ch);
            return new TreeNode(val);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q = new LinkedList<>();
        String[] s = data.split(",");
        int index = 0;
        TreeNode root = charToNode(s[index]);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                node.left = charToNode(s[++index]);
                node.right = charToNode(s[++index]);
                q.add(node.left);
                q.add(node.right);
            }
        }
        return root;
    }


    //987. Vertical Order Traversal of a Binary Tree
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> list = new ArrayList<>();
        dfs(root,list,0,0);
        List<List<Integer>> res = new ArrayList<>();
        Collections.sort(list,(o1,o2)->{
            if(o1.col == o2.col){
                if(o1.row == o2.row) return o1.value - o2.value;
                return o1.row - o2.row;
            }
            return o1.col - o2.col;
        });
        int i = 0;

        while(i < list.size()){
            List<Integer> temp = new ArrayList<>();
            int pre = Integer.MAX_VALUE;
            while(temp.size() == 0 || (pre == list.get(i).col)){
                pre = list.get(i).col;
                temp.add(list.get(i++).value);
                if(i >= list.size()) break;
            }
            res.add(temp);
        }
        return res;
    }
    private void dfs(TreeNode root,List<Node> list,int row,int col){
        if(root == null) return;
        Node curNode = new Node(row,col,root.val);
        list.add(curNode);
        if(root.left != null) dfs(root.left,list,row+1,col-1);
        if(root.right != null) dfs(root.right,list,row+1,col+1);
    }

    class Node{
        int row;
        int col;
        int value;
        public Node(int row,int col,int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    //222. Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        if (root==null) return 0;
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if(ld == rd) return (1 << ld) + countNodes(root.right);
        else return (1 << rd) + countNodes(root.left);

    }

    private int getDepth(TreeNode r) {
        int depth = 0;
        while(r != null) {
            depth++;
            r = r.left;
        }
        return depth;
    }


    //129. Sum Root to Leaf Numbers
    List<Integer> path = new ArrayList<>();
    int res = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        path.add(root.val);
        recur(root);
        return res;
    }

    public void recur(TreeNode root){
        if (root.left == null && root.right == null) {
            res += listToInt(path);
            return;
        }

        if (root.left != null){
            path.add(root.left.val);
            recur(root.left);
            path.remove(path.size() - 1);
        }
        if (root.right != null){
            path.add(root.right.val);
            recur(root.right);
            path.remove(path.size() - 1);
        }
        return;
    }
    public int listToInt(List<Integer> path){
        int sum = 0;
        for (Integer num:path){
            sum = sum * 10 + num;
        }
        return sum;
    }


    //1325. Delete Leaves With a Given Value
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode g = new TreeNode(-1);
        g.left = root;
        dfs(g , root , target , 0);
        return g.left;
    }
    void dfs(TreeNode p , TreeNode c , int t , int f){
        if(c == null){
            return;
        }
        dfs(c , c.left , t , 0);
        dfs(c , c.right , t , 1);
        if(c.left == null && c.right == null && c.val == t){
            if(f == 0){
                p.left = null;
            }else{
                p.right = null;
            }
        }
    }


}
