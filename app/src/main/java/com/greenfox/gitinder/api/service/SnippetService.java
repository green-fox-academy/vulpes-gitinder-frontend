package com.greenfox.gitinder.api.service;

import android.os.AsyncTask;

import com.greenfox.gitinder.activity.SnippetListener;
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

    public SnippetService() {
        allSnippets = new HashMap<>();
    }

    public void getSnippets(SnippetRequest snippetRequest) {

        if (allSnippets != null && allSnippets.containsKey(snippetRequest.getUrl())) {
            snippetRequest.getFragment().onSnippetLoaded(allSnippets.get(snippetRequest.getUrl()));
        } else {
            DownloadSnippet dl = new DownloadSnippet();
            dl.execute(snippetRequest);
        }
    }

    private class DownloadSnippet extends AsyncTask<SnippetRequest, Integer, SnippetRequest> {
        @Override
        protected SnippetRequest doInBackground(SnippetRequest... snippetRequests) {
            String snippet = "";
            try {
                String line;
                URL url = new URL(snippetRequests[0].getUrl());
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                while ((line = in.readLine()) != null) {
                    snippet += line + "\n";
                }
                in.close();
                return new SnippetRequest(snippetRequests[0].getUrl(), snippet, snippetRequests[0].getFragment());
            } catch (IOException e) {
                return new SnippetRequest(snippetRequests[0].getUrl(), "Failed to load url", snippetRequests[0].getFragment());
            }
        }

        @Override
        protected void onPostExecute(SnippetRequest request) {
            allSnippets.put(request.getUrl(), request.getSnippet());
            request.getFragment().onSnippetLoaded(request.getSnippet());
        }
    }
}
