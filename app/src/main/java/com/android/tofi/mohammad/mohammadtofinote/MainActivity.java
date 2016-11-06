package com.android.tofi.mohammad.mohammadtofinote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.android.tofi.mohammad.mohammadtofinote.com.note.ItemTouch.RecyclerListFragment;
import com.android.tofi.mohammad.mohammadtofinote.com.note.database.NoteDBHelper;


public class MainActivity extends AppCompatActivity  {
    //    private RecyclerView listNote;
//    private RecyclerView.Adapter mAdapter;
//    private List<Note> noteList = new ArrayList<>();
//    NoteDBHelper db;
//    AdapterBox.OnItemTouchListener itemTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NoteDBHelper db = new NoteDBHelper(this);
//        db.getAllNote();
        //setContentView(R.layout.activity_main);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (savedInstanceState == null) {
            RecyclerListFragment fragment = new RecyclerListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
//        // initializeData();
//        db = new NoteDBHelper(this);
//        //   db.insertContact("mohammad","hi", Utility.getCurrentlyDate());
//
//        noteList = db.getAllNote();
//
//
//        itemTouchListener = new AdapterBox.OnItemTouchListener() {
//            @Override
//            public void onCardViewTap(View view, int position) {
//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                i.putExtra("note", noteList.get(position));
//                startActivity(i);
//            }
//        };
//
//        mAdapter = new AdapterBox(noteList, itemTouchListener);
//        listNote = (RecyclerView) findViewById(R.id.rv);
//        listNote.setLayoutManager(new LinearLayoutManager(this));
//        listNote.setAdapter(mAdapter);

    }

//    @Override
//    public void onListItemClick(int position) {
//        Fragment fragment = null;
//        switch (position) {
//            case 0:
//                fragment = new RecyclerListFragment();
//                break;
//        }
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content, fragment)
//                .addToBackStack(null)
//                .commit();
//    }

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
    }
    //
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        noteList = db.getAllNote();
//        mAdapter = new AdapterBox(noteList, itemTouchListener);
//        listNote.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
//       /* ((AdapterBox) mAdapter).setOnItemClickListener(new AdapterBox.MyClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                i.putExtra("note", noteList.get(position));
//                startActivity(i);
//
//            }
//        });*/
//    }

//    public void open(final int position) {
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
//
//        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                db.deleteContact(noteList.get(position).getId());
//                noteList.remove(position);
//                mAdapter.notifyItemRemoved(position);
//            }
//        });
//
//        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

}
