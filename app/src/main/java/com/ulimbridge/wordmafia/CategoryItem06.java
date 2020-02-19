package com.ulimbridge.wordmafia;

public class CategoryItem06 {

    //    private Drawable icon ;
    private String text;
    private boolean checked;

    //    public void setIcon(Drawable icon) {
    //        this.icon = icon ;
    //    }
//    public Drawable getIcon() {
//        return this.icon ;
//    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }

    // set 이 안됨! set하려면 CategoryAdapter06의 add()의 parameter에 checked넣고 안에서 set해야 하는데 그게 안됨...
    public void setCheck(boolean checked) {
        this.checked = checked;
    }

    public boolean getCheck() {
        return this.checked;
    }
}

