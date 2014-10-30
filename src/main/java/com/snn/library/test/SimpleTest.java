package com.snn.library.test;

import static org.junit.Assert.assertEquals;

import java.net.InetSocketAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.snn.library.utility.DBConfig;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class SimpleTest {

	private DBCollection collection;
	private MongoClient client;
	private MongoServer server;

	@Before
	public void setUp() {
		server = new MongoServer(new MemoryBackend());

		InetSocketAddress serverAddress = server.bind();

		client = new MongoClient(new ServerAddress(serverAddress));
		collection = client.getDB(DBConfig.DATABASE_NAME).getCollection(DBConfig.COLLECTION_NAME);
	}

	@After
	public void tearDown() {
		client.close();
		server.shutdownNow();
	}

	@Test
	public void testSimpleInsertQuery() throws Exception {
		assertEquals(0, collection.count());

		DBObject obj = new BasicDBObject("_id", 1).append("key", "value");
		collection.insert(obj);

		assertEquals(1, collection.count());
		assertEquals(obj, collection.findOne());
	}

}