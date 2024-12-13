package logic;

import java.util.*;

public class TreeMap<K extends Comparable<K>, V extends Comparable<V>> implements MyMap<K, V> {
    int size = 0;
    TreeNode<K, V> root;
    TreeNode<K, V> leftSide;
    TreeNode<K, V> rightSide;

    public class TreeNode<K, V> {
        TreeNode<K, V> leftSide;
        TreeNode<K, V> rightSide;
        K key;
        V value;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }


    //todo add balancing
    private void putin(TreeNode<K, V> branch, K key, V value) {
        if (branch.key.compareTo(key) > 0) {
            if (branch.leftSide == null) {
                branch.leftSide = new TreeNode<>(key, value);
                size++;
            } else {
                putin(branch.leftSide, key, value);
            }
        } else if (branch.key.compareTo(key) < 0) {
            if (branch.rightSide == null) {
                branch.rightSide = new TreeNode<>(key, value);
                size++;
            } else {
                putin(branch.rightSide, key, value);
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
                return containsKeyRecursive(branch.leftSide, k);
            }
            if (branch.rightSide != null) {
                return containsKeyRecursive(branch.rightSide, k);
            }
        }
        return false;
    }
    private V getValueorDefaultRecursive(TreeNode<K, V> branch, Object k, V defaultValue) {
        if (branch != null) {
            if (branch.value.equals(k)) {
                return branch.value;
            }
            if (branch.leftSide != null) {
                return getValueorDefaultRecursive(branch.leftSide, k, defaultValue);
            }
            if (branch.rightSide != null) {
                return getValueorDefaultRecursive(branch.rightSide, k, defaultValue);
            }
        }
        return defaultValue;
    }



    @Override
    public boolean containsValue(Object v) {
        return getValueorDefaultRecursive(root, v, null) != null;
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
            size++;
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
                getAllKey(cur.leftSide, ans);
            }
            if(cur.rightSide != null);{
                getAllKey(cur.rightSide, ans);
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
                getAllValues(cur.leftSide, ans);
            }
            if(cur.rightSide != null);{
                getAllValues(cur.rightSide, ans);
            }
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        size--;
    }

    @Override
    public V remove(Object k) {
        if(removeRecursive(root, k)) {
            size--;
        }
        return null;
    }
    //todo доделать удаление
    private boolean removeRecursive(TreeNode<K,V> branch,Object k) {
        if(branch.leftSide != null){
            if(branch.leftSide.key.equals(k)){
                return true;
            }else{
                removeRecursive(branch.leftSide, k);
            }
        }
        if(branch.rightSide != null){
            if(branch.rightSide.key.equals(k)){
                return true;
            }else{
                removeRecursive(branch.rightSide, k);
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

}
