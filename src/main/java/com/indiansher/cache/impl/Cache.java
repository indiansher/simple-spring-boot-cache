package com.indiansher.cache.impl;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache {

	private final Map<String, String> cacheMap;
	private boolean useCache;

	public Cache() {
		this.useCache = true;
		cacheMap = new HashMap<>();
	}

	/*
	 * Methods to set/get Use cache
	 */
	public boolean setUseCache(boolean useCache) {
		this.useCache = useCache;
		if (!this.useCache) {
			this.clear();
		}
		return this.useCache;
	}

	public boolean isUseCache() {
		return this.useCache;
	}

	/*
	 * Cache ID
	 */
	public String getCacheId() {
		return null;
	}

	/*
	 * Manage cache
	 */
	public void clear() {
		checkUseCache();
		this.cacheMap.clear();
	}

	public void put(String key, String value) {
		checkUseCache();
		this.cacheMap.put(key, value);
	}

	public String get(String key) {
		checkUseCache();
		return this.cacheMap.get(key);
	}

	public String getAll() {
		checkUseCache();
		return this.cacheMap.toString();
	}

	private void checkUseCache() {
		if (!this.isUseCache()) {
			throw new RuntimeException("Cache is disabled");
		}
	}
}
