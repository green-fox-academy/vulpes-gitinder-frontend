package com.greenfox.gitinder.fragment.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.model.SnippetRequest;
import com.greenfox.gitinder.api.service.SnippetService;
import com.greenfox.gitinder.model.Profile;

import javax.inject.Inject;
import com.greenfox.gitinder.fragment.BaseFragment;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class CodeFragment extends BaseFragment implements SnippetService.SnippetListener {

    private static final String TAG = "CodeFragment";
    private CodeView codeView;
    private Profile profile;

    @Inject
    SnippetService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_fragment, container, false);
        profile = (Profile) getActivity().getIntent().getSerializableExtra(Constants.PROFILE);
        codeView = view.findViewById(R.id.fragment_code_codeview);
        service.getSnippets(new SnippetRequest(profile.getSnippets().get(Integer.parseInt(getTitle())-1), null, this));
        return view;
    }

    @Override
    public void reload() {
    }

    @Override
    public void onSnippetLoaded(String snippet) {
        codeView.setTheme(Theme.ATOM_ONE_LIGHT).setCode(snippet).setLanguage(Language.JAVA).setShowLineNumber(true).setFontSize(8).setWrapLine(false).apply();
    }
}


