package com.example.tanganan.androidpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
            if (ContextCompat.checkSelfPermission(this, mPermissions[0]) != PackageManager.PERMISSION_GRANTED) {
                //动态申请权限
                ActivityCompat.requestPermissions(this, mPermissions, REQUEST_PERMISSION_CODE);
            } else if (ContextCompat.checkSelfPermission(this, mPermissions[1]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, mPermissions, REQUEST_PERMISSION_CODE);
            } else if (ContextCompat.checkSelfPermission(this, mPermissions[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, mPermissions, REQUEST_PERMISSION_CODE);
            } else {
                next();
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
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//用户拒绝了
                Toast.makeText(this, "获取相机权限失败!", Toast.LENGTH_SHORT).show();
            } else if (grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "获取生意权限失败!", Toast.LENGTH_SHORT).show();
            } else if (grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "获取SDCard写入权限失败!", Toast.LENGTH_SHORT).show();
            } else {
                next();
            }
        }
    }

    /**
     * 获取到权限的下一步动作
     */
    public void next() {
        startActivity(new Intent(this, NeedPermissionActivity.class));
    }
}
