package usung.com.mqttclient.http.base;


/**
 * Created by lifei on 2018/5/3.
 */

public class BaseResult<T>  {
    int error;
    String msg;
    int total;
    T items;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
