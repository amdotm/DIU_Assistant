package free.mathsolution.com.diuassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText etAns;
    TextView tvQsn;
    Button btnHelpMe;
    DatabaseReference dRef;
    String QSN;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        QSN = getIntent().getExtras().getString("QSN");
        etAns = findViewById(R.id.et_ans);
        tvQsn = findViewById(R.id.qsn);
        btnHelpMe = findViewById(R.id.btn_help);
        dRef = FirebaseDatabase.getInstance().getReference("tData");
        id = dRef.push().getKey();

        tvQsn.setText(QSN+"");
        btnHelpMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model = new Model(etAns.getText().toString(),QSN);
                dRef.child(id).setValue(model);
                Toast.makeText(Main2Activity.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
