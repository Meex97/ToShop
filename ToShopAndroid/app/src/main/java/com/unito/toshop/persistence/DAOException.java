package com.unito.toshop.persistence;

public class DAOException extends Exception {

    public DAOException() {
        super();
    }

    public DAOException(String s) {
        super(s);
    }

    public DAOException(Exception e) {
        super(e);
    }

}
