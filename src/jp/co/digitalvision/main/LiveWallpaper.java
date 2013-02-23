/**
 *
 */
package jp.co.digitalvision.main;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * @author digital5
 *
 */
public class LiveWallpaper extends WallpaperService {

	@Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

	/* (非 Javadoc)
	 * @see android.service.wallpaper.WallpaperService#onCreateEngine()
	 */
	@Override
	public Engine onCreateEngine() {
		// TODO 自動生成されたメソッド・スタブ
		return new LiveEngine();
	}

	/** 描画を行うEngineクラス **/
    public class LiveEngine extends Engine{

    	/** イメージ **/
    	private Bitmap image;
    	/** x座標 **/
    	private int x = 0;
    	/** y座標 **/
    	private int y = 0;
    	/** x速度 **/
    	private int vx = 10;
    	/** y速度 **/
    	private int vy = 10;
    	/** 幅 **/
    	private int width;
    	/** 高さ **/
    	private int height;
    	/** 描画画像切り替えフラグ **/
    	private boolean flag = false;
    	/** 表示状態フラグ **/
    	private boolean visible;

    	// ここに描画用の処理を記述していく
    	@Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            doDraw(0,0);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            // デフォルトではtouchEventで画面のTouchを受け取れない。
            //
            setTouchEventsEnabled(true);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                doDraw((int)event.getX(),(int)event.getY());
            }
            super.onTouchEvent(event);
        }

        public void doDraw(int posX, int posY){
            Canvas canvas = getSurfaceHolder().lockCanvas();

            Paint paint = new Paint();
            canvas.drawColor(Color.BLACK);
            paint.setTextSize(24);
            paint.setColor(Color.WHITE);
            canvas.drawText("TechBooster", posX, posY,paint);

            getSurfaceHolder().unlockCanvasAndPost(canvas);
        }
    }

}
