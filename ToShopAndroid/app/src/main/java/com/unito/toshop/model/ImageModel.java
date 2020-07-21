package com.unito.toshop.model;

import java.io.Serializable;

public class ImageModel implements Serializable {

    public ImageModel() {
        super();
    }
    public ImageModel(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
    private Long id;
    private String name;
    private String type;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    private byte[] picByte;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

}
