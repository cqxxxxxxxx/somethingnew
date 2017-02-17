package com.cqx.hv;

/**
 * Created by Shan on 2017/2/17.
 */
public class BussException extends Exception {

    private int status;


    public BussException(String message) {
        super(message);
        this.status = 500;
    }

    public BussException(String message, int status) {
        super(message);
        this.status = status;
    }


}
