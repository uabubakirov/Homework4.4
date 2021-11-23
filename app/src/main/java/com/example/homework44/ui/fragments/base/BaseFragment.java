package com.example.homework44.ui.fragments.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<ViewModel extends BaseViewModel,Binding extends ViewBinding> extends Fragment {

    protected ViewModel viewModel;
    protected Binding binding;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupListeners();
        setupRequests();
        setupObservers();
    }

    protected void setupObservers() {

    }

    protected void setupRequests() {

    }

    protected void setupListeners() {

    }

    protected void initialize() {

    }
}
