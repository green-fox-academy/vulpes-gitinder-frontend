package com.greenfox.gitinder.fragment.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.fragment.BaseFragment;

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
        codeView.setTheme(Theme.ATOM_ONE_DARK).setCode("public class MainActivity extends AppCompatActivity {\n" +
                "    private static final String TAG = \"MainActivity\";\n" +
                "    private SectionsPageAdapter mSectionsPageAdapter;\n" +
                "    private ViewPager mViewPager;\n" +
                "\n" +
                "    @Inject\n" +
                "    SharedPreferences sharedPreferences;\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        AndroidInjection.inject(this);\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.layout.activity_main);\n" +
                "\n" +
                "        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){\n" +
                "            Log.d(TAG, \"Token is missing!\");\n" +
                "            toLogin();\n" +
                "        } else {\n" +
                "            Log.d(TAG, \"Token is present.\");\n" +
                "        }\n" +
                "\n" +
                "        Log.d(TAG, \"onCreate: Starting.\");\n" +
                "\n" +
                "        mViewPager = (ViewPager) findViewById(R.id.container);\n" +
                "        setupViewPager(mViewPager);\n" +
                "\n" +
                "        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);\n" +
                "        tabLayout.setupWithViewPager(mViewPager);\n" +
                "\n" +
                "        getSupportActionBar().setElevation(0);\n" +
                "        getSupportActionBar().setDisplayShowHomeEnabled(true);\n" +
                "        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);\n" +
                "    }\n" +
                "\n" +
                "    public void setupViewPager(ViewPager viewPager){\n" +
                "        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());\n" +
                "        sectionsPageAdapter.addFragment(new SwipingFragment(), getString(R.string.tab_title_swiping));\n" +
                "        sectionsPageAdapter.addFragment(new MatchesFragment(), getString(R.string.tab_title_matches));\n" +
                "        sectionsPageAdapter.addFragment(new SettingsFragment(), getString(R.string.tab_title_settings));\n" +
                "        viewPager.setAdapter(sectionsPageAdapter);\n" +
                "    }\n" +
                "\n" +
                "    public void toLogin() {\n" +
                "        Intent intent = new Intent(this, Login.class);\n" +
                "        startActivity(intent);\n" +
                "    }\n" +
                "}").setLanguage(Language.JAVA).setShowLineNumber(true).setFontSize(8).setWrapLine(false).apply();
        Log.d(TAG, "onCreateView: created");
        return view;
    }
}
