package com.fiserv.codeforce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.fiserv.codeforce.login.CredentialsPojo;
import com.fiserv.codeforce.login.LoginRepository;
import com.fiserv.codeforce.login.ResponseLogin;
import com.fiserv.codeforce.utils.AsteriskPasswordTransformationMethod;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.ResourceAccessException;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    CredentialsPojo credentialsPojo;

    @ViewById
    EditText txt_user;

    @ViewById
    EditText txt_password;

    @RestService
    LoginRepository loginRepository;

    @Bean
    GlobalContainer globalContainer;
    
    @Click(R.id.btn_login)
    public void click() {
        if(matchLogin()){
                initSession();
//                toastMessage("No se pudo inicar sesión");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        txt_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
    }

    @Background
    public void initSession(){
        try {
            ResponseLogin r = loginRepository.login(new CredentialsPojo()
                    .setUsername(txt_user.getText().toString())
                    .setPassword(txt_password.getText().toString())).getBody();

            globalContainer.setLoginData(r.getLoginData());
            globalContainer.setUserInfo(r.getUserInfo());
            goIntent();

        }
        catch (Exception e){
            if(e.getClass() != ResourceAccessException.class)
                toastMessage("No se pudo inicar sesión");
            else
                toastMessage("No se pudo conectar a internet");
        }

    }

    @UiThread
    public void goIntent(){
        PrincipalActivity_.intent(getApplicationContext()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
    }

    public Boolean matchUsername(String username){
        String regexUser = "^[1-9][0-9]{1,9}$";
        return username.matches(regexUser);
    }

    public Boolean matchPass(String pass){
        String regexPass = "^[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]{6,8}$$";
        return pass.matches(regexPass);
    }

    public boolean matchLogin(){

        String userId = txt_user.getText().toString();
        String pass = txt_password.getText().toString();
        if(!matchUsername(userId)) {
            toastMessage("DNI Inválido!");
            return false;
        }
        if(!matchPass(pass)){
            toastMessage("Password Inválido!");
            return false;
        }
        return true;
    }

    @UiThread
    public void toastMessage(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
