package com.yunshi.yapiutils;

/**
 * @Classname YapiOpenApiInterface
 * @Description TODO
 * @Date 2019-06-04 21:24
 * @Created by shanhongfeng
 */
public class YapiOpenApiInterface {

    /**
     * 获取项目基本信息
     */
    public static final String getProjectInfo = "/api/project/get";


    /**
     * 获取单个项目-菜单列表
     */
    public static final String getCatMenuByProId = "/api/interface/getCatMenu";

    /**
     * 获取某个分类下接口列表
     */
    public static final String getListCatByCatId = "/api/interface/list_cat";

    /**
     * 根据id获取接口详细信息
     */
    public static final String getInterfaceById = "/api/interface/get";


    /**
     * 根据id获取接口详细信息
     */
    public static final String updateInterface = "/api/interface/up";



}
