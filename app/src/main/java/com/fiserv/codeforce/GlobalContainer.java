package com.fiserv.codeforce;

import com.fiserv.codeforce.login.LoginData;
import com.fiserv.codeforce.user.UserInfo;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class GlobalContainer {

    @Bean
    LoginData loginData;

    @Bean
    UserInfo userInfo;

    public GlobalContainer setLoginData(LoginData loginData){
        this.loginData = loginData.clone();
        return this;
    }

    public GlobalContainer setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo.clone();
        return this;
    }

    public GlobalContainer logout(){
        loginData.setAccess_token("");
        return this;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
