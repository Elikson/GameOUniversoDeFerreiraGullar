package com.studiobt.descobrindomundoscomferreiragular;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Elikson Bastos on 05/05/2017.
 */

public class ObjectGame {
    private int width;
    private int height;
    private int source_id;
    private int original_width;
    private int original_height;
    private int x;
    private int y;
    private int yOrigem;
    private int yOldOrigem;

    private float scale;

    private String tag;
    private String text;

    private Context context;

    private boolean moveToUp = false;
    private boolean moveToDown = false;
    private boolean moveToRight = false;
    private boolean moveToLeft = false;
    private boolean move = false;

    public ObjectGame(Context context, int source_id, int x, int y, int width, int height, String tag){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.source_id = source_id;
        this.context = context;
        this.tag = tag;

        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;
        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), source_id, dimension);
        original_width = dimension.outWidth;
        original_height =  dimension.outHeight;
    }

    public ObjectGame(Context context, int source_id, int x, int y, float scale, String text, String tag){
        this.x = x;
        this.y = y;
        this.source_id = source_id;
        this.context = context;
        this.tag = tag;
        this.text = text;
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getyOrigem() {
        return yOrigem;
    }

    public int getyOldOrigem() {
        return yOldOrigem;
    }

    public void setyOldOrigem(int yOldOrigem) {
        this.yOldOrigem = yOldOrigem;
    }

    public void setyOrigem(int yOrigem) {
        this.yOrigem = yOrigem;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isMoveToUp() {
        return moveToUp;
    }

    public void setMoveToUp(boolean moveToUp) {
        this.moveToUp = moveToUp;
    }

    public boolean isMoveToDown() {
        return moveToDown;
    }

    public void setMoveToDown(boolean moveToDown) {
        this.moveToDown = moveToDown;
    }

    public boolean isMoveToRight() {
        return moveToRight;
    }

    public void setMoveToRight(boolean moveToRight) {
        this.moveToRight = moveToRight;
    }

    public boolean isMoveToLeft() {
        return moveToLeft;
    }

    public void setMoveToLeft(boolean moveToLeft) {
        this.moveToLeft = moveToLeft;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveByX(int x){
        this.x += x;
    }

    public void moveByY(int y){
        this.y += y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getOriginal_width() {
        return original_width;
    }

    public void setOriginal_width(int original_width) {
        this.original_width = original_width;
    }

    public int getOriginal_height() {
        return original_height;
    }

    public void setOriginal_height(int original_height) {
        this.original_height = original_height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
