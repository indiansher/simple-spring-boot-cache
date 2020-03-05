package com.indiansher.cache.impl;

import org.springframework.stereotype.Component;

@Component
public class CacheA extends Cache {

	@Override
	public String getCacheId() {
		return "a";
	}
}
