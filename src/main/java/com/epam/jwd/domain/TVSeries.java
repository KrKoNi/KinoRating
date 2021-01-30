package com.epam.jwd.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TVSeries extends Show implements Serializable {

    private static final long serialVersionUID = 6794246014815205720L;

    public TVSeries(int id, String title) {
        super(id, title);
    }
    public TVSeries() {
        super();
    }

}
