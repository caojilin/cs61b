public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        // YOUR CODE HERE
        if (r == null) {
            return null;
        }
        if (r.getItemCount() == 3) {
            RBTreeNode re = new RBTreeNode(true, r.getItemAt(1));
            re.left = new RBTreeNode(false, r.getItemAt(0));
            re.right = new RBTreeNode(false, r.getItemAt(2));
            if (!(r.getChildrenCount() == 0)) {
                re.left.left = buildRedBlackTree(r.getChildAt(0));
                re.left.right = buildRedBlackTree(r.getChildAt(1));
                re.right.left = buildRedBlackTree(r.getChildAt(2));
                re.right.right = buildRedBlackTree(r.getChildAt(3));
            }
            return re;
        } else if (r.getItemCount() == 2) {
            RBTreeNode re = new RBTreeNode(true, r.getItemAt(0));
            re.right = new RBTreeNode(false, r.getItemAt(1));
            if (!(r.getChildrenCount() == 0)) {
                re.left = buildRedBlackTree(r.getChildAt(0));
                re.right.left = buildRedBlackTree(r.getChildAt(1));
                re.right.right = buildRedBlackTree(r.getChildAt(2));
            }
            return re;
        } else {
            RBTreeNode re = new RBTreeNode(true, r.getItemAt(0));
            if (!(r.getChildrenCount() == 0)) {
                re.left = buildRedBlackTree(r.getChildAt(0));
                re.right = buildRedBlackTree(r.getChildAt(1));
            }
            return re;
        }
    }

    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {

        RBTreeNode newR = new RBTreeNode<>(false, node.item, node.left.right, node.right);
        node = new RBTreeNode<T>(node.isBlack, node.left.item, node.left.left, newR);
        return node;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {

        RBTreeNode newL = new RBTreeNode<>(false, node.item, node.left, node.right.left);
        node = new RBTreeNode<T>(node.isBlack, node.right.item, newL, node.right.right);
        return node;
    }

    /* Insert ITEM into the red black tree, rotating
       it accordingly afterwards. */
    void insert(T item) {
        // YOUR CODE HERE
        root = insert(root, item);
        root.isBlack = true;

    }

    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {

        // Insert (return) new red leaf node.
        if (node == null) {
            node = new RBTreeNode(false, item);
        }

        // Handle normal binary search tree insertion.
        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node; // do nothing.
        } else if (comp < 0) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        // handle case C and "Right-leaning" situation.
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        // handle case B
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        // handle case A
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    static class RBTreeNode<T extends Comparable<T>> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
