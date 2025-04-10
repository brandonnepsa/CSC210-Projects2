import java.util.ArrayList;
import java.util.Set;

public class MyHashMap<K,V>{
    private ArrayList<K,V> buckets;
    private int numBuckets = 8;
    private int size = 0;

    public MyHashMap(K key, V value){
        this.buckets = new ArrayList<>();

    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    public V put(K key,V value){
        //IDK what to do here
        size++;
    }

    public boolean remove(Object key, Object value){
        //IDK what to do here
        size--;
    }

    public int size(){
        return size;
    }
    public void clear(){
        this.buckets = new ArrayList<>();
    }

    public boolean containsKey(K search_key){
        for(K key: buckets){
            if(key.equals(search_key)){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V search_val){
        for(K val: buckets){
            if(val.equals(search_val)){
                return true;
            }
        }
        return false;
    }

    public K get(K search_key){
        for(K key: buckets){
            if(key.equals(search_key)){
                return key;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        //IDK where to begin here 
    }

    public Set<K> keySet(){
        //IDK where to begin here
    }

    public void printTable(){
        // IDK how to format this but this is what the output should be:
        // Index 0: (0 conflicts), []
        // Index 1: (0 conflicts), []
        // Index 2: (0 conflicts), []
        // Index 3: (0 conflicts), []
        // Index 4: (0 conflicts), []
        // Index 5: (0 conflicts), [ExampleKeyX, ]
        // Index 6: (0 conflicts), [ExampleKeyY, ]
        // Index 7: (0 conflicts), []
        // Total # of conflicts: 0

    }
}