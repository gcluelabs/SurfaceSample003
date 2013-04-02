package com.example.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceSample extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView mSurfaceView = new MySurfaceView(this);
		setContentView(mSurfaceView);
	}
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	public MySurfaceView(Context context) {
		super(context);
		Log.i("SURFACE","MySurfaceView()");
		
		// Callbackを登録する
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.i("SURFACE","surfaceChanged()");
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i("SURFACE","surfaceDestroyed()");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i("SURFACE","surfaceCreaded()");
		
		// Canvasを取得する
		Canvas canvas = holder.lockCanvas();

		// 背景を白にする
		canvas.drawColor(Color.WHITE);

		// 画面に描画をする
		holder.unlockCanvasAndPost(canvas);
	}
}