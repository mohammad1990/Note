package com.android.tofi.mohammad.mohammadtofinote;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.adapter.AdapterBox;
import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.entity.Note;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listNote;
    private RecyclerView.Adapter mAdapter;
    private List<Note> noteList = new ArrayList<>();
    NoteDBHelper db;
    AdapterBox.OnItemTouchListener itemTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initializeData();
        db = new NoteDBHelper(this);
        //   db.insertContact("mohammad","hi", Utility.getCurrentlyDate());

        noteList = db.getAllNote();


        itemTouchListener = new AdapterBox.OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("note", noteList.get(position));
                startActivity(i);
            }
        };

        mAdapter = new AdapterBox(noteList, itemTouchListener);
        listNote = (RecyclerView) findViewById(R.id.rv);
        listNote.setLayoutManager(new LinearLayoutManager(this));
        listNote.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(listNote,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    open(position);

                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    open(position);

                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        listNote.addOnItemTouchListener(swipeTouchListener);


        /*((AdapterBox) mAdapter).deleteItem(0);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note:
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("default", -1);
                startActivity(i);
                break;

            case R.id.about_us:
                Log.i("DEV", "MENU ABOUT CLICKED");
                break;
        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;

    }


    @Override
    protected void onResume() {
        super.onResume();
        noteList = db.getAllNote();
        mAdapter = new AdapterBox(noteList, itemTouchListener);
        listNote.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
       /* ((AdapterBox) mAdapter).setOnItemClickListener(new AdapterBox.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("note", noteList.get(position));
                startActivity(i);

            }
        });*/
    }

    public void open(final int position) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                db.deleteContact(noteList.get(position).getId());
                noteList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
