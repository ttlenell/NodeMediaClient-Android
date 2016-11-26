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

        /**
         * 开启H.264硬解码
         * 实验功能,因Android碎片化,兼容性并不保证.若初始化失败自动转软解,但存在部分机型初始化成功但解码失败或跳帧或绿边的情况
         */
        nvp.setHWEnable(true);

        String url = SharedPreUtil.getString(this,"avPlayUrl");

        /**
         * 设置视频源,可以是http的web地址,也可以是本地文件路径
         */
        nvp.setDataSource(url);

        /**
         * 开始播放
         */
        nvp.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 点击显示区域显示控制条
         */
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
        /**
         * 响应控制条的播放操作,如被暂停,则继续播放
         */
        nvp.start();
    }

    @Override
    public void pause() {
        /**
         * 响应控制条的暂停操作
         */
        nvp.pause();
    }

    @Override
    public int getDuration() {
        /**
         * 获取视频总时长
         */
        return nvp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        /**
         * 获取视频当前位置
         */
        return nvp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        /**
         * 定位播放进度到多少秒处
         */
        nvp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return nvp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        /**
         * 获取缓冲垫
         */
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
