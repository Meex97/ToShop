package com.unito.toshop.model;

import android.util.Log;
import com.unito.toshop.Application;
import com.unito.toshop.persistence.DAOException;
import com.unito.toshop.persistence.DAOGenericJson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PersistentModel {

    private final static String TAG = PersistentModel.class.getSimpleName();
    private final DAOGenericJson daoGenericJson = new DAOGenericJson();
    private final Map<String, Object> cache = new HashMap<String, Object>();

    public void saveBean(String key, Object bean){
        this.cache.put(key, bean);
        try {
            save(key, bean);
        } catch (DAOException e) {
            Log.e(TAG,"saveBean error");
            e.printStackTrace();
        }
    }

    public Object getPersistentBean(String key, Class type) {
        Object cachedObject = this.cache.get(key);
        if (cachedObject != null) {
            return cachedObject;
        }
        Object persistentObject = load(key, type);
        if (persistentObject == null) {
            return null;
        }
        cache.put(key, persistentObject);
        return persistentObject;
    }

    private Object load(String key, Class type) {
        File file = new File(Application.getInstance().getFilesDir(), getFileName(key));
        if (!file.exists()) {
            return null;
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return daoGenericJson.load(in, type);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    private void save(String key, Object bean) throws DAOException {
        File file = new File(Application.getInstance().getFilesDir(), getFileName(key));
        try {
            daoGenericJson.save(bean, new FileOutputStream(file));
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
            throw new DAOException(e.getLocalizedMessage());
        }
    }

    private String getFileName(String key) {
        return key + ".json";
    }

}
