package ch.walica.temp121124_4tp_2_sqlite;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ch.walica.temp121124_4tp_2_sqlite.db_helper.DatabaseHelper;
import ch.walica.temp121124_4tp_2_sqlite.model.Note;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView tvMsg;
    private FloatingActionButton floatingActionButton;
    private DatabaseHelper databaseHelper;
    private List<Note> notes = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        tvMsg = view.findViewById(R.id.tvMsg);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        databaseHelper = new DatabaseHelper(requireContext());

        storeNotesInList();

        floatingActionButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new AddFragment()).commit();
        });
    }

    private void storeNotesInList() {
        Cursor cursor = databaseHelper.getAllNotes();
        if (cursor.getCount() == 0) {
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3));
                notes.add(note);
            }

            Log.d("my_log", "storeNotesInList: " + notes.size());
        }
    }
}