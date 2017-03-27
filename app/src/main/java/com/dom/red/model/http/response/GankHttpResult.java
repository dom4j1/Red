package com.dom.red.model.http.response;

import java.util.List;

/**
 * Created by dom4j on 2017/3/24.
 */

public class GankHttpResult<T> {

    private boolean error;

    private T results;

    public boolean getError() {
        return error;
    }

    public T getResults() {
        return results;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
