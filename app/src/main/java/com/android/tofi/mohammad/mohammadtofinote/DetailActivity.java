package com.android.tofi.mohammad.mohammadtofinote;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.com.note.com.note.utitlity.Utility;
import com.android.tofi.mohammad.mohammadtofinote.com.note.database.NoteDBHelper;
import com.android.tofi.mohammad.mohammadtofinote.com.note.entity.Note;

public class DetailActivity extends AppCompatActivity {
    EditText editText_title;
    EditText editText_content;
    TextView textView_date;
    private ActionMode mActionMode;
    NoteDBHelper db = new NoteDBHelper(DetailActivity.this);
    Note n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        editText_title =(EditText)findViewById(R.id.title_content);
        editText_content=(EditText)findViewById(R.id.content_content);

   editText_content.addTextChangedListener(new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {}

       @Override
       public void afterTextChanged(Editable s) {
           mActionMode = startSupportActionMode(mActioModeCallBack);
       }
   });

        textView_date=(TextView)findViewById(R.id.date_content);
        Intent i = getIntent();
         n = i.getParcelableExtra("note");
        if(n!=null) {
            editText_title.setText(n.getTitle());
            editText_content.setText(n.getNoteContain());
            textView_date.setText(Utility.convertDToS(n.getDate()));
            //textView_date.setText(n.getId()+"");
        }
    }
    ActionMode.Callback mActioModeCallBack =
            new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                    getMenuInflater().inflate(R.menu.contextmenu,menu);

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
                            if(n==null) {
                            boolean re= db.insertContact(editText_title.getText().toString(), editText_content.getText().toString(), Utility.getCurrentlyDate());
                                if(re)
                                Toast.makeText(DetailActivity.this,"your Date is saved",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DetailActivity.this,"your Date did not saved pleased try again",Toast.LENGTH_LONG).show();
                            }
                            else {
                                boolean re= db.updateContact(n.getId(),editText_title.getText().toString(), editText_content.getText().toString(), Utility.getCurrentlyDate());
                                if (re)
                                    Toast.makeText(DetailActivity.this,"your Date is update",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DetailActivity.this,"your Date did not update pleased try again",Toast.LENGTH_LONG).show();

                            }
                            i =new Intent(DetailActivity.this,MainActivity.class);
                            startActivity(i);
                            return true;
                        case R.id.consoleDoc:
                             i =new Intent(DetailActivity.this,MainActivity.class);
                            startActivity(i);
                            return true;
                    }
                    mActionMode.finish();

                    return true;


                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }

            };

}
