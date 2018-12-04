package com.example.laptracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFragment extends Fragment {

    private TextView textViewinstructions;
    private EditText editTextReps;
    private EditText editTextDistance;
    private EditText editTextRounds;
    private Button buttonAddPart;
    private Button buttonSubmit;

    private Set set;
    private Workout w;

    public static final String EXTRA_WORKOUT = "the workout";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflate our view (make java objects from the xml)
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        MainActivity.set = new Set();
        MainActivity.w = new Workout();

        //wire widgets & set listeners
        //the rootView has the findViewById in it
        textViewinstructions = rootView.findViewById(R.id.textView_add_instructions);
        editTextReps = rootView.findViewById(R.id.editText_add_reps);
        editTextDistance = rootView.findViewById(R.id.editText_add_distance);
        editTextRounds = rootView.findViewById(R.id.editText_add_rounds);
        buttonAddPart = rootView.findViewById(R.id.button_add_addPart);
        buttonSubmit = rootView.findViewById(R.id.button_add_submit);

        textViewinstructions.setText("Please enter each part of the set, pressing \"Add Part\" after each input. \n After adding the last part, enter the number of rounds and then press \"Submit Set\".");

        buttonAddPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int repsLength = editTextReps.getText().length();
                int distanceLength = editTextDistance.getText().length();

                if (repsLength > 0 && distanceLength > 0) {
                    int reps = Integer.parseInt(editTextReps.getText().toString());
                    int distance = Integer.parseInt(editTextDistance.getText().toString());
                    MainActivity.set.addPart(reps, distance);
                    Toast.makeText(getActivity(), "Part added successfully.", Toast.LENGTH_SHORT).show();
                    editTextReps.setText("");
                    editTextDistance.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rounds = 1;

                String input = editTextRounds.getText().toString();

                if (!(input.equals(""))) {
                    rounds = Integer.parseInt(input);
                }

                if (rounds > 1) {
                    MainActivity.set.addRounds(rounds);
                }

                if (!(MainActivity.set.equals(null))) {
                    MainActivity.w.addSet(MainActivity.set);
                    Toast.makeText(getActivity(), "Set added successfully.", Toast.LENGTH_SHORT).show();
                    editTextRounds.setText("");
                    MainActivity.set = new Set();
                }
                else {
                    Toast.makeText(getActivity(), "Please enter at least one part.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return rootView;
    }

}
