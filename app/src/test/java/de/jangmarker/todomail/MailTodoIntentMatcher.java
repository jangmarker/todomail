package de.jangmarker.todomail;

import android.content.Intent;
import android.net.Uri;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static org.hamcrest.Matchers.any;

public class MailTodoIntentMatcher extends TypeSafeDiagnosingMatcher<Intent> {
    private String subject;

    private MailTodoIntentMatcher(String subject) {
        this.subject = subject;
    }

    public static MailTodoIntentMatcher mailTodoIntent() {
        return new MailTodoIntentMatcher(null);
    }

    public static MailTodoIntentMatcher mailTodoIntentWithSubject(String subject) {
        return new MailTodoIntentMatcher(subject);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("intent to send a todo mail ");
        if (subject != null) {
            description.appendText("with subject ").appendValue(subject);
        }
    }

    @Override
    protected boolean matchesSafely(Intent todoIntent, Description mismatchDescription) {
        boolean matches = true;
        if (!hasAction(Intent.ACTION_SENDTO).matches(todoIntent)) {
            mismatchDescription.appendText("does not have the action type to send mail");
            matches = false;
        }
        if (!hasData(Uri.parse("mailto:Create Todo <todo@jangmarker.de>")).matches(todoIntent)) {
            mismatchDescription.appendText("does not send mail to todo address");
            matches = false;
        }
        if (subject == null && hasSubject(todoIntent, any(String.class))) {
            mismatchDescription.appendText("has subject although it should not have one");
            matches = false;
        } else if (subject != null && !hasSubject(todoIntent, subject)) {
            mismatchDescription
                    .appendText("does not have subject ").appendValue(subject)
                    .appendText(" but ").appendValue(todoIntent.getStringExtra(Intent.EXTRA_SUBJECT));
            matches = false;
        }
        return matches;
    }

    private boolean hasSubject(Intent todoIntent, Matcher<String> subjectMatcher) {
        return hasExtra(Intent.EXTRA_SUBJECT, subjectMatcher).matches(todoIntent);
    }
    private boolean hasSubject(Intent todoIntent, String subject) {
        return hasExtra(Intent.EXTRA_SUBJECT, subject).matches(todoIntent);
    }
}
