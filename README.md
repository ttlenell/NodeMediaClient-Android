#NodeMediaClient-Android

##简介
NodeMediaClient是为移动端应用量身打造的基于RTMP协议的流媒体直播系统。通过集成本SDK，只需几个简单API调用，便可实现一套完整的直播流媒体应用基础。包含了流媒体应用中：『采集->编码->传输->解码->播放』的所有步骤。

##支持的平台
Android 4.0及以上

##支持的CPU架构
armeabi-v7a arm64-v8a x86 x86_64

##支持的流媒体服务端
fms, wowza, evostream, red5, crtmpserver, nginx-rtmp-module, srs, Node-Media-Server及其他标准RTMP协议服务端

##支持的流媒体云服务器
[奥点云](http://www.aodianyun.com/)及其他标准RTMP协议云服务器

##直播发布特性
* H.264/AAC 组合的RTMP协议音视频流发布
* 全屏视频采集，720p原画质缩放
* NEON指令集优化H.264软件编码器，性能强劲，兼容性极强
* H.264支持Baseline,Main,High profile
* 支持手机旋转,横屏16:9，竖屏9:16分辨率自动输出横竖屏视频流
* OpenGL ES 摄像头预览窗,任意视图比例不失真
* NEON优化AAC软件编码器，极少CPU占用，支持LC和HE profile，音质还原效果好
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
* 支持动态设置视频码率
* 支持视频码率自适应网络带宽

##直播播放特性
* 只为RTMP协议优化的码流解析器，极短的分析时间，秒开RTMP视频流
* NEON指令集优化的软件解码器，性能好，兼容性强
* 支持的网络协议 RTMP/RTMPT/RTSP/HLS/HTTP-FLV
* 支持的视频解码器:H.264, FLV, VP6, MPEG4
* 支持的音频解码器:AAC, MP3, SPEEX, NELLYMOSER, ADPCM_SWF, G.711
* OpenGL ES视频渲染
* 全自动网络异常重连
* 支持播放中途来电保持网络流，暂停播放，挂机后继续播放
* 支持播放中途任意时刻截图
* 支持设置最大缓冲时长,杜绝累计延迟
* 支持播放前设置receiveAudio,receiveVideo来控制只接收音频或视频流(需服务端实现，fms，red5支持)
* 支持发送FCSubscribe命令，兼容国外Akamai, Edgecast , Limelight 等CDN
* 支持主播停止推流后，播放端立即获取到结束状态（RTMP协议下）
* 支持多路直播流同时播放，理论最大32路

##直播串流器
v1.2.3新增NodeStreamer类,可用于户外环境下,具有RTSP协议的运动相机/无人机连接手机热点,通过手机4G网络串流到RTMP服务器.  
手机端不进行编解码,只有网络IO,不占用CPU.  
v1.2.9增加对本地mp4文件直接进行串流的支持.(注意:并不进行二次编码,不改变码率\清晰度,不调整视频方向.手机内置相机录像的视频码率非常大,竖向录制的视频是旋转90度的)

##网络流点播\本地文件播放特性  
* H.264硬解码(实验性,因Android碎片化,兼容性并不保证.若初始化失败自动转软解,存在部分初始化成功但解码失败或跳帧的情况) 
* 支持的文件格式 : MP4,MOV,FLV,TS,AVI
* 支持的音频解码器 : AAC,AC3,MP3
* 支持的视频解码器 : H.264,FLV,H.263,MPEG4
* 支持获取视频时长,当前播放点,缓冲点,暂停,快进等基本操作
* 支持Android原生MediaPlayerControl规范的播放控制器
* OpenGL ES视频渲染


##关于多播
NodeMediaClien从第一个版本提交以来，Android端始终是以单例静态方法来实现播放和发布的功能，限制了多播的实现。自推出以来，不少客户也反馈了诸如多人会议、多路监控、直播间多人视频等需求。为了不影响旧版的使用，我们在Android端从v1.1.0版开始新增了NodePlayer类以提供同原来LivePlayer类一模一样的功能且支持多播和多种拉伸缩放模式。老用户如果不想升级仍然可以继续使用LivePlayer,我们也将继续保持bug维护.新用户不管单播还是多播需求,都直接用NodePlayer.

##文档
http://www.nodemedia.cn/doc

##商用授权
可在应用程序包名为cn.nodemedia.* 下试用体验  
程序发布需商用授权，业务咨询请联系  
QQ:281269007  
Email:service@nodemedia.cn
