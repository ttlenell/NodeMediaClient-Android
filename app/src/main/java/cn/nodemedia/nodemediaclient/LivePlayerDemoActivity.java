package cn.nodemedia.nodemediaclient;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.nodemedia.NodePlayer;
import cn.nodemedia.NodePlayerDelegate;

/**
 * 直播播放示例
 */
public class LivePlayerDemoActivity extends AppCompatActivity implements NodePlayerDelegate {

    NodePlayer np;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_player_demo);
        getSupportActionBar().hide();

        SurfaceView sv = (SurfaceView)findViewById(R.id.surfaceView_play);
        np = new NodePlayer(this);

        //设置事件回调代理
        np.setDelegate(this);

        /**
         * 设置视频显示SurfaceView视图
         *
         * sv : SurfaceView对象,如果传null,则只解码播放纯音频
         * NodePlayer.UIViewContentModeScaleAspectFit : 视频等比缩放填充模式显示
         *   画面填充模式,当前支持下面三种现实模式，他们的差别是
         *   当SurfaceView高宽比与视频高宽比不同时           视频画面是否铺满SurfaceView |画面是否变形|有无黑边|视频画面是否会被裁剪
         *   拉伸填充 UIViewContentModeScaleToFill           是                     |   是      |  无   | 否
         *   等比缩放 UIViewContentModeScaleAspectFit        否                     |   否      |  有   | 否
         *   等比缩放填充 UIViewContentModeScaleAspectFill    是                     |   否      |  无   | 是
         */
        np.setSurfaceView(sv,NodePlayer.UIViewContentModeScaleAspectFit);

        int bufferTime = Integer.valueOf(SharedPreUtil.getString(this, "bufferTime"));

        /**
         * 设置启动缓冲区时长,单位毫秒.此参数关系视频流连接成功开始获取数据后缓冲区存在多少毫秒后开始播放
         */
        np.setBufferTime(bufferTime);

        int maxBufferTime = Integer.valueOf(SharedPreUtil.getString(this, "maxBufferTime"));

        /**
         * 设置最大缓冲区时长,单位毫秒.此参数关系视频最大缓冲时长.
         * RTMP基于TCP协议不丢包,网络抖动且缓冲区播完,之后仍然会接受到抖动期的过期数据包.
         * 设置改参数,sdk内部会自动清理超出部分的数据包以保证不会存在累计延迟,始终与直播时间线保持最大maxBufferTime的延迟
         */
        np.setMaxBufferTime(maxBufferTime);

        String playUrl = SharedPreUtil.getString(this, "playUrl");

        /**
         * 开始播放直播视频url
         */
        np.startPlay(playUrl);

        /**
         * 开始播放直播视频url,并且服务端会验证pageUrl,swfUrl的形式
         */
//        np.startPlay(playUrl,"your page url","your swf url");



        Button capBtn = (Button) findViewById(R.id.button_liveplayer_cap);
        capBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String capFilePath = Environment.getExternalStorageDirectory().getPath() + "/play_cap.jpg";
                /**
                 * 直播中截图,保存到一个可以写入的完整路径.
                 */
                if (np.capturePicture(capFilePath)) {
                    Toast.makeText(LivePlayerDemoActivity.this, "截图保存到 " + capFilePath, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LivePlayerDemoActivity.this, "截图保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 停止播放
         */
        np.stopPlay();

        /**
         * 释放资源
         */
        np.deInit();
    }


    /**
     * 事件回调
     * @param nodePlayer 对象
     * @param event 事件状态码
     * @param msg   事件描述
     */
    @Override
    public void onEventCallback(NodePlayer nodePlayer, int event, String msg) {
        Log.i("NodeMediaClient","onEventCallback:"+event+" msg:"+msg);

        switch (event) {
            case 1000:
                // 正在连接视频
                break;
            case 1001:
                // 视频连接成功
                break;
            case 1002:
                // 视频连接失败 流地址不存在，或者本地网络无法和服务端通信，回调这里。5秒后重连， 可停止
//                nodePlayer.stopPlay();
                break;
            case 1003:
                // 视频开始重连,自动重连总开关
//                nodePlayer.stopPlay();
                break;
            case 1004:
                // 视频播放结束
                break;
            case 1005:
                // 网络异常,播放中断,播放中途网络异常，回调这里。1秒后重连，如不需要，可停止
//                nodePlayer.stopPlay();
                break;
            case 1100:
                // 播放缓冲区为空
//				System.out.println("NetStream.Buffer.Empty");
                break;
            case 1101:
                // 播放缓冲区正在缓冲数据,但还没达到设定的bufferTime时长
//				System.out.println("NetStream.Buffer.Buffering");
                break;
            case 1102:
                // 播放缓冲区达到bufferTime时长,开始播放.
                // 如果视频关键帧间隔比bufferTime长,并且服务端没有在第一帧返回视频关键帧,会先开始播放音频.
//				System.out.println("NetStream.Buffer.Full");
                break;
            case 1103:
//				System.out.println("Stream EOF");
                // 客户端明确收到服务端发送来的 StreamEOF 和 NetStream.Play.UnpublishNotify时回调这里
                // 注意:不是所有云cdn会发送该指令,使用前请先测试
                // 收到本事件，说明：此流的发布者明确停止了发布，或者因其网络异常，被服务端明确关闭了流
                // 本sdk仍然会继续在1秒后重连，如不需要，可停止
//                nodePlayer.stopPlay();
                break;
            case 1104:
                //解码后得到的视频高宽值 格式为:{width}x{height}
                break;
            default:
                break;
        }
    }
}
