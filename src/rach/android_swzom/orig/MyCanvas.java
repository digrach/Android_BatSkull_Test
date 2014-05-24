package rach.android_swzom.orig;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyCanvas extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder holder;
	private MyThread myThread;

	public MyCanvas(Context context) {
		super(context);
		Log.d(">>>>>>>>>>>>>>>>","MyCanvas.construct");
		holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(">>>>>>>>>>>>>>>>","surfaceCreated");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(">>>>>>>>>>>>>>>>","surfaceChanged");
		if (myThread==null){  
			myThread = new MyThread(holder);  
			myThread.setRunning(true);  
			myThread.setSurfaceSize(width, height);  
			myThread.start();  
		}  
		myThread.setRunning(true);  
		

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(">>>>>>>>>>>>>>>>","surfaceDestroyed");
		
		myThread.setRunning(false);  
		
		
//		boolean retry = true;  
//		myThread.setRunning(false);  
//		while (retry) {  
//			try {  
//				myThread.join();  
//				Log.d(">>>>>>>>>>>>>>>>","surfaceDestroyed - rejoined thread");
//				retry = false;  
//			} catch (InterruptedException e) {}  
//		}  

	}

	public Thread getThread() {  
		return myThread;  
	}  



}
