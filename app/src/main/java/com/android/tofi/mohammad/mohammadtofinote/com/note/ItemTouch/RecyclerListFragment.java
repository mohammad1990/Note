package com.android.tofi.mohammad.mohammadtofinote.com.note.ItemTouch;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tofi.mohammad.mohammadtofinote.DetailActivity;

import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemOnClick;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.OnStartDragListener;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.SimpleItemTouchHelperCallback;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Adapter.AdapterBox;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;


import static android.content.Context.MODE_PRIVATE;


public class RecyclerListFragment extends Fragment implements  ItemOnClick {
    private ItemTouchHelper mItemTouchHelper;
    RecyclerView recyclerView;
    AdapterBox adapter;
    String sort = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view;
        adapter = new AdapterBox(getActivity(), this, sort);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
       /* recyclerView.setItemAnimator(animator);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

    }

//    public void refreshAdapter() {
//        adapter = new AdapterBox(getActivity(), this, sort);
//        adapter.notifyDataSetChanged();
//
//    }

//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHelper.startDrag(viewHolder);
//    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setMyList();
        // adapter.notifyDataSetChanged();
    }


    @Override
    public void onClickItem(Note note) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("note", note);
        startActivity(i);
    }
}
