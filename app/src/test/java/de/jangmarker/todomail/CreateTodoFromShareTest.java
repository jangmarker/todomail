package de.jangmarker.todomail;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static de.jangmarker.todomail.MailTodoIntentMatcher.mailTodoIntentWithSubject;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateTodoFromShareTest {
    private static String SUBJECT = "Hello World!";

    private Intent intent;

    @Before
    public void resetIntentToValidIntent() {
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, SUBJECT);
    }

    @Test
    public void withValidSubject_sendsMail() {
        final ShadowActivity shadow = startActivity(intent);
        assertThat(shadow.peekNextStartedActivity(), mailTodoIntentWithSubject(SUBJECT));
    }

    @Test
    public void notSendAction_doNothing() {
        intent.setAction("another action");
        final ShadowActivity shadow = startActivity(intent);
        assertThat(shadow.peekNextStartedActivity(), nullValue());
    }

    @Test
    public void mimeTypeNotSet_doNothing() {
        intent.setType(null);
        final ShadowActivity shadow = startActivity(intent);
        assertThat(shadow.peekNextStartedActivity(), nullValue());
    }

    @Test
    public void mimeTypeNotString_doNothing() {
        intent.setType("not text/plain");
        final ShadowActivity shadow = startActivity(intent);
        assertThat(shadow.peekNextStartedActivity(), nullValue());
    }

    public ShadowActivity startActivity(Intent intent) {
        CreateTodoFromShare activity =
                Robolectric.buildActivity(CreateTodoFromShare.class, intent)
                        .create().start().visible().get();
        return Shadows.shadowOf(activity);
    }

}