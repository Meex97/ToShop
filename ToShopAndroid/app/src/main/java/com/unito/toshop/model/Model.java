package com.unito.toshop.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private final Map<String, Object> mapBean = new HashMap<String, Object>();

    public void putBean(String nome, Object bean) {
        this.mapBean.put(nome, bean);
    }

    public Object getBean(String nome) {
        return this.mapBean.get(nome);
    }

}
