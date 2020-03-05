package com.indiansher.cache.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indiansher.cache.impl.Cache;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/custom-cache")
@RestController
public class CacheController {

	private final List<Cache> caches;

	/*
	 * Use Cache Handling
	 */
	@PostMapping(value = "/action/get-use-cache-status")
	@ResponseBody
	public Map<String, Boolean> getUseCache() {
		return caches.stream().collect(Collectors.toMap(Cache::getCacheId, Cache::isUseCache));
	}

	@PostMapping(value = "/action/get-use-cache-status/{cacheId}")
	public Boolean getUseCache(@PathVariable String cacheId) {
		return findCache(cacheId).isUseCache();
	}

	@PostMapping(value = "/action/set-use-cache")
	public void setUseCache(@RequestParam boolean useCache) {
		caches.forEach(cache -> cache.setUseCache(useCache));
	}

	@PostMapping(value = "/action/set-use-cache/{cacheId}")
	public void setUseCache(@PathVariable String cacheId, @RequestParam boolean useCache) {
		findCache(cacheId).setUseCache(useCache);
	}

	/*
	 * Get Cache Data Handling
	 */
	@GetMapping(value = "/get")
	@ResponseBody
	public Map<String, String> getCache() {
		return caches.stream().collect(Collectors.toMap(Cache::getCacheId, Cache::getAll));
	}

	@GetMapping(value = "/get/{cacheId}")
	@ResponseBody
	public String getCache(@PathVariable String cacheId) {
		return findCache(cacheId).getAll();
	}

	/*
	 * Clear Caches
	 */
	@PostMapping(value = "/clear")
	public void clear() {
		caches.forEach(cache -> cache.clear());
	}

	@PostMapping(value = "/clear/{cacheId}")
	public void clear(@PathVariable String cacheId) {
		findCache(cacheId).clear();
	}

	/*
	 * Update Cache
	 */
	@PutMapping(value = "/put/{cacheId}")
	public void clear(@PathVariable String cacheId, @RequestParam String key, @RequestParam String value) {
		findCache(cacheId).put(key, value);
	}

	private Cache findCache(String cacheId) {
		return caches.stream().filter(cache -> cache.getCacheId().equals(cacheId)).findFirst().orElse(null);
	}

}
