package com.android.tofi.mohammad.mohammadtofinote;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.Adapter.AdapterBox;
import com.android.tofi.mohammad.mohammadtofinote.com.note.ItemTouch.RecyclerListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    Spinner spinnerCustom;
    EditText search;
    Toolbar toolbar;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("default", -1);
                startActivity(i);
            }
        });

        if (savedInstanceState == null) {
            RecyclerListFragment fragment = new RecyclerListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();

        }

    }

    private void initCustomSpinner(Menu menu) {
        final MenuItem item = menu.findItem(R.id.sort_item);
        spinnerCustom = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<String> customSpinnerAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner_item, getResources().getStringArray(R.array.spinner_item_array));
        customSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Resources res = getResources();
        final String[] planets = res.getStringArray(R.array.spinner_item_array);
        spinnerCustom.setAdapter(customSpinnerAdapter);

        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String o = planets[position];
                sharedpreferences = getSharedPreferences("sortPre", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("sortValue", o);
                editor.commit();
                FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.content,new RecyclerListFragment());
                tr.commit();
//

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        initCustomSpinner(menu);
        setupSearchView(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.new_note:
//                Intent i = new Intent(MainActivity.this, DetailActivity.class);
//                i.putExtra("default", -1);
//                startActivity(i);
//                finish();
//                break;

            /*case R.id.about_us:
                Log.i("DEV", "MENU ABOUT CLICKED");
                break;*/
        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;

    }
    private void setupSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
       /* search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setSubmitButtonEnabled(true);
        search.setQueryHint("Search Here");*/
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        EventBus.getDefault().post(newText);

        return true;
    }
//// TODO: 11/8/2016
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
