package com.tanaseb.auditweather.infrastructure.sqs;

import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
@EnableJms
public class JmsConfig {

	@Value("${aws.accessKey}")
	private String accessKey;
	@Value("${aws.secretKey}")
	private String secretKey;
	@Value("${aws.queueEndpoint}")
	private String queueEndpoint;

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setDestinationResolver(new DynamicDestinationResolver());
		factory.setConcurrency("3-10");
		factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(connectionFactory());
	}

	private SQSConnectionFactory connectionFactory() {
		return SQSConnectionFactory.builder()
				.withRegion(Region.getRegion(Regions.US_EAST_2))
				.withAWSCredentialsProvider(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.withEndpoint(queueEndpoint)
				.build();
	}
}
