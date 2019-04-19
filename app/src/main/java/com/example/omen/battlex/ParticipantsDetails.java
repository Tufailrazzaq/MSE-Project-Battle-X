package com.example.omen.battlex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParticipantsDetails extends AppCompatActivity {

    private TextView teamName,competition,university;
    private LinearLayout linearLayout;
    private DynamicForm dynamicForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants_details);

        teamName = (TextView) findViewById(R.id.teamName_participant);
        university = (TextView) findViewById(R.id.universityName_participant);
        competition = (TextView) findViewById(R.id.competitionName_participant);
        linearLayout = (LinearLayout) findViewById(R.id.linear_participant);
        dynamicForm = new DynamicForm(this);

        Intent intent = getIntent();

        teamName.setText(intent.getStringExtra("teamName"));
        university.setText(intent.getStringExtra("institute"));
        competition.setText(intent.getStringExtra("competition"));

        for(int i=0; i < intent.getIntExtra("memberSize",0); i++){
            if(i==0){
                linearLayout.addView(dynamicForm.createtextView("Team Lead"));
            }
            else {
                linearLayout.addView(dynamicForm.createtextView("Team Member " + i));
            }
            linearLayout.addView(dynamicForm.createtextViewLabels("Name : " + intent.getStringExtra("name"+i)));
            linearLayout.addView(dynamicForm.createtextViewLabels("NIC : " + intent.getStringExtra("nic"+i)));
            linearLayout.addView(dynamicForm.createtextViewLabels("Contact No. : " + intent.getStringExtra("contact"+i)));
            linearLayout.addView(dynamicForm.createtextViewLabels("Email : " + intent.getStringExtra("email"+i)));
        }




    }
}
