package ro.pub.cs.systems.eim.practic.practicaltest01var03firstactivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var03Service extends Service {

    ro.pub.cs.systems.eim.practic.practicaltest01var03firstactivity.ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        String grupa = intent.getStringExtra("grupa");

        processingThread = new ProcessingThread(this, name, grupa);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}
