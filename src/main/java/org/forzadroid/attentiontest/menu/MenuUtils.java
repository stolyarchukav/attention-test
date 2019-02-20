package org.forzadroid.attentiontest.menu;

import android.app.ActionBar;
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
		if (actionBar) {
			ActionBar ab = activity.getActionBar();
			ab.setHomeButtonEnabled(true);
			ab.setDisplayHomeAsUpEnabled(true);
			ab.setTitle(activity.getTitle());
			ab.show();
		}
		MenuInflater inflater = activity.getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public static boolean optionsItemSelected(MenuItem item, Activity activity) {
		switch (item.getItemId()) {
			case android.R.id.home :
				activity.startActivity(new Intent(activity, AttentionTest.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
				break;
			case R.id.menu_records :
				activity.startActivity(new Intent(activity, RecordsActivity.class));
				break;
			case R.id.menu_sequence:
				activity.startActivity(new Intent(activity, DigitalSequenceActivity.class));
				break;
		}
		return true;
	}
	
}
