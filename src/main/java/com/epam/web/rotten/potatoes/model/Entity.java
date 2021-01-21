package com.epam.web.rotten.potatoes.model;

import java.io.Serializable;

public interface Entity extends Serializable, Cloneable {
    /**
     * This interface describes all Entities in DB. It is used to eliminate
     * the possibility of inserting entities that don't implement it into the DB.
     *
     * @return entities id
     */
    Integer getId();
}
