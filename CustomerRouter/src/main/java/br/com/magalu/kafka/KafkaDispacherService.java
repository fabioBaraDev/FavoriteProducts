package br.com.magalu.kafka;

import java.util.concurrent.ExecutionException;

public interface KafkaDispacherService {
	public void send(String topic, String key, String message) throws InterruptedException, ExecutionException;
}
