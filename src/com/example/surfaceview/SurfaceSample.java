package com.example.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	/**
	 * サウンド再生データを保持します。
	 */
	private MediaPlayer mMp;
	
	public MySurfaceView(Context context) {
		super(context);
		Log.i("SURFACE","MySurfaceView()");

		// イベントが取得できるようにFocusを有効にする 
		setFocusable(true);
	
		// サウンドデータの読み込み(res/raw/pon.wav)
		mMp = MediaPlayer.create(context, R.raw.pon);	
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
		
	}


	/**
	 * タッチイベント
	 */
	public boolean onTouchEvent(MotionEvent event) {
		// タッチした時に実行
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// 音の再生ポジションを0に設定
			mMp.seekTo(0);
			// 音の再生を開始
			mMp.start();
		} 
		// タッチを話した時に実行
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			// 音の停止
			mMp.stop();
			
			// 一度再生をstop()した場合は、再び音を再生する場合は、prepare()を呼び出す必要がある
			try {
				mMp.prepare();
			} catch (Exception e) {

			}
		}
		return true;
	}
}