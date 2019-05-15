public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    public static void main(String[] args) {
        BinarySearchTree<Integer> x = new BinarySearchTree<>();
        x.add(4);
        x.add(8);
        x.add(9);
        x.add(6);
        x.add(7);
        x.printInorder();
    }

    /* Creates an empty BST. */
    public BinarySearchTree() {
        super();
    }

    /* Creates a BST with root as ROOT. */
    public BinarySearchTree(TreeNode root) {
        super(root);
    }

    /* Returns true if the BST contains the given KEY. */
    public boolean contains(T key) {
        return containsHelper(root, key);
    }

    private boolean containsHelper(TreeNode t, T key) {
        if (t == null) {
            return false;
        } else if (t.item.compareTo(key) == 0) {
            return true;
        } else if (t.item.compareTo(key) < 0) {
            return containsHelper(t.right, key);
        } else {
            return containsHelper(t.left, key);
        }
    }

    /* Adds a node for KEY iff KEY isn't in the BST already. */
    public void add(T key) {
        if (!contains(key)) {
            if (root != null) {
                addHelper(root, key);
            } else {
                root = new TreeNode(key);
            }
        }
    }

    private void addHelper(TreeNode t, T key) {
        if (t.item.compareTo(key) < 0) {
            if (t.right != null) {
                addHelper(t.right, key);
            } else {
                t.right = new TreeNode(key);
            }
        } else {
            if (t.left != null) {
                addHelper(t.left, key);
            } else {
                t.left = new TreeNode(key);
            }
        }
    }

    /* Deletes a node from the BST. */
    public T delete(T key) {
        TreeNode parent = null;
        TreeNode curr = root;
        TreeNode delNode = null;
        TreeNode replacement = null;
        boolean rightSide = false;

        while (curr != null && !curr.item.equals(key)) {
            if (curr.item.compareTo(key) > 0) {
                parent = curr;
                curr = curr.left;
                rightSide = false;
            } else {
                parent = curr;
                curr = curr.right;
                rightSide = true;
            }
        }
        delNode = curr;
        if (curr == null) {
            return null;
        }

        if (delNode.right == null) {
            if (root == delNode) {
                root = root.left;
            } else {
                if (rightSide) {
                    parent.right = delNode.left;
                } else {
                    parent.left = delNode.left;
                }
            }
        } else {
            curr = delNode.right;
            replacement = curr.left;
            if (replacement == null) {
                replacement = curr;
            } else {
                while (replacement.left != null) {
                    curr = replacement;
                    replacement = replacement.left;
                }
                curr.left = replacement.right;
                replacement.right = delNode.right;
            }
            replacement.left = delNode.left;
            if (root == delNode) {
                root = replacement;
            } else {
                if (rightSide) {
                    parent.right = replacement;
                } else {
                    parent.left = replacement;
                }
            }
        }
        return delNode.item;
    }
}
