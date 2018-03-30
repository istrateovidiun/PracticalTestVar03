package ro.pub.cs.systems.eim.practic.practicaltest01var03firstactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {


    private TextView name_edittext = null;
    private TextView grupa_editext = null;
    private Button okButton = null;
    private Button cancelButton = null;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_save:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.button_cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        name_edittext = (TextView)findViewById(R.id.edittext_name);
        grupa_editext = (TextView)findViewById(R.id.edittext_grupa);
        Intent intent = getIntent();
        if (intent != null  && intent.getExtras().containsKey("name")  && intent.getExtras().containsKey("grupa")) {
          name_edittext.setText(intent.getStringExtra("name"));
          grupa_editext.setText(intent.getStringExtra("grupa"));
        }

        okButton = (Button)findViewById(R.id.button_save);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}
