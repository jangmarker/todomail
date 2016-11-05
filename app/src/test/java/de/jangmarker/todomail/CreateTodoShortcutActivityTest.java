package de.jangmarker.todomail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static de.jangmarker.todomail.MailTodoIntentMatcher.mailTodoIntent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateTodoShortcutActivityTest {

    private ShadowActivity shadow;

    @Before
    public void setUp() {
        CreateTodoShortcutActivity activity =
                Robolectric.buildActivity(CreateTodoShortcutActivity.class)
                        .create().start().visible().get();

        shadow = Shadows.shadowOf(activity);
    }

    @Test
    public void onCreate_providesLauncherIntent() throws Exception {
        assertThat(shadow.getResultCode(), is(Activity.RESULT_OK));
        assertThat(shadow.getResultIntent(), allOf(
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_INTENT),
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_NAME),
                hasExtraWithKey(Intent.EXTRA_SHORTCUT_ICON_RESOURCE)
        ));
    }

    @Test
    public void launcher_createsPrefilledSendMailForm() {
        Object intent = shadow.getResultIntent().getExtras().get(Intent.EXTRA_SHORTCUT_INTENT);
        assertThat((Intent) intent, mailTodoIntent());
    }

}