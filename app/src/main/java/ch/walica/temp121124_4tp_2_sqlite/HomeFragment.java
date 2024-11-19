package ch.walica.temp121124_4tp_2_sqlite;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ch.walica.temp121124_4tp_2_sqlite.adapter.NoteAdapter;
import ch.walica.temp121124_4tp_2_sqlite.db_helper.DatabaseHelper;
import ch.walica.temp121124_4tp_2_sqlite.model.Note;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView tvMsg;
    private FloatingActionButton floatingActionButton;
    private DatabaseHelper databaseHelper;
    private List<Note> notes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteAdapter = new NoteAdapter(notes, (position, view) -> {
            if(view.getId() == R.id.ivDelete) {
                //usuwanie elementu
                databaseHelper.deleteNote(position);
                notes.remove(position);
                noteAdapter.notifyItemRemoved(position);
                noteAdapter.notifyItemRangeChanged(position, notes.size());
            } else if(view.getId() == R.id.ivEdit) {
                //edycja
                UpdateFragment updateFragment = new UpdateFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", notes.get(position).getId());
                updateFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, updateFragment).commit();
            }
        });
    }

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

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(noteAdapter);

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