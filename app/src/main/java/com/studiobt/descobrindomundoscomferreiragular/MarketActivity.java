package com.studiobt.descobrindomundoscomferreiragular;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MarketActivity extends AppCompatActivity {

    ImageView btPayGunOne, btPayGunTwo, btPayGunThree,
            btPayNaveOne, btPayNaveTwo, btPayNaveThree,
            btPayShieldOne, btPayShieldTwo, btPayShieldThree;

    TextView tvEnergy;

    private static int
            PRICE_GO = 500, PRICE_GT = 500, PRICE_GTH = 500,
            PRICE_NT = 400, PRICE_NTH = 400,
            PRICE_SO = 700, PRICE_ST = 700, PRICE_STH = 700;

    private static final int
            NAVE_ONE = R.drawable.ic_nave_one, NAVE_TWO = R.drawable.ic_nave_two, NAVE_THREE = R.drawable.ic_nave_three,
            NAVE_ONE_SHIELD_ONE = R.drawable.ic_no_so, NAVE_ONE_SHIELD_TWO = R.drawable.ic_no_st, NAVE_ONE_SHIELD_THREE = R.drawable.ic_no_sth,
            NAVE_TWO_SHIELD_ONE = R.drawable.ic_nt_so, NAVE_TWO_SHIELD_TWO = R.drawable.ic_nt_st, NAVE_TWO_SHIELD_THREE = R.drawable.ic_nt_sth,
            NAVE_THREE_SHIELD_ONE = R.drawable.ic_nth_so, NAVE_THREE_SHIELD_TWO = R.drawable.ic_nth_st, NAVE_THREE_SHIELD_THREE = R.drawable.ic_nth_sth,
            GUN_ONE = R.drawable.ic_gun_two, GUN_TWO = R.drawable.ic_gun_three, GUN_THREE = R.drawable.ic_gun_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        btPayGunOne = (ImageView)findViewById(R.id.imgPayGunOne);
        btPayGunTwo = (ImageView)findViewById(R.id.imgPayGunTwo);
        btPayGunThree = (ImageView)findViewById(R.id.imgPayGunThree);

        btPayNaveOne = (ImageView)findViewById(R.id.imgpayNaveOne);
        btPayNaveTwo = (ImageView)findViewById(R.id.imgPayNaveTwo);
        btPayNaveThree = (ImageView)findViewById(R.id.imgPayNaveThree);

        btPayShieldOne = (ImageView)findViewById(R.id.imgPayShieldOne);
        btPayShieldTwo = (ImageView)findViewById(R.id.imgPayShieldTwo);
        btPayShieldThree = (ImageView)findViewById(R.id.imgPayShieldThree);

        tvEnergy = (TextView)findViewById(R.id.tv_energy);

        updateValues();
    }

    public void onClickMarket(View view){
        switch (view.getId()){
            case R.id.imgPayGunOne:
                if(!isPayed(Cts.PREF_PAYED_GO)){
                    if(getMoney() >= PRICE_GO){
                        comprar(PRICE_GO, Cts.PREF_PAYED_GO, "Este LASER custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_GO+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    setGun(16, 16, R.drawable.ic_gun_two);
                    updateValues();
                }
                break;
            case R.id.imgPayGunTwo:
                if(!isPayed(Cts.PREF_PAYED_GT)){
                    if(getMoney() >= PRICE_GT){
                        comprar(PRICE_GT, Cts.PREF_PAYED_GT, "Este LASER custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_GT+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    setGun(16, 18, R.drawable.ic_gun_three);
                    updateValues();
                }
                break;
            case R.id.imgPayGunThree:
                if(!isPayed(Cts.PREF_PAYED_GTH)){
                    if(getMoney() >= PRICE_GTH){
                        comprar(PRICE_GTH, Cts.PREF_PAYED_GTH, "Este LASER custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_GTH+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    setGun(16, 16, R.drawable.ic_gun_four);
                    updateValues();
                }
                break;

            case R.id.imgpayNaveOne:
                setNave(NAVE_ONE);
                updateValues();
                break;
            case R.id.imgPayNaveTwo:
                if(!isPayed(Cts.PREF_PAYED_NT)){
                    if(getMoney() >= PRICE_NT){
                        comprar(PRICE_NT, Cts.PREF_PAYED_NT, "Esta NAVE custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_NT+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    setNave(NAVE_TWO);
                    updateValues();
                }
                break;
            case R.id.imgPayNaveThree:
                if(!isPayed(Cts.PREF_PAYED_NTH)){
                    if(getMoney() >= PRICE_NTH){
                        comprar(PRICE_NTH, Cts.PREF_PAYED_NTH, "Esta NAVE custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_NTH+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    setNave(NAVE_THREE);
                    updateValues();
                }
                break;

            case R.id.imgPayShieldOne:
                if(!isPayed(Cts.PREF_PAYED_SO)){
                    if(getMoney() >= PRICE_SO){
                        comprar(PRICE_SO, Cts.PREF_PAYED_SO, "Este ESCUDO custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_SO+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(isChoiced(Cts.PREF_NAVE, NAVE_ONE)){
                        setNave(NAVE_ONE_SHIELD_ONE);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO)){
                        setNave(NAVE_TWO_SHIELD_ONE);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE)){
                        setNave(NAVE_THREE_SHIELD_ONE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_TWO)){
                        setNave(NAVE_ONE_SHIELD_ONE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_THREE)){
                        setNave(NAVE_ONE_SHIELD_ONE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_TWO)){
                        setNave(NAVE_TWO_SHIELD_ONE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_THREE)){
                        setNave(NAVE_TWO_SHIELD_ONE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_TWO)){
                        setNave(NAVE_THREE_SHIELD_ONE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_THREE)){
                        setNave(NAVE_THREE_SHIELD_ONE);
                    }
                    updateValues();
                }
                break;
            case R.id.imgPayShieldTwo:
                if(!isPayed(Cts.PREF_PAYED_ST)){
                    if(getMoney() >= PRICE_ST){
                        comprar(PRICE_ST, Cts.PREF_PAYED_ST, "Este ESCUDO custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_ST+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(isChoiced(Cts.PREF_NAVE, NAVE_ONE)){
                        setNave(NAVE_ONE_SHIELD_TWO);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO)){
                        setNave(NAVE_TWO_SHIELD_TWO);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE)){
                        setNave(NAVE_THREE_SHIELD_TWO);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_ONE)){
                        setNave(NAVE_ONE_SHIELD_TWO);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_THREE)){
                        setNave(NAVE_ONE_SHIELD_TWO);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_ONE)){
                        setNave(NAVE_TWO_SHIELD_TWO);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_THREE)){
                        setNave(NAVE_TWO_SHIELD_TWO);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_ONE)){
                        setNave(NAVE_THREE_SHIELD_TWO);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_THREE)){
                        setNave(NAVE_THREE_SHIELD_TWO);
                    }
                    updateValues();
                }
                break;
            case R.id.imgPayShieldThree:
                if(!isPayed(Cts.PREF_PAYED_STH)){
                    if(getMoney() >= PRICE_STH){
                        comprar(PRICE_STH, Cts.PREF_PAYED_STH, "Este ESCUDO custa: ");
                    }else{
                        Toast.makeText(getApplicationContext(), "Energias insuficientes, custa "+PRICE_STH+" ES",Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(isChoiced(Cts.PREF_NAVE, NAVE_ONE)){
                        setNave(NAVE_ONE_SHIELD_THREE);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO)){
                        setNave(NAVE_TWO_SHIELD_THREE);
                    }else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE)){
                        setNave(NAVE_THREE_SHIELD_THREE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_ONE)){
                        setNave(NAVE_ONE_SHIELD_THREE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_ONE_SHIELD_TWO)){
                        setNave(NAVE_ONE_SHIELD_THREE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_ONE)){
                        setNave(NAVE_TWO_SHIELD_THREE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_TWO_SHIELD_TWO)){
                        setNave(NAVE_TWO_SHIELD_THREE);
                    }

                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_ONE)){
                        setNave(NAVE_THREE_SHIELD_THREE);
                    }
                    else if(isChoiced(Cts.PREF_NAVE, NAVE_THREE_SHIELD_TWO)){
                        setNave(NAVE_THREE_SHIELD_THREE);
                    }
                    updateValues();
                }
                break;
        }
    }

    public void comprar(final int price, final String namePay, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message+price+" ES");
        builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addPayed(namePay);
                setMoney(getMoney() - price);
                updateValues();
            }
        });
        builder.setNegativeButton("Dispensar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void setNave(int idNave){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Cts.PREF_NAVE, idNave);
        editor.apply();
    }

    public void setGun(int hGun, int wGun, int idLaser){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Cts.PREF_H_LASER, hGun);
        editor.putInt(Cts.PREF_W_LASER, wGun);
        editor.putInt(Cts.PREF_LASER, idLaser);
        editor.apply();
    }

    public boolean isChoiced(String pf, int resId){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        int id = preferences.getInt(pf, 0);
        return id == resId;
    }

    public boolean isPayed(String pf){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        return preferences.getBoolean(pf, false);
    }

    public void addPayed(String pf){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(pf, true);
        editor.apply();
    }

    public int getMoney(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        return  preferences.getInt(Cts.PREF_MUNITION, 0);
    }

    public void setMoney(int money){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Cts.PREF_MUNITION, money);
        editor.apply();
    }

    public void updateValues(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        int energy = preferences.getInt(Cts.PREF_MUNITION,0);
        int naveChoiced = preferences.getInt(Cts.PREF_NAVE, R.drawable.ic_nave_one);
        int laserChoiced = preferences.getInt(Cts.PREF_LASER, 0);

        boolean payedNO = preferences.getBoolean(Cts.PREF_PAYED_NO ,true);
        boolean payedNT = preferences.getBoolean(Cts.PREF_PAYED_NT ,false);
        boolean payedNTH = preferences.getBoolean(Cts.PREF_PAYED_NTH ,false);

        boolean payedGO = preferences.getBoolean(Cts.PREF_PAYED_GO ,false);
        boolean payedGT = preferences.getBoolean(Cts.PREF_PAYED_GT ,false);
        boolean payedGTH = preferences.getBoolean(Cts.PREF_PAYED_GTH ,false);

        boolean payedSO = preferences.getBoolean(Cts.PREF_PAYED_SO ,false);
        boolean payedST = preferences.getBoolean(Cts.PREF_PAYED_ST ,false);
        boolean payedSTH = preferences.getBoolean(Cts.PREF_PAYED_STH ,false);

        String energyText = getString(R.string.energias)+" "+energy+" ES";
        tvEnergy.setText(energyText);

        //Lasers comprados
        if(payedGO){
            btPayGunOne.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayGunOne.setImageResource(R.drawable.ic_comprar);
        }

        if(payedGT){
            btPayGunTwo.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayGunTwo.setImageResource(R.drawable.ic_comprar);
        }

        if(payedGTH){
            btPayGunThree.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayGunThree.setImageResource(R.drawable.ic_comprar);
        }

        //Naves compradas
        if(payedNO){
            btPayNaveOne.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayNaveOne.setImageResource(R.drawable.ic_comprar);
        }

        if(payedNT){
            btPayNaveTwo.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayNaveThree.setImageResource(R.drawable.ic_comprar);
        }

        if(payedNTH){
            btPayNaveThree.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayNaveThree.setImageResource(R.drawable.ic_comprar);
        }

        //Escudos comprados
        if(payedSO){
            btPayShieldOne.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayShieldOne.setImageResource(R.drawable.ic_comprar);
        }

        if(payedST){
            btPayShieldTwo.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayShieldThree.setImageResource(R.drawable.ic_comprar);
        }

        if(payedSTH){
            btPayShieldThree.setImageResource(R.drawable.ic_comprado);
        }else{
            btPayShieldThree.setImageResource(R.drawable.ic_comprar);
        }

        //Laser selecionado
        if(laserChoiced == GUN_ONE){
            btPayGunOne.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(laserChoiced == GUN_TWO){
            btPayGunTwo.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(laserChoiced == GUN_THREE){
            btPayGunThree.setImageResource(R.drawable.ic_bt_selecionado);
        }

        //Nave e Escudo selecionados
        if(naveChoiced == NAVE_ONE){
            btPayNaveOne.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_TWO){
            btPayNaveTwo.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_THREE){
            btPayNaveThree.setImageResource(R.drawable.ic_bt_selecionado);

        }else if(naveChoiced == NAVE_ONE_SHIELD_ONE){
            btPayNaveOne.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldOne.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_ONE_SHIELD_TWO){
            btPayNaveOne.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldTwo.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_ONE_SHIELD_THREE){
            btPayNaveOne.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldThree.setImageResource(R.drawable.ic_bt_selecionado);

        }else if(naveChoiced == NAVE_TWO_SHIELD_ONE){
            btPayNaveTwo.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldOne.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_TWO_SHIELD_TWO){
            btPayNaveTwo.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldTwo.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_TWO_SHIELD_THREE){
            btPayNaveTwo.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldThree.setImageResource(R.drawable.ic_bt_selecionado);

        }else if(naveChoiced == NAVE_THREE_SHIELD_ONE){
            btPayNaveThree.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldOne.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_THREE_SHIELD_TWO){
            btPayNaveThree.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldTwo.setImageResource(R.drawable.ic_bt_selecionado);
        }else if(naveChoiced == NAVE_THREE_SHIELD_THREE){
            btPayNaveThree.setImageResource(R.drawable.ic_bt_selecionado);
            btPayShieldThree.setImageResource(R.drawable.ic_bt_selecionado);
        }
    }
}
