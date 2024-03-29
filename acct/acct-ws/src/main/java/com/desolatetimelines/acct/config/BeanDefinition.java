package com.desolatetimelines.acct.config;

import com.desolatetimelines.acct.service.AccountReportingService;
import com.desolatetimelines.acct.service.AccountService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class BeanDefinition implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// Define the account service for auto-wiring in all REST controller components
		registerBean(registry, AccountService.class, RootBeanDefinition.ROLE_APPLICATION, "AccountService");

		// Define the reporting service for auto-wiring in REST/REPORTING controllers
		registerBean(registry, AccountReportingService.class, RootBeanDefinition.ROLE_APPLICATION,
				"AccountReportingService");
	}

	private static void registerBean(BeanDefinitionRegistry registry, Class<?> targetType, int role, String beanName) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition(targetType);
		beanDefinition.setTargetType(targetType);
		beanDefinition.setRole(role);
		registry.registerBeanDefinition(beanName, beanDefinition);
	}

}
