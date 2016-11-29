package com.android.tofi.mohammad.mohammadtofinote.com.note.ItemTouch;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tofi.mohammad.mohammadtofinote.DetailActivity;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Adapter.AdapterBox;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Entity.Note;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.ItemOnClick;
import com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList.SimpleItemTouchHelperCallback;
import com.android.tofi.mohammad.mohammadtofinote.com.note.Utitlity.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class RecyclerListFragment extends Fragment implements ItemOnClick {
    private ItemTouchHelper mItemTouchHelper;
    RecyclerView recyclerView;
    AdapterBox adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view;
        adapter = new AdapterBox(getActivity(), this);
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doThis(String query) {
        query = query.toString().toLowerCase();
        List<Note> filteredList = new ArrayList<>();
        List<Note> notes = Utility.getNotes(getActivity());
        for (int i = 0; i < notes.size(); i++) {
            String text = notes.get(i).getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredList.add(notes.get(i));
            }
        }
        adapter.filter(filteredList);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        adapter.setMyList();
        // adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onClickItem(Note note) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("note", note);
        startActivity(i);
    }
}
