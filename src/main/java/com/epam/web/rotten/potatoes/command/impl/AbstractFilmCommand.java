package com.epam.web.rotten.potatoes.command.impl;

import com.epam.web.rotten.potatoes.exceptions.ServiceException;
import com.epam.web.rotten.potatoes.model.Film;
import com.epam.web.rotten.potatoes.model.UserAction;
import com.epam.web.rotten.potatoes.service.action.UserActionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class AbstractFilmCommand {
    private final UserActionService userActionService;

    /*package-private*/ AbstractFilmCommand(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    /*package-private*/ double getCurrentAvgRate(Film film) throws ServiceException {
        int id = film.getId();
        double defaultAvgRate = film.getDefaultRate();
        List<UserAction> userActions = userActionService.getReviewsByFilmId(id);
        double sumAvgRate = 0;
        for (UserAction userAction : userActions) {
            sumAvgRate += userAction.getFilmRate();
        }
        if (sumAvgRate > 0) {
            double resultAvgRate = sumAvgRate / userActions.size();
            return (resultAvgRate + defaultAvgRate) / 2;
        } else {
            return defaultAvgRate;
        }
    }

    /*package-private*/ static double round(double value) {
        BigDecimal bigDecimalNumber = new BigDecimal(Double.toString(value));
        bigDecimalNumber = bigDecimalNumber.setScale(1, RoundingMode.HALF_UP);
        return bigDecimalNumber.doubleValue();
    }
}
