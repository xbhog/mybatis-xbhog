package com.xbhog.builder;

import com.xbhog.session.Configuration;

/**
 * @author 衣立君
 * @date 2024/03/01 16:15
 **/
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}