package com.example.omen.battlex;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class DynamicForm {

    private Context context;
    private ArrayList<Integer> idList;

    public DynamicForm(Context context) {
        this.context = context;
        idList = new ArrayList<>();
    }

    public ArrayList<Integer> getIdList() {
        return idList;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public LinearLayout linearLayoutVertical(){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,25,0,0);
        LinearLayout outerLinearLayout = new LinearLayout(context);
        outerLinearLayout.setLayoutParams(layoutParams);
        outerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        outerLinearLayout.setBackground(context.getResources().getDrawable(R.drawable.register_person_layout));
        outerLinearLayout.setPadding(50,50,50,50);

        outerLinearLayout.addView(linearLayoutHorizontal());
        outerLinearLayout.addView(linearLayoutHorizontal2());

        return outerLinearLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public LinearLayout linearLayoutHorizontal(){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        LinearLayout innerLinearLayout = new LinearLayout(context);
        innerLinearLayout.setLayoutParams(layoutParams);
        innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        innerLinearLayout.setPadding(50,50,50,50);

        innerLinearLayout.addView(editText("Name"));
        innerLinearLayout.addView(editText("NIC"));

        return innerLinearLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public LinearLayout linearLayoutHorizontal2(){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        LinearLayout innerLinearLayout = new LinearLayout(context);
        innerLinearLayout.setLayoutParams(layoutParams);
        innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        innerLinearLayout.setPadding(50,50,50,50);

        innerLinearLayout.addView(editText("Contact No."));
        innerLinearLayout.addView(editText("Email"));

        return innerLinearLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EditText editText(String hint){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        EditText textBox = new EditText(context);
        textBox.setLayoutParams(params);
        textBox.setHint(hint);
        textBox.setPadding(25,0,0,30);
        int id = View.generateViewId();
        idList.add(id);
        textBox.setId(id);

        return textBox;
    }

    public TextView createtextView(String member){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,100,0,0);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setText(member);
        textView.setTextColor(context.getResources().getColor(R.color.orangeTheme));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        return textView;
    }

    public TextView createtextViewLabels(String member){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(150,50,0,0);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setText(member);
        //textView.setTextColor(context.getResources().getColor(R.color.orangeTheme));
        //textView.setGravity(Gravity.CENTER_HORIZONTAL);

        return textView;
    }

    public void destructor(){
        this.context = null;
        idList = null;
    }

    public void clearIdList() {
        this.idList.clear();
    }
}
