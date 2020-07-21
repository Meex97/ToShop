package com.unito.toshop.persistence;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DAOGenericJson {
    private static String TAG = DAOGenericJson.class.getSimpleName();
    private String datePatternFormat = "dd-MM-yyyy HH:mm:ss";

    /* ******************************************
     *               Conversion
     * ****************************************** */
    public String toJson(Object objec){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new AdapterDate());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(objec);
    }

    /* ******************************************
     *               Load
     * ****************************************** */
    public Object load(InputStream inputStream, Class t) throws DAOException {
        Object object = null;
        Reader flow = null;
        try {
            flow = new InputStreamReader(inputStream);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new AdapterDate());
            Gson gson = builder.create();
            object = gson.fromJson(flow, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(e);
        } finally {
            try {
                if (flow != null) {
                    flow.close();
                }
            } catch (java.io.IOException ioe) {
            }
        }
        return object;
    }

    /* ******************************************
     *               Save
     * ****************************************** */
    public void save(Object object, OutputStream out) throws DAOException {
        PrintWriter flow = null;
        try {
            flow = new java.io.PrintWriter(out);
            String jsonString = toJson(object);
            flow.print(jsonString);
        } catch (Exception ioe) {
            throw new DAOException(ioe);
        } finally {
            if (flow != null) {
                flow.close();
            }
        }

    }

    private class AdapterDate implements JsonSerializer<Date>, JsonDeserializer<Date> {

        public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
            DateFormat dateFormat = new SimpleDateFormat(datePatternFormat);
            return new JsonPrimitive(dateFormat.format(date.getTime()));
        }

        public Date deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                String stringData = json.getAsString();
                DateFormat dateFormat = new SimpleDateFormat(datePatternFormat);
                Date registrationDate = dateFormat.parse(stringData);
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(registrationDate);
                return calendar.getTime();
            } catch (ParseException ex) {
                throw new JsonParseException(ex);
            }
        }
    }

    public String getDatePatternFormat() {
        return datePatternFormat;
    }

    public void setDatePatternFormat(String datePatternFormat) {
        this.datePatternFormat = datePatternFormat;
    }
}
