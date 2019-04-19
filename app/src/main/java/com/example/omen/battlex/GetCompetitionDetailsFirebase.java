package com.example.omen.battlex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetCompetitionDetailsFirebase {
    private static GetCompetitionDetailsFirebase instance = null;
    private ArrayList<AllCompetitionsDetails> allCompetitions;
    private DatabaseReference databaseReference;

    private GetCompetitionDetailsFirebase() {

        getData();
    }


    public static GetCompetitionDetailsFirebase getInstance() {
        if(instance == null){
            instance = new GetCompetitionDetailsFirebase();
            return instance;
        }
        else {
            return instance;
        }

    }

    public void getData(){
        allCompetitions = new ArrayList<AllCompetitionsDetails>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Competition");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    AllCompetitionsDetails competition = snapshot.getValue(AllCompetitionsDetails.class);
                    allCompetitions.add(competition);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setInstance(GetCompetitionDetailsFirebase instance) {
        this.instance = instance;
    }

    public ArrayList<AllCompetitionsDetails> getAllCompetitions() {
        return allCompetitions;
    }

    public void setAllCompetitions(ArrayList<AllCompetitionsDetails> allCompetitions) {
        this.allCompetitions = allCompetitions;
    }

    public int competitionIndex(String compName){
        for(int i=0; i<allCompetitions.size();i++){
           if(allCompetitions.get(i).getCompetitionName().equals(compName)){
               return i;
           }
        }
        return 2;

    }

    public String competitionNameReturn(int index){
        return allCompetitions.get(index).getCompetitionName();
    }
}
