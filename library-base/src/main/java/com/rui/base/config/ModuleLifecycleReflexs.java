package com.rui.base.config;

/**
 * ******************************
 * *@Author
 * *date ：zjr
 * *description:组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法,注意：以下模块中初始化的Module类不能被混淆
 * *******************************
 */
public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.rui.base.base.BaseModuleInit";
    private static final String HomeInit = "com.rui.home.ModuleInit";
    private static final String DemoInit = "com.rui.demo.ModuleInit";
    private static final String SignInit = "com.rui.sign.SignModuleInit";
    public static String[] initModuleNames = {BaseInit, HomeInit, DemoInit, SignInit};
}
