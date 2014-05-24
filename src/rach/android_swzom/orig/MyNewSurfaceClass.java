package rach.android_swzom.orig;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyNewSurfaceClass extends SurfaceView implements Runnable {

	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;

	public MyNewSurfaceClass(Context context) {
		super(context);
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass Construct");
		this.holder = getHolder();
	}

	public void resume() { 
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass.resume");
		running = true;
		renderThread = new Thread(this);
		renderThread.start();         
	}      

	public void run() {
		long startTime = System.nanoTime();
		while(running) {  
			if(!holder.getSurface().isValid())
				continue;           

			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			//            game.getCurrentScreen().update(deltaTime);
			//            game.getCurrentScreen().present(deltaTime);

			Canvas canvas = holder.lockCanvas();
			// canvas.drawRGB(255, 0, 0);
			//            canvas.getClipBounds(dstRect);
			//            canvas.drawBitmap(framebuffer, null, dstRect, null);        
			drawStuff(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
	}

	private void drawStuff(Canvas canvas)  {  		
		canvas.drawColor(Color.WHITE);  

		Random r = new Random();
		int rx = r.nextInt(this.getWidth());
		int ry = r.nextInt(this.getHeight());
		int rad = r.nextInt(this.getWidth() / 4);

		Paint p = new Paint(Color.BLACK);
		p.setStyle(Style.FILL_AND_STROKE);
		canvas.drawCircle(rx, ry, rad, p);
	}

	public void pause() {      
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass.pause");
		running = false;                        
		while(true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}        
}