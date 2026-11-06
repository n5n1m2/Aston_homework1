import java.util.Objects;

import static java.util.Objects.hash;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    Node<K, V>[] table;

    @Override
    public V put(K key, V value) {
        if (table == null) {
            table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        }

        int hash = hash(key);
        int index = (table.length - 1) & hash;

        for (Node<K, V> node = table[index]; node != null; node = node.next) {
            if (node.hash == hash && Objects.equals(node.key, key)) {
                return node.setValue(value);
            }
        }

        table[index] = new Node<>(hash, key, value, table[index]);
        return null;
    }

    @Override
    public Boolean remove(Object key) {
        if (table == null) {
            return false;
        }

        int hash = hash(key);
        int index = (table.length - 1) & hash;

        Node<K, V> prev = null;
        Node<K, V> curr = table[index];

        while (curr != null) {
            if (curr.hash == hash && Objects.equals(curr.key, key)) {
                if (prev == null) {
                    table[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node;
        return (node = getNode(key)) == null ? null : node.value;
    }


    private Node<K, V> getNode(Object key) {
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n, hash;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & (hash = hash(key))]) != null) {
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;

            return o instanceof CustomHashMap.Node<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }
    }
}
