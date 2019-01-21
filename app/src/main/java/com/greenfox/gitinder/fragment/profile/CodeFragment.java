package com.greenfox.gitinder.fragment.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.service.SnippetService;
import com.greenfox.gitinder.model.BaseFragment;
import com.greenfox.gitinder.model.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class CodeFragment extends BaseFragment {

    private static final String TAG = "CodeFragment";
    private CodeView codeView;
    private Profile profile;
    @Inject
    SnippetService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_fragment, container, false);
        profile = (Profile) getActivity().getIntent().getSerializableExtra("profile");
        codeView = view.findViewById(R.id.fragment_code_codeview);
        service.getSnippets(profile.getSnippets().get(Integer.parseInt(getTitle())-1), this);
        Log.d(TAG, "onCreateView: created");
        return view;
    }

    public void onSnippetsLoaded(String snippet) {
        codeView.setTheme(Theme.ATOM_ONE_DARK).setCode(snippet).setLanguage(Language.JAVA).setShowLineNumber(true).setFontSize(8).setWrapLine(false).apply();
    }
}


