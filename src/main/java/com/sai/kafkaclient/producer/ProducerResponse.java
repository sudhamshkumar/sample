package com.sai.kafkaclient.producer;

import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerResponse {

	private RecordMetadata meta;
	
	public ProducerResponse(RecordMetadata meta){
		this.meta = meta;
	}
	
	public long getOffset(){
		return meta.offset();
	}
	
	public int getPartition(){
		return meta.partition();
	}
	
	public String getTopic(){
		return meta.topic();
	}
}
