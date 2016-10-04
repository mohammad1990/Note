package com.android.tofi.mohammad.mohammadtofinote.com.note.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tofi.mohammad.mohammadtofinote.R;
import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.entity.Note;

import java.util.List;

/**
 * Created by hamzaK on 9.5.2016.
 */
public class AdapterBox extends RecyclerView.Adapter<AdapterBox.NoteViewHolder> {
    List<Note> notes;

    private OnItemTouchListener onItemTouchListener;

    public AdapterBox(List<Note> notes,OnItemTouchListener onItemTouchListener) {
        this.notes = notes;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_detail, parent, false);
        NoteViewHolder nvh = new NoteViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int i) {
        holder.noteTitle.setText(notes.get(i).getTitle());
        holder.noteDate.setText(Utility.convertDToS(notes.get(i).getDate()));
        holder.noteContain.setText(notes.get(i).getNoteContain());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
// implements View.OnClickListener
    public  class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView noteTitle;
        TextView noteDate;
        TextView noteContain;

        NoteViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            noteTitle = (TextView) itemView.findViewById(R.id.note_title);
            noteContain = (TextView) itemView.findViewById(R.id.note_contain);
            noteDate = (TextView) itemView.findViewById(R.id.note_date);
            /*itemView.setOnClickListener(this);*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                }
            });
        }


    }
 /*   public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }*/
 /*   public interface MyClickListener {
        public void onItemClick(int position, View v);
    }*/
 /*   public void addItem(DataObject dataObj, int index) {
      //  mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }*/

    public void deleteItem(int index) {
        notes.remove(index);
        notifyItemRemoved(index);
    }
    //new
    public interface OnItemTouchListener {
        /**
         * Callback invoked when the user Taps one of the RecyclerView items
         *
         * @param view     the CardView touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onCardViewTap(View view, int position);

    }
}


