package com.jwei.mysearch;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jwei.mysearch.item.MyUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class activity_profile_edit extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button profile_edit_back;
    private Spinner sex;
    private TextView age_edit;
    private Button profile_edit_done;
    private String sex1;
    private EditText Email;
    private EditText self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一：默认初始化
        Bmob.initialize(this, "c3691faf8b85561c7d207be91a25b9e4");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_edit);
        Email=(EditText) findViewById(R.id.mailbox_edit);
        self=(EditText) findViewById(R.id.sig_edit);
        profile_edit_done=(Button) findViewById(R.id.profile_edit_done);
        profile_edit_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BmobUser bmobUser=BmobUser.getCurrentUser();


                MyUser myUser= new MyUser();
//                Toast.makeText(getApplicationContext(),bmobUser.getUsername(),Toast.LENGTH_LONG).show();
                myUser.setSex(sex1);
                myUser.setAge(Integer.valueOf(age_edit.getText().toString()));
                myUser.setsEmail(Email.getText().toString());
                myUser.setSelfintroduce(self.getText().toString());
                BmobUser bmobUser=BmobUser.getCurrentUser();
                myUser.update(bmobUser.getObjectId(),new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"修改失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }

        });

        profile_edit_back = (Button) findViewById(R.id.profile_edit_back);
        profile_edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_profile_edit.this,activity_profile_page.class);
                startActivity(intent);
            }
        });

        sex = (Spinner) findViewById(R.id.sex_edit);
        ArrayAdapter< String> adapter = new ArrayAdapter< String>( this,R.layout.spinner_style);
        adapter.add("男");
        adapter.add("女");
        sex.setAdapter(adapter);
        age_edit = (TextView) findViewById(R.id.age_edit);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex1=sex.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void age_set(View v) {
        new DatePickerDialog(activity_profile_edit.this,this,2000,0,1).show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Toast.makeText(activity_profile_edit.this, "你选择的是" + i + "年" + (i1+1) + "月" + i2 + "日",
                Toast.LENGTH_LONG).show();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
       int  NowYear = Integer.parseInt(format.format(new Date()))-i;
//        Toast.makeText(getApplicationContext(),NowYear,Toast.LENGTH_LONG).show();
        age_edit.setText(String.valueOf(NowYear));
    }

}
