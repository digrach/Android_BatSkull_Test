package rach.android_swzom.orig;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;

public class MyThread extends Thread {

	private SurfaceHolder holder;
	private int canvasWidth;  
	private int canvasHeight;  
	private Canvas canvas = null;  
	private boolean running;  
	private final int refresh_rate=16;   

	public MyThread(SurfaceHolder holder) {  
		Log.d(">>>>>>>>>>>>>>>>","MyThread.construct");
		this.holder = holder;  
	} 

	public void setSurfaceSize(int width, int height) {  
		synchronized (holder){  
			canvasWidth = width;  
			canvasHeight = height;  
			// Set model here.
		}  
	}  

	@Override  
	public void run() { 
		long previousTime, currentTime;  
		previousTime = System.currentTimeMillis();

		while(running == true) {

			currentTime = System.currentTimeMillis();  
			while ((currentTime-previousTime) < refresh_rate){  
				currentTime = System.currentTimeMillis();  
			}  
			previousTime=currentTime;

			try {  
				canvas = holder.lockCanvas();  
				synchronized (holder) {  
					draw(canvas);
				}  
			}  
			finally {  
				if (canvas != null) {  
					holder.unlockCanvasAndPost(canvas);  
				}  
			}  
			try {   
				Thread.sleep(refresh_rate-5);   
			} catch (InterruptedException e) {  
				e.printStackTrace();  
			} 

		}
	}

	private void draw(Canvas canvas)  {  
		Log.d(">>>>>>>>>>>>>>>>","draw");
		// Include model to draw.
		canvas.drawColor(Color.WHITE);  

		Random r = new Random();
		float rx = r.nextFloat() * canvasWidth;
		float ry = r.nextFloat() * canvasHeight;
		float rrad = r.nextFloat() * (canvasWidth / 2);


		Paint p = new Paint(Color.BLACK);
		p.setStyle(Style.FILL_AND_STROKE);
		canvas.drawCircle(rx, ry, rrad, p);
	}

	public void setRunning(boolean b) {
		Log.d(">>>>>>>>>>>>>>>>","setRunning " + b);
		running = b;
	}

}
