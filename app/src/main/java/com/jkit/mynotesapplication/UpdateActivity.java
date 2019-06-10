package com.jkit.mynotesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jkit.mynotesapplication.db.DBManager;
import com.jkit.mynotesapplication.fragment.ViewNoteListFragment;

import java.time.Instant;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText titleEdTxt;
    private EditText desEdTxt;

    private Button updateBtn;
    private Button deleteBtn;

    private DBManager dbManager;
    private int _id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbManager = new DBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");

        titleEdTxt = (EditText) findViewById(R.id.titleEdTxt);
        desEdTxt = (EditText) findViewById(R.id.desEdTxt);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        titleEdTxt.setText(title);
        desEdTxt.setText(des);

        _id = Integer.parseInt(id);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateBtn:

                String title = titleEdTxt.getText().toString();
                String des = desEdTxt.getText().toString();

                dbManager.update(_id, title, des);
                returnHomr();
                break;

            case R.id.deleteBtn:
                dbManager.delete(_id);
                returnHomr();
                break;

        }
    }

    public void returnHomr(){
        Intent intent = new Intent(this, ViewNotesActivity.class);
        startActivity(intent);
    }
}
