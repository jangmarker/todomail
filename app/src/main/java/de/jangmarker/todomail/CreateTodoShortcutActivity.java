package de.jangmarker.todomail;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;

public class CreateTodoShortcutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent createTodoActivity = new Intent(this, CreateTodoShortcutActivity.class);
        // TODO #10 implement logic

        final ShortcutIconResource iconResource = ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);

        final Intent createShortcutIntent = new Intent();
        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, createTodoActivity);
        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Create Todo");
        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
        setResult(RESULT_OK, createShortcutIntent);

        finish();
    }

}
