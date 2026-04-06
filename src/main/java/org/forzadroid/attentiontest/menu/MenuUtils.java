package org.forzadroid.attentiontest.menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.forzadroid.attentiontest.AttentionTest;
import org.forzadroid.attentiontest.DigitalSequenceActivity;
import org.forzadroid.attentiontest.R;
import org.forzadroid.attentiontest.RecordsActivity;

public class MenuUtils {

	public static boolean createOptionsMenu(Menu menu, Activity activity) {
		return createOptionsMenu(menu, activity, true);
	}

	public static boolean createOptionsMenuNoBar(Menu menu, Activity activity) {
		return createOptionsMenu(menu, activity, false);
	}

	private static boolean createOptionsMenu(Menu menu, Activity activity, boolean actionBar) {
		if (actionBar && activity instanceof AppCompatActivity) {
			ActionBar ab = ((AppCompatActivity) activity).getSupportActionBar();
			if (ab != null) {
				ab.setHomeButtonEnabled(true);
				ab.setDisplayHomeAsUpEnabled(true);
				ab.setTitle(activity.getTitle());
				ab.show();
			}
		}
		MenuInflater inflater = activity.getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public static boolean optionsItemSelected(MenuItem item, Activity activity) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			activity.startActivity(new Intent(activity, AttentionTest.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		} else if (id == R.id.menu_records) {
			activity.startActivity(new Intent(activity, RecordsActivity.class));
		} else if (id == R.id.menu_sequence) {
			activity.startActivity(new Intent(activity, DigitalSequenceActivity.class));
		}
		return true;
	}
	
}
