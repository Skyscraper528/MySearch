package com.jwei.mysearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jwei.mysearch.item.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class activity_login_page extends AppCompatActivity {
    private Button register;
    private Button Login;
    private EditText uname;
    private EditText upassword;
    private BmobUser mBmobUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一：默认初始化
        Bmob.initialize(this, "c3691faf8b85561c7d207be91a25b9e4");
        setContentView(R.layout.activity_login_page);

        uname=(EditText) findViewById(R.id.start);
        upassword=(EditText) findViewById(R.id.end);

        register = (Button) findViewById(R.id.zhuce);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login_page.this,activity_register_page.class);
                startActivity(intent);
            }
        });
        BmobUser bmobUser=BmobUser.getCurrentUser();
        if(bmobUser!=null){
            Intent intent=new Intent(activity_login_page.this,MainPages.class);
            startActivity(intent);
        }else{
        }
        Login=(Button) findViewById(R.id.Login1);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.loginByAccount(uname.getText().toString(),upassword.getText().toString(),new LogInListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(myUser!=null){

                            Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(activity_login_page.this,MainPages.class);
//                            intent.putExtra("username",uname.getText().toString());
//                            intent.putExtra("id",2);
                            //LoginName.setLoginname(uname.getText().toString());
                            SharedPreferences.Editor spe = (SharedPreferences.Editor) getSharedPreferences("data", MODE_PRIVATE).edit();
                            spe.putString("LoginName",uname.getText().toString());
                            spe.apply();

                            startActivity(intent);

                           // finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"登录失败，请重试",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });



    }
}
