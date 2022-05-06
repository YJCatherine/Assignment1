package Final;

import javax.swing.tree.TreeNode;
import java.util.*;

public class Final {
    //Question 1
    public String compressString(String str){
        //TODO: Write your code here

        int length = str.length();

        if (length > 2) {
            String compressedText = "";
            char temp = str.charAt(0);
            int count = 1;

            for (int i = 1; i < length; i++) {
                if (str.charAt(i) == temp){
                    count++;
                }else {
                    compressedText += Character.toString(temp) + Integer.toString(count);
                    temp = str.charAt(i);
                    count = 1;
                }
            }
            compressedText += Character.toString(temp) + Integer.toString(count);

            if(compressedText.length() < length)
                return compressedText;
        }
        return str;
    }

    //Question 2
    public int numIslands(char[][] grid) {
        //TODO: Write your code here
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (grid[i][j] == '1'){
                    count++;
                    dfs(grid, row - 1, col - 1, i, j);
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] grid, int row, int col, int i, int j){
        if (i < 0 || i > row || j < 0 || j > row ||grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';

        dfs(grid, row, col, i + 1, j);
        dfs(grid, row, col, i - 1, j);
        dfs(grid, row, col, i, j + 1);
        dfs(grid, row, col, i, j - 1);

        return;
    }

    //Question 3
    public List<List<String>> groupAnagrams(String[] strs) {
        //TODO: Write your code here
        Map<String, List<String>> ans = new HashMap<>();

        for (String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String val = String.valueOf(arr);

            List<String> lst = ans.getOrDefault(val, new ArrayList<>());
            lst.add(str);

            ans.put(val, lst);
        }

        return new ArrayList<>(ans.values());

    }

    //Question 4
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //TODO: Write your code here
        if (root == null){
            return null;
        }
        if (root.val == p.val || root.val == q.val){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }

        return root;
    }

}
