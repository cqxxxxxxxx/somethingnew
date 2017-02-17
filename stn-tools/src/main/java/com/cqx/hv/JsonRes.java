package com.cqx.hv;

/**
 * Created by Shan on 2017/2/17.
 */
public class JsonRes {

    private int status;

    private String msg;

    private static JsonRes jsonResSuccess = new JsonRes(200, "success");

    public static JsonRes buildSuccess(){
        return jsonResSuccess;
    }

    public JsonRes(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
