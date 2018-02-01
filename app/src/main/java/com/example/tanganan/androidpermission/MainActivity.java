package com.example.tanganan.androidpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * @author TangAnna  2018/01/04
 *         在用权限的上一步就开始申请权限，这样做能避免很多问题
 */
public class MainActivity extends AppCompatActivity {


    private TextView mTextView;
    private String mPermissions[] = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};//所要获取的权限数组  相机  录音  写入
    private static final int REQUEST_PERMISSION_CODE = 101;//请求权限的请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv_main_request);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    /**
     * 请求权限的方法
     */
    public void requestPermission() {
        if (android.os.Build.VERSION.SDK_INT >= M) {//判断SDK版本号是否大于等于23
            //判断想要用到的权限是否授权   GRANTED：授权
            //拥有的权限不能再次进行请求，否则会导致崩溃，这里去除已经拥有的权限
            List<String> requestPermission = new ArrayList<>();//未拥有的权限
            for (int i = 0; i < mPermissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission.add(mPermissions[i]);
                }
            }
            if (requestPermission.size() == 0) {
                next();
            } else {
                String[] request = requestPermission.toArray(new String[requestPermission.size()]);
                ActivityCompat.requestPermissions(this, request, REQUEST_PERMISSION_CODE);
            }
        } else {
            next();
        }

    }

    /**
     * 申请权限的返回结果
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 返回结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            int count = 0;
            if (grantResults != null && grantResults.length > 0) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        count++;
                    }
                }
                if (count == 0) {
                    next();
                } else {
                    Toast.makeText(this, "需要录音和摄像头权限，请到【设置】【应用】打开", Toast.LENGTH_SHORT).show();
                }
            }
            Log.d("TAG", "===拒绝的权限大小onRequestPermissionsResult: " + count);
        }
    }

    /**
     * 获取到权限的下一步动作
     */
    public void next() {
        startActivity(new Intent(this, NeedPermissionActivity.class));
    }
}
