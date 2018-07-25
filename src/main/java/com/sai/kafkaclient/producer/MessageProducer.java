package com.sai.kafkaclient.producer;

import java.util.concurrent.Future;

import com.sai.kafkaclient.MessageException;

public interface MessageProducer {

	ProducerResponse send(String topic, String value) throws MessageException;
	
	ProducerResponse send(String topic, String key, String value) throws MessageException;
	
	Future<ProducerResponse> sendAsync(String topic, String value);
	
	Future<ProducerResponse> sendAsync(String topic, String key, String value);
}
