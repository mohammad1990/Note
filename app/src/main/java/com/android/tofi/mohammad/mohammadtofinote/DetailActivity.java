package com.android.tofi.mohammad.mohammadtofinote;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;

public class DetailActivity extends AppCompatActivity {
    EditText editText_title;
    EditText editText_content;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        editText_title = (EditText) findViewById(R.id.title_content);
        editText_content = (EditText) findViewById(R.id.content_content);
        Intent i = getIntent();
        note = new Note();
        note = i.getParcelableExtra("note");

        if (note != null) {
            editText_title.setText(note.getTitle());
            editText_content.setText(note.getNoteContain());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.saveDoc:
                if (note == null) {
                    Utility.storeNote(DetailActivity.this, new Note(editText_title.getText().toString(), editText_content.getText().toString(), Utility.convertSTodD(Utility.getCurrentlyDate())));
                } else {
                    Utility.updateNote(DetailActivity.this, new Note(note.getId(), editText_title.getText().toString(), editText_content.getText().toString(), Utility.convertSTodD(Utility.getCurrentlyDate())));

                }
                i = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.consoleDoc:
                i = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.share_note:
                // if (note.getTitle() != null || note.getNoteContain() != null) {
                if (note != null) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = note.getNoteContain();
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, note.getTitle());
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } else {
                    //   Toast.makeText(this, "www", Toast.LENGTH_LONG).show();

                }
                break;
            default:
                Toast.makeText(this, "Some", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
