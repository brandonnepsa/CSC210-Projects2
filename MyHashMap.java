import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K,V>{
    private ArrayList<HashNode<K,V>> buckets;
    private int numBuckets;
    private int size;

    public MyHashMap(){
    	this.numBuckets = 8;
    	this.size = 0;
        this.buckets = new ArrayList<>(numBuckets);
        for(int i=0; i < numBuckets; i++) {
        	buckets.add(null);
        }

    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    public V put(K key,V value){
    	int index = hash(key);
    	System.out.println("Putting key: " + key + ", index: " + index);
    	System.out.println("Buckets size: " + buckets.size());
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
            if(node != null) {
            	if(node.getKey().equals(key) && node.getValue().equals(value)){
            		buckets.add(i, node.getNext());
            		size -= 1;
            		return true;
            	}
            	HashNode<K,V> curr = node;
            	HashNode<K,V> prev = null;
            	while (curr != null){
            		if(curr.getKey().equals(key) && curr.getValue().equals(value) ){
            			prev.setNext(curr.getNext());
            			size -= 1;
            			return true;
            		}
            		curr = curr.getNext();
            		prev = curr;
            	}
            }
        }
        size -= 1;
        return false;
    }

    public int size(){
        return size;
    }
    public void clear(){
    	this.size = 0;
        this.buckets = new ArrayList<>(numBuckets);
        for(int i=0; i < numBuckets; i++) {
        	buckets.add(null);
        }
    }

    public boolean containsKey(K search_key){
        for(HashNode<K,V> node: buckets){
        	if(node != null) {
        		if(node.getKey().equals(search_key)){
        			return true;
        		}
            }
        }
        return false;
    }

    public boolean containsValue(V search_val){
        for(HashNode<K,V> node: buckets){
        	if(node != null) {
        		if(node.getValue().equals(search_val)){
        			return true;
        		}
            }
        }
        return false;
    }

    public V get(K search_key){
        for(HashNode<K,V> node: buckets){
        	if(node != null) {
        		if(node.getKey().equals(search_key)){
        			return node.getValue();
        		}
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

    public void printTable() {
        int totalConflicts = 0;
        for (int i = 0; i < numBuckets; i++) {
            HashNode<K, V> current = buckets.get(i);
            ArrayList<K> keyList = new ArrayList<>();
            int count = 0;
            while (current != null) {
                keyList.add(current.getKey());
                current = current.getNext();
                count++;
            }
            int conflicts = 0;
            if (count > 1) {
                conflicts = count - 1;
            }
            totalConflicts += conflicts;
            System.out.println("Index " + i + ": (" + conflicts + " conflicts), " + keyList);
        }
        System.out.println("Total # of conflicts: " + totalConflicts);
    }
}
