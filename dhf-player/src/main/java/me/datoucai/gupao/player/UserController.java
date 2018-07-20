package me.datoucai.gupao.player;

/**
 * @author dengtongcai
 * @date 2018/7/16
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/Application")
public class UserController {
    final String success = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<u>\n" +
            "  <i>522655</i>\n" +
            "  <m>0</m>\n" +
            "  <s>True</s>\n" +
            "  <vali>20200710</vali>\n" +
            "  <times />\n" +
            "  <fn>新宋体</fn>\n" +
            "  <fc>255,140,0</fc>\n" +
            "  <fs>8</fs>\n" +
            "  <wmtype>1</wmtype>\n" +
            "  <waterMark />\n" +
            "  <userstatus>0</userstatus>\n" +
            "  <errorid>0</errorid>\n" +
            "  <msg />\n" +
            "</u>";

    final String vedio = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<License>\n" +
            "  <lic>10DC34B6182B683FD384F8ACD2631EB7A0D9D43F65AD93637E</lic>\n" +
            "  <msg />\n" +
            "</License>";

    final String fail = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<u>\n" +
            "    <userstatus>-1</userstatus>\n" +
            "    <errorid>-22</errorid>\n" +
            "    <msg>网络请求缺少参数</msg>\n" +
            "</u>";

    @RequestMapping("/GetUser.ashx")
    public String getUser(HttpServletRequest request, @RequestParam Map<String, String> data) {
        System.out.println("request ip: " + getIpAddress(request) + ", login data: " + data);

        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if (value.contains("张中亮") || value.contains("韩乐") || value.contains("邓同财")) {
                return success;
            }
        }
        return fail;
    }

    @RequestMapping("/GetVideo.ashx")
    public String getVedio(HttpServletRequest request, @RequestParam Map<String, String> data) {
        System.out.println("request ip: " + getIpAddress(request) + ", vedio data: " + data);

        Set<Map.Entry<String, String>> entries = data.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            if (value.contains("邓同财")) {
                return vedio;
            }
        }
        return fail;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
