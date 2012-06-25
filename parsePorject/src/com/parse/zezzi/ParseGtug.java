package com.parse.zezzi;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ParseException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ParseGtug extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Parse.initialize(this, "APPID", "CLIENTKEY");
        ParseFacebookUtils.initialize("APPIDFB");
        setContentView(R.layout.mainlogin);
        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			Intent i = new Intent(getApplicationContext(),SignUp.class);
    			startActivity(i);
    		}
    	});
        
        Button btn2 = (Button)findViewById(R.id.button1);
        btn2.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			EditText a=(EditText)findViewById(R.id.usernameinput);
    			String username=a.getText().toString();
    			EditText b=(EditText)findViewById(R.id.passwordinput);
    			String password=b.getText().toString();
    			ParseUser.logInInBackground(username, password, new LogInCallback(){
    				 public void done(ParseUser user, ParseException e) {
    				        if (e == null && user != null) {
    				        	Intent i = new Intent(getApplicationContext(),PostCard.class);
        		    			startActivity(i);
    				        } else if (user == null) {
    				           Log.i("user","user dosent exists");
    				        } else {
    				           Log.i("error","error");
    				        }
    				   }
    			});
    		}
    	});
        
        
        
        

        
        final Activity currentActivity=this;
        Button btn3 = (Button)findViewById(R.id.facebookLogin);
        btn3.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			ParseFacebookUtils.logIn(currentActivity, new LogInCallback(){
    			    public void done(ParseUser user, ParseException err) {
    			    	if (user == null) {
    			            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
    			            
    			        } else if (user.isNew()) {
    			            Log.d("MyApp", "User signed up and logged in through Facebook!");
    			            Intent i = new Intent(getApplicationContext(),PostCard.class);
    		    			startActivity(i);
    			        } else {
    			        	Log.d("MyApp", "User logged in through Facebook!");
    			        	Intent i = new Intent(getApplicationContext(),PostCard.class);
    		    			startActivity(i);
    			           
    			        }
    			    }
    			});
    		}
    	});
	}

}
