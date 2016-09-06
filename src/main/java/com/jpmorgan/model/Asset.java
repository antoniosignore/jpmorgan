package com.jpmorgan.model;

import java.io.Serializable;

public abstract class Asset extends Instrument implements Serializable {

    public Asset(String name) {
        super(name);
    }

}

