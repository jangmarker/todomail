package de.jangmarker.todomail;

import android.app.Activity;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateTodoShortcutActivityTest {

    @Test
    public void onCreate_providesLauncherIntent() throws Exception {
        CreateTodoShortcutActivity activity =
                Robolectric.buildActivity(CreateTodoShortcutActivity.class)
                    .create().start().visible().get();

        ShadowActivity shadow = Shadows.shadowOf(activity);

        assertThat(shadow.getResultCode(), is(Activity.RESULT_OK));
        assertThat(shadow.getResultIntent(), allOf(
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_INTENT),
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_NAME),
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_ICON_RESOURCE)
        ));
    }

}