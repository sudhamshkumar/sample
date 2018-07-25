package com.sai.kafkaclient.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerResponseFuture implements Future<ProducerResponse>{

	private Future<RecordMetadata> meta;
	public ProducerResponseFuture(Future<RecordMetadata> meta){
		this.meta = meta;
	}
	
	public boolean cancel(boolean mayInterruptRunning) {
		
		return meta.cancel(mayInterruptRunning);
	}

	public ProducerResponse get() throws InterruptedException, ExecutionException {
		RecordMetadata recordMetadata = meta.get();
		return new ProducerResponse(recordMetadata);
	}

	public ProducerResponse get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		RecordMetadata recordMetadata = meta.get(timeout, unit);
		return new ProducerResponse(recordMetadata);
	}

	public boolean isCancelled() {
		
		return meta.isCancelled();
	}

	public boolean isDone() {
		return meta.isDone();
	}

}
