package com.agesun.mybatis.util;

import java.io.Serializable;

public class Json implements Serializable {
    private static final long serialVersionUID = -7938049457308215498L;

    private String msg = "";

    private Object obj = null;

    private Object data= null;

    // 状态码 0:成功 1：失败
    private Integer result;

    private Integer line;

    private Object pd;

    private String message="";

    private boolean isOperation;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOperation() {
        return isOperation;
    }

    public void setIsOperation(boolean operation) {
        isOperation = operation;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    public Object getData()
    {
        return data;
    }
    public void setData(Object data)
    {
        this.data = data;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Object getPd() {
        return pd;
    }

    public void setPd(Object pd) {
        this.pd = pd;
    }
}
