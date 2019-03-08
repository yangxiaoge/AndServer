package com.yanzhenjie.andserver.sample.controller;

import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestBody;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.sample.model.UserInfo;
import com.yanzhenjie.andserver.sample.response.UserData;

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
        return "name, 这是有参数get";
    }

    //post json数据,consumes限定只接受json
    @PostMapping(path = "/jsonBody", consumes = {"application/json"})
    UserInfo jsonBody(@RequestBody UserInfo userInfo) {
        return userInfo;
    }

    //produce，用来规定客户端的 Accept 头
    @GetMapping(path = "/produce", produces =
            {"application/json;charset=utf-8"/*MediaType.APPLICATION_JSON_UTF8_VALUE*/})
    String produce() {
        return "Produce is successful";
    }
}
