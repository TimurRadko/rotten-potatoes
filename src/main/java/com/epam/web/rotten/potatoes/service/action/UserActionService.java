package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.UserAction;

public interface UserActionService {
    void addReviewAndRate(UserAction userAction) throws ServiceException;
}
