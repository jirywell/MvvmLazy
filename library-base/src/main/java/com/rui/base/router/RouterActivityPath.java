package com.rui.base.router;

/**
 * ******************************
 * *description: 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * *******************************
 */
public class RouterActivityPath {
    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN + "/Main";
        public static final String PAGER_TEST = MAIN + "/Test";
        //用户主界面
        public static final String USER_MAIN = MAIN + "/UserMain";
        public static final String PAGER_WEBVIEW = MAIN + "/webview";
        public static final String ABOUT_US = MAIN + "/aboutus";
    }

    /**
     * 身份验证组件
     */
    public static class Sign {
        private static final String SIGN = "/sign";
        public static final String PAGER_ACCOUNT_LOGIN = SIGN + "/accountLogin";
        public static final String PAGER_MOBILE_LOGIN = SIGN + "/mobileLogin";
    }

    /**
     * 用户组件
     */
    public static class Mine {
        private static final String MINE = "/mine";
        public static final String PAGER_OSNOTICE = MINE + "/OsNotice";
        public static final String POLICYPAGER = MINE + "/policyPage";
    }


    public static class Test {
        static final String TEST = "/test";

        public static final String TESTPAGER = TEST + "/testPage";

    }


}
