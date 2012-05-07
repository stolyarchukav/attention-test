package org.forzaverita.daldic.service;

import java.util.Date;
import java.util.Map;

import org.forzaverita.daldic.preferences.TextAlignment;
import org.forzaverita.daldic.preferences.TextFont;
import org.forzaverita.daldic.widget.WidgetRefreshTask;

import android.database.Cursor;
import android.graphics.Typeface;

public interface DalDicService {

	Typeface getFont();

	Map<Integer, String> getWordsBeginWith(char letter);
	
	Map<Integer, String> getWordsBeginWith(String begin);
	
	Map<Integer, String> getWordsFullSearch(String query);

	String[] getDescription(Integer id);

	String getNextWord();

	String getPreviuosWord();
	
	String[] getCurrentWord();
	
	void setWidgetRefreshTask(WidgetRefreshTask task);
	
	WidgetRefreshTask getWidgetRefreshTask();

	boolean isAutoRefresh();
	
	int getRefreshInterval();

	boolean isDatabaseReady();

	void openDatabase();

	TextAlignment getWordTextAlign();

	Map<Integer, String> getHistory();

	void addToHistory(Integer id, String word);

	String getWordById(Integer wordId);

	Cursor getCursorOfWordsBeginWith(String string);

	TextFont resolveTypeface(Typeface typeface);

	boolean isPreferencesChanged(Date lastPreferencesCheck);

	void preferencesChanged();
	
}
