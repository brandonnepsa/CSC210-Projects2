import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K,V>{
    private ArrayList<HashNode<K,V>> buckets;
    private int numBuckets = 8;
    private int size = 0;

    public MyHashMap(K key, V value){
        this.buckets = new ArrayList<>(numBuckets);

    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    public V put(K key,V value){
        HashNode<K,V> input_node = new HashNode<>(key, value);
        for (HashNode<K,V> node: buckets){
            HashNode<K,V> curr = node;
            while (curr != null){
                if(curr.getKey().equals(input_node.getKey())){
                    curr.setValue(value);
                    return value;
                }
                curr = curr.getNext();
                }
        }
        int hash_val = hash(key);
        if (buckets.get(hash_val) != null){
            input_node.setNext(buckets.get(hash_val));
        }
        buckets.add(hash_val, input_node);
        size += 1;
        return input_node.getValue();
    }

    public boolean remove(Object key, Object value){
        for (int i = 0; i < buckets.size(); i++){
            HashNode<K,V> node = buckets.get(i);
            if(node.getKey().equals(key) && node.getValue().equals(value)){
                buckets.add(i, node.getNext());
                return true;
            }
            HashNode<K,V> curr = node;
            HashNode<K,V> prev = null;
            while (curr != null){
                if(curr.getKey().equals(key) && curr.getValue().equals(value) ){
                    prev.setNext(curr.getNext());
                    return true;
                }
                curr = curr.getNext();
                prev = curr;
            }
        }
        size -= 1;
        return false;
    }

    public int size(){
        return size;
    }
    public void clear(){
        this.buckets = new ArrayList<>();
    }

    public boolean containsKey(K search_key){
        for(HashNode<K,V> node: buckets){
            if(node.getKey().equals(search_key)){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V search_val){
        for(HashNode<K,V> node: buckets){
            if(node.getValue().equals(search_val)){
                return true;
            }
        }
        return false;
    }

    public V get(K search_key){
        for(HashNode<K,V> node: buckets){
            if(node.getKey().equals(search_key)){
                return node.getValue();
            }
        }
        return null;
    }

    public boolean isEmpty(){
        for(HashNode<K,V> node: buckets){
            if(node != null){
                return false;
            }
        }
        return true; 
    }

    public Set<K> keySet(){
        Set<K> myKeySet = new HashSet<>();
        for(HashNode<K,V> node: buckets){
            myKeySet.add(node.getKey());
        }
        return myKeySet;
    }

    public Set<K> bucketKeySet(int i){
        Set<K> bucketSet = new HashSet<>();
        HashNode<K,V> curr = buckets.get(i);
        while (curr != null){
            bucketSet.add(curr.getKey());
            curr = curr.getNext();
        }
        return bucketSet;
    }

    public int collisionCounter(int index){
        int collisions = 0;
        HashNode<K,V> curr = buckets.get(index);
        while (curr != null){
            collisions += 1;
            curr = curr.getNext();
        }
        return collisions;
    }

    public void printTable(){
        String msg = "";
        for(int i=0; i < buckets.size(); i++){
            int collisions = collisionCounter(i);
            Set<K> keySet = bucketKeySet(i);
            String keys = "";
            for(K key: keySet){
                keys += key + ",";
            }
            msg += "Index " + i + ": (" + collisions + " conlficts), ["+keys+"]\n";
        }
        System.out.println(msg);
    }
}