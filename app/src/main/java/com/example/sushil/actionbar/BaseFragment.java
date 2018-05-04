package com.example.sushil.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for layout animation demos
 *
 * Created by Patrick Ivarsson on 7/17/17.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final Handler mHandler = new Handler();

    private AnimationItem[] mAnimationItems;
    private AnimationItem mSelectedItem;

    /*
    * edited 21.11.2017*/
    RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    SimpleAdapter simpleAdapter;
    EditText search;
    private static final int[] IMAGES= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};

    /**
     * Get the layout to use for the demo
     * @return the resource id
     */
    protected abstract int getLayoutResId();

    /**
     * Get the layout manager to use for the demo
     * @param context the context
     * @return the layout manager
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager(Context context);

    /**
     * Get the array of animations to choose from
     * @return the array
     */
    protected abstract AnimationItem[] getAnimationItems();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    public void countryList(){

        list.add("Afghanistan");
        list.add("Albania");
        list.add("Algeria");
        list.add("Bangladesh");
    }
    public void countryList1(){

        list1.add("Afghanistannn");
        list1.add("Albania");
        list1.add("Algeria");
        list1.add("Bangladesh");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAnimationItems = getAnimationItems();
        mSelectedItem = mAnimationItems[0];
        search = (EditText) view.findViewById( R.id.search);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        setupRecyclerView();
        runLayoutAnimation(mRecyclerView, mSelectedItem);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacksAndMessages(null);
    }


    private void runLayoutAnimation(final RecyclerView recyclerView, final AnimationItem item) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, item.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void setupRecyclerView() {
        final Context context = mRecyclerView.getContext();
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(getLayoutManager(context));
        countryList();
        countryList1();
        simpleAdapter=new SimpleAdapter(list,list1,getActivity());
        mRecyclerView.setAdapter(simpleAdapter);
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
    }
    }
