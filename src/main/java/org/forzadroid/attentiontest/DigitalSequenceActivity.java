package org.forzadroid.attentiontest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import org.forzadroid.attentiontest.menu.MenuUtils;

public class DigitalSequenceActivity extends Activity {

	private AttentionTestApplication appState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dig_seq);
		
		appState = (AttentionTestApplication) getApplicationContext();

        CheckBox hideCompletedSquares = findViewById(R.id.hideCompletedSquares);
        hideCompletedSquares.setChecked(appState.isHideCompletedSquares());
        hideCompletedSquares.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appState.setHideCompletedSquares(isChecked);
            }
        });

		CheckBox var_font_size = findViewById(R.id.varFontSize);
		var_font_size.setChecked(appState.isVarFontSize());
		var_font_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				appState.setVarFontSize(isChecked);
			}
		});
		
		CheckBox var_font_color = findViewById(R.id.varFontColor);
		var_font_color.setChecked(appState.isVarFontColor());
		var_font_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				appState.setVarFontColor(isChecked);
			}
		});

        CheckBox reverseOrder = findViewById(R.id.reverseOrder);
        reverseOrder.setChecked(appState.isReverse());
        reverseOrder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appState.setReverse(isChecked);
            }
        });
		
		Button digSeq3 = findViewById(R.id.digSeq3);
		digSeq3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startDigSquare(3);
			}
		});
		
		Button digSeq4 = findViewById(R.id.digSeq4);
		digSeq4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startDigSquare(4);
			}
		});
		
		Button digSeq5 = findViewById(R.id.digSeq5);
		digSeq5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startDigSquare(5);
			}
		});
		
		Button digSeq6 = findViewById(R.id.digSeq6);
		digSeq6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startDigSquare(6);
			}
		});
	}
	
	private void startDigSquare(int size) {
		appState.clearDigSequence();
		Intent intent = new Intent(DigitalSequenceActivity.this, DigitalSquareActivity.class);
		intent.putExtra(Constants.DIG_SQUARE_SIZE, size);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return MenuUtils.createOptionsMenu(menu, this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuUtils.optionsItemSelected(item, this);
	}

}
