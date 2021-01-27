package com.epam.web.rotten.potatoes.dao.extractor;

import java.util.Map;

public interface FieldsExtractor<T> {
    /**
     * Extracts fields of Entity.
     *
     * @param item - passed into the method all Entities in the DB
     * @return Map<Integer, Object> - return pair key - value,
     * where key - number of parameter and value - parameter value
     */
    Map<Integer, Object> extract(T item);
}
