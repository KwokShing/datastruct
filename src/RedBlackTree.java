public class RedBlackTree<E extends Comparable<E>> {
    private TreeNode<E> root;
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

    public boolean isRED(TreeNode<E> node) {
        return (node == null || node.color != RED) ? false : true;
    }

    public boolean isBlack(TreeNode<E> node) {
        return (node == null || node.color != BLACK) ? false : true;
    }

    public void setColor(TreeNode<E> node, boolean color) {
        node.color = color;
    }

    public void setRed(TreeNode<E> node) {
        node.color = RED;
    }

    public void setBlack(TreeNode<E> node) {
        node.color = BLACK;
    }

    public TreeNode<E> parentOf(TreeNode<E> node) {
        return node != null ? node.parent : null;
    }

    public void setParent(TreeNode<E> node, TreeNode<E> parent) {
        node.parent = parent;
    }

    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    private void preOrderTraverse(TreeNode<E> root) {
        if (root != null) {
            System.out.print(root + " ");
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(TreeNode<E> root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root + " ");
            inOrderTraverse(root.right);
        }
    }

    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    private void postOrderTraverse(TreeNode<E> root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root + " ");
        }
    }

    public TreeNode<E> searchKey(E key) {
        return searchKey(key, root);
    }

    private TreeNode<E> searchKey(E key, TreeNode<E> node) {
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

    public E findMinVal() {
        TreeNode<E> res = findMin(root);
        if (res != null)
            return res.key;
        return null;
    }

    private TreeNode<E> findMin(TreeNode<E> node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public E findMaxVal() {
        TreeNode<E> res = findMax(root);
        if (res != null)
            return res.key;
        return null;
    }

    private TreeNode<E> findMax(TreeNode<E> node) {
        if (node == null)
            return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public TreeNode<E> findSuccessor(TreeNode<E> node) {
        if (node == null)
            return null;
        if (node.right != null)
            return findMin(node.right);

        TreeNode<E> p = node.parent;
        while ((p != null) && (node == p.parent.right)) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    public TreeNode<E> findPredecessor(TreeNode<E> node) {
        if (node == null)
            return null;
        if (node.left != null)
            return findMin(node.left);

        TreeNode<E> p = node.parent;
        while ((p != null) && (node == p.parent.left)) {
            node = p;
            p = p.parent;
        }
        return p;
    }

    private void leftRotate(TreeNode<E> x) {
        if (x == null)
            return;
        TreeNode<E> y = x.right;
        TreeNode<E> p = x.parent;

        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = p;
        if (p == null) {
            root = y;
        } else if (x == p.left) {
            p.left = y;
        } else {
            p.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(TreeNode<E> y) {
        if (y == null)
            return;
        TreeNode<E> x = y.left;
        TreeNode<E> p = y.parent;

        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        y.parent = p;
        if (p == null) {
            root = x;
        } else if (y == p.left) {
            p.left = x;
        } else {
            p.right = x;
        }

        x.right = y;
        y.parent = x;
    }
}
