package com.hmmelton.kibbble;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashscreenActivity extends AppCompatActivity {

    @BindView(R.id.splash_content)
    RelativeLayout mContentView;
    @BindView(R.id.login_facebook)
    Button mFBLogin;

    @OnClick(R.id.login_facebook)
    void onFacebookLoginClick() {
        // Login with Facebook
        LoginManager.getInstance().logInWithReadPermissions(SplashscreenActivity.this,
                Arrays.asList("public_profile", "email"));
    }

    private final int LOGIN_ANIMATION_TIME = 600;
    private final int SPLASHSCREEN_WAIT_TIME = 1000;

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    // Facebook callback manager
    private CallbackManager mCallbackManager;
    // Firebase authentication fields
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);

        // Initialize Facebook login
        initFacebookLogin();
        // Initialize Firebase auth
        initFirebaseAuth();

        setLayoutFullScreen();
        runLoginDelay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Required by Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuth != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    /**
     * This method makes the Activity's layout take up the entire screen.
     */
    private void setLayoutFullScreen() {
        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    /**
     * This method causes the app to delay picking logged in or logged out state until
     * {@link #SPLASHSCREEN_WAIT_TIME} milliseconds have passed.
     */
    private void runLoginDelay() {
        new Handler().postDelayed(() -> {
            // After displaying splash screen,
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                // User has not yet logged in
                showLogin();
            } else {
                // User is already logged in, so go to main screen
                login();
            }
        }, SPLASHSCREEN_WAIT_TIME);
    }

    /**
     * This method initializes the Facebook login process.
     */
    private void initFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create();
        // Register callback for later use
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e(TAG, "going to do graph request");
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "FB Login Cancelled");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e(TAG, error.toString());
                    }
                });
    }

    /**
     * This method initializes fields related to Firebase authentication.
     */
    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        // TODO: Do I even need this?
        mAuthStateListener = firebaseAuth -> {
            // Get current user
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
            } else {
                // User is signed out
            }
        };
    }

    /**
     * This method authenticates with Firebase, using the passed Facebook AccessToken.
     * @param token Facebook AccessToken with which to authenticate
     */
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (!task.isSuccessful()) {
                        // Sign in failed -- notify user
                        Toast.makeText(SplashscreenActivity.this, R.string.login_error,
                                Toast.LENGTH_LONG).show();
                    } else {
                        // Sign in was successful
                        login();
                    }
                });
    }

    /**
     * This method navigates to the login page.
     */
    private void showLogin() {
        // Create fade in animation for title
        AlphaAnimation fadeInAnim = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnim.setDuration(LOGIN_ANIMATION_TIME);
        fadeInAnim.setStartOffset(LOGIN_ANIMATION_TIME);

        mFBLogin.setVisibility(View.VISIBLE);
        mFBLogin.startAnimation(fadeInAnim);
    }

    /**
     * This method logs the user into the application.
     */
    private void login() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        finish();
    }
}
