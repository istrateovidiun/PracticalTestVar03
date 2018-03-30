package ro.pub.cs.systems.eim.practic.practicaltest01var03firstactivity;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private String name;
    private String grupa;

    private Random random = new Random();


    public ProcessingThread(Context context, String name, String grupa) {
        this.context = context;
        this.name = name;
        this.grupa = grupa;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sleep();
            sendMessage();
            stopThread();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("TestTest" + random.nextInt(1));
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + this.name + " " + this.grupa);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
