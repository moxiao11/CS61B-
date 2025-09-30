package bstmap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> ,Iterable<K> {
    private BSTNode root ;
    /** Removes all of the mappings from this map. */
    private class BSTNode
    {
        private K key ;
        private V value ;
        private BSTNode left, right ;
        private int size ;
        public BSTNode(K key, V value, int size )
        {
            this.key = key ;
            this.value = value ;
            this.size = size ;
        }
    }

    @Override
    public void clear(){
        root = null ;
    }
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return containsKeyHelp(root ,key) ;
    }
    private boolean containsKeyHelp(BSTNode root, K key)
    {
        if(root == null) return false ;
        int cmp = root.key.compareTo(key);
        if(cmp == 0) return true ;
        else if(cmp < 0) return containsKeyHelp(root.right ,key) ;
        else return containsKeyHelp(root.left , key) ;
    }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        BSTNode node = getNode(root,key);
        if(node == null) return null ;
        else return node.value;
    }
    private BSTNode getNode(BSTNode root , K key)
    {
        if(root == null ) return null ;
        int cmp = root.key.compareTo(key) ;
        if(cmp == 0) return root;
        else if(cmp < 0 ) return getNode(root.right ,key) ;
        else return getNode(root.left , key) ;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return getsize(root) ;
    }
    private int getsize(BSTNode root)
    {
        if(root == null) return 0  ;
        else return root.size;
    }


    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        root = put_help(root, key ,value ) ;
    }
    private BSTNode put_help(BSTNode root , K key ,V value){
        if(root == null) return new BSTNode(key ,value ,1) ;
        int cmp = root.key.compareTo(key) ;
        if(cmp == 0) root.value = value ;
        else if( cmp < 0) root.right  =  put_help(root.right , key , value ) ;
        else root.left  =  put_help(root.left , key, value) ;
        root.size = 1 + getsize(root.left) + getsize(root.right);
        return root;
    }
    private class myIterator implements Iterator<K>{
        LinkedList<BSTNode> list;
        public myIterator() {
            list = new LinkedList<>();
            list.addLast(root);
        }
        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }
        @Override
        public K next(){
            BSTNode node = list.removeFirst();
            if(node.left != null ) list.addLast(node.left);
            if(node.right != null ) list.addLast(node.right);
            return node.key ;
        }

    }
    public Iterator<K> iterator()
    {
        return new myIterator() ;
    }
    public void printInOrder()
    {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(BSTNode root)
    {
        if(root == null) return ;
        printInOrderHelper(root.left);
        System.out.println(root.key + " ");
        printInOrderHelper(root.right);
    }
    private BSTNode getParentNode(BSTNode root, K key) {
        if (root == null) return null;

        // 如果左孩子存在并且就是目标
        if (root.left != null && root.left.key.compareTo(key) == 0) {
            return root;
        }
        // 如果右孩子存在并且就是目标
        if (root.right != null && root.right.key.compareTo(key) == 0) {
            return root;
        }

        // 根据 key 大小决定往哪边找
        int cmp = root.key.compareTo(key);
        if (cmp > 0) {
            return getParentNode(root.left, key);
        } else if (cmp < 0) {
            return getParentNode(root.right, key);
        } else {
            return null; // 根节点没有父节点
        }
    }
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet(){
        if(root == null) return null ;
        Set<K> set = new HashSet<>() ;
        for (K k : this) {
            set.add(k);
        }
        return set ;
    }
    @Override
    public V remove(K key)
    {
        V oldValue = get(key);
        remove(key ,oldValue);
        return oldValue;
    }
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key, V value) {
        V oldValue = get(key);
        if (oldValue == null || !oldValue.equals(value)) {
            return null; // 不存在该 key 或 value 不匹配
        }
        root = removeNode(root, key);
        return oldValue;
    }

    private BSTNode removeNode(BSTNode node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            // 找到目标节点
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 左右子树都存在，找后继
            BSTNode successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = removeNode(node.right, successor.key);
        }

        node.size = 1 + getsize(node.left) + getsize(node.right);
        return node;
    }

    private BSTNode min(BSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
