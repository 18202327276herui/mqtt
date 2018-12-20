package usung.com.mqttclient.bean.user;

import java.io.Serializable;

import usung.com.mqttclient.bean.HttpRequestParameterBase;

/**
 * 更换管理员 参数
 * @author herui
 * @date 2018/12/14
 */

public class GetAdministratorParameter extends HttpRequestParameterBase implements Serializable {
    public String AdministratorId;

    public String getAdministratorId() {
        return AdministratorId;
    }

    public void setAdministratorId(String administratorId) {
        AdministratorId = administratorId;
    }
}
