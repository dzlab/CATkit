package org.ca.toolkit.common;

public class Peer<K, V> {
	public K key;
	public V value;
	public Peer() {}
	public Peer(K k, V v) {
		key = k;
		value = v;
	}
}
