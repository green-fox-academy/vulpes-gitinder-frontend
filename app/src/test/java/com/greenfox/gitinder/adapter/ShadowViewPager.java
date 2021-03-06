package com.greenfox.gitinder.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import org.mockito.internal.util.reflection.Fields;
import org.mockito.internal.util.reflection.InstanceField;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowViewGroup;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;
import static org.robolectric.shadow.api.Shadow.directlyOn;

@Implements(ViewPager.class)
public class ShadowViewPager extends ShadowViewGroup {

    @RealObject
    ViewPager realViewPager;

    @Implementation
    public void setAdapter(PagerAdapter adapter) {
        directlyOn(realViewPager, ViewPager.class).setAdapter(addWorkaround(adapter));
    }

    private PagerAdapter addWorkaround(PagerAdapter adapter) {
        PagerAdapter spied = spy(adapter);
        FragmentManager fragmentManager = getFragmentManagerFromAdapter(spied);
        doAnswer(invocation -> {
            if (fragmentManager.getFragments().isEmpty())
                invocation.callRealMethod();
            return null;
        }).when(spied).finishUpdate(any());
        return spied;
    }

    private FragmentManager getFragmentManagerFromAdapter(PagerAdapter adapter) {
        for (InstanceField instanceField : Fields.allDeclaredFieldsOf(adapter).instanceFields()) {
            Object obj = instanceField.read();
            if (obj instanceof FragmentManager) {
                return (FragmentManager) obj;
            }
        }
        return null;
    }
}
