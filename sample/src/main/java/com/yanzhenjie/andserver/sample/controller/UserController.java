package com.yanzhenjie.andserver.sample.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestBody;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.sample.model.UserInfo;
import com.yanzhenjie.andserver.sample.response.UserData;
import com.yanzhenjie.andserver.util.MediaType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequestMapping(path = "/user")
class UserController {
    @PostMapping("/login")
    UserData login(@RequestParam("account") String account,
                   @RequestParam("password") String password) {
        UserData userData = new UserData();
        if ("123".equals(account) && "123".equals(password)) {
            userData.setAreaId(1);
            userData.setAreaName("浦口区");
            userData.setGender("2");
            userData.setHeadPic("https://c.disquscdn.com/uploads/users/20705/8485/avatar92.jpg?1462509896");
            userData.setIsFirstLogin("Y");
            userData.setStaffName("小羊");
            userData.setOrgName("星火路");
            return userData;
        } else {
            return userData;
        }
    }

    //无参数get
    @GetMapping("/getName")
    String getName() {
        return "哈哈哈，这是无参数get";
    }

    //有参数get
    @GetMapping(path = "/getInfo", params = {"name"})
    String getInfo(@RequestParam(name = "name") String name) {
        return name + ", 这是有参数get";
    }

    //post json数据,consumes限定只接受json
    //调用方法见 "post上传byte数组.md" 文档
    @PostMapping(path = "/jsonBody", consumes = {"application/json"})
    UserInfo jsonBody(@RequestBody UserInfo userInfo) {
        if (userInfo.getAvatar() != null && userInfo.getAvatar().length > 0) {
            String imageSavePath = PathUtils.getExternalDocumentsPath() + File.separator + TimeUtils.getNowString(
                    new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss_SSS", Locale.getDefault())
            ) + "_aaa.jpg";
            Bitmap bmp = BitmapFactory.decodeByteArray(userInfo.getAvatar(), 0, userInfo.getAvatar().length);
            boolean save = ImageUtils.save(bmp, imageSavePath, Bitmap.CompressFormat.WEBP, true);
            Log.i("啊啊", "jsonBody: 保持图片 save = " + save + " path = " + imageSavePath);

        }
        return userInfo;
    }

    //produce，用来规定客户端的 Accept 头
    @GetMapping(path = "/produce", produces =
            {"application/json;charset=utf-8"/*MediaType.APPLICATION_JSON_UTF8_VALUE*/})
    String produce() {
        return "Produce is successful";
    }

    //post xml请求体
    @PostMapping(path = "/wo.do", consumes = {MediaType.APPLICATION_XML_UTF8_VALUE})
    String abbort(@RequestBody String body) {
        Log.i("RequestBody", "RequestBody: \n" + body);
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Response>\n" +
                "<Head>\n" +
                "\t<Version>1</Version>\n" +
                "\t<StatusCode>100</StatusCode>\n" +
                "\t<Message></Message>\n" +
                "\t<SequenceId>0000000002</SequenceId>\n" +
                "\t<Signed>dR0+VoDN92U2juivyO304w==</Signed>\n" +
                "</Head>\n" +
                "<Body>XQJfY05vIMfIvKSOoJb6WeEyXouJv8jxIsMmRw5Bhcstpik+b0E00KHVEExIipaHRpqnsI6uEzha\n" +
                "Z/f/7t9pmDKhC+S8SEqBPF21NwtLPmjZ6Je731z9xolwkUjDUSuTw2HqKyaOZBIJ33vbzulrQ//3\n" +
                "UBFhl1XIV+jA1Mfy35EV3C3AnGZA7YaGijtAgLh00cxiTU257kT3Lc3ZKHovO533NAV/yPEn0mLJ\n" +
                "JtFv5aCYS/wVXVNtvJlUchLMDo0NclOZHR4M8wMFf/XIGHx8HlWKfrtofw1XdzBsz2bza3IA9VZq\n" +
                "lwXtwpIx9V2hrXjU4zsMnzSGBE3WeWDckh/m9jnZE8vRJC+sw7TisqwLK+m8Qv5wX1q41YilWwSR\n" +
                "Jdq00bEkEfkBzqL+2DNSgsLNka+I1i8Z8TnDggtdbgBEq+DfDKIAmkw61broFg53dHgdUYw+0eYK\n" +
                "1CsC3lo4Aik+pDSzJYAoDNurCqeJT4H5Bv5V+6gb0b603lGMPtHmCtQrAt5aOAIpPqQjUkzTr/ed\n" +
                "g3a4nDaivA/b3T/ifsgznyRRjD7R5grUKwLeWjgCKT6kdtACy0/vJaZdLRCYDm/kgfoXEQXOGZiX\n" +
                "ZMgZg5HGW/Ybg2bIBwywbAEWBmTX4m3e5wSz5/YUjMWU+8sE3bzH8Bo+1Wd48oK41D8ulXZfjjwa\n" +
                "PtVnePKCuB8jYHwwOzMc/YezI4x4wFdQCqJNXPavwho+1Wd48oK41D8ulXZfjjwaPtVnePKCuO6C\n" +
                "/+ZNRaoqeJcVVAxCYqoaPtVnePKCuNQ/LpV2X448Gj7VZ3jygrgj1Urkd5DAVACedXm5TAxTFo5k\n" +
                "JBVeEeUaWKiIGtqIPv7YM1KCws2Ru0+oqROua7GluTMW+XH04XUrSvfRAZIq8llM0veFBJBmPP8p\n" +
                "fVH+CwgqALXX+SiKws75AoHpu7cpYH9fcG+I7JpdiC8FTY4zUYw+0eYK1CsC3lo4Aik+pJQgCHEc\n" +
                "j0PdOfCoZDXhyjqQ2+WVjUWAbzAsAqeeQ97Ue1SF1Q5bTFb1spBNlq8i2EJ9U+/PcWTAdIrvllkE\n" +
                "bU9RjD7R5grUKwLeWjgCKT6kDuOmZRd0bg4PnqWEt1G5bf2HsyOMeMBXUAqiTVz2r8IaPtVnePKC\n" +
                "uNQ/LpV2X448Gj7VZ3jygrj/NDxYsNhPOtuZyFMW/+9hGj7VZ3jygrjUPy6Vdl+OPBo+1Wd48oK4\n" +
                "S5jlc2ea9cU/fpEqym2XHRo+1Wd48oK41D8ulXZfjjwaPtVnePKCuMCqofWkvMoS2chgKqyf4Mdk\n" +
                "yBmDkcZb9huDZsgHDLBs5UMI8d8S8u1v5lMRqvq4jDxxo1uFl0cKGj7VZ3jygrjUPy6Vdl+OPBo+\n" +
                "1Wd48oK4l5ZpbyBmRSUD3VagnFwnOE71T/k+jEVLeFEba1x7ZfCIpVsEkSXatEu5QttUaPxzQjrX\n" +
                "OLBLIVr92Xlrjfs06SrGtYodvxhZ8llM0veFBJBmPP8pfVH+CwgqALXX+SiKMviziBdHAq7BfLKu\n" +
                "+sIRFmTIGYORxlv2G4NmyAcMsGx0BFHSLN6Y24kbh2M/feHw5kjAJVn0X/xkyBmDkcZb9huDZsgH\n" +
                "DLBs5UMI8d8S8u289wQXma7A1uXn5NepdDfI/wFaU+5IA6QwLAKnnkPe1HtUhdUOW0xWvSWFjzbw\n" +
                "UcaKcrERuXt5rHhRG2tce2XwiKVbBJEl2rSjNTkXQojeIZQ+NsvrUXGi/wFaU+5IA6QwLAKnnkPe\n" +
                "1HtUhdUOW0xWKlQv3pNZD6cXmrxbpb+Ku8MotBam6VO/eFEba1x7ZfCIpVsEkSXatMTFU+yIYKd2\n" +
                "Me+X5lh30EVOMl8DxE4cyfJZTNL3hQSQZjz/KX1R/gsIKgC11/koisdRAnlEAVXrIm2fVtCPffTy\n" +
                "WUzS94UEkGY8/yl9Uf4LCCoAtdf5KIoX85TlVtTjKWfds/RtFETxKtCAdzI7/GbyWUzS94UEkGY8\n" +
                "/yl9Uf4LCCoAtdf5KIpzcsBu3ZcrtirBonKT8P9N+EQzdGPQH4S66BYOd3R4HVGMPtHmCtQrAt5a\n" +
                "OAIpPqQjUkzTr/edg3C3FiXGPMsn8km8lhKBGYAaWKiIGtqIPv7YM1KCws2RUmXXIIGcpjh5mqhb\n" +
                "5HQ8JEoXEJvnPRw5uYsPiPVredFRjD7R5grUKwLeWjgCKT6kDuOmZRd0bg4QwiVo/UhEmbJUDXPX\n" +
                "gDmgTvVP+T6MRUt4URtrXHtl8IilWwSRJdq0XLk0x6lh2GGx9YtfRIi6zlE9VDT/9Kn3ZMgZg5HG\n" +
                "W/Ybg2bIBwywbAEWBmTX4m3eBJfKRQfB3DfDdCapCRCj3d+tzGye+5JFd67shQzTd5vadS3VzljI\n" +
                "gQv0oH7rVNo0cDvdRoc8B3EaWKiIGtqIPkVtsf58yMXd64thPlCd1UsE6OqwXNr4B1aRjgq46AkI\n" +
                "G9u8+N7MMFT+odsDouL3siUplVL6PzQH/fP5o+AFFfajxipV6IsVolXrKvuE4bkhvd6XW4odZvVL\n" +
                "8nengvGDU6zxeyhY/BJsXty6KNHQHH5wviy38MJ83rOcKBiKZ0oEHhXynjXVbxXwDayVpqHY3s2E\n" +
                "PS4TdjhQB3z/esguVF1nJ0r13nCMffnD1Eye+1rBVdPaGPJYgRmRxlnG7X6DxvN3douSm7hHd67s\n" +
                "hQzTd5vadS3VzljIgTBU6sDm3Oz38llM0veFBJBmPP8pfVH+CwgqALXX+SiK5NuOmXlCRXYIg0Ph\n" +
                "fu3b6FAKok1c9q/CGj7VZ3jygrjUPy6Vdl+OPBo+1Wd48oK40Bnpdof6qlKQwyJA0SxFkho+1Wd4\n" +
                "8oK41D8ulXZfjjwaPtVnePKCuMK6U13+IMwG26ORZ3iXkscaPtVnePKCuNQ/LpV2X448Gj7VZ3jy\n" +
                "grgIu7hKYXm6BP0ShD39VDEz/wFaU+5IA6QwLAKnnkPe1HtUhdUOW0xW8RDFY5txI0q25qlNE/lH\n" +
                "f1U/57ZwsxYcGlioiBraiD7+2DNSgsLNkRxPmJcn5ezmCoCzuAtJi4lPi5NjF9PhmWTIGYORxlv2\n" +
                "G4NmyAcMsGzlQwjx3xLy7W/mUxGq+riMI/p2uv587NxI/ri4xSy372TIGYORxlv2G4NmyAcMsGwB\n" +
                "FgZk1+Jt3l45Nai1on1phu49IXNQtGRkyBmDkcZb9huDZsgHDLBs6EhB50tsOrBp9hPFqsZTWhE+\n" +
                "dXzUxSgUUrzdNiSMZrN4URtrXHtl8IilWwSRJdq01QCWCfiQu1hRbeqTeizNkH6W/u/zS4fV363M\n" +
                "bJ77kkV3ruyFDNN3m9p1LdXOWMiByA3gEVLkrcADeT43+Ni27FGMPtHmCtQrAt5aOAIpPqQO46Zl\n" +
                "F3RuDhDCJWj9SESZIlU7miNM0+caWKiIGtqIPv7YM1KCws2Rr4jWLxnxOcOCC11uAESr4I45AiZB\n" +
                "b684eFEba1x7ZfCIpVsEkSXatEu5QttUaPxzxpQDTyGTr6gLhdC8x8GCeBpYqIga2og+/tgzUoLC\n" +
                "zZGEfpcEhGqBxtT6VTEFa2Mf/RKEPf1UMTM6cO6E6YwglvJZTNL3hQSQZjz/KX1R/gsIKgC11/ko\n" +
                "ijL4s4gXRwKuvgl8Q96Mzd3yWUzS94UEkGY8/yl9Uf4LCCoAtdf5KIqGCYsGe5CB64oOgF6IJORI\n" +
                "8llM0veFBJBmPP8pfVH+CwgqALXX+SiKnV580uymq+irctDftxu2+d+tzGye+5JFd67shQzTd5va\n" +
                "dS3VzljIgafG9mfIR/wwkNvllY1FgG8wLAKnnkPe1HtUhdUOW0xW7IaUwieoSkrWY6b/jJvnTWVW\n" +
                "SS9s4z9vUYw+0eYK1CsC3lo4Aik+pHYFbxcCdxrXouUfv+5njNGSAmEganuok9+tzGye+5JFd67s\n" +
                "hQzTd5vadS3VzljIgeLvm8gqtUelGeXii3K4+KUvtQQ8t/xd+yHE71pVRyblGlioiBraiD7+2DNS\n" +
                "gsLNke+QUR/cugMVrA3vRi4mB12rU/7AS6AFoTAsAqeeQ97Ue1SF1Q5bTFawLZtEXSdxjeQECqdJ\n" +
                "qJLx8llM0veFBJBmPP8pfVH+CwgqALXX+SiKx1ECeUQBVeu8A3zLT4YxlPJZTNL3hQSQZjz/KX1R\n" +
                "/gsIKgC11/koioYJiwZ7kIHrOYzsgobrtdBVP+e2cLMWHBpYqIga2og+/tgzUoLCzZGOILrFO8LF\n" +
                "kFCcJf9FzEQfZVZJL2zjP29RjD7R5grUKwLeWjgCKT6kEZWHdw5+X8AwfT2d8R72SvJJvJYSgRmA\n" +
                "GlioiBraiD7+2DNSgsLNkYR+lwSEaoHG1PpVMQVrYx/9EoQ9/VQxM/8BWlPuSAOkMCwCp55D3tR7\n" +
                "VIXVDltMVsAJucJfW4SKevhgxdg51VTfrcxsnvuSRXeu7IUM03eb2nUt1c5YyIGWX1GfCuOPTaw1\n" +
                "DK/hFTVY363MbJ77kkV3ruyFDNN3m9p1LdXOWMiB7f8Cd+3uvELPTFpLEL+2PsN0JqkJEKPd363M\n" +
                "bJ77kkV3ruyFDNN3m9p1LdXOWMiBGRcnP8SMVAdIKik7xOfZHCynPVauSN2w/wFaU+5IA6QwLAKn\n" +
                "nkPe1HtUhdUOW0xWdJKJhj8hlPvxOsQMul0jZLmLD4j1a3nRUYw+0eYK1CsC3lo4Aik+pCNSTNOv\n" +
                "952DcLcWJcY8yyfySbyWEoEZgBpYqIga2og+/tgzUoLCzZFSZdcggZymOHmaqFvkdDwkShcQm+c9\n" +
                "HDm5iw+I9Wt50VGMPtHmCtQrAt5aOAIpPqQO46ZlF3RuDhDCJWj9SESZslQNc9eAOaBO9U/5PoxF\n" +
                "S3hRG2tce2XwiKVbBJEl2rRcuTTHqWHYYbH1i19EiLrOUT1UNP/0qfdkyBmDkcZb9huDZsgHDLBs\n" +
                "ARYGZNfibd4El8pFB8HcN8N0JqkJEKPd363MbJ77kkV4/O2cJMNPi+J3fnMumsvIYbmw7cOZsphS\n" +
                "ok5YXu1WprNmNnh9o/HcBW8VyxvJ0yE=</Body>\n" +
                "</Response>\n";
    }
}
