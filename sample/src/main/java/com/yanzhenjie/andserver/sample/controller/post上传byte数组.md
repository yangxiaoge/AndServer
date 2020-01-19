```
//TODO: 2020/1/19 测试上传数据给 androidserver 安卓作为服务器
OkHttpUtils
.postString()
//.url("http://"+ NetworkUtils.getIPAddress(true)+":8080/user/jsonBody")
.url("http://192.168.1.110:8080/user/jsonBody")
.content(GsonUtils.toJson(
        new Remote(byteArrayOutputStream.toByteArray(),
                ScreenUtils.getScaledDisplaySize().x,
                ScreenUtils.getScaledDisplaySize().y)
))
.mediaType(MediaType.parse("application/json; charset=utf-8"))
.build()
.execute(new Callback() {
    @Override
    public Object parseNetworkResponse(Response response, int id) throws Exception {
        Log.i(TAG, "parseNetworkResponse: 上传给andserver1");
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        LogUtils.file("onError 上传给andserver1失败: " + e.getMessage());
        Log.e(TAG, "onError 上传给andserver1失败:", e);
    }

    @Override
    public void onResponse(Object response, int id) {

    }
});
```
实体类
```
/**
 * 实时图片上传bean
 */
public class Remote {
    //bitmap字节数组
    private byte[] image;
    //屏幕宽(缩放后的)
    private int x;
    //屏幕高(缩放后的)
    private int y;

    public Remote(byte[] image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
}
```
