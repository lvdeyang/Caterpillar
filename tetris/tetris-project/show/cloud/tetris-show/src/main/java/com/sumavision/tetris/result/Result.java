package com.sumavision.tetris.result;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Result
 * @Description TODO
 * @Author yud
 * @Date 2019/12/28 10:07
 **/
public class Result {

    private int code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认的成功
     *
     * @return
     */
    public static Map<String, Object> success() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", Status.SUCCESS.Code);
        map.put("msg", Status.SUCCESS.msg);
        return map;
    }

    /**
     * 默认的失败
     *
     * @return
     */
    public static Map<String, Object> error() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", Status.FAIL.Code);
        map.put("msg", Status.FAIL.msg);
        return map;
    }

    /**
     * 成功 + 返回的成功信息
     *
     * @param data
     * @return
     */
    public static Map<String, Object> sussess(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", Status.SUCCESS.Code);
        map.put("msg", Status.SUCCESS.msg);
        map.put("data", data);
        return map;
    }

    public enum Status {
        SUCCESS(200, "成功"),
        FAIL(400, "失败");

        private int Code;
        private String msg;

        Status(int code, String msg) {
            Code = code;
            this.msg = msg;
        }
    }
}

