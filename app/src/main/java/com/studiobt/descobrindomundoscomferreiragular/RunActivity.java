package com.studiobt.descobrindomundoscomferreiragular;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;

import com.twicecircled.spritebatcher.Drawer;
import com.twicecircled.spritebatcher.SpriteBatcher;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Elikson Bastos on 05/05/2017.
 */

public class RunActivity extends Activity implements Drawer{

    CopyOnWriteArrayList<ObjectGame> objectGames = new CopyOnWriteArrayList<>();
    int[] aux = new int[]{0,100,195,295,390};
    int[] auxImgStones = new int[]{R.drawable.ic_stone1,R.drawable.ic_stone2,R.drawable.ic_stone2,R.drawable.ic_stone3,R.drawable.ic_stone3,R.drawable.ic_stone4,R.drawable.ic_stone4,R.drawable.ic_stone4,R.drawable.ic_stone4};
    GLSurfaceView surfaceView;
    private int viewWidth, viewHeight;
    private int timeGenerateStone = 0, timeGenerateGun = 0, timeDeadStone = 0, timeGenerateSpace = 0, distance = 0, auxDistance = 0, nPontos = 0, idNave, idLaser, hLaser, wLaser;
    private boolean actionTouch, disabledShield = false;
    private int difficultyGenerate = 0, difficulteMove = 0;
    private int timeShield = 0;

    static final int NAVE_ONE = R.drawable.ic_nave_one, NAVE_TWO = R.drawable.ic_nave_two, NAVE_THREE = R.drawable.ic_nave_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getValuesNaveAndLaser();
        declare();

        surfaceView = new GLSurfaceView(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(surfaceView);

        int[] resourceIds = new int[objectGames.size()];

        int indexId = 0;
        for (ObjectGame objectGame : objectGames) {
            resourceIds[indexId] = objectGame.getSource_id();
            indexId++;
        }

        surfaceView.setRenderer(new SpriteBatcher(this, resourceIds,this));
    }

    public void declare(){
        //Declarar meus objectGames x, y, width, height, tag_name
        objectGames.add(new ObjectGame(this, R.drawable.ic_space1, 0, -Cts.SCREEN_HEIGHT, Cts.SCREEN_WIDTH, Cts.SCREEN_HEIGHT, "space1"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_stone1, 0, 0, 0, 0, "auxImg"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_stone2, 0, 0, 0, 0, "auxImg"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_stone3, 0, 0, 0, 0, "auxImg"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_stone4, 0, 0, 0, 0, "auxImg"));
        objectGames.add(new ObjectGame(this, idNave, (Cts.SCREEN_WIDTH/2)-40, 650, 80, 80, "nave"));
        objectGames.add(new ObjectGame(this, idLaser, 0, 0, 0, 0, "auxImgGun"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_base_om, 0, 0, Cts.SCREEN_WIDTH, 60, "base1"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_pause, (Cts.SCREEN_WIDTH/2)-35, 30, 70, 10, "pause"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_base_espacial, 35, -300, Cts.SCREEN_WIDTH-70, 300, "station"));

        //Font, x, y, size, text, tag
        objectGames.add(new ObjectGame(this, R.string.supersonic, 75, 25, 1, "0 km", "text_distance"));
        objectGames.add(new ObjectGame(this, R.string.supersonic, Cts.SCREEN_WIDTH-80, 25, 1, "0 xp", "text_points"));

        objectGames.add(new ObjectGame(this, NAVE_ONE, (Cts.SCREEN_WIDTH/2)-40, 650, 0, 0, "nave"));
        objectGames.add(new ObjectGame(this, NAVE_TWO, (Cts.SCREEN_WIDTH/2)-40, 650, 0, 0, "nave"));
        objectGames.add(new ObjectGame(this, NAVE_THREE, (Cts.SCREEN_WIDTH/2)-40, 650, 0, 0, "nave"));
        //
    }

    public void drawing(){

        generateStones();
        removeStones();
        moveStones();

        generateGun();
        moveGuns();
        removeGuns();

        generateSpace();
        moveSpace();
        removeSpace();

        naveColision();

        generateDistance();

        if(disabledShield){
            timeShield++;
            if(timeShield >= 20){
                if(objectGames.get(5).getSource_id() == R.drawable.ic_no_so || objectGames.get(5).getSource_id() == R.drawable.ic_no_st || objectGames.get(5).getSource_id() == R.drawable.ic_no_sth){
                    objectGames.get(5).setSource_id(R.drawable.ic_nave_one);
                }else if(objectGames.get(5).getSource_id() == R.drawable.ic_nt_so || objectGames.get(5).getSource_id() == R.drawable.ic_nt_st || objectGames.get(5).getSource_id() == R.drawable.ic_nt_sth){
                    objectGames.get(5).setSource_id(R.drawable.ic_nave_two);
                }else if(objectGames.get(5).getSource_id() == R.drawable.ic_nth_so || objectGames.get(5).getSource_id() == R.drawable.ic_nth_st || objectGames.get(5).getSource_id() == R.drawable.ic_nth_sth){
                    objectGames.get(5).setSource_id(R.drawable.ic_nave_three);
                }
                disabledShield = false;
            }
        }

        if(objectGames.get(5).isMoveToLeft() && objectGames.get(5).getX() >= 0){
            objectGames.get(5).moveByX(-7);
        }
        if(objectGames.get(5).isMoveToRight() && objectGames.get(5).getX() <= Cts.SCREEN_WIDTH-objectGames.get(5).getWidth()){
            objectGames.get(5).moveByX(7);
        }
    }

    public void touching(float x, float y){
        if(actionTouch){
            if(x < (Cts.SCREEN_WIDTH/2)){
                objectGames.get(5).setMoveToLeft(true);
                objectGames.get(5).setMoveToRight(false);
            }
        }else {
            objectGames.get(5).setMoveToLeft(false);
        }

        if(actionTouch){
            if(x > (Cts.SCREEN_WIDTH/2)){
                objectGames.get(5).setMoveToLeft(false);
                objectGames.get(5).setMoveToRight(true);
            }
        }else {
            objectGames.get(5).setMoveToRight(false);

        }
    }

    public void naveColision(){
        for(ObjectGame ob : objectGames){
            if(ob.getTag().contains("stone") && colision(ob,objectGames.get(5).getX()+50,objectGames.get(5).getY())){
                if(objectGames.get(5).getSource_id() == R.drawable.ic_nave_one || objectGames.get(5).getSource_id() == R.drawable.ic_nave_two || objectGames.get(5).getSource_id() == R.drawable.ic_nave_three){
                    Log.i(Cts.TAG, "BATEU CARA!");
                    vibrar();
                    finish();
                }else{
                    disabledShield = true;
                }
            }
        }
    }

    public void moveSpace(){
        for (ObjectGame ob : objectGames) {
            if(ob.getTag().contains("space")){
                ob.moveByY(3);
            }
        }
    }

    public void generateSpace(){
        timeGenerateSpace++;
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        if(timeGenerateSpace == 250){
            aux.add(new ObjectGame(this, R.drawable.ic_space1, 0, -Cts.SCREEN_HEIGHT, Cts.SCREEN_WIDTH, Cts.SCREEN_HEIGHT, "space"));
            timeGenerateSpace = 0;
        }
        objectGames.addAll(aux);
    }

    public void removeSpace(){
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        for(int i = 1; i < objectGames.size(); i++){
            ObjectGame ob = objectGames.get(i);
            if(ob.getTag().contains("space") && ob.getY() > Cts.SCREEN_HEIGHT){
                aux.add(ob);
                Log.i(Cts.TAG, "Removeu bg espaço");
            }
        }
        objectGames.removeAll(aux);
    }

    public void moveGuns(){
        for (ObjectGame ob : objectGames) {
            if(ob.getTag().contains("gun")){
                ob.moveByY(-10);
            }
        }
    }

    public void generateGun(){
        timeGenerateGun++;
        if(timeGenerateGun == 20){
            objectGames.add(new ObjectGame(this, idLaser, objectGames.get(5).getX()+35, objectGames.get(5).getY(), wLaser, hLaser, "gun"));
            timeGenerateGun = 0;
        }
    }

    public void removeGuns(){
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        for(int i = 0; i < objectGames.size(); i++){
            ObjectGame ob = objectGames.get(i);
            if(ob.getTag().contains("gun") && ob.getY() < 0){
                aux.add(ob);
            }
        }
        objectGames.removeAll(aux);
    }

    public void moveStones(){
        for (ObjectGame ob : objectGames) {
            if(ob.getTag().contains("stone")){
                ob.moveByY(6+difficulteMove);
            }
        }
    }

    public void generateStones(){
        CopyOnWriteArrayList<Integer> aux = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Integer> auxImgs = new CopyOnWriteArrayList<>();
        for(int i : this.aux){
            aux.add(i);
        }
        for(int i : this.auxImgStones){
            auxImgs.add(i);
        }
        Collections.shuffle(aux);
        Collections.shuffle(auxImgs);
        timeGenerateStone++;
        if(timeGenerateStone == 50-difficultyGenerate){
            objectGames.add(new ObjectGame(this, auxImgs.get(0), aux.get(0), -200, 100, 100, "stone"));
            timeGenerateStone = 0;
        }
    }

    public void removeStones(){
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<ObjectGame> aux2 = objectGames;

        //Remove just Stone and with Guns
        for(int i = 0; i < objectGames.size(); i++){
            ObjectGame ob = objectGames.get(i);
            for (int j = 2; j < objectGames.size(); j++) {
                ObjectGame bb = objectGames.get(j);
                if(((bb.getTag().contains("stone") && ob.getTag().contains("gun") && bb.getSource_id() != R.drawable.ic_stone4 && ob.getY() <= (bb.getY()+bb.getHeight()) && ob.getX() >= bb.getX() && (ob.getX() + ob.getWidth()) <= (bb.getX()+bb.getWidth())))){
                    aux.add(ob);
                    timeDeadStone++;
                    if(timeDeadStone == 2){
                        aux.add(bb);
                        generatePoints();
                        timeDeadStone = 0;
                    }
                }
            }
        }
        aux2.removeAll(aux);
        for (int i = 2; i < aux2.size(); i++) {
            ObjectGame ob = aux2.get(i);
            if(ob.getTag().contains("stone") && ob.getY() > Cts.SCREEN_HEIGHT){
                aux.add(ob);
            }
        }
        objectGames.removeAll(aux);
    }

    public void generateDistance(){
        auxDistance++;
        if(auxDistance == 10){
            distance++;
            auxDistance = 0;
            objectGames.get(10).setText(distance+" km");
        }
        if(distance >= 500){
            difficultyGenerate = 1;
            difficultyGenerate = 10;
        }else if(distance >= 1000){
            difficultyGenerate = 2;
            difficultyGenerate = 20;
        }else if(distance >= 1500){
            difficultyGenerate = 3;
            difficultyGenerate = 30;
        }else if(distance >= 2000){
            difficultyGenerate = 3;
            difficultyGenerate = 30;
        }else if(distance >= 3000){
            difficultyGenerate = 3;
            difficultyGenerate = 30;
        }
        else if(distance >= 4000){
            difficultyGenerate = 3;
            difficultyGenerate = 30;
        }
    }

    public void generatePoints(){
        nPontos++;
        objectGames.get(11).setText(nPontos+" xp");
    }

    public void updateValues(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        int oldDistance = preferences.getInt(Cts.PREF_DISTANCE,0);
        int oldPoints = preferences.getInt(Cts.PREF_BASES,0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Cts.PREF_BASES, oldPoints+nPontos);
        editor.putInt(Cts.PREF_DISTANCE, oldDistance+distance);
        editor.apply();
    }

    private void vibrar()
    {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 60;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds);
    }

    public void getValuesNaveAndLaser(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME,0);
        idNave = preferences.getInt(Cts.PREF_NAVE,R.drawable.ic_nave_one);
        idLaser = preferences.getInt(Cts.PREF_LASER, R.drawable.ic_gun_one);
        wLaser = preferences.getInt(Cts.PREF_W_LASER, 6);
        hLaser = preferences.getInt(Cts.PREF_H_LASER, 15);
    }

    public boolean colision(ObjectGame objectGame, int x, int y){
        return (x >= objectGame.getX()) && (x <= objectGame.getX() + objectGame.getWidth())
                && (y >= objectGame.getY()) && y <= (objectGame.getY() + objectGame.getHeight());
    }

    public void draw(SpriteBatcher sb){
        //Desenhando o que foi declarado
        for (ObjectGame objectGame : objectGames) {
            if(objectGame.getTag().contains("text")){
                sb.drawText(objectGame.getSource_id(), objectGame.getText(), objectGame.getX(), objectGame.getY(), objectGame.getScale());
            }else {
                sb.draw(objectGame.getSource_id(), new Rect(0, 0, objectGame.getOriginal_width(), objectGame.getOriginal_height()), objectGame.getX(), objectGame.getY(), new Rect(0, 0, objectGame.getWidth(), objectGame.getHeight()), 0, 1.0f);
            }
        }
        //
    }

    public boolean isTouch(float posx, float posy, int pos) {

        ObjectGame objectGame = objectGames.get(pos);

        return ((posx >= objectGame.getX()) && (posx <= objectGame.getX() + objectGame.getWidth())
                && (posy >= objectGame.getY()) && posy <= (objectGame.getY() + objectGame.getHeight()*2));

    }

    @Override
    public void onDrawFrame(GL10 gl10, SpriteBatcher spriteBatcher) {
        //Tamanho da tala
        viewWidth = spriteBatcher.getViewWidth();
        viewHeight = spriteBatcher.getViewHeight();

        //Calcula a escala X referente a largura da tela de exibição do jogo
        float scaleX = ((float) viewWidth * 1.0f) / (float) Cts.SCREEN_WIDTH;

        //Calcula a escala Y referente a altura da tela de exibição do jogo
        float scaleY = ((float) viewHeight * 1.0f) / (float) Cts.SCREEN_HEIGHT;

        //Redimensiona a tela de exibição de acordo com o tamanho da tela do dispositivo
        //baseado no cálculo das escalas X e Y
        gl10.glScalef(scaleX,scaleY,1.0f);


        draw(spriteBatcher);



        drawing();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.setLocation((event.getX() * Cts.SCREEN_WIDTH) / viewWidth, (event.getY() * Cts.SCREEN_HEIGHT) / viewHeight);

        if(event.getAction() == MotionEvent.ACTION_UP){
            actionTouch = false;
        }else if(event.getAction() == MotionEvent.ACTION_DOWN){
            actionTouch = true;
        }

        float auxX = event.getX();
        float auxY = event.getY();
        touching(auxX, auxY);

        return true;
    }

    @Override
    protected void onPause() {
        updateValues();
        super.onPause();
    }
}
