package com.fiserv.codeforce;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fiserv.codeforce.login.CredentialsPojo;
import com.fiserv.codeforce.login.LoginRepository;
import com.fiserv.codeforce.login.ResponseLogin;
import com.fiserv.codeforce.rest.DemoAPI;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    CredentialsPojo credentialsPojo;

    @ViewById
    EditText txt_user;

    @ViewById
    EditText txt_password;

    @RestService
    LoginRepository loginRepository;
    
    @Click(R.id.btn_login)
    public void click() {
        initSession();
    }

    @Background
    public void initSession() {
        try {

            //if(!matchLogin()){
            ResponseLogin r = loginRepository.login(new CredentialsPojo()
                    .setUsername(txt_user.getText().toString())
                    .setPassword(txt_password.getText().toString())).getBody();
            PrincipalActivity_.intent(getApplicationContext()).start();
//            toastMessage(r.getUserInfo().getGivenName());
            //}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean matchUsername(String username){
        String regexUser = "^[1-9]{1}[0-9]{8}$";
        return username.matches(regexUser);
    }

    public Boolean matchPass(String pass){
        String regexPass = "^[a-zA-Z0-9]{6,8}$";
        return pass.matches(regexPass);
    }

    public boolean matchLogin(){

        String userId = txt_user.getText().toString();
        String pass = txt_password.getText().toString();
        if(!matchUsername(userId)) {
            toastMessage("ID incorrecto");
            return false;
        }
        if(!matchPass(pass)){
            toastMessage("Password no cumple con los requisitos");
            return false;
        }
        return true;
    }


    private void toastMessage(String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
