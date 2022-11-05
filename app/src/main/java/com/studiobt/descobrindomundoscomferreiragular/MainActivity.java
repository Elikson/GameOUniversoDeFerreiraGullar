package com.studiobt.descobrindomundoscomferreiragular;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import com.twicecircled.spritebatcher.Drawer;
import com.twicecircled.spritebatcher.SpriteBatcher;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity implements Drawer {

    CopyOnWriteArrayList<ObjectGame> objectGames = new CopyOnWriteArrayList<>();
    GLSurfaceView surfaceView;
    private int viewWidth, viewHeight, timeGenerateSpace = 350, munition = 0, nPontos = 0, distance = 0, delayUpdate = 0;
    private boolean actionTouch;
    public MediaPlayer mp = null;

    ManagerObras managerObras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getValues();
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

        //trapaca();
        welcome();
        verifyChoicedSong();

        addObras();
    }

    public void welcome(){

        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        boolean welcome = preferences.getBoolean(Cts.PREF_WELCOME, true);

        if(welcome){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bem vindo ao jogo 'O Universo de Enzimático'!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Já entendi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(Cts.PREF_WELCOME, false);
                    editor.apply();
                }
            });
            builder.show();
        }
    }

    @Override
    protected void onDestroy() {
        if(mp != null){
            mp.stop();
        }
        super.onDestroy();
    }

    public void declare(){
        //Declarar meus objectGames x, y, width, height, tag_name
        objectGames.add(new ObjectGame(this, R.drawable.ic_space2, 0, 0, Cts.SCREEN_WIDTH, Cts.SCREEN_HEIGHT, "bg_space"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_panel_primary, 0, 0, Cts.SCREEN_WIDTH, Cts.SCREEN_HEIGHT, "bg_panel"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_iniciar, 150, 590, 260, 60, "iniciar"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_obter_municao, 150, 670, 260, 60, "obter_municao"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_sound_on, Cts.SCREEN_WIDTH-85, 0, 85, 80, "sound_on"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_sound_off, 0, 0, 0, 0, "auxImg"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_loja, 50, 630, 75, 65, "market"));

        //Font, x, y, size, text, tag
        objectGames.add(new ObjectGame(this, R.string.supersonic, Cts.SCREEN_WIDTH/2, 260, 1, "Desbravado: "+distance+" km", "text_distance"));
        objectGames.add(new ObjectGame(this, R.string.supersonic, Cts.SCREEN_WIDTH/2, 330, 1, "Energias: "+munition+" es", "text_distance"));
        objectGames.add(new ObjectGame(this, R.string.supersonic, Cts.SCREEN_WIDTH/2, 400, 1, "Pontos: "+ nPontos+" xp", "text_distance"));
        //
/*
        objectGames.add(new ObjectGame(this, R.drawable.ic_iniciar, 15, 190, 30, 30, "shit"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_iniciar, 15, 500, 30, 30, "shit"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_iniciar, 15, 190, 30, 30, "shit"));
        objectGames.add(new ObjectGame(this, R.drawable.ic_iniciar, 15, 190, 30, 30, "shit"));
        */
    }

    public void drawing(){
        updateValues();

        generateSpace();
        moveSpace();
        removeSpace();
    }

    public void touching(float x, float y){
        if(!actionTouch){
            if(isTouch(x,y,2)){
                Intent intent = new Intent(this, RunActivity.class);
                startActivity(intent);
            }
            if(isTouch(x,y,3)){
//                Intent intent = new Intent(this, ObrasActivity.class);
//                startActivity(intent);

                Intent intent = new Intent(this, ReadActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
            }

            if(isTouch(x,y,6)){
                Intent intent = new Intent(this, MarketActivity.class);
                startActivity(intent);
            }
            if(isTouch(x,y,4)){
                choiceSong();
            }
        }

    }

    public void trapace(){
        SharedPreferences preferences = getSharedPreferences(Cts.PREF_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Cts.PREF_MUNITION, 500);
        editor.apply();
    }

    public void addObras(){
        managerObras = new ManagerObras();
        managerObras.removeAll();

        ArrayList<Question> questions;

        questions = new ArrayList<>();
        questions.add(new Question("A proteína é a principal constituição das reações enzimáticas?", true));
        questions.add(new Question("A fibra é a principal constituição das reações enzimáticas?", false));
        questions.add(new Question("O Carboidrato é a principal constituição das reações enzimáticas?", false));
        questions.add(new Question("O neurotransmissor é à região da enzima capaz de interagir com o Substrato?", true));
        questions.add(new Question("O sítio ativo é à região da enzima capaz de interagir com o Substrato?", false));
        questions.add(new Question("Pepsina é uma enzima?", false));
        questions.add(new Question("Galactose é uma enzima?", true));
        questions.add(new Question("As enzimas atuam aumentando a velocidade de uma reação?", true));
        questions.add(new Question("As enzimas atuam apenas degradando substâncias?", false));
        questions.add(new Question("As enzimas atuam retardando a velocidade de uma reação?", false));
        questions.add(new Question("As enzimas biocatalizadoras de indução de reações químicas reconhecem seus substratos através da forma tridimensional das moléculas?", true));
        questions.add(new Question("As enzimas biocatalizadoras de indução de reações químicas reconhecem seus substratos através da concentração de minerais?", false));
        questions.add(new Question("As enzimas não atuam como catalisadores. Geralmente, elas são lipídeos, embora algumas moléculas de DNA também atuem como enzimas?", false));
        questions.add(new Question("As enzimas diminuem a energia de ativação de uma reação,que é a quantidade de energia necessária para que uma reação ocorra. Elas realizam isso ligando-se a um substrato e segurando-o de forma a permitir que a reação aconteça de maneira mais eficiente?", true));

        managerObras.addObra("Reações enzimáticas", "•Composição da enzima\n" +
                "A maioria das enzimas, são proteínas, sendo formadas, portanto, por aminoácidos. A composição de aminoácidos dessas biomoléculas define a estrutura tridimensional que ela irá adquirir.\n" +
                "\n" +
                "•Meio de interação \n" +
                "Para catalizar uma reação, uma enzima irá pegar (se ligar) uma ou mais moléculas de reagentes. Essas moléculas são os substratos das enzimas.\n" +
                "\n" +
                "Em algumas reações, um substrato é quebrado em vários produtos. Em outras, dois substratos se unem para formar uma molécula maior ou trocar pedaços. De fato, em qualquer tipo de reação biológica que você possa pensar, há provavelmente uma enzima para acelerá-la!\n" +
                "\n" +
                "A parte da enzima em que o substrato se conecta é chamado de sítio de ativação (uma vez que é aí onde ocorre a \"ação\" catalítica).\n" +
                "•Exemplicação\n" +
                "Catalase: decompõe o peróxido de hidrogênio;\n" +
                "DNA polimerase ou Transcriptase Reversa: catalisa a duplicação do DNA;\n" +
                "Lactase: facilita a hidrólise da lactose;\n" +
                "Lipase: facilita a digestão de lipídios;\n" +
                "Protease: atuam sobre as proteínas;\n" +
                "Urease: facilita a degradação da ureia;\n" +
                "\n" +
                "•Função \n" +
                "As enzimas aceleram as reações de forma seletiva, sendo, portanto, catalisadores muito específicos. As enzimas são capazes de acelerar uma reação mediante a diminuição da energia de ativação, ou seja, elas reduzem a quantidade de energia que deve ser adicionada para que uma reação tenha início.\n" +
                "\n" +
                "•Reconhecimento de substratos\n" +
                "A parte da enzima em que o substrato se conecta é chamado de sítio de ativação (uma vez que é aí onde ocorre a \"ação\" catalítica). O substrato entra no sítio de ativação da enzima, formando o complexo enzima-substrato. A reação então ocorre, convertendo o substrato em produtos e formando um complexo produtos e enzima\n", questions);

    }

    public void moveSpace(){
        for (ObjectGame ob : objectGames) {
            if(ob.getTag().contains("space")){
                ob.moveByX(1);
            }
        }
    }

    public void generateSpace(){
        timeGenerateSpace++;
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        if(timeGenerateSpace == 400){
            aux.add(new ObjectGame(this, R.drawable.ic_space2, -Cts.SCREEN_WIDTH, 0, Cts.SCREEN_WIDTH, Cts.SCREEN_HEIGHT, "space"));
            timeGenerateSpace = 0;
        }
        objectGames.addAll(aux);
    }

    public void removeSpace(){
        CopyOnWriteArrayList<ObjectGame> aux = new CopyOnWriteArrayList<>();
        for(int i = 1; i < objectGames.size(); i++){
            ObjectGame ob = objectGames.get(i);
            if(ob.getTag().contains("space") && ob.getX() >= Cts.SCREEN_WIDTH){
                aux.add(ob);
            }
        }
        objectGames.removeAll(aux);
    }

    public void updateValues(){
        delayUpdate++;
        if(delayUpdate == 50){
            SharedPreferences sharedPreferences = getSharedPreferences(Cts.PREF_NAME, 0);
            munition = sharedPreferences.getInt(Cts.PREF_MUNITION, 0);
            nPontos = sharedPreferences.getInt(Cts.PREF_BASES, 0);
            distance = sharedPreferences.getInt(Cts.PREF_DISTANCE, 0);

            objectGames.get(7).setText("Desbravado: "+distance+" km");
            objectGames.get(8).setText("Energias: "+munition+" es");
            objectGames.get(9).setText("Pontos: "+ nPontos+" xp");
            delayUpdate = 0;
        }
    }

    public void getValues(){
        SharedPreferences sharedPreferences = getSharedPreferences(Cts.PREF_NAME, 0);
        munition = sharedPreferences.getInt(Cts.PREF_MUNITION, 0);
        nPontos = sharedPreferences.getInt(Cts.PREF_BASES, 0);
        distance = sharedPreferences.getInt(Cts.PREF_DISTANCE, 0);
    }

    public void choiceSong(){
        SharedPreferences pf = getSharedPreferences(Cts.PREF_NAME,0);
        SharedPreferences.Editor editor = pf.edit();
        boolean ss = pf.getBoolean("sing_song",true);
        if(ss){
            editor.putBoolean("sing_song",false);

        }else{
            editor.putBoolean("sing_song",true);
        }
        editor.apply();
        verifyChoicedSong();
    }

    public void verifyChoicedSong(){
        SharedPreferences pf = getSharedPreferences(Cts.PREF_NAME,0);
        boolean ss = pf.getBoolean("sing_song",true);
        if(ss){
            objectGames.get(4).setSource_id(R.drawable.ic_sound_on);
            mp = MediaPlayer.create(this, R.raw.elecktronomia);
            mp.setLooping(true);
            mp.start();
        }else{
            objectGames.get(4).setSource_id(R.drawable.ic_sound_off);
            try{
                mp.stop();
            }catch (Exception e){

            }
        }
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
                && (posy >= objectGame.getY()+(objectGame.getHeight()/3)) && (posy <= (objectGame.getY() + objectGame.getHeight()+(objectGame.getHeight()/2))));

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
}
