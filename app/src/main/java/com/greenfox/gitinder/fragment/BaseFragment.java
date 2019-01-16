package com.greenfox.gitinder.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.greenfox.gitinder.R;

public class BaseFragment extends Fragment {

    String title;
    protected ProgressBar progressBar;
    RelativeLayout relativeLayout;
    RelativeLayout.LayoutParams params;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyle);
        relativeLayout = new RelativeLayout(getActivity());
        params = new RelativeLayout.LayoutParams(100, 100);

    }

    public BaseFragment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorWhite));
        if (relativeLayout.getParent() != null) {
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.addView(progressBar, params);
            ((ViewGroup) getView()).addView(relativeLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }
    public void hideProgressBar() {
        relativeLayout.setVisibility(View.GONE);
        getView().setVisibility(View.VISIBLE);

    }
}
