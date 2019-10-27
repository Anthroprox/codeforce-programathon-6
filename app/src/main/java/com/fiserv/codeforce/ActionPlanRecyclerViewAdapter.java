package com.fiserv.codeforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fiserv.codeforce.utils.CustomExpandableListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionPlanRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public List<ActionaPlanRecyclerViewerCardObject> mItemList;


    public ActionPlanRecyclerViewAdapter(List<ActionaPlanRecyclerViewerCardObject> itemList) {

        mItemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_action_plan, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView exam;
        TextView date;
        ExpandableListView expandableListView;
        ExpandableListAdapter expandableListAdapter;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.card_name_2);
            exam = itemView.findViewById(R.id.card_exam_2);
            date = itemView.findViewById(R.id.card_date_2);
            final AtomicInteger previousGroup = new AtomicInteger(-1);
            final AtomicInteger originalHeight = new AtomicInteger(-1);
            expandableListView = itemView.findViewById(R.id.expandableListViewACtionPlan);
            originalHeight.set(expandableListView.getLayoutParams().height);
            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int l) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.findViewById(R.id.card_view_action).getLayoutParams();
                    if (l != previousGroup.get() && previousGroup.get() != -1) {
                        expandableListView.collapseGroup(previousGroup.get());
//                        layoutParams.height = layoutParams.height - 180;
                    }
                    previousGroup.set(l);
                    layoutParams.height = 1200;
                }
            });

            expandableListView.setOnGroupCollapseListener(l -> {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.findViewById(R.id.card_view_action).getLayoutParams();
                layoutParams.height = 900;
                previousGroup.set(-1);
            });

            itemView.setClickable(false);
        }

        public void setData(List<String> expandableListTitle,
                            HashMap<String, List<String>> expandableListDetail) {
            expandableListAdapter = new CustomExpandableListAdapter(itemView.getContext(), expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(expandableListAdapter);
        }


    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {

        ActionaPlanRecyclerViewerCardObject item = mItemList.get(position);
        viewHolder.name.setText(item.getName());
        viewHolder.exam.setText(item.getForm());
        viewHolder.date.setText(item.getDate());


        viewHolder.setData(item.getArea(), item.getActions());

    }


}

