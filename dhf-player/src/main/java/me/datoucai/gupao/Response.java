package me.datoucai.gupao;

import java.util.HashMap;

public class Response {
    public static final HashMap<String, String> vedio = new HashMap<String, String>() {
        {
            put(Device.ANDROID_ONEPLUS, "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<License>\n" +
                    "  <lic>10DC34B6182B683FD384F8ACD2631EB7A0D9D43F65AD93637E</lic>\n" +
                    "  <msg />\n" +
                    "</License>");
            put(Device.PC_GAMER, "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<License>\n" +
                    "  <lic>985109E5A879CFC6301EDFA204954228122E0E8328F9645120</lic>\n" +
                    "  <msg />\n" +
                    "</License>");
            put(Device.PC_HANLE, "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<License>\n" +
                    "  <lic>E40407A380F99A07B506A95E7A15ADC6B6ACAC9EC83CB52004</lic>\n" +
                    "  <msg />\n" +
                    "</License>");
        }
    };

    public static final String login = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
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


    public static final String fail = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<u>\n" +
            "    <userstatus>-1</userstatus>\n" +
            "    <errorid>-22</errorid>\n" +
            "    <msg>网络请求缺少参数</msg>\n" +
            "</u>";
}
