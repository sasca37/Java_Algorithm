import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rootNode = Integer.parseInt(br.readLine());
        Node tree = new Node(rootNode);
        String num = "";
        while (true) {
            num = br.readLine();
            if (num == null || num.equals("")) break;
            tree.insert(Integer.parseInt(num));
        }

        postOrder(tree);
        System.out.println(sb);
    }

    private static void postOrder(Node tree) {
        if (tree.left != null) postOrder(tree.left);
        if (tree.right != null) postOrder(tree.right);
        sb.append(tree.node).append("\n");
    }

    static class Node {
        int node;
        Node left, right;

        public Node(int node) {
            this.node = node;
        }

        public void insert(int insertNode) {
            // 현재 노드 보다 작은 경우 왼쪽
            if (this.node > insertNode) {
                if (this.left == null) {
                    this.left = new Node(insertNode);
                } else {
                    this.left.insert(insertNode);
                }
            } // 현재 노드 보다 큰 경우 오른쪽
            else {
                if (this.right == null) {
                    this.right = new Node(insertNode);
                } else {
                    this.right.insert(insertNode);
                }
            }
        }
    }
}
