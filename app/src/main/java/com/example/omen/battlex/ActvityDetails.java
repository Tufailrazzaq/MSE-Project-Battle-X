package com.example.omen.battlex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActvityDetails extends AppCompatActivity {

    private TextView headerText, entryFee, minMembers, maxMembers, winPrize, runnerUpPrize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvity_details);

        Intent intent = getIntent();
        intent.getStringExtra("tag");

        headerText = (TextView) findViewById(R.id.headerText);
        entryFee = (TextView) findViewById(R.id.entryFeeText);
        minMembers = (TextView) findViewById(R.id.minMembersText);
        maxMembers = (TextView) findViewById(R.id.maxMembersText);
        winPrize = (TextView) findViewById(R.id.winPrizeText);
        runnerUpPrize = (TextView) findViewById(R.id.runnerUpText);



        Log.d("tag", intent.getStringExtra(("tag")));
        populateActivity(intent.getStringExtra("tag"));



    }

    public void populateActivity(String tag){
        if(tag.equals("counterStrike")){
            headerText.setText("Counter Strike");
            entryFee.setText("Rs. 500");
            minMembers.setText("5");
            maxMembers.setText("5");
            winPrize.setText("Rs. 20,000");
            runnerUpPrize.setText("Rs. 10,000");
        }
        else if(tag.equals("pubg")){
            headerText.setText("PUBG Mobile");
            entryFee.setText("Rs. 300");
            minMembers.setText("1");
            maxMembers.setText("4");
            winPrize.setText("Rs. 15,000");
            runnerUpPrize.setText("Rs. 7,000");
        }
        else if(tag.equals("soapSoccer")){
            headerText.setText("Soap Soccer");
            entryFee.setText("Rs. 600");
            minMembers.setText("5");
            maxMembers.setText("5");
            winPrize.setText("Rs. 10,000");
            runnerUpPrize.setText("Rs. 5,000");
        }
        else if(tag.equals("speedProgramming")){
            headerText.setText("Speed Programming");
            entryFee.setText("Rs. 300");
            minMembers.setText("2");
            maxMembers.setText("2");
            winPrize.setText("Rs. 5000");
            runnerUpPrize.setText("Rs. 2,000");
        }
        else {
            headerText.setText("Farigh");
        }

    }
}
