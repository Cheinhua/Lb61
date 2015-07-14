package com.example.sioletsky.lb61;

//test funtion
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sioletsky.lb61.R;

public class MainActivity extends Activity {
    private EditText Input_tall, Input_weight,et;
    private Button btn;
    private TextView my_standard_weight;
    private ListView ls;
    private int gender;
    private TextView my_fat;
    private RadioGroup rg;

    private String[] Name;
    private int[][]detail;



//GitHub function nothing testing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final sqlite myDB=new sqlite(this,"MyDB",null,1);
        final SQLiteDatabase db=myDB.getWritableDatabase();

        Input_tall=(EditText)findViewById(R.id.editText);
        Input_weight=(EditText)findViewById(R.id.editText2);
        my_standard_weight=(TextView)findViewById(R.id.textView4);
        et=(EditText)findViewById(R.id.editText3);
        ls=(ListView)findViewById(R.id.listView);
        my_fat=(TextView)findViewById(R.id.textView5);
        btn=(Button)findViewById(R.id.button);
        rg=(RadioGroup)findViewById(R.id.RadioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        gender = 1;
                        group.check(R.id.radioButton);
                        break;
                    case R.id.radioButton2:
                        gender = 2;
                        group.check(R.id.radioButton2);
                        break;
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new calculater().execute(gender);
                ContentValues value=new ContentValues();
                String temp;
                temp=et.getText().toString();
                value.put("name",temp);
                double i;
                i=Double.parseDouble(Input_tall.getText().toString());
                value.put("tall",i);
                i=Double.parseDouble(Input_weight.getText().toString());
                value.put("weight", i);
                db.insert("tablename", null, value);
                int count=0;
                String []name=new String[100];
                String []tall=new String[100];
                String []weigh=new String[100];
                Cursor c=db.rawQuery("select * from tablename",null);
                c.moveToFirst();
                do{

                    name[count]="Name:"+c.getString(0)+"\n";
                    tall[count]="tall:"+Integer.toString(c.getInt(2))+"\n";
                    weigh[count]="weight"+Integer.toString(c.getInt(1))+"\n";
                    count++;
                }while(c.moveToNext());
                ls.setAdapter(new MyAdapter(MainActivity.this,name,tall,weigh));
                db.close();

            }
        });
    }


    private class calculater extends AsyncTask<Integer, Void, Void> {
        private ProgressDialog MessageDialog=new ProgressDialog(MainActivity.this);
        double tall;
        double weight;
        double standard_weight;
        double body_fat;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            MessageDialog.setMessage("Calcutating...");
            MessageDialog.show();
            tall=Double.parseDouble(Input_tall.getText().toString());
            weight=Double.parseDouble(Input_weight.getText().toString());
        }
        @Override
        protected Void doInBackground(Integer... parms){
            if(parms[0]==1){
                standard_weight=22*(tall/100)*(tall/100);
                body_fat=(weight-(0.88*standard_weight))/weight*80;
            }else if(parms[0]==2){
                standard_weight=22*(tall/100)*(tall/100);
                body_fat=(weight-(0.82*standard_weight))/weight*80;
            }else{
                standard_weight=9999;
                body_fat=9999;
            }
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused){
            MessageDialog.dismiss();
            my_standard_weight.setText(Double.toString(standard_weight));
            my_fat.setText(Double.toString(body_fat));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
