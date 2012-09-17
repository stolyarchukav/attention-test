package org.forzaverita.iverbs;

import java.util.List;

import org.forzaverita.iverbs.data.Verb;
import org.forzaverita.iverbs.service.AppService;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends BaseActivity {

	private AppService service;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        setActivityTitle();
        
        service = (AppService) getApplicationContext();
        
        List<Verb> verbs = service.getVerbs();
        
        TableLayout layout = (TableLayout) findViewById(R.id.table_table);
        for (Verb verb : verbs) {
        	TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.table_row, null);
            TextView form1 = (TextView) row.findViewById(R.id.table_form_1);
            form1.setText(verb.getForm1());
            configureClickListener(form1);
            TextView form2 = (TextView) row.findViewById(R.id.table_form_2);
            form2.setText(verb.getForm2());
            configureClickListener(form2);
            TextView form3 = (TextView) row.findViewById(R.id.table_form_3);
            form3.setText(verb.getForm3());
            configureClickListener(form3);
            TextView translation = (TextView) row.findViewById(R.id.table_translation);
            translation.setText(verb.getTranslation());
            layout.addView(row);
        }
    }
    
    private void configureClickListener(final TextView text) {
    	text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				speak((String) text.getText());
			}
		});
    }
    
}
