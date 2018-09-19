package br.com.fourmart.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoRepository.class);

    public static final String ID_FIELD = "_id";

    private final MongoClient mongoClient;


    public MongoRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Document storeDocument(String databaseName, String collectionName, Document document) {
        LOGGER.debug("storeDocument: database: {}, collection: {}", databaseName, collectionName);
        // remove any already set id, since this should be provided by Mongo DB on insert
        document.remove(ID_FIELD);
        getCollection(databaseName, collectionName).insertOne(document);
        return document;
    }

//    public Optional<Document> getDocument(String applicationName, String applicationVersion, String collectionName, Bson query) {
//        LOGGER.debug("getDocument: app: {}, collection: {}, query: {}", applicationName, collectionName, query);
//        return Optional.ofNullable(getCollection(applicationName, collectionName).find(query).first());
//    }
//
//    public Optional<Document> updateDocument(String applicationName, String applicationVersion, String collectionName, Bson query, Document document) {
//        LOGGER.debug("updateDocument: app: {}, collection: {}, query: {}", applicationName, collectionName, query);
//        MongoCollection<Document> collection = getCollection(applicationName, collectionName);
//        return Optional.ofNullable(collection.findOneAndReplace(query, document));
//    }
//
//    public Optional<Document> deleteDocument(String applicationName, String applicationVersion, String collectionName, Bson query) {
//        LOGGER.debug("deleteDocument: app: {}, collection: {}, query: {}", applicationName, collectionName, query);
//        return Optional.ofNullable(getCollection(applicationName, collectionName).findOneAndDelete(query));
//    }
//
//    public void deleteCollection(String applicationName, String collectionName) {
//        LOGGER.debug("Deleting collection {} for {}", collectionName, applicationName);
//        getCollection(applicationName, collectionName).drop();
//    }

//    public List<Document> getSortedDocuments(String applicationName, String applicationVersion, String collectionName,
//                                             Sort sort, List<String> fields, Bson search) {
//        LOGGER.debug("getDocuments: app: {}, collection: {}", applicationName, collectionName);
//        Query query = new Query();
//        if (sort != null) {
//        	query.with(sort);
//
//        }
//        if (fields != null && !fields.isEmpty()) {
//            fields.forEach(query.fields()::include);
//        }
//
//        MongoCollection<Document> collection = getCollection(applicationName, collectionName);
//        Iterable<Document> result = collection.find(search)
//        		.sort((Bson) query.getSortObject())
//                .projection((Bson) query.getFieldsObject());
//        List<Document> documents = new ArrayList<>();
//        result.forEach(documents::add);
//        return documents;
//    }
//
//    public Page<Document> getDocuments(String applicationName, String applicationVersion, String collectionName,
//                                       Pageable pageable, List<String> fields, Bson search) {
//        LOGGER.debug("getDocuments: app: {}, collection: {}", applicationName, collectionName);
//        Query query = new Query();
//        query.with(pageable);
//        // projection (does not apply to _id by default)
//        if (fields != null && !fields.isEmpty()) {
//            fields.forEach(query.fields()::include);
//        }
//
//        MongoCollection<Document> collection = getCollection(applicationName, collectionName);
//        long total = collection.count(search);
//        Iterable<Document> result = collection.find(search)
//                .sort((Bson) query.getSortObject())
//                .skip(query.getSkip()).limit(query.getLimit())
//                .projection((Bson) query.getFieldsObject());
//        List<Document> documents = new ArrayList<>();
//        result.forEach(documents::add);
//        return new PageImpl<>(documents, pageable, total);
//    }

    private MongoCollection<Document> getCollection(String databaseName, String collectionName) {
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        return db.getCollection(collectionName);
    }

}
