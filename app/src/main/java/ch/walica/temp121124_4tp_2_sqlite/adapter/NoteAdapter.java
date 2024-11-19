package ch.walica.temp121124_4tp_2_sqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.walica.temp121124_4tp_2_sqlite.R;
import ch.walica.temp121124_4tp_2_sqlite.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnNoteClickListener onNoteClickListener;

    public NoteAdapter(List<Note> notes, OnNoteClickListener onNoteClickListener) {
        this.notes = notes;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvDate, tvTitle, tvDescription;
        private ImageView ivDelete, ivEdit;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);

            ivDelete.setOnClickListener(this);
            ivEdit.setOnClickListener(this);
        }

        public void bind(Note note) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            Date date = new Date(note.getCreateDate() * 1000);

            tvDate.setText(sdf.format(date));
            tvTitle.setText(note.getTitle());
            tvDescription.setText(note.getDescription());

        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                onNoteClickListener.onNoteClick(getAdapterPosition(), view);
            }
        }
    }
}
