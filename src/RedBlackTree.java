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

    public boolean getColor(TreeNode<T> node) {
        return node.color;
    }

    public void setRed(TreeNode<T> node) {
        node.color = RED;
    }

    public void setBlack(TreeNode<T> node) {
        node.color = BLACK;
    }

    public void setKey(TreeNode<T> node, T key) {
        node.key = key;
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
        while ((parent = parentOf(node)) != null && isRED(parent)) {
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

    private void remove(TreeNode<T> node) {
        TreeNode<T> replace = null;
        TreeNode<T> replace_son = null;
        if (node.left == null || node.right == null) {
            replace = node;
        } else {
            replace = findPredecessor(node);
        }
        if (replace.left != null) {
            replace_son = replace.left;
        } else {
            replace_son = replace.right;
        }
        if (replace_son != null) {
            setParent(replace, parentOf(replace));
        }
        if (replace.parent == null) {
            root = replace_son;
        } else if (replace == parentOf(replace).left) {
            parentOf(replace).left = replace_son;
        } else {
            parentOf(replace).right = replace_son;
        }
        if (replace != node) {
            setKey(node, replace.getKey());
        }
        if (getColor(replace) == BLACK) {
            removeAdjust(replace_son, parentOf(replace));
        }
        replace = null;
        return;
    }

    public void removeAdjust(TreeNode<T> node, TreeNode<T> parent) {
        TreeNode<T> brother = null;
        while (isBlack(node) && node != root) {
            if (node == parent.left) {
                brother = parent.right;
                // case 1: brother is red
                if (isRED(brother)) {
                    setBlack(brother);
                    setRed(parent);
                    leftRotate(parent);
                    brother = parent.right;
                }
                // brother is black

                // case 2: both nephews are black
                if ((brother.left == null || isBlack(brother.left)) && (brother.right == null || isBlack(brother.right))) {
                    setRed(brother);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    // case 3: far nephew is black, then near nephew must be red
                    if (brother.right == null || isBlack(brother.right)) {
                        setRed(brother);
                        setBlack(brother.left);
                        rightRotate(brother);
                        brother = parent.right;
                    }
                    // case 4: far nephew is red, near nephew can be any color
                    setColor(brother, getColor(parent));
                    setBlack(parent);
                    setBlack(brother.right);
                    leftRotate(parent);
                    node = root;
                }


            } else {
                brother = parent.left;
                if (isRED(brother)) {
                    setBlack(brother);
                    setRed(parent);
                    rightRotate(parent);
                    brother = parent.left;
                }

                if ((brother.left == null || isBlack(brother.left)) && (brother.right == null || isBlack(brother.right))) {
                    setRed(brother);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (brother.left == null || isBlack(brother.left)) {
                        setRed(brother);
                        setBlack(brother.right);
                        leftRotate(brother);
                        brother = parent.left;
                    }
                    setColor(brother, getColor(parent));
                    setBlack(parent);
                    setBlack(brother.left);
                    rightRotate(parent);
                    node = root;
                }
            }
        }
    }

    public void remove(T key) {
        TreeNode<T> node;
        if ((node = searchKey(key, root)) != null)
            remove(node);
    }


    private void destroy(TreeNode<T> node) {
        if (node == null)
            return;
        destroy(node.left);
        destroy(node.right);
        node = null;
    }

    public void clear() {
        destroy(root);
        root = null;
    }

    private void print(TreeNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)
                System.out.printf("%2d(B) is root\n", tree.key);
            else
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRED(tree) ? "R" : "B", key,
                        direction == 1 ? "right" : "left");
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.key, 0);
    }

}
