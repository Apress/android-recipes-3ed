package com.examples.drawablexml;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patch);
        
//        ListView list = (ListView) findViewById(R.id.list);
//        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View v = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
//                TextView tv = (TextView) v.findViewById(android.R.id.text1);
//                tv.setBackgroundResource(R.drawable.backgradient);
//                return tv;
//            }
//            
//            @Override
//            public int getCount() {
//                return 15;
//            }
//        });
    }
}