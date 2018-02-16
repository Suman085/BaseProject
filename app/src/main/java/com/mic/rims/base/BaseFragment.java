package com.mic.rims.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mic.rims.callbacks.BaseActivityCallback;
import com.mic.rims.utils.LanguageHelper;
import com.mic.rims.utils.ProgressBarHandler;

public class BaseFragment extends Fragment {

    private BaseActivityCallback callback;
    private ProgressBarHandler progressBarHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBarHandler = new ProgressBarHandler(getActivity());
    }

    /**
     * Used for setting title of Toolbar
     * @param title String you want to display as title
     */
    protected void setToolbarTitle(String title) {
        callback.setToolbarTitle(title);
    }

    /**
     * Displays {@link ProgressBarHandler}
     */
    protected void showProgressBar() {
        progressBarHandler.show();
    }

    /**
     * Hides {@link ProgressBarHandler}
     */
    protected void hideProgressBar() {
        progressBarHandler.hide();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(LanguageHelper.onAttach(context));
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            callback = (BaseActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement BaseActivityCallback methods");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

}
