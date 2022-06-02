import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Node>> trees = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) trees.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';
            trees.get(node).add(new Node(left, right));
        }

        preOrder(0);
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);
        sb.append("\n");
        System.out.println(sb);
    }

    // 루트 왼쪽 오른쪽
    private static void preOrder(int node) {
        for (Node current : trees.get(node)) {
            char currentNode = (char) (node+'A');
            sb.append(currentNode);
            if (current.left >= 0) preOrder(current.left);
            if (current.right >= 0) preOrder(current.right);
        }
    }

    // 왼쪽 루트 오른쪽
    private static void inOrder(int node) {
        for (Node current : trees.get(node)) {
            char currentNode = (char) (node+'A');
            if (current.left >= 0) inOrder(current.left);
            sb.append(currentNode);
            if (current.right >= 0) inOrder(current.right);
        }
    }

    // 왼쪽 오른쪽 루트
    private static void postOrder(int node) {
        for (Node current : trees.get(node)) {
            char currentNode = (char) (node+'A');
            if (current.left >= 0) postOrder(current.left);
            if (current.right >= 0) postOrder(current.right);
            sb.append(currentNode);
        }
    }

    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
