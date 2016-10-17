package de.jangmarker.todomail;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import android.os.Bundle;

public class CreateTodoShortcutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent createShortcutIntent = new Intent();

        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "Create Todo <todo@jangmarker.de>"));
        final ShortcutIconResource iconResource = ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);

        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, emailIntent);
        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Create Todo");
        createShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        setResult(RESULT_OK, createShortcutIntent);
        finish();
    }

}
