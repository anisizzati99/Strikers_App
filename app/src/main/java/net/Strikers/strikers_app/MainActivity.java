package net.Strikers.strikers_app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    //Button buttonMap;

    final String URL = "http://192.168.0.154/comments/api.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        //update UI simplified by sir
        if (account == null) {
            //null means that user did not sign in yet
            Toast.makeText(this, "Please sign in with Gmail account", Toast.LENGTH_SHORT).show();
        } else {
            //user already log in so, user will be redirect to the secret page

            Intent intent = new Intent(this,RahsiaActivity.class);
            intent.putExtra("Name",account.getDisplayName());
            intent.putExtra("Email",account.getEmail());

            startActivity(intent);
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 10);
                break;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 10) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

            //buka activity baru yang rahsia tadi tu
            Toast.makeText(this,"already sign in",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,RahsiaActivity.class);
            intent.putExtra("Name",account.getDisplayName());
            intent.putExtra("Email",account.getEmail());

            startActivity(intent);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ALAMAK", "signInResult:failed code=" + e.getStatusCode());

            //TAKBOLEH SIGN IN
           Toast.makeText(this, "Ops, cannot be signed in", Toast.LENGTH_SHORT).show();

            //updateUI(null);
        }
    }
}
