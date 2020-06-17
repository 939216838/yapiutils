package com.yunshi.yapiutils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.servlet.ServletContext;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Classname YapiUtils
 * @Description TODO
 * @Date 2019-06-04 20:17
 * @Created by shanhongfeng
 */
@RestController
public class YapiUtils {

    private static Logger logger = Logger.getLogger(YapiUtils.class);

    String yapiUrl = "https://yapi.yunshicloud.com";

    String commonPage = "&page=1&limit=1000";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ConfigurableWebApplicationContext configurableWebApplicationContext;

    /**
     * 获取单个项目基本信息
     *
     * @return 项目基本信息
     * @see
     */
    @GetMapping("/api/project/get")
    public String getProjectInfo(@RequestParam("token") String token) {
        System.out.println("调用获取项目基本信息接口");
        String url = yapiUrl + YapiOpenApiInterface.getProjectInfo + "?token=" + token;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        System.out.println(body);

        return body;
    }

    /**
     * 获取单个项目-菜单列表
     *
     * @return
     */
    @GetMapping("/api/interface/getCatMenu")
    public String getCatMenuByProId(@RequestParam("project_id") String project_id,
                                    @RequestParam("token") String token) {
        System.out.println("调用获取单个项目菜单列表");
        String url = yapiUrl + YapiOpenApiInterface.getCatMenuByProId + "?token=" + token + "&project_id=" + project_id;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        System.out.println(body);
        return body;
    }

    /**
     * [{
     * "index": 0,
     * "_id": 169,
     * "name": "公共分类",
     * "project_id": 290,
     * "desc": "公共分类",
     * "uid": 65,
     * "add_time": 1559092781,
     * "up_time": 1559092781,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 225,
     * "name": "文稿",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559186700,
     * "up_time": 1559187947,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 227,
     * "name": "素材",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559187942,
     * "up_time": 1559187942,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 229,
     * "name": "审核",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559210285,
     * "up_time": 1559210285,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 231,
     * "name": "水印",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559211005,
     * "up_time": 1559211005,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 233,
     * "name": "用户",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559211421,
     * "up_time": 1559211421,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 251,
     * "name": "技审",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559292435,
     * "up_time": 1559292435,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 253,
     * "name": "统计",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559292562,
     * "up_time": 1559617516,
     * "__v": 0
     * }, {
     * "index": 0,
     * "_id": 255,
     * "name": "转码模板",
     * "project_id": 290,
     * "desc": null,
     * "uid": 71,
     * "add_time": 1559293403,
     * "up_time": 1559293403,
     * "__v": 0
     * }]
     */

    /**
     * 查询分类下所有接口列表
     *
     * @param catId
     * @return
     */
    @GetMapping("/api/interface/list_cat/")
    public String getListCatByCatId(@RequestParam("catId") String catId,
                                    @RequestParam("token") String token) {

        String url = yapiUrl + YapiOpenApiInterface.getListCatByCatId + "?token=" + token
                + "&catid=" + catId + commonPage;

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        System.out.println(body);
        return body;

    }


    /**
     * 根据接口id查询接口详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/api/interface/get")
    public String getInterfaceById(@RequestParam("id") String id,
                                   @RequestParam("token") String token) {
        String url = yapiUrl + YapiOpenApiInterface.getInterfaceById + "?token=" + token
                + "&id=" + id;
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        String body = forEntity.getBody();
        System.out.println(body);


        return body;
    }

    /**
     * 更新接口描述文档
     *
     * @return
     */
    @GetMapping("/api/interface/up")
    public String updateInterfaceById(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam("token") String token) {

        String url = yapiUrl + YapiOpenApiInterface.updateInterface + "?token=" + token + "&id=" + id;
        String json = this.getInterfaceById(id, token);
        HashMap hashMap = JSON.parseObject(json, HashMap.class);
        Map<String, Object> data = (Map<String, Object>) hashMap.get("data");
        System.out.println("开始修改接口 [ " + data.get("title") + " ] ");
        data.put("id", data.get("_id"));
        data.put("status","done");

        List<Map<String, Object>> req_query = (List<Map<String, Object>>) data.get("req_query");
        for (Map<String, Object> map : req_query) {
            if (StringUtils.isEmpty(map.get("desc"))) {
                String name = String.valueOf(map.get("name"));
                if (!StringUtils.isEmpty(name)) {
                    String properties = this.getProperties(name);
                    if (StringUtils.isEmpty(properties)) {
                        String valus = inputCnEnTable(name);
                        map.put("desc", valus);
                    } else {
                        map.put("desc", properties);
                    }
                } else {
                    continue;
                }
            }
        }
        String resBody = (String) data.get("res_body");

        if (!StringUtils.isEmpty(resBody)) {
            Map map = JSON.parseObject(resBody, Map.class);
            Map<String, Object> properties = (Map<String, Object>) map.get("properties");
            this.recursionMap(properties);
            map.put("properties", properties);
            String s = JSON.toJSONString(map);
            data.put("res_body", s);
        }

        String reqBodyOther = (String) data.get("req_body_other");
        if (!StringUtils.isEmpty(reqBodyOther)) {
            Map reqBodyOtherMap = JSON.parseObject(reqBodyOther, Map.class);
            Map<String, Object> other = (Map<String, Object>) reqBodyOtherMap.get("properties");
            if (other != null) {
                this.recursionMap(other);
                reqBodyOtherMap.put("properties", other);
                String req_body_otherStr = JSON.toJSONString(reqBodyOtherMap);
                data.put("req_body_other", req_body_otherStr);
            }
        }
        String body = restTemplate.postForObject(url, data, String.class);


        System.out.println("修改接口 [ " + data.get("title") + " ] 完毕");
        System.out.println(body);
        return body;
    }


    private int recursionMap(Map<String, Object> map) {
        synchronized (this) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String entryKey = entry.getKey();
                Map<String, Object> value = (Map<String, Object>) entry.getValue();
                if (StringUtils.isEmpty(value.get("description"))) {
                    if (!StringUtils.isEmpty(entryKey)) {
                        String properties = this.getProperties(entryKey);
                        if (StringUtils.isEmpty(properties)) {
                            String valus = inputCnEnTable(entryKey);
                            value.put("description", valus);
                        } else {
                            value.put("description", properties);
                        }
                    } else {
                        continue;
                    }
                } else {
                    String properties = this.getProperties(entryKey);
                    if (StringUtils.isEmpty(properties)) {
                        this.insertProperties(entryKey, String.valueOf(value.get("description")));
                    }
                }
                if (value.containsKey("properties")) {
                    Map<String, Object> properties = (Map<String, Object>) value.get("properties");
                    recursionMap(properties);
                }
                if (value.containsKey("items")) {
                    Map<String, Object> items = (Map<String, Object>) value.get("items");
                    Map<String, Object> properties = (Map<String, Object>) items.get("properties");
                    if (properties != null) {
                        recursionMap(properties);
                    }
                }
                System.out.println(value);
            }
        }
        return -1;
    }

    /**
     * 控制台输入配置文件里未包含的中英文对照
     */
    public String inputCnEnTable(String strEn) {

        System.out.println("对照表中未包含  " + strEn + "  请输入中文对照");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("您输入的是" + read + ",现在加入到配置文件中");
        if (StrUtil.isEmpty(read)) {
            return strEn;
        } else {
            this.insertProperties(strEn, read);
            return read;
        }

    }

    /**
     * 在配置文件中加入未匹配的配置
     *
     * @param strEn
     * @param strCn
     */
    public void insertProperties(String strEn, String strCn) {
        try {

            Properties properties = new Properties();

            InputStream in = new FileInputStream("src/main/resources/Chinese-EnglishContrastTable.properties");

            // 使用properties对象加载输入流
            properties.load(in);

            //获取key对应的value值
            properties.setProperty(strEn, strCn);

            OutputStream outputStream = new FileOutputStream("src/main/resources/Chinese-EnglishContrastTable.properties");

            properties.store(outputStream, LocalDateTime.now().toString());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置文件中Key对应的Value
     *
     * @param key
     * @return
     */
    public String getProperties(String key) {

        String value = "";
        Properties properties = new Properties();
        try {
            //获取最新的
            properties.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("Chinese-EnglishContrastTable.properties"), "UTF-8"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        value = properties.getProperty(key);//获取最新的ak配置

        return value;
    }

    /**
     * 通过项目token对整个项目初始化
     *
     * @param token
     * @return
     */
    @GetMapping("/initByProject")
    public String initByProject(@RequestParam("token") String token) {
        List<String> catIds = new ArrayList<>(16);
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();

        String projectInfo = this.getProjectInfo(token);
        System.out.println(projectInfo);

        Map entry = JSON.parseObject(projectInfo, Map.class);
        Map<String, Object> data = (Map<String, Object>) entry.get("data");
        String id = data.get("_id").toString();
        String catMenuByProId = getCatMenuByProId(id, token);
        Map catMenu = JSON.parseObject(catMenuByProId, Map.class);
        System.out.println("catMenuList是" + catMenu.get("data").toString());
        List<Map<String, Object>> catMenuList = (List<Map<String, Object>>) catMenu.get("data");

        for (Map<String, Object> map : catMenuList) {
            catIds.add(map.get("_id").toString());
        }
        System.out.println(catIds.toString());
        for (String catId : catIds) {
            String listCatByCatId = getListCatByCatId(catId, token);
            System.out.println("接口列表是" + listCatByCatId);
            Map map = JSON.parseObject(listCatByCatId, Map.class);
            Map mapIds = (Map) map.get("data");
            List<Map<String, Object>> interfaceIds = (List<Map<String, Object>>) mapIds.get("list");
            for (Map<String, Object> interfaceId : interfaceIds) {
                concurrentSkipListSet.add(interfaceId.get("_id"));
            }
        }
        System.out.println("最后接口全部的id是  + " + concurrentSkipListSet.toString());
        for (Object str : concurrentSkipListSet) {
            this.updateInterfaceById(String.valueOf(str), token);

        }

        return "成功";
    }

    /**
     * 通过分类id,单个项目某个分类初始化
     *
     * @param id
     * @return
     */
    @GetMapping("/initByCat")
    public String initByCat(@RequestParam("id") String id) {


        return "成功";
    }

    /**
     * 通过单个接口id,对单个接口初始化
     *
     * @param id
     * @return
     */
    @GetMapping("/initByInterfaceId")
    public String initByInterfaceId(@RequestParam("id") String id,
                                    @RequestParam("token") String token) {

        String s = this.updateInterfaceById(String.valueOf(id), token);

        return s;
    }


}
