package me.datoucai.spring.servlet;

import lombok.extern.slf4j.Slf4j;
import me.datoucai.spring.annotation.CcAutowired;
import me.datoucai.spring.annotation.CcController;
import me.datoucai.spring.annotation.CcService;
import me.datoucai.spring.controller.DatoucaiController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * 自定义核心Servlet
 */
@Slf4j
public class DispatchServlet extends HttpServlet {
    Properties contextConfig = new Properties();
    List<String> classNames = new ArrayList<>();
    HashMap<String, Object> beanContext = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost...");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //定位配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //加载配置文件
        doScanner(contextConfig.getProperty("scan-package"));
        //注册
        doRegistry();
        //依赖注入
        doDependencyInjection();

        DatoucaiController con = (DatoucaiController) beanContext.get("datoucaiController");
        con.getReq();

        //自动映射mvc路径URL
        inithandlerMapping();

    }

    /**
     * 加载@RequestMapping中的配置加载
     */
    private void inithandlerMapping() {

    }


    /**
     * 自动依赖注入
     */
    private void doDependencyInjection() {
        if (beanContext.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : beanContext.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(CcAutowired.class)) {
                    continue;
                }
                CcAutowired annotation = field.getAnnotation(CcAutowired.class);
                String filedName = annotation.value().trim();
                if ("".equals(filedName)) {
                    filedName = field.getType().getName();
                }
                field.setAccessible(true);

                try {
                    //赋值
                    field.set(entry.getValue(), beanContext.get(filedName));
                    log.info("======================={}", filedName);
                    log.info("put【{}】into【{}】for filed【{}】", beanContext.get(filedName), entry.getValue(), field);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 注册：实例化对象
     */
    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String definition : classNames) {
                Class<?> clazz = Class.forName(definition);

                if (clazz.isAnnotationPresent(CcController.class)) {
                    beanContext.put(toBeanName(clazz.getSimpleName()), clazz.newInstance());
                } else if (clazz.isAnnotationPresent(CcService.class)) {
                    CcService service = clazz.getAnnotation(CcService.class);
                    String name = service.value();
                    if ("".equals(name)) {
                        name = toBeanName(clazz.getSimpleName());
                    }
                    Object ins = clazz.newInstance();
                    beanContext.put(name, ins);

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> ainterface : interfaces) {
                        beanContext.put(ainterface.getName(), ins);
                    }
                } else {
                    continue;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private String toBeanName(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);

    }

    /**
     * 加载配置文件,读取配置
     */
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File baseDir = new File(url.getFile());
        for (File file : baseDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replaceAll(".class", ""));
                log.info("加载定义：{}", packageName + "." + file.getName().replaceAll(".class", ""));
            }
        }
    }

    /**
     * 定位配置application.xml文件
     */
    private void doLoadConfig(String location) {
        log.info("doLoadConfig:{}", location);
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            if (is == null) {
                log.info("配置文件加载失败");
                return;
            }
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
