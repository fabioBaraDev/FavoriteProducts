package br.com.magalu.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.magalu.model.FavoriteProductModel;
import br.com.magalu.model.ProductModel;
import br.com.magalu.model.ProductUpdateModel;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${kafka.bootstrap.server}")
	private String bootstrapServer;
	
	@Value("${topic.name.group}")
	private String topicName;
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
//	@Bean
//	public ConsumerFactory<String, Integer> consumerByIntegerFactory() {
//		Map<String, Object> config = new HashMap<>();
//
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//		return new DefaultKafkaConsumerFactory<>(config);
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, Integer> kafkaListenerByIntegerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, Integer> factory = new ConcurrentKafkaListenerContainerFactory();
//		factory.setConsumerFactory(consumerByIntegerFactory());
//		return factory;
//	}

	@Bean
	public ConsumerFactory<String, ProductModel> productConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(ProductModel.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductModel> productKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ProductModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(productConsumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, FavoriteProductModel> favoriteConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(FavoriteProductModel.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FavoriteProductModel> favoriteKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FavoriteProductModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(favoriteConsumerFactory());
		return factory;
	}
	

	@Bean
	public ConsumerFactory<String, ProductUpdateModel> updateFavoriteConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(ProductUpdateModel.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductUpdateModel> updateFavoriteKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ProductUpdateModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(updateFavoriteConsumerFactory());
		return factory;
	}

	
}
