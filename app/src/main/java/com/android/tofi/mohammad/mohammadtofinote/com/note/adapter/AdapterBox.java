package com.android.tofi.mohammad.mohammadtofinote.com.note.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tofi.mohammad.mohammadtofinote.DetailActivity;
import com.android.tofi.mohammad.mohammadtofinote.MainActivity;
import com.android.tofi.mohammad.mohammadtofinote.R;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemOnClick;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemTouchHelperAdapter;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemTouchHelperViewHolder;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.OnStartDragListener;
import com.android.tofi.mohammad.mohammadtofinote.com.note.ItemTouch.RecyclerListFragment;
import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.entity.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hamzaK on 9.5.2016.
 */
public class AdapterBox extends RecyclerView.Adapter<AdapterBox.NoteViewHolder> implements ItemTouchHelperAdapter {
    List<Note> notes = new ArrayList<>();
    NoteDBHelper db;
    //private final OnStartDragListener mDragStartListener;
    private ItemOnClick mItemOnClick;

    public AdapterBox(Context context, OnStartDragListener dragStartListener, ItemOnClick ItemOnClick) {
        //     mDragStartListener = dragStartListener;
        mItemOnClick = ItemOnClick;
        db = new NoteDBHelper(context);
        this.notes = db.getAllNote();
    }


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_detail, parent, false);
        NoteViewHolder nvh = new NoteViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int i) {
        holder.noteTitle.setText(notes.get(i).getTitle());
        holder.noteDate.setText(Utility.convertDToS(notes.get(i).getDate()));
        holder.noteContain.setText(notes.get(i).getNoteContain());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemOnClick.onClickItem(notes.get(i));
            }
        });
        //// TODO: 11/8/2016
        /*holder.cv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(notes, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
    public void setMyList() {
        notes.clear();
        notes = db.getAllNote(); // reload the items from database
        notifyDataSetChanged();
    }

    @Override
    public void onItemDismiss(int position) {
        db.deleteContact(notes.get(position).getId());
        notes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

    }


    // implements View.OnClickListener
    public class NoteViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
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
        }


        @Override
        public void onItemSelected() {
            // itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            //itemView.setBackgroundColor(0);
        }
    }

}


