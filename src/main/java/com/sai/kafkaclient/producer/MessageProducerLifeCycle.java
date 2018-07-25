package com.sai.kafkaclient.producer;

import java.util.Properties;

public interface MessageProducerLifeCycle extends MessageProducer{

	void start();
	
	void configure(Properties props);
	
	void close();
}
