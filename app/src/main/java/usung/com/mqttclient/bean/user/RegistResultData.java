package usung.com.mqttclient.bean.user;

import java.io.Serializable;

import usung.com.mqttclient.bean.HttpResposeDataBase;

/**
 * Created by herui on 2018/12/14.
 */

public class RegistResultData extends HttpResposeDataBase implements Serializable {
    /// <summary>
    /// 注册结果
    /// </summary>
    public int Result;

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }
}
