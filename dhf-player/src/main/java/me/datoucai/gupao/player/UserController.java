package me.datoucai.gupao.player;

/**
 * @author dengtongcai
 * @date 2018/7/16
 */

import lombok.extern.slf4j.Slf4j;
import me.datoucai.gupao.Device;
import me.datoucai.gupao.IPUtil;
import me.datoucai.gupao.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/Application")
public class UserController {

    @RequestMapping("/GetUser.ashx")
    public String getUser(HttpServletRequest request, @RequestParam Map<String, String> data) {
        log.info("request ip: 【{}】, login data:【{}】 ", IPUtil.getIpAddress(request), data);

        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if (value.contains("张中亮") || value.contains("韩乐") || value.contains("邓同财")) {
                return Response.login;
            }
        }
        return Response.fail;
    }

    @RequestMapping("/GetVideo.ashx")
    public String getVedio(HttpServletRequest request, @RequestParam Map<String, String> data) {
        log.info("request ip: 【{}】, vedio data:【{}】 ", IPUtil.getIpAddress(request), data);

        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if (value.contains(Device.ANDROID_ONEPLUS)) {
                return Response.vedio.get(Device.ANDROID_ONEPLUS);
            } else if (value.contains(Device.PC_GAMER)) {
                return Response.vedio.get(Device.PC_GAMER);
            } else if (value.contains(Device.PC_HANLE)) {
                return Response.vedio.get(Device.PC_HANLE);
            }
        }
        return Response.fail;
    }

}
