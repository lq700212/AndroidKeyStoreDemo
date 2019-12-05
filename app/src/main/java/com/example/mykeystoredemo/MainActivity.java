package com.example.mykeystoredemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button bt_create;
    private Button bt_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_create = (Button) findViewById(R.id.bt_create);
        bt_get = (Button) findViewById(R.id.bt_get);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyStoreUtil.getInstance().createNewKeys(MainActivity.this, "md5UUID:1234567890");
            }
        });

        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = KeyStoreUtil.getInstance().getKey("md5UUID");
                Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
