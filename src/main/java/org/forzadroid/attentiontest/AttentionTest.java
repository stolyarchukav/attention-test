package org.forzadroid.attentiontest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import org.forzadroid.attentiontest.menu.MenuUtils;

public class AttentionTest extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button digSeqButton = findViewById(R.id.digSeqButton);
        digSeqButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(AttentionTest.this, DigitalSequenceActivity.class);
				startActivity(intent);
			}
		});
        
        Button recordsButton = findViewById(R.id.recordsButton);
        recordsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(AttentionTest.this, RecordsActivity.class);
				startActivity(intent);
			}
		});
    }

    public void onClickRateApp(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                    getApplicationInfo().packageName)));
        } catch (ActivityNotFoundException e) {
            Log.w(Constants.LOG_TAG, "Can't open market app page");
        }
    }

    public void onClickMoreApps(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://search?q=pub:ForzaVerita")));
        } catch (ActivityNotFoundException e) {
            Log.w(Constants.LOG_TAG, "Can't open market app page with all apps");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return MenuUtils.createOptionsMenuNoBar(menu, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuUtils.optionsItemSelected(item, this);
    }

}
