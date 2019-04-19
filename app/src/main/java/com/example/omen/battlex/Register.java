package com.example.omen.battlex;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Register extends Fragment {

    private EditText teamNameTextBox;
    private Button registerTeam;
    private TextInputLayout teamNameLayout;
    private Spinner universityName;
    private Spinner competitionName;
    private ArrayList<String> competitionNameList;
    private ArrayAdapter<String> competitionListAdapater;
    private DatabaseReference databaseReference;
    private LinearLayout linearLayout;
    private DynamicForm dynamicForm;
    private ArrayList<Integer> idList;
    private ArrayList<TeamMember> teamMembersList;
    private ArrayList<TeamMember> teamMembersList2;
    private int size;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragement, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        competitionNameList = new ArrayList<>();
        dynamicForm = new DynamicForm(getActivity());
        teamNameTextBox = (EditText) getActivity().findViewById(R.id.teamName_textBox);
        registerTeam = (Button) getActivity().findViewById(R.id.registerTeamBtn);
        teamNameLayout = (TextInputLayout) getActivity().findViewById(R.id.teamName_layout);
        universityName = (Spinner) getActivity().findViewById(R.id.universityName_Spinner);
        competitionName = (Spinner) getActivity().findViewById(R.id.competitionName_Spinner);
        linearLayout = (LinearLayout) getActivity().findViewById(R.id.dynamicLinearLayout);
        teamMembersList = new ArrayList<>();
        teamMembersList2 = new ArrayList<>();

        GetCompetitionDetailsFirebase compDetails = GetCompetitionDetailsFirebase.getInstance();

        for(int i = 0; i< compDetails.getAllCompetitions().size();i++){
            competitionNameList.add(compDetails.competitionNameReturn(i));
        }

        competitionListAdapater = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,competitionNameList);
        competitionListAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        competitionName.setAdapter(competitionListAdapater);




        registerTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateRegister();
            }
        });

        competitionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                GetCompetitionDetailsFirebase compDetails = GetCompetitionDetailsFirebase.getInstance();
                linearLayout.removeAllViews();
                dynamicForm.clearIdList();
//                switch (competitionName.getSelectedItem().toString()){
//                    case "Counter-Strike":
//                        size = compDetails.getAllCompetitions().get(compDetails.competitionIndex(competitionName.getSelectedItem().toString())).getMax();
//                        break;
//                    case "PUBG Mobile":
//                        size = 3;
//                        break;
//                    case "Soap Soccer":
//                        size = 5;
//                        break;
//                    case "Speed Programming":
//                        size = 2;
//                        break;
//                    default:
//                        size = 1;
//                        break;
//                }
                size = compDetails.getAllCompetitions().get(compDetails.competitionIndex(competitionName.getSelectedItem().toString())).getMax();
                for(int i=0; i<size; i++){
                    if(i==0){
                        linearLayout.addView(dynamicForm.createtextView("Team Lead"));
                    }
                    else {
                        linearLayout.addView(dynamicForm.createtextView("Team Member " + i));
                    }

                    linearLayout.addView(dynamicForm.linearLayoutVertical());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void validateRegister(){

        if(teamNameTextBox.getText().toString().isEmpty()){
            teamNameLayout.setError("This field can't be empty");
        }
        else if(teamNameTextBox.getText().toString().length() > 15){
            teamNameLayout.setError("Team Name is too long");
        }
        else {
            teamNameLayout.setError(null);
            addRegistration();
        }
    }

    public void addRegistration(){

        teamMembersList.clear();

        idList = dynamicForm.getIdList();

        for(int i=0; i<4*size; i++){
            EditText name = (EditText) getActivity().findViewById(idList.get(i));
            EditText nic = (EditText) getActivity().findViewById(idList.get((i+1)));
            EditText contactNo = (EditText) getActivity().findViewById(idList.get((i+2)));
            EditText email = (EditText) getActivity().findViewById(idList.get((i+3)));

            if(name.getText().toString().isEmpty() || nic.getText().toString().isEmpty() || contactNo.getText().toString().isEmpty() || email.getText().toString().isEmpty()){
                Snackbar.make(getView(),"All fields of all members must be filled", Snackbar.LENGTH_LONG).show();
                return;
            }

            TeamMember member = new TeamMember(name.getText().toString(), nic.getText().toString(), contactNo.getText().toString(), email.getText().toString());
            teamMembersList.add(member);

            i=i+3;

        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Registrations");
        String key = databaseReference.push().getKey();

//        int k=0;
//        for(int i=teamMembersList.size()-size; i< teamMembersList.size() ; i++){
//            //databaseReference.child(newRegister.getUniversityName()).child(key).child("Members").child("Member " + (k+1)).setValue(teamMembersList.get(i));
//            teamMembersList2.add(teamMembersList.get(i));
//            k++;
//        }

        NewRegister newRegister = new NewRegister(key,teamNameTextBox.getText().toString(), universityName.getSelectedItem().toString(), competitionName.getSelectedItem().toString(), teamMembersList);

        databaseReference.child(newRegister.getUniversityName()).child(key).setValue(newRegister);




        Snackbar.make(getView(),"Registration Successful",Snackbar.LENGTH_LONG).show();
    }

}
