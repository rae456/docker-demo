package com.potato.docker.demo1.common;

import lombok.Data;
import lombok.experimental.Accessors;
import reactor.util.annotation.Nullable;

@Data
@Accessors(chain = true)
public class ResponseVo<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResponseVo ok() {
        return new ResponseVo<>().setCode(200).setMessage("ok");
    }
    public static<T> ResponseVo<T> ok(T t) {
        return new ResponseVo<T>().setCode(200).setMessage("ok").setData(t);
    }

    public static ResponseVo fail() {
        return new ResponseVo<>().setCode(400).setMessage("not ok");
    }
    public static ResponseVo fail(String message) {
        return new ResponseVo<>().setCode(400).setMessage(message);
    }
}
