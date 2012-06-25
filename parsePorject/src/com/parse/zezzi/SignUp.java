package com.parse.zezzi;

//import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button btn = (Button)findViewById(R.id.signupbutton);
        btn.setOnClickListener(new OnClickListener(){
    		public void onClick(View v) {
    			EditText a=(EditText)findViewById(R.id.signUpName);
    			String nombre=a.getText().toString();
    			EditText b=(EditText)findViewById(R.id.signUpPassword);
    			String password=b.getText().toString();
    			EditText c=(EditText)findViewById(R.id.signUpEmail);
    			String email=c.getText().toString();
    			ParseUser user = new ParseUser();
    			user.setUsername(nombre);
    			user.setPassword(password);
    			user.setEmail(email);
    			user.signUpInBackground(new SignUpCallback() {
    			    public void done(ParseException e) {
    			        if (e == null) {
    			        	Intent i = new Intent(getApplicationContext(),ParseGtug.class);
    		    			startActivity(i);
    			        } else {
    			            Log.i("me","failsignup");
    			        }
    			    }
    			});
    			
    		}
    	});
        
	}

}
