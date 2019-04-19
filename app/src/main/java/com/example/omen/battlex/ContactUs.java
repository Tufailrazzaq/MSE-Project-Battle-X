package com.example.omen.battlex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends Fragment {

    private EditText nameTextBox, emailTextBox, contactTextBox, messageTextBox;
    private DatabaseReference databaseReference;
    private Button sendMessageBtn;
    private TextInputLayout nameLayout, emailLayout, contactLayout, messageLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        nameTextBox = (EditText) getActivity().findViewById(R.id.contact_us_nameTextbox);
        emailTextBox = (EditText) getActivity().findViewById(R.id.contact_us_emailTextbox);
        contactTextBox = (EditText) getActivity().findViewById(R.id.contact_us_contactTextbox);
        messageTextBox = (EditText) getActivity().findViewById(R.id.contact_us_messageTextbox);
        sendMessageBtn = (Button) getActivity().findViewById(R.id.contact_us_btn);
        nameLayout = (TextInputLayout) getActivity().findViewById(R.id.name_textbox_layout_contact);
        emailLayout = (TextInputLayout) getActivity().findViewById(R.id.email_textbox_layout_contact);
        contactLayout = (TextInputLayout) getActivity().findViewById(R.id.message_textbox_layout_contact);
        messageLayout = (TextInputLayout) getActivity().findViewById(R.id.contact_textbox_layout_contact);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageOnClick();
            }
        });

    }

    public void sendMessageOnClick(){

        if(nameTextBox.getText().toString().isEmpty()){
            nameLayout.setError("This field can't be empty");
        }
        else {
            nameLayout.setError(null);
        }
        if(emailTextBox.getText().toString().isEmpty()){
            emailLayout.setError("This field can't be empty");
        }
        else {
            emailLayout.setError(null);
        }
        if(contactTextBox.getText().toString().isEmpty()){
            contactLayout.setError("This field can't be empty");
        }
        else {
            contactLayout.setError(null);
        }
        if(messageTextBox.getText().toString().isEmpty()){
            messageLayout.setError("This field can't be empty");
        }
        else {
            messageLayout.setError(null);
        }
        if(nameTextBox.getText().toString().isEmpty() | emailTextBox.getText().toString().isEmpty() | contactTextBox.getText().toString().isEmpty() | messageTextBox.getText().toString().isEmpty()){
            return;
        }
        else {
            nameLayout.setError(null);
            emailLayout.setError(null);
            contactLayout.setError(null);
            messageLayout.setError(null);
        }



        databaseReference = FirebaseDatabase.getInstance().getReference("Messages");
        String key = databaseReference.push().getKey();

        ContactUsMessage contactUsMessage = new ContactUsMessage(key, nameTextBox.getText().toString(), emailTextBox.getText().toString(), contactTextBox.getText().toString(), messageTextBox.getText().toString());

        databaseReference.child(key).setValue(contactUsMessage);

        Snackbar.make(getView(),"Message Sent Successfully, we'll contact you soon", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }
}
