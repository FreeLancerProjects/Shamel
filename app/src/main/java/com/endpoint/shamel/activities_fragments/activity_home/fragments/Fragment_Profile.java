package com.endpoint.shamel.activities_fragments.activity_home.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.endpoint.shamel.R;
import com.endpoint.shamel.activities_fragments.activity_home.HomeActivity;
import com.endpoint.shamel.databinding.FragmentProfileBinding;
import com.endpoint.shamel.models.UserModel;
import com.endpoint.shamel.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Profile extends Fragment {
    private static Dialog dialog;
    private HomeActivity activity;
    private FragmentProfileBinding binding;
    private String lang;

    private Preferences preferences;
    private UserModel userModel;

    public static Fragment_Profile newInstance() {
        return new Fragment_Profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        initView();


        return binding.getRoot();
    }


    private void initView() {
        activity = (HomeActivity) getActivity();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(activity);
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());binding.setLang(lang);

binding.setUsermodel(userModel);
binding.setLang(lang);



    }


}
