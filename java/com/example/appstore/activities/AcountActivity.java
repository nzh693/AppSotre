package com.example.appstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstore.MainActivity;
import com.example.appstore.R;
import com.example.appstore.Utils.MyToast;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.ViewModule.AcountViewModule;
import com.example.appstore.database.entity.Acount;

import java.util.List;

public class AcountActivity extends AppCompatActivity {

    private Button btFinish;

    private EditText acount;
    private EditText password;

    private TextView acountName;
    private TextView acountPhone;
    private TextView load;
    private TextView exit;

    private TextView showData;
    private LinearLayout loading;
    private LinearLayout loaded;

    private TextView dateinsert;
    private TextView dataDeleteAll;

    private LiveData<List<Acount>> liveData;

    private AcountViewModule acountHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);
        btFinish=findViewById(R.id.bt_finish);
        loading=findViewById(R.id.loading);
        loaded=findViewById(R.id.loaded);
        View viewYet=findViewById(R.id.acount_loadyet);
        View viewIng=findViewById(R.id.acount_loading);

        exit=viewYet.findViewById(R.id.Acount_exit);
        acountName=viewYet.findViewById(R.id.Acount_name);
        acountPhone=viewYet.findViewById(R.id.Acount_phone);

        acount=viewIng.findViewById(R.id.Acount_acount);
        password=viewIng.findViewById(R.id.Acount_password);
        load=viewIng.findViewById(R.id.Acount_load);
        showData=viewIng.findViewById(R.id.show_data);

        dateinsert=viewIng.findViewById(R.id.data_insert);
        dataDeleteAll=viewIng.findViewById(R.id.data_deleteAll);

        if (MainActivity.isLoad){
            loaded.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
        else {
            loaded.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        }


        //插入数据
        dateinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acount tempAcount=new Acount("nzh","女","123456","+86 123456789");
                Acount tempAcount2=new Acount("zxl","男","123456","+86 133640203939");
                acountHolder.insert(tempAcount,tempAcount2);
            }
        });

        //删除全部
        dataDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acountHolder.deleteAllAcount();
            }
        });

        //退出登录》显示登录界面
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.isLoad=false;
                loaded.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                acount.setText("");
                password.setText("");
            }
        });

        acountHolder= ViewModelProviders.of(this).get(AcountViewModule.class);
        liveData=acountHolder.getLiveData();

        liveData.observe(this, new Observer<List<Acount>>() {
            @Override
            public void onChanged(List<Acount> acounts) {
                String text="";
                for(Acount a:acounts){
                    text+=a.getId()+a.getAcountName()+" "+a.getAcountPhone()+" "+a.getPassword();
                }
                showData.setText(text);
            }
        });

        acount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().length()>0){
                    int color=getResources().getColor(R.color.colorBlue);
                    load.setTextColor(color);
                }else {
                    int color=getResources().getColor(R.color.colorMaxlitterDark);
                    load.setTextColor(color);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        acount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                if (manager != null)
                    manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (acount.getText().length()>0){
                    int color=getResources().getColor(R.color.colorBlue);
                    load.setTextColor(color);
                }else {
                    int color=getResources().getColor(R.color.colorMaxlitterDark);
                    load.setTextColor(color);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                if (manager != null)
                    manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        init();
    }

    public void init(){
        Onclick onclick=new Onclick();
        btFinish.setOnClickListener(onclick);//点击个人中心跳转
        load.setOnClickListener(onclick);

    }

    //处理点击事件
    public class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.bt_finish://点击完成返回主界面
                    intent=new Intent(AcountActivity.this, MainActivity.class);
                    break;
                case R.id.Acount_load://点击登录 登录成功加载个人主页，否则提示登录失败
                    List<Acount> acounts=acountHolder.queryByNameAndPassword(acount.getText().toString(),password.getText().toString());
                    acountHolder.setCallBackListener(new CallBack() {
                        @Override
                        public void onDataSuccessfully(Object object) {
                            List<Acount> acounts=(List<Acount>)object;
                            if (acounts.size()>0){
                                MyToast.showToast(getApplicationContext(),"登录成功");
                                loaded.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                                acountName.setText(acounts.get(0).getAcountName());
                                acountPhone.setText(acounts.get(0).getAcountPhone());
                                MainActivity.isLoad=true;
                            }else {
                                MyToast.showToast(getApplicationContext(),"登录失败");
                                acount.setText("");
                                password.setText("");
                            }
                        }
                        @Override
                        public void onDataFailed() {

                        }
                    });

                    break;

            }
            if (intent!=null)
            startActivity(intent);
        }


    }
}
