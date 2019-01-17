package com.greenfox.gitinder.fragment.profile;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.BaseFragment;
import com.squareup.picasso.Downloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

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

    private class DownloadSnnipets extends AsyncTask<URL, Integer, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String toReturn = null;

            try {
                URLConnection connection = urls[0].openConnection();
                int contentLength = connection.getContentLength();
                DataInputStream input = new DataInputStream(urls[0].openStream());

                byte[] buffer = new byte[contentLength];
                input.readFully(buffer);
                input.close();
                toReturn = new String(buffer);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return toReturn;
        }
    }
}


