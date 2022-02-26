
package com.example.sensorgpsservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LightService extends Service implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;

    boolean power;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,sensor, 600000000, 600000000);

        return Service.START_STICKY;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_LIGHT) {

            Constants.light_v = event.values[0] ;
            Log.d("LIGHTVALUE",Constants.light_v +" ");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    //phone screen on- off detection
/*
    private class MyReceiver extends BroadcastReceiver {

        LightService mActivity;

        @Override
        public void onReceive(Context arg0, Intent arg1) {

            mActivity = (LightService) arg0;

        //    TextView tv = (TextView)mActivity.findViewById(R.id.textView1);

            if(arg1.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
              //  tv.setText("Headset Plugin ");
              //  Log.d("Headset Plugin",light_v +" ");
            } else if(arg1.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
             //   tv.setText("Power Connected  ");
                power= true;
                //Log.d("Power Connected",light_v +" ");
            } else if(arg1.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                //  tv.setText("Power Disconnected  ");
                power=false;
               // Log.d("Power Disconnected",light_v +" ");
            } else if(arg1.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                // tv.setText("Screen ON ");
                Constants.screen="On";
               // Log.d("Screen ON",screen +" ");
            } else if(arg1.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //  tv.setText("Screen OFF ");
                Constants.screen="Off";
              //  Log.d("Screen OFF",screen +" ");
            }
        }
    }


*/


}
