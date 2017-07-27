public class RedBlackTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    class TreeNode<E extends Comparable<E>> {
        boolean color;
        E key;
        TreeNode<E> left;
        TreeNode<E> right;
        TreeNode<E> parent;


        public TreeNode(boolean color, E key, TreeNode<E> parent, TreeNode<E> left, TreeNode<E> right) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public E getKey() {
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

    private void leftRotate(TreeNode<T> x) {
        if (x == null)
            return;
        TreeNode<T> y = x.right;
        TreeNode<T> p = x.parent;

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

    private void rightRotate(TreeNode<T> y) {
        if (y == null)
            return;
        TreeNode<T> x = y.left;
        TreeNode<T> p = y.parent;

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

    public void insert(T key) {
        TreeNode<T> node = new TreeNode<>(RED, key, null, null, null);
        insert(node);
    }

    private void insert(TreeNode<T> node) {
        TreeNode<T> node_parent = null;
        TreeNode<T> x = root;

        while (x != null) {
            node_parent = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = node_parent;

        if (node_parent != null) {
            int cmp = node.key.compareTo(node_parent.key);
            if (cmp < 0) {
                node_parent.left = node;
            } else {
                node_parent.right = node;
            }
        } else {
            root = node;
        }
        insertAdjust(node);
    }

    private void insertAdjust(TreeNode<T> node) {
        TreeNode<T> parent, grandParent;
        while((parent = parentOf(node))!=null && isRED(parent)) {
            grandParent = parentOf(parent);
            if (parent == grandParent.left) {
                TreeNode<T> uncle = grandParent.right;
                if ((uncle != null) && isRED(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                } else if (node == parent.right) {
                    leftRotate(parent);
                    TreeNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setBlack(parent);
                setRed(grandParent);
                rightRotate(grandParent);
            } else {
                TreeNode<T> uncle = grandParent.left;
                if ((uncle != null) && isRED(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                } else if (node == parent.left) {
                    rightRotate(parent);
                    TreeNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setBlack(parent);
                setRed(grandParent);
                leftRotate(grandParent);
            }
        }
        setBlack(root);
    }
}
