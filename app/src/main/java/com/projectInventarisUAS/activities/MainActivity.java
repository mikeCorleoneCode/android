package com.projectInventarisUAS.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import com.projectInventarisUAS.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_PERMISSION_READIMAGE=1001;
    Button btItem, btTransaction, btCategory, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btItem=(Button) findViewById(R.id.btnItem);
        btCategory=(Button) findViewById(R.id.btnCategory);
        btTransaction=(Button) findViewById(R.id.btnTransaction);
        btExit=(Button) findViewById(R.id.btnExit);
        showPermission();

        btItem.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, ItemActivity.class);
            startActivity(intent);
        });

        btCategory.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
        btTransaction.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
            startActivity(intent);
        });
        btExit.setOnClickListener(view -> finish());
    }
    private void showPermission(){
        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.INTERNET};
        ArrayList<String> permissionList=new ArrayList<String>();
        boolean cekAll=true;
        for (int i=0;i<permissions.length;i++){
            int permissionCheck= ContextCompat.checkSelfPermission(this,permissions[i]);
            if (permissionCheck!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(permissions[i]);
                cekAll=false;
            }
        }
        if (cekAll==false){
            String[] stringArray=new String[permissionList.size()];
            permissionList.toArray(stringArray);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permissionList.get(0))){
                showExplanation("Permission Needed","Application need permission please",
                        stringArray,1001);
            }else{
                requestPermission(stringArray,1001);
            }
        }
    }

    private void showExplanation(String title, String message, String[] permission,
                                 final int permissionRequestCode){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermission(permission,permissionRequestCode);
                    }
                });
        builder.create().show();
    }
    private void requestPermission(String[] permissionName, int permissionRequestCode){
        ActivityCompat.requestPermissions(this,permissionName,
                permissionRequestCode);
    }


}