//Task 2: Binary Search Tree (BST) Implementation

import java.util.Scanner;

class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}
public class BinarySearchTree {
    TreeNode root;

    // Insert a node
    public TreeNode insert(TreeNode root, int value) {
        if (root == null)
            return new TreeNode(value);
        if (value < root.value)
            root.left = insert(root.left, value);
         else if (value > root.value)
            root.right = insert(root.right, value);
            return root;
        }

    // Search a value
    public boolean search(TreeNode root, int value) {
        if (root == null)
            return false;
        if (value == root.value)
            return true;
        if (value < root.value)
            return search(root.left, value);
        return search(root.right, value);
    }

    // Delete a node
    public TreeNode delete(TreeNode root, int value) {
        if (root == null)
            return null;
        if (value < root.value)
            root.left = delete(root.left, value);
        else if (value > root.value)
            root.right = delete(root.right, value);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Node with two children
            root.value = minValue(root.right);
            root.right = delete(root.right, root.value);
        }
        return root;
    }

    private int minValue(TreeNode node) {
        int min = node.value;
        while (node.left != null) {
            node = node.left;
            min = node.value;
        }
        return min;
    }

    // Traversals
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.value + " ");
            inOrder(root.right);
        }
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.value + " ");
        }
    }

    // Main method
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Binary Search Tree Menu =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. In-order Traversal");
            System.out.println("5. Pre-order Traversal");
            System.out.println("6. Post-order Traversal");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int insertVal = scanner.nextInt();
                    bst.root = bst.insert(bst.root, insertVal);
                    break;

                case 2:
                    System.out.print("Enter value to delete: ");
                    int deleteVal = scanner.nextInt();
                    bst.root = bst.delete(bst.root, deleteVal);
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    int searchVal = scanner.nextInt();
                    boolean found = bst.search(bst.root, searchVal);
                    if (found)
                        System.out.println(searchVal + " found in BST.");
                    else
                        System.out.println(searchVal + " not found.");
                    break;

                case 4:
                    System.out.print("In-order Traversal: ");
                    bst.inOrder(bst.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Pre-order Traversal: ");
                    bst.preOrder(bst.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Post-order Traversal: ");
                    bst.postOrder(bst.root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter between 1 and 7.");
            }
        }
    }
}