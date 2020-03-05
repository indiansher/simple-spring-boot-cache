package com.indiansher.cache.impl;

import org.springframework.stereotype.Component;

@Component
public class CacheB extends Cache {

	@Override
	public String getCacheId() {
		return "b";
	}
}
