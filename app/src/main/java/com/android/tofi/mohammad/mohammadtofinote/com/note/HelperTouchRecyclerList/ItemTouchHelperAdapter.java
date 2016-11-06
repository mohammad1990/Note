package com.android.tofi.mohammad.mohammadtofinote.com.note.HelperTouchRecyclerList;

/**
 * Created by aliT on 11/5/2016.
 */

public interface ItemTouchHelperAdapter {
    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and <strong>not</strong> at the end of a "drop" event.<br/>
     * <br/>
     **/
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * Called when an item has been dismissed by a swipe.<br/>
     * <br/>
     **/
    void onItemDismiss(int position);
}
