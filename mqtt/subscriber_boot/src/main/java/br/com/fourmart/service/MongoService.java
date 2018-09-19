package br.com.fourmart.service;

import br.com.fourmart.repository.MongoRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Date;

@Service
public class MongoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoService.class);

    public static final String CREATE_TIMESTAMP_FIELD = "createTimestamp";
    public static final String UPDATE_TIMESTAMP_FIELD = "updateTimestamp";
    public static final String UPDATED_BY_FIELD = "updatedBy";
    public static final String CREATED_BY_FIELD = "createdBy";
    public static final String OWNER_ID_FIELD = "ownerId";
    public static final String OWNED_BY_FIELD = "ownedBy";
    public static final String HOST_FIELD = "host";

    private final MongoRepository mongoRepository;
    private final Clock defaultClock;

    public MongoService(MongoRepository mongoRepository, Clock defaultClock) {
        this.mongoRepository = mongoRepository;
        this.defaultClock = defaultClock;
    }

    public Document storeDocument(String databaseName, String collectionName, Document document) {
        setDocumentMetadata(document);
        return mongoRepository.storeDocument(databaseName, collectionName, document);
    }
//
//    public Document getDocumentById(String applicationName, String applicationVersion, String collectionName, String id) {
//        Bson query = queryProvider.getIdQuery(HttpMethod.GET, applicationName, applicationVersion, collectionName, id);
//        return baasDao.getDocument(applicationName, applicationVersion, collectionName, query)
//                .orElseThrow(notFoundException(id));
//    }
//
//    public Document updateDocument(String applicationName, String applicationVersion, String collectionName, String id,
//                                   Document document, UserInfo user, HttpServletRequest request) {
//
//        Bson query = queryProvider.getIdQuery(HttpMethod.PUT, applicationName, applicationVersion, collectionName, id);
//
//        // Patch the document, replacing/adding the values that come in the update, but keeping the existing ones
//        Document stored = baasDao.getDocument(applicationName, applicationVersion, collectionName, query)
//                .orElseThrow(notFoundException(id));
//
//
//        stored = this.mergeDeep(stored, document);
//
//        setDocumentMetadata(stored, request, user);
//
//        return baasDao.updateDocument(applicationName, applicationVersion, collectionName, query, stored)
//                .orElseThrow(notFoundException(id)); // this should not happen, since the document was got before
//    }
//
//
//
//    public Document deleteDocument(String applicationName, String applicationVersion, String collectionName, String id) {
//        Bson query = queryProvider.getIdQuery(HttpMethod.DELETE, applicationName, applicationVersion, collectionName, id);
//        return baasDao.deleteDocument(applicationName, applicationVersion, collectionName, query)
//                .orElseThrow(notFoundException(id));
//    }
//
//    public List<Document> getSortedDocuments(String applicationName, String applicationVersion, String collectionName,
//                                             Sort sort, List<String> fields, String search) {
//        Bson query = queryProvider.getSearchQuery(HttpMethod.GET, applicationName, applicationVersion, collectionName, search);
//        return baasDao.getSortedDocuments(applicationName, applicationVersion, collectionName, sort, fields, query);
//    }
//
//    public Page<Document> getDocuments(String applicationName, String applicationVersion, String collectionName,
//                                       Pageable pageable, List<String> fields, String search) {
//        Bson query = queryProvider.getSearchQuery(HttpMethod.GET, applicationName, applicationVersion, collectionName, search);
//        return baasDao.getDocuments(applicationName, applicationVersion, collectionName, pageable, fields, query);
//    }

//    public MediaType parseMediaType(String fileType) {
//        try {
//        	return MediaType.parseMediaType(fileType);
//		} catch (InvalidMimeTypeException ex) {
//			return MediaType.APPLICATION_OCTET_STREAM;
//		}
//    }
//
    private void setDocumentMetadata(Document document) {
        updateTimestamps(document);
    }

    private void updateTimestamps(Document document) {
        Date now = Date.from(defaultClock.instant());
        document.putIfAbsent(CREATE_TIMESTAMP_FIELD, now);
        document.put(UPDATE_TIMESTAMP_FIELD, now);
    }

//
//    private Document mergeDeep(Document target, Document source) {
//
//        source.entrySet().stream().forEach(entry -> {
//
//            if (entry.getValue() instanceof Document && target.get(entry.getKey()) instanceof Document) {
//                Document targetDocument = (Document) target.get(entry.getKey());
//                Document sourceDocument = (Document) entry.getValue();
//
//                this.mergeDeep(targetDocument, sourceDocument);
//
//            } else {
//                target.put(entry.getKey(), entry.getValue());
//
//            }
//
//        });
//        return target;
//    }

}
