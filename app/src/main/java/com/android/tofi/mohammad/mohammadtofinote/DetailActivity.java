package com.android.tofi.mohammad.mohammadtofinote;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;

public class DetailActivity extends AppCompatActivity {
    EditText editText_title;
    EditText editText_content;
    TextView textView_date;
    private ActionMode mActionMode;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        editText_title = (EditText) findViewById(R.id.title_content);
        editText_content = (EditText) findViewById(R.id.content_content);
        getSupportActionBar().hide();

        mActionMode = startSupportActionMode(mActioModeCallBack);

        textView_date = (TextView) findViewById(R.id.date_content);
        Intent i = getIntent();
        note=null;
        note = i.getParcelableExtra("note");

        if (note != null) {
            editText_title.setText(note.getTitle());
            editText_content.setText(note.getNoteContain());
            textView_date.setText(Utility.convertDToS(note.getDate()));
        }
    }

    ActionMode.Callback mActioModeCallBack =
            new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.contextmenu, menu);


                    return true;
                }


                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    Intent i;
                    switch (item.getItemId()) {
                        case R.id.saveDoc:
                            if (note == null) {
                                Utility.storeNote(DetailActivity.this, new Note(editText_title.getText().toString(), editText_content.getText().toString(), Utility.convertSTodD(Utility.getCurrentlyDate())));
                            } else {
                                Utility.updateNote(DetailActivity.this, new Note(note.getId(),editText_title.getText().toString(), editText_content.getText().toString(), Utility.convertSTodD(Utility.getCurrentlyDate())));

                            }
                            //boolean re = db.insertContact(editText_title.getText().toString(), editText_content.getText().toString(), Utility.getCurrentlyDate());
                                /*if (re)
                                    Toast.makeText(DetailActivity.this, "your Date is saved", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DetailActivity.this, "your Date did not saved pleased try again", Toast.LENGTH_LONG).show();
                            } else {
                                boolean re = db.updateContact(n.getId(), editText_title.getText().toString(), editText_content.getText().toString(), Utility.getCurrentlyDate());
                                if (re)
                                    Toast.makeText(DetailActivity.this, "your Date is update", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DetailActivity.this, "your Date did not update pleased try again", Toast.LENGTH_LONG).show();
*/

                            i = new Intent(DetailActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            return true;
                        case R.id.consoleDoc:
                            i = new Intent(DetailActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            return true;
                        default:
                            return false;
                    }

                }


                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    mActionMode = null;
                }

            };


//    @Override
//    public void onBackPressed() {
//        /*Intent i = new Intent(DetailActivity.this, MainActivity.class);
//        startActivity(i);*/
//    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();

        }
        return super.onKeyDown(keyCode, event);
    }*/
}
