package com.mooc.house.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;


/**
 * 自定义配置规则
 * @author x1 carbon
 *
 */
public class NewRuleConfig {
	
	@Autowired
	private IClientConfig ribbonClientConfig;
	
	/**
	 * 通过ping命令检查服务是否可用
	 * @param config
	 * @return
	 */
	@Bean
	public IPing ribbonPing(IClientConfig config){
		return new PingUrl(false,"/health");
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config){
		//随机负载均衡策略
		//return new RandomRule();
		//对上次调用的服务做记录，下次调用选择上次成功的服务，不选择失败的服务
		return new AvailabilityFilteringRule();
	}

}
