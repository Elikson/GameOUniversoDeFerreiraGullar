package com.studiobt.descobrindomundoscomferreiragular;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class EnergiasActivity extends AppCompatActivity {

    ManagerObras managerObras;
    boolean answear;
    String question;
    int idObra, sizeQuestions, powers;
    ArrayList<Integer> posQuestions = new ArrayList<>();

    TextView tvQuestion, tvEnergy;
    ImageView btYes, btNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energias);

        tvQuestion = (TextView)findViewById(R.id.tv_question);
        tvEnergy = (TextView)findViewById(R.id.tv_energy);
        btNo = (ImageView)findViewById(R.id.bt_no);
        btYes = (ImageView)findViewById(R.id.bt_yes);

        getEnergias();
        obterPosObras();
    }

    public void onClickEneergias(View view){
        if(view.getId() == R.id.bt_yes) {
            if (answear) {
                btYes.setImageResource(R.drawable.ic_bt_yes_green);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btYes.setImageResource(R.drawable.ic_bt_yes);
                                right();
                                upEnergias();
                            }
                        });
                    }
                }).start();

            }else {
                btYes.setImageResource(R.drawable.ic_bt_yes_red);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btYes.setImageResource(R.drawable.ic_bt_yes);
                                wrong();
                            }
                        });
                    }
                }).start();
            }

        }else if(view.getId() == R.id.bt_no) {
            if (!answear) {
                btNo.setImageResource(R.drawable.ic_bt_no_green);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btNo.setImageResource(R.drawable.ic_bt_no);
                                right();
                                upEnergias();
                            }
                        });
                    }
                }).start();
            }else{
                btNo.setImageResource(R.drawable.ic_bt_no_red);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btNo.setImageResource(R.drawable.ic_bt_no);
                                wrong();
                            }
                        });
                    }
                }).start();
            }
        }
    }

    public void right(){
        obterQuestion();
    }

    public void wrong(){
        vibrar();
        finish();
    }

    private void obterPosObras(){
        Bundle bundle = getIntent().getExtras();

        managerObras = new ManagerObras();
        idObra = bundle.getInt("id",0);
        sizeQuestions = managerObras.getCountQuestions(idObra);
        for (int i = 0; i < sizeQuestions; i++){
            posQuestions.add(i);
        }
        obterQuestion();
    }

    private void obterQuestion(){

        if(!(posQuestions.size() == 0)){
            Collections.shuffle(posQuestions);
            question = managerObras.getQuestion(idObra ,posQuestions.get(0));
            answear = managerObras.getAnswear(idObra, posQuestions.get(0));
            posQuestions.remove(0);
            setQuestion();
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext() ,"Parabéns!", Toast.LENGTH_SHORT).show();
                }
            });
            finish();
        }
    }

    public void setQuestion(){
        tvQuestion.setText(question);
    }

    private void vibrar()
    {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 200;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds);
    }

    public void getEnergias(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        powers = preferences.getInt(Cts.PREF_MUNITION, 0);
        String power = getString(R.string.powers)+" "+powers;
        tvEnergy.setText(power);
    }

    public void upEnergias(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        powers += 10;
        editor.putInt(Cts.PREF_MUNITION, powers);
        editor.apply();
        String power = getString(R.string.powers)+" "+powers;
        tvEnergy.setText(power);
    }
}
