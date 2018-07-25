package com.sai.kafkaclient.producer.impl;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.sai.kafkaclient.MessageException;
import com.sai.kafkaclient.producer.MessageProducerLifeCycle;
import com.sai.kafkaclient.producer.ProducerResponse;
import com.sai.kafkaclient.producer.ProducerResponseFuture;

public class MessageProducerImpl implements MessageProducerLifeCycle{

	private KafkaProducer<String, String> producer;
	private Properties props;
	
	public ProducerResponse send(String topic, String value) throws MessageException {
		return send(topic, null, value);
	}

	public ProducerResponse send(String topic, String key, String value) throws MessageException {
		
		RecordMetadata response;
		try{
			 response = (RecordMetadata)producer.send(createProducerRecord(topic, key, value)).get();
			
		}catch(Exception e){
			throw new MessageException(e);
		}
		return new ProducerResponse(response);
	}

	public Future<ProducerResponse> sendAsync(String topic, String value) {
		return sendAsync(topic, null, value);
	}

	public Future<ProducerResponse> sendAsync(String topic, String key, String value) {
		
		Future<RecordMetadata> record;
		record = producer.send(createProducerRecord(topic, key, value));
		return new ProducerResponseFuture(record);
	}

	public void start() {
		producer = new KafkaProducer<String, String>(props);
		
	}

	public void configure(Properties props) {
		
		if(props == null){
			props = new Properties();
			props.put("producer.bootstrap.servers", "avs07,avs08");//ask for cluster location
			props.put("producer.key.serializer","org.apache.common.serialization.StringSerializer");
			props.put("producer.value.serializer","org.apache.common.serialization.StringSerializer");
			props.put("producer.acks", "1");
			props.put("retries", "3");
			props.put("producer.batch.size", "32768");
			props.put("producer.linger.ms", "9");
		}
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	private ProducerRecord createProducerRecord(String topic, String key, String value){
		
		ProducerRecord<String, String> record = null;
		if(key != null && key.length() > 0)
			record = new ProducerRecord<String, String>(topic, key, value);
		else
			record = new ProducerRecord<String, String>(topic, value);
		return record;
		
	}
	
	

}
