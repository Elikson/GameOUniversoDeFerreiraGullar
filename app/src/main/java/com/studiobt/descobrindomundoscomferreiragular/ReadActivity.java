package com.studiobt.descobrindomundoscomferreiragular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    ListView listView;
    Button btPronto;
    ManagerObras managerObras;
    ArrayList<String> content = new ArrayList<>();
    int idObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        listView = (ListView) findViewById(R.id.list_content);
        btPronto = (Button)findViewById(R.id.bt_pronto);

        Bundle bundle = getIntent().getExtras();
        idObra = bundle.getInt("id",0);
        sortContent(idObra);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, content);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        btPronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPlay(idObra);
            }
        });
        super.onResume();
    }

    public void goPlay(int id){
        Intent intent = new Intent(this, EnergiasActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void sortContent(int id){
        managerObras = new ManagerObras();
        content.add("\n"+"   "+managerObras.getName(id)+"\n \n"+managerObras.getContent(id)+"\n");
    }
}
