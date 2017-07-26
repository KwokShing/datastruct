public class RedBlackTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    class TreeNode<T extends Comparable<T>> {
        boolean color;
        T key;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode<T> parent;


        public TreeNode(boolean color, T key, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return key;
        }

        public String toString() {
            return "" + key + (this.color == RED ? "red" : "black");
        }
    }

    public RedBlackTree() {
        root = null;
    }

    public boolean isRED(TreeNode<T> node) {
        return (node == null || node.color != RED) ? false : true;
    }

    public boolean isBlack(TreeNode<T> node) {
        return (node == null || node.color != BLACK) ? false : true;
    }

    public void setColor(TreeNode<T> node, boolean color) {
        node.color = color;
    }

    public void setRed(TreeNode<T> node) {
        node.color = RED;
    }

    public void setBlack(TreeNode<T> node) {
        node.color = BLACK;
    }

    public TreeNode<T> parentOf(TreeNode<T> node) {
        return node != null ? node.parent : null;
    }

    public void setParent(TreeNode<T> node, TreeNode<T> parent) {
        node.parent = parent;
    }

    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    private void preOrderTraverse(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root + " ");
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(TreeNode<T> root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root + " ");
            inOrderTraverse(root.right);
        }
    }

    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    private void postOrderTraverse(TreeNode<T> root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root + " ");
        }
    }

    public TreeNode<T> searchKey(T key) {
        return searchKey(key, root);
    }

    private TreeNode<T> searchKey(T key, TreeNode<T> node) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public T findMinVal() {
        TreeNode<T> res = findMin(root);
        if (res != null)
            return res.key;
        return null;
    }

    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T findMaxVal() {
        TreeNode<T> res = findMax(root);
        if (res != null)
            return res.key;
        return null;
    }

    private TreeNode<T> findMax(TreeNode<T> node) {
        if (node == null)
            return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public TreeNode<T> findSuccessor(TreeNode<T> node) {
        if (node == null)
            return null;
        if (node.right != null)
            return findMin(node.right);

        TreeNode<T> p = node.parent;
        while ((p != null) && (node == p.parent.right)) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    public TreeNode<T> findPredecessor(TreeNode<T> node) {
        if (node == null)
            return null;
        if (node.left != null)
            return findMin(node.left);

        TreeNode<T> p = node.parent;
        while ((p != null) && (node == p.parent.left)) {
            node = p;
            p = p.parent;
        }
        return p;
    }
}
