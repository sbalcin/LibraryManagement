package com.snn.library.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class DBConfig extends AbstractMongoConfiguration {

	public static final String SERVER_ADDRESS = "mongodb://library:library@dogen.mongohq.com:10081/library";
	public static final String DATABASE_NAME = "library";
	public static final String COLLECTION_NAME = "book";

	@Override
	public String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		MongoClientURI clientURI = new MongoClientURI(SERVER_ADDRESS);
		return new MongoClient(clientURI);
	}
}