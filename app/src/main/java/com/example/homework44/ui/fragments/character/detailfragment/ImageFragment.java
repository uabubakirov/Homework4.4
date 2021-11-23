package com.example.homework44.ui.fragments.character.detailfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.Script;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework44.R;
import com.example.homework44.databinding.FragmentImageBinding;
import com.example.homework44.ui.fragments.base.BaseFragment;
import com.example.homework44.ui.fragments.character.CharacterViewModel;


public class ImageFragment extends BaseFragment<CharacterViewModel, FragmentImageBinding> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

}