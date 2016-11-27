package com.android.tofi.mohammad.mohammadtofinote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.ColorPickerDialog.ColorPicker;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;

public class DetailActivity extends AppCompatActivity {
    EditText editText_title;
    EditText editText_content;
    Note note;
    // View root;
    private int currentBackgroundColor = 0xffffffff;
    private int mPickedColor = Color.WHITE;
    LinearLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        rl = (LinearLayout) findViewById(R.id.rl);
        TextView toolbarTextView = (TextView) findViewById(R.id.toolbar_title);
        toolbarTextView.setVisibility(View.VISIBLE);

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

    private void colorNoteDialog() {
        GridView gv = (GridView) ColorPicker.getColorPicker(DetailActivity.this);

        // Initialize a new AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);

        // Set the alert dialog content to GridView (color picker)
        builder.setView(gv);

        // Initialize a new AlertDialog object
        final AlertDialog dialog = builder.create();

        // Show the color picker window
        dialog.show();

        // Set the color picker dialog size
        dialog.getWindow().setGravity(Gravity.CENTER);

//        dialog.getWindow().setLayout(
//                getScreenSize().x - rl.getPaddingLeft() - rl.getPaddingRight(),
//                getScreenSize().y - getStatusBarHeight() - rl.getPaddingTop() - rl.getPaddingBottom());

        // Set an item click listener for GridView widget
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the pickedColor from AdapterView
                mPickedColor = (int) parent.getItemAtPosition(position);

                // Set the layout background color as picked color
                rl.setBackgroundColor(mPickedColor);

                // close the color picker
                dialog.dismiss();
            }
        });

    }

    // Custom method to get status bar height in pixels
    public int getStatusBarHeight() {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
                colorNoteDialog();

                /*i = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(i);
                */
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
