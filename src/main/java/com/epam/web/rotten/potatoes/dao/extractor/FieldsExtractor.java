package com.epam.web.rotten.potatoes.dao.extractor;

import java.util.Map;

public interface FieldsExtractor<T> {
    Map<Integer, Object> extract(T item);
}
