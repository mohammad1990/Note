package com.android.tofi.mohammad.mohammadtofinote.com.note.Adapter;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.util.SortedList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.R;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemOnClick;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemTouchHelperAdapter;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemTouchHelperViewHolder;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.OnStartDragListener;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.R.attr.filter;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hamzaK on 9.5.2016.
 */
public class AdapterBox extends RecyclerView.Adapter<AdapterBox.NoteViewHolder> implements ItemTouchHelperAdapter {
    //List<Note> notes = new ArrayList<>();
    private ItemOnClick mItemOnClick;
    public SortedList<Note> mNote;
    //public ArrayList<Note>  filterList = new ArrayList<>();
    Context mContext;

    public AdapterBox(Context context, ItemOnClick ItemOnClick) {

        SharedPreferences prefs = context.getSharedPreferences("sortPre", MODE_PRIVATE);
        String restoredText = prefs.getString("sortValue", null);
        if (restoredText != null) {
            SortedList(restoredText);
        }
        mItemOnClick = ItemOnClick;
        mContext = context;
        if (Utility.getNotes(context) != null) {
            mNote.addAll(Utility.getNotes(context));
            // this.filterList.addAll(Utility.getNotes(context));
        }

    }

    public void filter(List<Note> mNote) {
        this.mNote.clear();
        this.mNote.addAll(mNote);
        notifyDataSetChanged();
    }

    private void SortedList(final String finalSort) {
        mNote = new SortedList<Note>(Note.class, new SortedList.Callback<Note>() {

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);

            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);

            }

            @Override
            public int compare(Note o1, Note o2) {
                switch (finalSort) {
                    case "Alpha": {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                    case "Date": {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                    default:
                        return -1;
                }
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);

            }

            @Override
            public boolean areContentsTheSame(Note oldItem, Note newItem) {
                switch (finalSort) {
                    case "Alpha": {
                        return oldItem.getTitle().equals(newItem.getTitle());
                    }
                    case "Date": {
                        return oldItem.getDate().equals(newItem.getDate());
                    }
                    default:
                        return false;
                }
            }

            @Override
            public boolean areItemsTheSame(Note item1, Note item2) {
                return item1.getId() == item2.getId();
            }
        });
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_detail, parent, false);
        NoteViewHolder nvh = new NoteViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int i) {
        Note note = mNote.get(i);
        holder.noteTitle.setText(note.getTitle());
        holder.noteDate.setText(Utility.convertDToS(note.getDate()));
        holder.noteContain.setText(note.getNoteContain());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemOnClick.onClickItem(mNote.get(i));
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
        return mNote.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //  Collections.swap(mNote, fromPosition, toPosition);
        //notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public void setMyList() {
        mNote.addAll(Utility.getNotes(mContext)); // reload the items from database
    }

    @Override
    public void onItemDismiss(int position) {
        Utility.deleteNote(mContext, mNote.get(position));
        mNote.removeItemAt(position);
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


