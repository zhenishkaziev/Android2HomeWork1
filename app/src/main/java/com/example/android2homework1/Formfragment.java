package com.example.android2homework1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;


public class Formfragment extends Fragment {

    private EditText etLogin, etPassword;
    private  Button bntSave;
    private TaskModel model;
    private int position;
    private String resKey = "form";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view =  inflater.inflate(R.layout.fragment_formfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        //initClick (view);
    }

    private void initView(View view) {
        etLogin = view.findViewById(R.id.form_login);
        etPassword = view.findViewById(R.id.form_password);
        bntSave = view.findViewById(R.id.bnt_save);
//        if (getArguments() != null){
//            TaskModel model = (TaskModel) getArguments().getSerializable("task");
//            position = getArguments().getInt("position");
//            assert model != null;
//            etLogin.setText(model.getTitle());
//            etPassword.setText(model.getDescription());
//            resKey = "formRed";
//        }
        String titlee = etLogin.getText().toString();
        Log.e("fff", "hhhhh" );
        String pasword = etPassword.getText().toString();
        bntSave.setOnClickListener(v -> {
            model = new TaskModel(titlee, pasword);
            Bundle bundle = new Bundle();
            bundle.putSerializable("task",model);
            bundle.putInt("position",position);
            getParentFragmentManager().setFragmentResult(resKey, bundle);
//            getParentFragmentManager().setFragmentResult(resKey,bundle);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            //navController.navigateUp();
            navController.popBackStack();
            Log.e("fff",   model.getTitle());

        });

    }
    private void initClick (View view) {


    }

}