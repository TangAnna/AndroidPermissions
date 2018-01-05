# AndroidPermission
Android6.0动态申请权限
============================
谷歌官方将权限分为了两类，一个是正常权限（Normal Permissions），这类权限不涉及用户隐私，是不需要用户进行授权的，比如访问网络，手机震动等。还有一类是危险权限（Dangerous Permissions），一般是涉及到用户隐私的，需要用户进行授权，比如操作SD卡的写入，相机，录音等。
Android 6.0 版本以前权限只要写在manifest就可以，但是6.0版本以后为了保证用户的隐私安全Dangerous Permissions权限要使用时动态去申请；
开发中targetSdkVersion版本大于等于23的时候要添加动态申请权限


Dangerous Permissions：
-------------------------
1.联系人

`  permission:android.permission.WRITE_CONTACTS;
     permission:android.permission.GET_ACCOUNTS;  
     permission:android.permission.READ_CONTACTS;`
     
   
2.通话类

`    permission:android.permission.READ_CALL_LOG;
     permission:android.permission.READ_PHONE_STATE;
     permission:android.permission.CALL_PHONE;
     permission:android.permission.WRITE_CALL_LOG;
     permission:android.permission.USE_SIP;
     permission:android.permission.PROCESS_OUTGOING_CALLS;
     permission:com.android.voicemail.permission.ADD_VOICEMAIL;`
     
    
    
3.日历类

     permission:android.permission.READ_CALENDAR;
     permission:android.permission.WRITE_CALENDAR;
    
4.相机

    permission:android.permission.CAMERA;
   
5.传感器

    permission:android.permission.BODY_SENSORS;
  
6.位置

    permission:android.permission.ACCESS_FINE_LOCATION;
    permission:android.permission.ACCESS_COARSE_LOCATION;
 
7.SDCard
 
    permission:android.permission.READ_EXTERNAL_STORAGE;
    permission:android.permission.WRITE_EXTERNAL_STORAGE;
    
8.麦克风

    permission:android.permission.RECORD_AUDIO;
    
9.SMS(短信)

    permission:android.permission.READ_SMS;
    permission:android.permission.RECEIVE_WAP_PUSH;
    permission:android.permission.RECEIVE_MMS;
    permission:android.permission.RECEIVE_SMS;
    permission:android.permission.SEND_SMS;
    permission:android.permission.READ_CELL_BROADCASTS;



关键代码：

1.检查手机版本
```
android.os.Build.VERSION.SDK_INT >= M
```

2.要用的权限是否有（以相机权限为例）

```
ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
```

3.申请权限（权限以数组的形式）
```
ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CODE);
```

4.重写方法onRequestPermissionsResult

```
@Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    
        if (requestCode == REQUEST_PERMISSION_CODE) {
        
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//用户拒绝了
            
                Toast.makeText(this, "获取相机权限失败!", Toast.LENGTH_SHORT).show();
                
            }  else {        
                //获取到权限了，做下一步动作
                
                    }   
        }  
    }
    ```
    
<br/>(此Demo是记录自己工作中的一些问题，如发现错误的地方请指教，谢谢! 
--（TangAnna QQ:1101870076）) 
