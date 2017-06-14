package com.example.cho.drawing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {


    protected ArcAnimView arcView;
    protected CircleAnimView circleView;
    protected RectAnimView rectView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arcView = new ArcAnimView(this);
        circleView = new CircleAnimView(this);
        rectView = new RectAnimView(this);

        setContentView(arcView);

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


        switch(item.getItemId())
        {
            case R.id.action_arc:
                setContentView(arcView);
                return true;
            case R.id.action_circle:
                setContentView(circleView);
                return true;
            case R.id.action_rect:
                setContentView(rectView);
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_arc) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
