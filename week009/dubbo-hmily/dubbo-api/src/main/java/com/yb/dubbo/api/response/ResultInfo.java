package com.yb.dubbo.api.response;

import lombok.Data;

/**
 * 这里是restful风格,返回数据封装类,为了方便,可以直接传入Object,
 * 而不是具体的类型例如Result<Object>就可以接受各种返回类型,就不会每次都去更改了
 *
 * @author yangbiao
 * @date 2021/5/25
 */
@Data
public class ResultInfo<T> {
   public static final String OK = "操作成功";
   public static final String NO_OK = "操作失败";
   public static final int OK_CODE = 200;
   public static final int BAD_REQUEST_CODE = 400;

    private int code;
    private String message;
    private T data;

    /**
     * 供接口返回数据使用
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success(T t) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(OK_CODE);
        result.setMessage(OK);
        result.setData(t);
        return result;
    }

    /**
     * 供接口返回数据使用(只有标识)
     *
     * @param
     * @param
     * @return
     */
    public static ResultInfo success() {
        ResultInfo result = new ResultInfo<>();
        result.setCode(OK_CODE);
        result.setMessage(OK);
        result.setData(null);
        return result;
    }

    /**
     * 这个一般用不着,因为基本都是用异常来捕捉的
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String message) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(BAD_REQUEST_CODE);
        result.setMessage(message);
        return result;
    }

    /**
     * 这个一般用不着,因为基本都是用异常来捕捉的
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String message, Integer code) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 这个主要用来在异常捕捉类里简化返回数据的封装
     *
     * @param status
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> withCode(Integer status) {
        ResultInfo<T> result = new ResultInfo<>();
        result.setCode(status);
        return result;
    }

    /**
     * 将传入的信息设置到成员变量并返回对象
     *
     * @param message
     * @return
     */
    public ResultInfo withMessage(String message) {
        this.message = message;
        return this;
    }

    public static ResultInfo result(Object t) {
        ResultInfo result = new ResultInfo<>();
        if (t instanceof Boolean) {
            boolean flag = (boolean) t;
            return flag ? ResultInfo.success() : ResultInfo.error("操作失败");
        }
        result.setMessage(OK);
        result.setData(t);
        return result;
    }

}
