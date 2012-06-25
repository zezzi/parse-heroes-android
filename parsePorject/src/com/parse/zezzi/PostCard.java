package com.parse.zezzi;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;
//import com.parse.Parse;
import com.parse.ParseException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PostCard extends Activity {
	private ArrayAdapter<String> aa;
	private ListView myListView;
	private ArrayList<String> todoItems;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postcard);
		PushService.subscribe(this, "", PostCard.class);
		myListView=(ListView)findViewById(R.id.myListView);
		todoItems = new ArrayList<String>(); 
        aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        myListView.setAdapter(aa);
		
        ParseQuery query = new ParseQuery("ToDoList");
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback() {
            public void done(List<ParseObject> todoList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + todoList.size() + " list");
                    for(int i=0;i<todoList.size()-1;i++){
                    	todoItems.add(todoList.get(i).getString("activity"));
                    }
                    aa.notifyDataSetChanged();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        
		
		Button btn = (Button)findViewById(R.id.insetInfo);
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			EditText a=(EditText)findViewById(R.id.toDoInfo);
    			String nombre=a.getText().toString();
    			ParseObject toDo = new ParseObject("ToDoList");
    	        toDo.put("activity",nombre);
    	        toDo.saveInBackground();
    	        aa.add(nombre);
    	        aa.notifyDataSetChanged();
    	        a.setText("");
    		}
        });
        
        
        
        
        
        
		
        
      
        
		
	}

}
