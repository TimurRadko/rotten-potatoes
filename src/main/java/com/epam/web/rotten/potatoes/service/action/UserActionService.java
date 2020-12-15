package com.epam.web.rotten.potatoes.service.action;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;

public interface UserActionService {
    void addReviewAndRate(int rate, String review) throws ServiceException;
}
