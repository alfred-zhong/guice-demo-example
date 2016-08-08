package com.snowinpluto.demo.utils.monogo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.snowinpluto.demo.utils.monogo.OrderBuilder.Order;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * MongoDB 工具类
 */
public class MongoUtil {

	private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    /**
     * MongoClient 缓存
     */
    private static Cache<MongoConfig, MongoClient> clientCache =
            CacheBuilder.newBuilder()
                        .maximumSize(10)
                        .expireAfterAccess(24, TimeUnit.HOURS)
                        .removalListener(new RemovalListener<MongoConfig, MongoClient>() {
                            @Override
                            public void onRemoval(
                                    RemovalNotification<MongoConfig, MongoClient> notification) {
                                MongoClient mongoClient = notification.getValue();
                                if (mongoClient != null) {
                                    mongoClient.close();
                                }
                            }
                        })
                        .build();

    private MongoUtil() {}

    /**
     * 根据 Mongo 配置获取 MongoClient
     * @param mongoConfig Mongo 配置
     * @return MongoClient
     */
    private static MongoClient getMongoClient(MongoConfig mongoConfig) {
        checkNotNull(mongoConfig, "MongoConfig can't be null");

        MongoClient client = clientCache.getIfPresent(mongoConfig);
        if (client == null) {
            client = new MongoClient(mongoConfig.getHost(), mongoConfig.getPort());
            clientCache.put(mongoConfig, client);
        }
        return client;
    }

    /**
     * 新增对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param document 对象
     * @return 生成的新对象信息
     */
	public static Document insert(MongoConfig mongoConfig, String dbName, String collection,
                                  Document document) {
        checkNotNull(mongoConfig, "MongoDB Config can't be null");

		MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient(mongoConfig);
            MongoDatabase db = mongoClient.getDatabase(checkNotNull(dbName));
            MongoCollection mongoCollection = db.getCollection(collection);
            mongoCollection.insertOne(document);
            return document;
        } catch (Exception e) {
            logger.error("MongoDB save fails", e);
        }

        return null;
    }

    /**
     * 统计数量
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @return 查询到的数量
     */
    public static long count(MongoConfig mongoConfig, String dbName, String collection,
                            Document filterDocument) {
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient(mongoConfig);
            MongoDatabase db = mongoClient.getDatabase(checkNotNull(dbName));
            MongoCollection mongoCollection = db.getCollection(collection);

            return mongoCollection.count(filterDocument);
        } catch (Exception e) {
            logger.error("MongoDB find fails", e);
        }

        return 0L;
    }

    /**
     * 查找对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @return 查询到的对象列表
     */
    public static List<Document> find(MongoConfig mongoConfig, String dbName, String collection,
                                      Document filterDocument) {
        return find(mongoConfig, dbName, collection, filterDocument, 0, Integer.MAX_VALUE);
    }

    /**
     * 查找对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @param skip 跳过数量
     * @param limit 限制数量
     * @return 查询到的对象列表
     */
    public static List<Document> find(MongoConfig mongoConfig, String dbName, String collection,
                                      Document filterDocument,
                                      int skip, int limit) {
        return find(mongoConfig, dbName, collection, filterDocument, skip, limit, null);
    }

    /**
     * 查找对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @param order 顺序
     * @return 查询到的对象列表
     */
    public static List<Document> find(MongoConfig mongoConfig, String dbName, String collection,
                                      Document filterDocument,
                                      Order order) {
        return find(mongoConfig, dbName, collection, filterDocument,
                    0, Integer.MAX_VALUE, order);
    }

    /**
     * 查找对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @param skip 跳过数量
     * @param limit 限制数量
     * @param order 顺序
     * @return 查询到的对象列表
     */
    public static List<Document> find(MongoConfig mongoConfig, String dbName, String collection,
                                      Document filterDocument,
                                      int skip, int limit, Order order) {
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient(mongoConfig);
            MongoDatabase db = mongoClient.getDatabase(checkNotNull(dbName));
            MongoCollection mongoCollection = db.getCollection(collection);
            FindIterable<Document> resultIterable = mongoCollection.find(filterDocument)
                                                                   .skip(skip)
                                                                   .limit(limit);

            if (order != null) {
                resultIterable.sort(new BasicDBObject(order.getField(), order.getSeq()));
            }

            return Lists.newArrayList(resultIterable);
        } catch (Exception e) {
            logger.error("MongoDB find fails", e);
        }

        return Lists.newArrayList();
    }

    /**
     * 更新对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     * @param newDocument 更新对象数据操作
     */
	public static void update(MongoConfig mongoConfig, String dbName, String collection,
                              Document filterDocument, Document newDocument) {
		MongoClient mongoClient = null;
		try {
            mongoClient = getMongoClient(mongoConfig);
            MongoDatabase db = mongoClient.getDatabase(checkNotNull(dbName));
            MongoCollection mongoCollection = db.getCollection(collection);
            mongoCollection.replaceOne(filterDocument, newDocument);
		} catch (Exception e) {
			logger.error("MongoDB update fails", e);
		}
	}

    /**
     * 删除对象
     * @param mongoConfig MongoDB 配置
     * @param dbName 数据库名
     * @param collection 集合名(表名)
     * @param filterDocument 过滤条件
     */
    public static void delete(MongoConfig mongoConfig, String dbName, String collection,
                              Document filterDocument) {
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient(mongoConfig);
            MongoDatabase db = mongoClient.getDatabase(checkNotNull(dbName));
            MongoCollection mongoCollection = db.getCollection(collection);
            mongoCollection.deleteOne(filterDocument);
        } catch (Exception e) {
            logger.error("MongoDB delete fails", e);
        }
    }
}
