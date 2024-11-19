package ch.walica.temp121124_4tp_2_sqlite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;


public class UpdateFragment extends Fragment {

    private EditText etEditTitle, etEditDescription;
    private Button btnEdit;
    MaterialToolbar materialToolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEditTitle = view.findViewById(R.id.etEditTitle);
        etEditTitle = view.findViewById(R.id.etEditDescription);
        btnEdit= view.findViewById(R.id.btnEdit);
        materialToolbar = view.findViewById(R.id.topAppBar3);
    }
}