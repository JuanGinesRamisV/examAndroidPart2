package com.example.examen2;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout sky;
    private FrameLayout sea;
    private ImageView sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadResorces();
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
        if (id == R.id.go2MainActivity) {
            Toast.makeText(this,"you are in the animation already",Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.go2HelloWorldActivity){
            Intent intent = new Intent(this,HelloWorldActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadResorces(){
        this.sun=findViewById(R.id.sunImage);
        this.sea=findViewById(R.id.sea);
        this.sky=findViewById(R.id.sky);
        this.sky.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.sky)){
            AnimatorSet skyAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.change_color);
            skyAnimation.setTarget(sky);
            skyAnimation.start();

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this.sun,"Y",600f);
            objectAnimator.setDuration(3000);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    AnimatorSet skyAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                            R.animator.change_color_night);
                    skyAnimation.setTarget(sky);
                    skyAnimation.start();
                }
            });
            objectAnimator.start();
        }
    }
}