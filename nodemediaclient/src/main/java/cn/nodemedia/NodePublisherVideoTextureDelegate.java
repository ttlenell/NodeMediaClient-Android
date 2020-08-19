package cn.nodemedia;

public interface NodePublisherVideoTextureDelegate {
    void onChangeTextureCallback(NodePublisher streamer, boolean isFront, int cameraOri, int windowOri);

    int onDrawTextureCallback(NodePublisher streamer, int textureID, int width, int height, boolean isFront, int cameraOri);
}
