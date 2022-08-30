package de.dlrg.rs.results;

import java.util.*;

class GroupedMap<K, V> {

    private Map<K, List<V>> map = new HashMap<>();

    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<V>());
        }
        List<V> values = map.get(key);
        values.add(value);
    }

    public Set<Map.Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }
}
