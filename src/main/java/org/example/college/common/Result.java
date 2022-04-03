package org.example.college.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回信息结果，提高消息规范性
 * @param <T>
 */

/**
 *
 * 因为 @Cacheable 会将方法的返回值R缓存在Redis中，而在Redis中存储对象，该对象是需要被序列化的，而对象要想被成功的序列化，就必须得实现 Serializable 接口。而当前我们定义的R，并未实现 Serializable 接口。所以，要解决该异常，只需要让R实现  Serializable 接口即可。如下：
 */
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据,使用泛型增加通用性
    private Map map = new HashMap(); //动态数据

    /**
     * 错误信息静态方法
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message){
        Result result = new Result();
        result.msg = message;
        result.code = 0;
        return result;
    }

    /**
     * 成功信息静态方法,
     * @param object
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T object){
        Result result = new Result();
        result.data = object;
        result.code = 1;
        return result;
    }

    /**
     * 动态数据添加
     * @param key
     * @param value
     * @return
     */
    public Result<T> add(String key,Object value){
        this.map.put(key,value);
        return this;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
