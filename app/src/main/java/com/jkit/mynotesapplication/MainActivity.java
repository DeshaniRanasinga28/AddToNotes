package com.jkit.mynotesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jkit.mynotesapplication.db.DBManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleEdTxt;
    private EditText desEdTxt;
    private Button addBtn;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEdTxt = (EditText) findViewById(R.id.noteEdTxt);
        desEdTxt = (EditText)findViewById(R.id.desEdTxt);
        addBtn = (Button)findViewById(R.id.addNotesBtn);

        addBtn.setOnClickListener(this);

        dbManager = new DBManager(this);
        dbManager.open();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNotesBtn :
                String title = titleEdTxt.getText().toString();
                String des = desEdTxt.getText().toString();

                if(title.isEmpty()){
                    titleEdTxt.setError("Please Enter Title");
                    titleEdTxt.requestFocus();
                }else if(des.isEmpty()){
                    desEdTxt.setError("Please enter Notes here");
                    desEdTxt.requestFocus();
                }else {
                    dbManager.insert(title, des);

                    Intent intent = new Intent(this, ViewNotesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
        }

    }
}
