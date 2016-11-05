package de.jangmarker.todomail;

import android.content.Intent;
import android.net.Uri;

class MailTodoIntent extends Intent {
    public MailTodoIntent() {
        this(null);
    }

    public MailTodoIntent(String subject) {
        super(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "Create Todo <todo@jangmarker.de>"));

        if (subject != null) {
            this.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
    }
}
