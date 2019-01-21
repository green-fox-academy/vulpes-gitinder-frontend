package com.greenfox.gitinder.api.service;

import android.os.AsyncTask;

import com.greenfox.gitinder.api.model.SnippetRequest;
import com.greenfox.gitinder.fragment.profile.CodeFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SnippetService {

    private static final String TAG = "SnippetService";

    Map<String, String> allSnippets;
    CodeFragment fragment;

    public SnippetService() {
        allSnippets = new HashMap<>();
    }

    public void getSnippets(String url, CodeFragment fragment) {
        this.fragment = fragment;
        if (allSnippets != null && allSnippets.containsKey(url)) {
            fragment.onSnippetsLoaded(allSnippets.get(url));
        } else {
            try {
                URL urll = new URL(url);
                DownloadSnippet dl = new DownloadSnippet();
                dl.execute(urll);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadSnippet extends AsyncTask<URL, Integer, SnippetRequest> {
        @Override
        protected SnippetRequest doInBackground(URL... urls) {
            String snippet = "";
            try {
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(urls[0].openStream()));
                while ((line = in.readLine()) != null) {
                    snippet += line + "\n";
                }
                in.close();
                return new SnippetRequest(urls[0].toString(), snippet);
            } catch (IOException e) {
                return new SnippetRequest(urls[0].toString(), "Failed to load url");
            }

        }

        @Override
        protected void onPostExecute(SnippetRequest request) {
            allSnippets.put(request.getUrl(), request.getSnippet());
            fragment.onSnippetsLoaded(request.getSnippet());
        }
    }
}
