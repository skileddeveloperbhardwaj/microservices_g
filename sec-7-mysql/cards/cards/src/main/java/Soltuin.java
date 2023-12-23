import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Soltuin {
//    private static boolean isFound = false;
    private static int M;
    private static int N;

    public static boolean isWordPresent(String str, char[][] arr) {
        M = arr.length;
        N= arr[0].length;

        boolean isFound = false;
        //FISH
        for (int i=0;i<M;i++) {
            for (int j=0;j<N;j++) {
                if (arr[i][j] == str.charAt(0)) {
                     isFound = dfs(str, arr, i, j, 0);
                }

                if (isFound) {
                    return isFound;
                }
            }
        }

        return false;
    }

    private static boolean dfs(String str, char[][] arr, int x, int y, int index) {
        if (x < 0 || x >= M || y < 0 || y >= N || index >= str.length() || arr[x][y] != str.charAt(index)) {
            return false;
        }

        if (index == str.length()-1 && arr[x][y] == str.charAt(index)) {
            return true;
        }

        return dfs(str, arr, x+1, y, index+1) || dfs(str, arr, x, y+1, index+1);
    }

    /*
    You have a List of Employees.
    1. Fetch all the employees whose salary is greater than 50,000
    2. Sort them by their name.
     */
    public List<Employee> salaryGT(List<Employee> list) {
        List<Employee> res = list.stream().
                filter(e -> e.salary > 50000).
                sorted(Comparator.comparing(e -> e.name)).
                collect(Collectors.toList());

        return res;
    }

//        -10
//                /         \
//                9              20
//                          /       \
//                          15          7
    public static void main(String[] args) {
//        char[][] arr = new char[][] {
//                {'F','I','T','E'},
//                {'B','S','I','S'},
//                {'A','D','S','H'}
//        };
//        String str = "FIS";
//        System.out.println(isWordPresent(str, arr));

        TreeNode root = new TreeNode(-10);
        TreeNode left1 = new TreeNode(9);
        TreeNode right1 = new TreeNode(20);
        root.left = left1;
        root.right = right1;
        TreeNode left2 = new TreeNode(15);
        right1.left = left2;
        right1.right = new TreeNode(7);

        System.out.println(maxPathSum(root));

    }

    public static int maxPathSum(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return node.val;
        }

        int leftVal = maxPathSum(node.left);
        int rightVal = maxPathSum(node.right);
        System.out.println("node:" + node.val);
        System.out.println("leftVal:" + leftVal);
        System.out.println("rightval:" + rightVal);

        return Math.max(leftVal+ node.val, Math.max(rightVal+ node.val, leftVal+rightVal+node.val));
    }

}

class Employee {
    long salary;
    String name;
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int v) {
        val = v;
    }
}