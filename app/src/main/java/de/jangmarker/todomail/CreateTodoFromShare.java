package de.jangmarker.todomail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class CreateTodoFromShare extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        final String type = intent.getType();

        if (!Intent.ACTION_SEND.equals(action) || type == null || !type.equals("text/plain")) {
            finish();
            return;
        }

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "Create Todo <todo@jangmarker.de>"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, intent.getStringExtra(Intent.EXTRA_TEXT));

        startActivity(emailIntent);
        finish();
    }

}
