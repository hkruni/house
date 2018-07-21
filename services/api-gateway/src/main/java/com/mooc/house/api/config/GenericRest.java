package com.mooc.house.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 既支持直连又支持服务发现的调用
 *
 */
@Service
public class GenericRest {
	
	@Autowired
	private RestTemplate lbRestTemplate;
	
	@Autowired
	private RestTemplate directRestTemplate;
	
	private static final String directFlag = "direct://";
	
	/**
	 * post请求
	 * @param url
	 * @param reqBody
	 * @param responseType 反序列化，包装类型
	 * @return
	 */
	public <T> ResponseEntity<T> post(String url,Object reqBody,ParameterizedTypeReference<T> responseType){
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		return template.exchange(url, HttpMethod.POST,new HttpEntity(reqBody),responseType);
	}
	
	/**
	 * get请求
	 * @param url
	 * @param responseType
	 * @return
	 */
	public <T> ResponseEntity<T> get(String url,ParameterizedTypeReference<T> responseType){
		RestTemplate template = getRestTemplate(url);
		url = url.replace(directFlag, "");
		return template.exchange(url, HttpMethod.GET,HttpEntity.EMPTY,responseType);
	}

	/**
	 * 通过url判断是直连请求还是负载均衡请求
	 * @param url
	 * @return
	 */
	private RestTemplate getRestTemplate(String url) {
		if (url.contains(directFlag)) {
			return directRestTemplate;
		}else {
			return lbRestTemplate;
		}
	}

}
