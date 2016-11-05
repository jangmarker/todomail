package de.jangmarker.todomail;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static de.jangmarker.todomail.MailTodoIntentMatcher.mailTodoIntentWithSubject;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CreateTodoFromShareTest {
    private static String subject = "Hello World!";

    private static Intent validIntent = new Intent(Intent.ACTION_SEND);

    static {
        validIntent.setType("text/plain");
        validIntent.putExtra(Intent.EXTRA_TEXT, subject);
    }

    @Test
    public void withValidSubject_sendsMail() {
        CreateTodoFromShare activity =
                Robolectric.buildActivity(CreateTodoFromShare.class, validIntent)
                        .create().start().visible().get();
        ShadowActivity shadow = Shadows.shadowOf(activity);

        assertThat(shadow.peekNextStartedActivity(), mailTodoIntentWithSubject(subject));
    }

}