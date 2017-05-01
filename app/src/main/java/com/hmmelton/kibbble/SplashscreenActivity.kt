package com.hmmelton.kibbble

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashscreenActivity : AppCompatActivity() {

    @BindView(R.id.splash_content)
    internal var mContentView: RelativeLayout? = null
    @BindView(R.id.login_facebook)
    internal var mFBLogin: Button? = null

    @OnClick(R.id.login_facebook)
    internal fun onFacebookLoginClick() {
        // Login with Facebook
        LoginManager.getInstance().logInWithReadPermissions(this@SplashscreenActivity,
                Arrays.asList("public_profile", "email"))
    }

    private val LOGIN_ANIMATION_TIME = 600
    private val SPLASHSCREEN_WAIT_TIME = 1000

    private val TAG = javaClass.simpleName

    // Facebook callback manager
    private var mCallbackManager: CallbackManager? = null
    // Firebase authentication fields
    private var mAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        ButterKnife.bind(this)

        // Initialize Facebook login
        initFacebookLogin()
        // Initialize Firebase auth
        initFirebaseAuth()

        setLayoutFullScreen()
        runLoginDelay()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Required by Facebook SDK
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuth != null) {
            mAuth!!.removeAuthStateListener(mAuthStateListener!!)
        }
    }

    /**
     * This method makes the Activity's layout take up the entire screen.
     */
    private fun setLayoutFullScreen() {
        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    /**
     * This method causes the app to delay picking logged in or logged out state until
     * [.SPLASHSCREEN_WAIT_TIME] milliseconds have passed.
     */
    private fun runLoginDelay() {
        Handler().postDelayed({
            // After displaying splash screen,
            if (FirebaseAuth.getInstance().currentUser == null) {
                // User has not yet logged in
                showLogin()
            } else {
                // User is already logged in, so go to main screen
                login()
            }
        }, SPLASHSCREEN_WAIT_TIME.toLong())
    }

    /**
     * This method initializes the Facebook login process.
     */
    private fun initFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create()
        // Register callback for later use
        LoginManager.getInstance().registerCallback(mCallbackManager!!,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.e(TAG, "going to do graph request")
                        handleFacebookAccessToken(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        Log.e(TAG, "FB Login Cancelled")
                    }

                    override fun onError(error: FacebookException) {
                        Log.e(TAG, error.toString())
                    }
                })
    }

    /**
     * This method initializes fields related to Firebase authentication.
     */
    private fun initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance()
    }

    /**
     * This method authenticates with Firebase, using the passed Facebook AccessToken.
     * @param token Facebook AccessToken with which to authenticate
     */
    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        // Sign in failed -- notify user
                        Toast.makeText(this@SplashscreenActivity, R.string.login_error,
                                Toast.LENGTH_LONG).show()
                    } else {
                        // Sign in was successful
                        login()
                    }
                }
    }

    /**
     * This method navigates to the login page.
     */
    private fun showLogin() {
        // Create fade in animation for title
        val fadeInAnim = AlphaAnimation(0.0f, 1.0f)
        fadeInAnim.duration = LOGIN_ANIMATION_TIME.toLong()
        fadeInAnim.startOffset = LOGIN_ANIMATION_TIME.toLong()

        mFBLogin!!.visibility = View.VISIBLE
        mFBLogin!!.startAnimation(fadeInAnim)
    }

    /**
     * This method logs the user into the application.
     */
    private fun login() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
        finish()
    }
}