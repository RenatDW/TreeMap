package logic;

import java.util.*;

public class TreeMap<K extends Comparable<K>, V> implements MyMap<K, V> {
    int size = 0;
    TreeNode<K, V> root;
    TreeNode<K, V> leftSide;
    TreeNode<K, V> rightSide;

    public class TreeNode<K, V> implements Comparable<K> {
        TreeNode<K, V> leftSide;
        TreeNode<K, V> rightSide;
        K key;
        V value;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(K o) {
            return this.key.compareTo(o);
        }
    }


    //todo add balancing
    private void putin(TreeNode<K, V> branch, K key, V value) {
        if (branch.key < key) {
            if (leftSide == null) {
                leftSide = new TreeNode<K, V>(key, value);
                size++;
            } else {
                putin(leftSide, key, value);
            }
        } else if (branch.key > key.compareTo(branch.key)) {
            if (rightSide == null) {
                rightSide = new TreeNode<K, V>(key, value);
                size++;
            } else {
                putin(rightSide, key, value);
            }
        } else {
            branch.value = value;
        }

    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(Object k) {
        return containsKeyRecursive(root, k);
    }

    private boolean containsKeyRecursive(TreeNode<K, V> branch, Object k) {
        if (branch != null) {
            if (branch.key.equals(k)) {
                return true;
            }
            if (branch.leftSide != null) {
                return containsKeyRecursive(leftSide, k);
            }
            if (branch.rightSide != null) {
                return containsKeyRecursive(rightSide, k);
            }
        }
        return false;
    }
    private V getValueorDefaultRecursive(TreeNode<K, V> branch, Object k, V defaultValue) {
        if (branch != null) {
            if (branch.key.equals(k)) {
                return branch.value;
            }
            if (branch.leftSide != null) {
                return getValueorDefaultRecursive(leftSide, k, defaultValue);
            }
            if (branch.rightSide != null) {
                return getValueorDefaultRecursive(rightSide, k, defaultValue);
            }
        }
        return defaultValue;
    }



    @Override
    public boolean containsValue(Object v) {
        return getValueorDefaultRecursive(root, v, null) == null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V get(Object k) {
        return getValueorDefaultRecursive(root, k, null);
    }

    @Override
    public V getOrDefault(Object k, V defaultValue) {
        return getValueorDefaultRecursive(root, k, defaultValue);
    }

    @Override
    public V put(K k, V v) {
        if (root != null) {
            putin(this.root, k, v);
        } else {
            this.root = new TreeNode<>(k, v);
        }
        return v;
    }



    @Override
    public Set<K> keySet() {
        Set<K> answer = new TreeSet<K>();
        getAllKey(root, answer);
        return answer;
    }

    public void getAllKey(TreeNode<K, V> cur, Set<K> ans) {
        if(cur != null){
            ans.add(cur.key);
            if(cur.leftSide != null);{
                getAllKey(leftSide, ans);
            }
            if(cur.rightSide != null);{
                getAllKey(rightSide, ans);
            }
        }
    }

    @Override
    public List<V> values() {
        List<V> ans = new ArrayList<>();
        getAllValues(root, ans);
        return ans;
    }

    public void getAllValues(TreeNode<K, V> cur, List<V> ans) {
        if(cur != null){
            ans.add(cur.value);
            if(cur.leftSide != null);{
                getAllValues(leftSide, ans);
            }
            if(cur.rightSide != null);{
                getAllValues(rightSide, ans);
            }
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        size--;
    }

    @Override
    public V remove(Object k) {
        size++;
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
