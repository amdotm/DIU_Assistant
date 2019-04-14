package free.mathsolution.com.diuassistant;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etQuery;
    Button btnAsk;
    DatabaseReference dRef;
    int KEY = 1 ;
    TextToSpeech t1;
    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etQuery = findViewById(R.id.et_query);
        btnAsk = findViewById(R.id.btn_ask);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });



        dRef = FirebaseDatabase.getInstance().getReference("tData");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                list = new ArrayList<Model>();
                for (DataSnapshot dt : dataSnapshot.getChildren() ){
                    Model model = dt.getValue(Model.class);
                    list.add(model);
                }


                btnAsk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Query = etQuery.getText().toString();
                        KEY = 1;
                        for (int i = 0 ; i <list.size();i++){
                            if (list.get(i).getQsn().toLowerCase().contains(Query.toLowerCase())){
                                KEY = 2 ;
                                Toast.makeText(MainActivity.this, ""+list.get(i).getAns(), Toast.LENGTH_SHORT).show();
                                t1.speak(list.get(i).getAns()+"", TextToSpeech.QUEUE_FLUSH, null);
                                break;
                            }

                        }

                         if (KEY!=2){

                            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                            intent.putExtra("QSN",Query);
                            startActivity(intent);
                        }
                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
