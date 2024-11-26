package ch.walica.temp121124_4tp_2_sqlite;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;

import ch.walica.temp121124_4tp_2_sqlite.db_helper.DatabaseHelper;
import ch.walica.temp121124_4tp_2_sqlite.model.Note;


public class UpdateFragment extends Fragment {

    private EditText etEditTitle, etEditDescription;
    private Button btnEdit;
    MaterialToolbar materialToolbar;
    private int id;
    private String title ="";
    private String description="";
    private long createDate;
    private DatabaseHelper databaseHelper;



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
        etEditDescription = view.findViewById(R.id.etEditDescription);
        btnEdit= view.findViewById(R.id.btnEdit);
        materialToolbar = view.findViewById(R.id.topAppBar3);
        databaseHelper = new DatabaseHelper(requireContext());

        materialToolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();
        });

        if(getArguments() != null) {
            id = getArguments().getInt("id");
            Log.d("my_log", "id = " + id);
            setValueInEditText();
        }

        btnEdit.setOnClickListener(v -> {
            title = etEditTitle.getText().toString().trim();
            description = etEditDescription.getText().toString().trim();
            if(!title.isEmpty() && !description.isEmpty()) {
                databaseHelper.updateNote(new Note(id, title, description, createDate));
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();
            }
        });


    }

    private void setValueInEditText() {
        Cursor cursor = databaseHelper.getNoteById(id);
        Log.d("my_log", "cursor: " + cursor);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            title = cursor.getString(1);
            description = cursor.getString(2);
            createDate = cursor.getLong(3);
            etEditTitle.setText(title);
            etEditDescription.setText(description);
        }
    }
}