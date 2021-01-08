package com.epam.web.rotten.potatoes.validator;

public interface Validator<T> {
    boolean isValid(T object);
}
