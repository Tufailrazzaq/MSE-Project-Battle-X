package com.example.omen.battlex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AmbassadorListView extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference databaseReference;
    private ArrayList<String> teamNameList;
    private ArrayAdapter<String> adapter;
    private ArrayList<NewRegister> ambassadorRegistrations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador_list_view);

        listView = (ListView) findViewById(R.id.list_View);
        teamNameList = new ArrayList<>();
        ambassadorRegistrations = new ArrayList<>();
        Intent intent = getIntent();


        databaseReference = FirebaseDatabase.getInstance().getReference("Registrations").child("DHA Suffa University");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NewRegister newRegister = snapshot.getValue(NewRegister.class);
                    teamNameList.add(newRegister.getTeamName());
                    ambassadorRegistrations.add(newRegister);
                }

                //Log.d("children", dataSnapshot.getValue().toString());
                adapter = new ArrayAdapter<>(AmbassadorListView.this,android.R.layout.simple_list_item_1,teamNameList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posititon, long l) {
                Intent intent = new Intent(AmbassadorListView.this,ParticipantsDetails.class);
                intent.putExtra("competition",ambassadorRegistrations.get(posititon).getCompetitionName());
                intent.putExtra("teamName",ambassadorRegistrations.get(posititon).getTeamName());
                intent.putExtra("institute",ambassadorRegistrations.get(posititon).getUniversityName());
                for(int i=0; i< ambassadorRegistrations.get(posititon).getTeamMember().size(); i++){
                    intent.putExtra("name"+i,ambassadorRegistrations.get(posititon).getTeamMember().get(i).getName());
                    intent.putExtra("nic"+i,ambassadorRegistrations.get(posititon).getTeamMember().get(i).getNic());
                    intent.putExtra("contact"+i,ambassadorRegistrations.get(posititon).getTeamMember().get(i).getContactNo());
                    intent.putExtra("email"+i,ambassadorRegistrations.get(posititon).getTeamMember().get(i).getEmail());
                }
                intent.putExtra("memberSize", ambassadorRegistrations.get(posititon).getTeamMember().size());
                startActivity(intent);
            }
        });
    }


}
