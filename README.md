# NodeMediaClient-Android

## 国内镜像
http://git.oschina.net/illuspas/NodeMediaClient-Android

## 简介
NodeMediaClient是为移动端应用量身打造的基于RTMP协议的流媒体直播系统。通过集成本SDK，只需几个简单API调用，便可实现一套完整的直播流媒体应用基础。包含了流媒体应用中：『采集->编码->传输->解码->播放』的所有步骤。

## 支持的平台
Android 4.0及以上

## 支持的CPU架构
armeabi-v7a arm64-v8a x86 x86_64

## 支持的流媒体服务端
fms, wowza, evostream, red5, crtmpserver, nginx-rtmp-module, srs, Node-Media-Server及其他标准RTMP协议服务端

## 支持的流媒体云服务器
[奥点云](http://www.aodianyun.com/)及其他标准RTMP协议云服务器

## 直播发布特性
* H.264/AAC 组合的RTMP/RTMFP协议音视频流发布
* 全屏视频采集，720p原画质缩放
* NEON指令集优化H.264软件编码器，性能强劲，兼容性极强
* H.264支持Baseline,Main,High profile
* 支持手机旋转,横屏16:9，竖屏9:16分辨率自动输出横竖屏视频流
* 支持4:3分辨率,1:1分辨率输出
* OpenGL ES 摄像头预览窗,任意视图比例不失真
* NEON优化AAC软件编码器，极少CPU占用，支持LC和HE profile，音质还原效果好
* 支持SPEEX音频编码
* 支持环境背景噪音抑制，不再有沙沙声
* 支持发布中途切换前后摄像头
* 支持闪光灯开关
* 支持全时自动对焦与手动对焦切换
* 支持单音频流发布
* 支持发布中途来电保持网络流，暂停发布，挂机后继续发布
* 支持预览摄像头后,任意时刻截图
* 支持定义为'live','record','append'的发布类型
* 支持Adobe auth模式的鉴权验证 如rtmp://user:pass@server:port/app/name
* 内置基于GPU加速的5级磨皮美白滤镜
* 支持GPU算法的镜头缩放,兼容性好
* 支持动态设置视频码率
* 支持视频码率自适应网络带宽
* 全自动网络异常重连

## 直播播放特性
* 只为RTMP协议优化的码流解析器，极短的分析时间，秒开RTMP视频流
* NEON指令集优化的软件解码器，性能好，兼容性强
* 支持的网络协议 RTMP/RTMFP/RTMPT/RTSP/HLS/HTTP-FLV
* 支持的视频解码器:H.264, H.265, FLV, VP6, MPEG4
* 支持的音频解码器:AAC, MP3, SPEEX, NELLYMOSER, ADPCM_SWF, G.711
* 视频编码:H.264/MPEG4/H.265 支持硬解加速
* OpenGL ES视频渲染
* 全自动网络异常重连
* 支持播放中途来电保持网络流，暂停播放，挂机后继续播放
* 支持播放中途任意时刻截图
* 支持设置最大缓冲时长,杜绝累计延迟
* 支持播放前设置receiveAudio,receiveVideo来控制只接收音频或视频流(需服务端实现，fms，red5支持)
* 支持发送FCSubscribe命令，兼容国外Akamai, Edgecast , Limelight 等CDN
* 支持主播停止推流后，播放端立即获取到结束状态（RTMP协议下）
* 支持多路直播流同时播放，理论最大32路

## 直播串流器
v1.2.3新增NodeStreamer类,可用于户外环境下,具有RTSP协议的运动相机/无人机连接手机热点,通过手机4G网络串流到RTMP服务器.  
手机端不进行编解码,只有网络IO,不占用CPU.  
v1.2.9增加对本地mp4文件直接进行串流的支持.(注意:并不进行二次编码,不改变码率\清晰度,不调整视频方向.手机内置相机录像的视频码率非常大,竖向录制的视频是旋转90度的)

## 网络流点播\本地文件播放特性  
* 支持的文件格式 : MP4,MOV,FLV,TS,AVI,MKV
* 支持的音频解码器 : AAC,AC3,MP3
* 支持的视频解码器 : H.264,MPEG4,H.265,FLV,H.263
* 视频编码:H.264/MPEG4/H.265支持硬解加速
* 支持获取视频时长,当前播放点,缓冲点,暂停,快进等基本操作
* 支持Android原生MediaPlayerControl规范的播放控制器
* OpenGL ES视频渲染

## 关于多播
NodeMediaClien从第一个版本提交以来，Android端始终是以单例静态方法来实现播放和发布的功能，限制了多播的实现。自推出以来，不少客户也反馈了诸如多人会议、多路监控、直播间多人视频等需求。为了不影响旧版的使用，我们在Android端从v1.1.0版开始新增了NodePlayer类以提供同原来LivePlayer类一模一样的功能且支持多播和多种拉伸缩放模式。老用户如果不想升级仍然可以继续使用LivePlayer,我们也将继续保持bug维护.新用户不管单播还是多播需求,都直接用NodePlayer.

## 视频点播
### v2.0.0版本开始支持视频点播
 * 支持的协议格式 HTTP/FILE
 * 支持的封装格式 MP4/FLV/AVI/MPEGTS
 * 支持的视频格式 H.263/H.264/H.265/FLV/MPEG4
 * 支持的音频格式 AAC/AC3/MP3/NELLYMOSER/SPEEX
 * 播放/暂停/快进/时长等基本操作
 * 视频编码:H.264/MPEG4/H.265 支持硬解加速
 
## RTMFP协议  
### v2.0.1实验版,支持RTMFP协议  
 * 基于UDP传输协议
 * 支持P2P连接(待实现)
 * 支持加入NetGroup(待实现)
  
## VR全景播放
### v2.0.2增加VR全景播放的实现,基于[MD360Player4Android](https://github.com/ashqal/MD360Player4Android)  
 * 支持HTTP点播,也支持RTMP直播. 
 * 支持H.264/H.265硬件解码,注意:超大视频(3840)是否支持硬解根据具体处理器内VideoCodec而定.  
 * gradle外部引入MD360Player4Android库,按需添加,不占sdk大小.  
 
![img](https://raw.githubusercontent.com/NodeMedia/NodeMediaClient-Android/2.x/Screenshot_20170413-002113.jpg)

## 开发计划
 * H.265直播传输
 * 仿ActionScript3 NetConnect.call() 客户端服务端方法互调
 * 完善RTMFP协议支持(UDP,P2P,NETGROUP)

## H.265直播
目前v2.1.3支持非Adobe官方协议下H.265直播播放,Android平台可在CPU支持的情况下开启硬件加速解码.  
服务端需要修改codecId进行支持,NodeMedia将会在近期推出更新支持[nginx-rtmp-win32](https://github.com/illuspas/nginx-rtmp-win32)  
推流端需要修改ffmpeg进行支持,NodeMedia将会在近期推出更新支持[ffmpeg-hw-win32](https://github.com/illuspas/nginx-rtmp-win32)  
目前暂不支持手机端推流,x265软编码性能消耗较大,解决方案为手机端仍然以H.264推送到服务端,服务端进行H.264-->H.265实时转码,播放端解码H.265视频.  
经测试,在相同分辨率帧率清晰度的情况下,码率减小一半以上.  
支持RTSP协议下海康摄像头Smart265的视频流

## 商用授权
商业软件需购买授权,业务咨询\定制开发,请联系  
QQ:281269007  
Email:service@nodemedia.cn

## 免费授权(需审核)
学生,个人开源项目可免费申请授权版,请联系  
Email:service@nodemedia.cn
