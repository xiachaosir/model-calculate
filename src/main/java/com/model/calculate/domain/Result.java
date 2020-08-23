package com.model.calculate.domain;

import java.io.Serializable;

public class Result implements Serializable {

    private int status;

    private String message;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Result success() {
        return new Result(0, "");
    }

    public static Result fail() {
        return new Result(1, "未知错误");
    }

    public static Result fail(String message) {
        return new Result(1, message);
    }
}
