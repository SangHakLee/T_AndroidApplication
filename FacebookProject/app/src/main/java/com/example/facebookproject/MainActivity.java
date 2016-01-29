package com.example.facebookproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    Button loginButton;
    Button postButton;
    Button feedButton;
    LoginManager manager;

    CallbackManager callbackManager; // 페북에서 제공하는 콜백 매니저

    // 분기 시키기
    int state;
    private static final int LOGIN = 0;
    private static final int POSTING = 1;
    private static final int FEEDING = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = LOGIN;
        feedButton = (Button)findViewById(R.id.button3);
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = FEEDING;
                if(!isLogin()){ //로그인이 안된경우
                    manager.logInWithReadPermissions(MainActivity.this, Arrays.asList("user_posts"));
                }else{ // 로그인 된 경우 로그 아웃
                    if(AccessToken.getCurrentAccessToken().getPermissions().contains("user_posts")){ // publish_actions 퍼미션이 있는지 없는지
                        getHome();
                    }else{
                        manager.logInWithReadPermissions(MainActivity.this, Arrays.asList("user_posts"));
                    }
                }
            }
        });

        postButton = (Button)findViewById(R.id.button2);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = POSTING;
                if(!isLogin()){ //로그인이 안된경우
                    manager.logInWithPublishPermissions(MainActivity.this, Arrays.asList("publish_actions"));
                }else{ // 로그인 된 경우 로그 아웃
                    if(AccessToken.getCurrentAccessToken().getPermissions().contains("publish_actions")){ // publish_actions 퍼미션이 있는지 없는지
                        // 글쓰기
                        postMessage();
                    }else{
                        manager.logInWithPublishPermissions(MainActivity.this, Arrays.asList("publish_actions"));
                    }
                }
            }
        });

        // 커스텀 로그인 버튼
        loginButton = (Button)findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLogin()){ //로그인이 안된경우
                    manager.logInWithReadPermissions(MainActivity.this, null);
                }else{ // 로그인 된 경우 로그 아웃
                    manager.logOut();
                    loginButton.setText("로그인");
                }
            }
        });

        callbackManager = CallbackManager.Factory.create(); // 콜백 매니저 등록

        manager = LoginManager.getInstance(); // loginButton1
        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                switch (state){ // 분기를 시킨다.로그인이냐, 글쓰기에서 들어온 로그인이냐
                    case LOGIN :
                        String id = AccessToken.getCurrentAccessToken().getUserId(); //유저 아이디
                        Toast.makeText(MainActivity.this, "success id : " + id, Toast.LENGTH_SHORT).show();
                        loginButton.setText("로그아웃");
                        break;
                    case POSTING :
                        postMessage();
                        break;
                    case FEEDING:
                        getHome();
                        break;

                }
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });


        final LoginButton loginButton = (LoginButton)findViewById(R.id.view);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { // 버튼에 콜백 매니저 연결
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "success id : " + loginResult.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        }); // 버튼에 콜백 등록

        isLogin();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void postMessage() {
        String message = "facebook test message";
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String graphPath = "/me/feed";  // 글 올리는 경로
        Bundle parameters = new Bundle();
        parameters.putString("message", message);
        parameters.putString("link", "http://developers.facebook.com/docs/android");
        parameters.putString("picture", "https://fbstatic-a.akamaihd.net/rsrc.php/v2/yx/r/pyNVUg5EM0j.png");
        parameters.putString("name", "Hello Facebook");
        parameters.putString("description", "The 'Hello Facebook' sample  showcases simple …");
        GraphRequest request = new GraphRequest(accessToken, graphPath, parameters, HttpMethod.POST,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject data = response.getJSONObject();
                        String id = (data == null)?null:data.optString("id");
                        if (id == null) {
                            Toast.makeText(MainActivity.this, "error : " + response.getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "post object id : " + id, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        request.executeAsync();
    }

    // 피드 가져오기. 없을수도 있기 때문에 try catch
    private void getHome() {
        Log.v("MainActitiy", "getHome : ");
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String graphPath = "/me/feed";
        GraphRequest request = new GraphRequest(accessToken, graphPath, null, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.v("MainActitiy", "response : " + response.toString());
                        JSONObject data = response.getJSONObject();
                        JSONArray array = data.optJSONArray("data");
                        String message = "";
                        for(int i = 0; i < array.length(); i++){
                            try { //메세지 없을수도 있기 때문에
                                message = array.optJSONObject(i).getString("message");
                                Log.v("MainActivity", message);
                            }catch(JSONException e){
                                Log.v("MainActivity", "e : " + e);
                            }
                        }
                    }
                });
        request.executeAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    boolean isLogin(){
        AccessToken token = AccessToken.getCurrentAccessToken();
        Log.v(TAG, "token : " + token);
        return token == null ? false : true;
    };

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
