package ch.walica.temp121124_4tp_2_sqlite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView tvMsg;
    private FloatingActionButton floatingActionButton;


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

        floatingActionButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new AddFragment()).commit();
        });
    }
}