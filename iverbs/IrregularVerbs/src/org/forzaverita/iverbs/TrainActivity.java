package org.forzaverita.iverbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import org.forzaverita.iverbs.data.TrainMode;
import org.forzaverita.iverbs.data.Verb;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainActivity extends BaseActivity {
	
	private Random random = new Random();
	
	private volatile int formQuest;
	private volatile Verb verb;
	private TextView counterCorrect;
	private TextView counterWrong;
	private int correctCount = 0;
	private int wrongCount = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train);
        setActivityTitle();
    	counterCorrect = (TextView) findViewById(R.id.train_count_correct);
        counterCorrect.setText(String.valueOf(correctCount));
        counterWrong = (TextView) findViewById(R.id.train_count_wrong);
        counterWrong.setText(String.valueOf(wrongCount));
        createQuestion();
    }
    
    private void createQuestion() {
    	verb = service.getRandomVerb();
    	TextView questFormText = (TextView) findViewById(R.id.train_question_form);
    	TextView questVerbText = (TextView) findViewById(R.id.train_question_verb);
    	formQuest = random.nextInt(4);
    	String answer = null;
    	switch (formQuest) {
    		case 0 : questFormText.setText(getString(R.string.table_form_1));
    			answer = verb.getForm1();
    			break;
    		case 1 : questFormText.setText(getString(R.string.table_form_2));
    			answer = verb.getForm2();
    			break;
    		case 2 : questFormText.setText(getString(R.string.table_form_3));
    			answer = verb.getForm3();
    			break;
    		default : questFormText.setText(getString(R.string.table_translation));
    			answer = verb.getTranslation();
    	}
    	String verbQuest = formQuest == 3 ? verb.getForm1() : verb.getTranslation();
    	questVerbText.setText(verbQuest);
    	
    	Map<String, Boolean> options = new HashMap<String, Boolean>();
    	options.put(answer, true);
    	if (formQuest < 3) {
    		putOption(options, verb.getForm1());
    		putOption(options, verb.getForm2());
    		putOption(options, verb.getForm3());
        	addRandomOption(false, options);
    	}
    	else {
    		addRandomOption(true, options);
    	}
    	List<String> keys = new ArrayList<String>(options.keySet());
    	Collections.shuffle(keys);
    	Iterator<String> iterator = keys.iterator();
    	configureButton(R.id.train_answer_1, iterator.next(), options);
    	configureButton(R.id.train_answer_2, iterator.next(), options);
    	configureButton(R.id.train_answer_3, iterator.next(), options);
    	configureButton(R.id.train_answer_4, iterator.next(), options);
    }
    
    private void configureButton(int id, String text, Map<String, Boolean> options) {
    	Button answer = (Button) findViewById(id);
    	answer.setText(text);
    	answer.setOnClickListener(new AnswerListener(options.get(text)));
    	answer.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_answer));
    }
    
    private void addRandomOption(boolean translate, Map<String, Boolean> options) {
    	if (options.size() < 4) {
    		Verb verb = service.getRandomVerb();
    		if (translate) {
    			putOption(options, verb.getTranslation());
    		}
    		else {
    			putOption(options, verb.getForm1());
    			if (options.size() < 4) {
    				putOption(options, verb.getForm2());
    			}
    		}
    		addRandomOption(translate, options);
    	}
    }
    
    private static void putOption(Map<String, Boolean> options, String key) {
		if (! options.containsKey(key)) {
			options.put(key, false);
		}
	}
    
    private class AnswerListener implements View.OnClickListener {
    	
    	private boolean correct;
    	private AtomicBoolean selected = new AtomicBoolean();
    	
    	public AnswerListener(boolean correct) {
			this.correct = correct;
		}
    	
		@Override
		public void onClick(final View v) {
			if (! selected.get()) {
				selected.set(true);
				if (correct) {
					v.setBackgroundColor(v.getResources().getColor(R.color.train_correct));
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							TrainActivity.this.createQuestion();
							selected.set(false);
						}
					}, 1000);
					service.addCorrect(formQuest, verb, TrainMode.SELECT);
					counterCorrect.setText(String.valueOf(++ correctCount));
				}
				else {
					v.setBackgroundColor(v.getResources().getColor(R.color.train_wrong));
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							v.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_answer));
							selected.set(false);
						}
					}, 500);
					service.addWrong(formQuest, verb, TrainMode.SELECT);
					counterWrong.setText(String.valueOf(++ wrongCount));
				}				
			}
		}
	}
    
}