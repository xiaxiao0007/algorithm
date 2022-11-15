import java.util.ArrayList;
import java.util.List;


class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

public class mid_2022_11_15 {

    List<List<Integer>> ans;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        mid_2022_11_15 test = new mid_2022_11_15();
        test.BSTSequences(root);
    }

    public List<List<Integer>> BSTSequences(TreeNode root) {
        // 这个数组是二叉搜索树
        // 二叉搜索树数组能产生多少个数组,和层序遍历很像
        // 使用回溯法求解,每一个节点都必须排在它的子孙结点前面,对于遍历所有出现的情况下,使用回溯法

        ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if(root == null){
            ans.add(path);
            return ans;
        }
        List<TreeNode> list = new ArrayList<>();// 选择列表
        list.add(root);
        bfs(path,list);

        return ans;
    }
    // path为选择路径,list为选择列表
    public void bfs(List<Integer> path,List<TreeNode> list){
        if(list.isEmpty()){
            ans.add(new ArrayList<>(path));
            return ;
        }

        // 用于回溯,保留此次选择之前选择列表的信息
        List<TreeNode> copy = new ArrayList<>(list);

        for(int i=0;i<list.size();i++){// 回溯的纵向遍历,选择链表
            TreeNode temp = list.get(i);
            path.add(temp.val);
            list.remove(i);
            if(temp.left != null) list.add(temp.left);
            if(temp.right != null) list.add(temp.right);
            bfs(path,list);

            // 恢复 path 和 list ,进行回溯 回到之前的选择列表之中。
            path.remove(path.size() - 1);
            list = new ArrayList<>(copy);// 恢复之前的选这列表,循环递增到下一个元素
        }
    }
}
