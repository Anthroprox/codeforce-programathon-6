package com.fiserv.codeforce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public List<RecyclerViewerCardObject> mItemList;
    private OnClickListenerProvider onClickListenerProvider;


    public RecyclerViewAdapter(List<RecyclerViewerCardObject> itemList, OnClickListenerProvider onClickListenerProvider) {

        mItemList = itemList;
        this.onClickListenerProvider = onClickListenerProvider;
    }

    @FunctionalInterface
    public static interface OnClickListenerProvider {
        View.OnClickListener provide(Integer studentDni, Integer formId, Integer studentId);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
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
        Integer dni;
        Integer formId;
        Integer studentId;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.card_name);
            exam = itemView.findViewById(R.id.card_exam);

            itemView.setClickable(true);
        }

        public void setDniAndFormId(Integer dni, Integer formId, Integer studentId) {
            this.dni = dni;
            this.formId = formId;
            this.studentId = studentId;
            itemView.setOnClickListener(onClickListenerProvider.provide(dni, formId, studentId));

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

        RecyclerViewerCardObject item = mItemList.get(position);
        viewHolder.name.setText(item.getName());
        viewHolder.exam.setText(item.getExam());
        viewHolder.setDniAndFormId(item.getDni(), item.getFormId(), item.getStudentId());

    }


}

