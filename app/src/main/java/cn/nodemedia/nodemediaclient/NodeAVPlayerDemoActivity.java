package cn.nodemedia.nodemediaclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.SeekBar;

import cn.nodemedia.NodeAVPlayer;
import cn.nodemedia.NodeAVPlayerDelegate;


public class NodeAVPlayerDemoActivity extends AppCompatActivity implements MediaController.MediaPlayerControl{

    SurfaceView videoSurface;
    NodeAVPlayer nvp;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_player);
        videoSurface = (SurfaceView) findViewById(R.id.videoSurface);

        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.mediaController));

        nvp = new NodeAVPlayer(this);
        nvp.setDelegate(new NodeAVPlayerDelegate() {
            @Override
            public void onEventCallback(int event, String msg) {
                Log.d("NodeMedia.VodPlayer","event:"+event+" msg:"+msg);
            }
        });
        nvp.setSurfaceView(videoSurface,NodeAVPlayer.UIViewContentModeScaleAspectFit);
        nvp.setHWEnable(true);

        String url = SharedPreUtil.getString(this,"avPlayUrl");
        nvp.setDataSource(url);
        nvp.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nvp.stop();
        nvp.deInit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nvp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nvp.start();
    }

    @Override
    public void start() {
        nvp.start();
    }

    @Override
    public void pause() {
        nvp.pause();
    }

    @Override
    public int getDuration() {
        return nvp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return nvp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        nvp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return nvp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return nvp.getBufferPercentage();
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
