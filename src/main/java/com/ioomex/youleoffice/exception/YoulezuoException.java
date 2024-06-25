package com.ioomex.youleoffice.exception;

import lombok.Data;

@Data
public class YoulezuoException extends RuntimeException {
    private String msg;
    private int code = 500;

    public YoulezuoException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public YoulezuoException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public YoulezuoException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public YoulezuoException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
