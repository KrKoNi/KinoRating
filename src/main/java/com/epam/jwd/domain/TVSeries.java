package com.epam.jwd.domain;

import java.io.Serializable;

/**
 * Tv series entity.
 */
public class TVSeries extends Show implements Serializable {

    private static final long serialVersionUID = 6794246014815205720L;

    /**
     * Instantiates a new Tv series.
     *
     * @param id    the id
     * @param title the title
     */
    public TVSeries(int id, String title) {
        super(id, title);
    }

    /**
     * Instantiates a new Tv series.
     */
    public TVSeries() {
        super();
    }

}
