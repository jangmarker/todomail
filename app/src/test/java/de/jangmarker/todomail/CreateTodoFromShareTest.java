package de.jangmarker.todomail;

import android.content.Intent;
import android.net.Uri;

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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;


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

        assertThat(shadow.peekNextStartedActivity(), allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasData(Uri.parse("mailto:Create Todo <todo@jangmarker.de>")),
                hasExtra(Intent.EXTRA_SUBJECT, subject))
        );
    }

}