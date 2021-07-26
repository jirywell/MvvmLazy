package com.rui.base.router;

/**
 * ******************************
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * *******************************
 */
public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_HOME = HOME + "/Home";
        //推荐
        public static final String  PAGER_RECOMMEND=HOME+PAGER_HOME+"/Recommend";
        //热门
        public static final String  PAGER_HOT=HOME+PAGER_HOME+"/Hot";
        //关注
        public static final String  PAGER_ATTENTION=HOME+PAGER_HOME+"/Attention";
        //附近
        public static final String  PAGER_NEARBY=HOME+PAGER_HOME+"/NearBy";

    }
    /**
     * 资源组件
     */
    public static class Resource {
        private static final String RESOURCE = "/resource";
        /*资源*/
        public static final String PAGER_RESOURCE = RESOURCE + "/Resource";
    }
    /**
     * 发现组件
     */
    public static class Discover {
        private static final String DISCOVER = "/discover";
        /*发现*/
        public static final String PAGER_DISCOVER = DISCOVER + "/Discover";

        //我的圈子
        public static final String  ME_CIRCLE=DISCOVER+"/meCircle";
        //全部圈子
        public static final String  ALL_CIRCLE=DISCOVER+"/allCircle";

    }
    /**
     * 消息组件
     */
    public static class Message {
        private static final String MESSAGE = "/message";
        /*消息*/
        public static final String PAGER_MESSAGE = MESSAGE + "/Message";

    }
    /**
     * 用户组件
     */
    public static class User {

        private static final String USER = "/user";
        /*我的*/
        public static final String PAGER_MINE = USER + "/Mine";

    }

}
