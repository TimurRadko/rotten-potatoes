package com.epam.web.rotten.potatoes.dao.extractor;

import com.epam.web.rotten.potatoes.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserFieldsExtractor implements FieldsExtractor<User> {
    private Map<Integer, Object> fields = new HashMap<>();
    private static final int ID = 1;
    private static final int LOGIN = 2;
    private static final int PASSWORD = 3;
    private static final int RIGHTS = 4;
    private static final int RATE = 5;
    private static final int BLOCKED = 6;

    @Override
    public Map<Integer, Object> extract(User user) {
        Integer id = user.getId();
        fields.put(ID, id);
        String login = user.getLogin();
        fields.put(LOGIN, login);
        String password = user.getPassword();
        fields.put(PASSWORD, password);
        String rights = user.getRights();
        fields.put(RIGHTS, rights);
        int rate = user.getRate();
        fields.put(RATE, rate);
        boolean blocked = user.isBlocked();
        fields.put(BLOCKED, blocked);
        return fields;
    }
}