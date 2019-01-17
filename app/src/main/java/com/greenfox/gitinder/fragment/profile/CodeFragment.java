package com.greenfox.gitinder.fragment.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.BaseFragment;

import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.Language;
import br.tiagohm.codeview.Theme;

public class CodeFragment extends BaseFragment {
    private static final String TAG = "CodeFragment";
    CodeView codeView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_fragment, container, false);
        codeView = view.findViewById(R.id.fragment_code_codeview);
        codeView.setTheme(Theme.ATOM_ONE_DARK).setCode("").setLanguage(Language.JAVA).setShowLineNumber(true).setFontSize(8).setWrapLine(false).apply();
        Log.d(TAG, "onCreateView: created");
        return view;
    }
}
