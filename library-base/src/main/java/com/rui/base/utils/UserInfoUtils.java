package com.rui.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.rui.base.global.SPKeyGlobal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.rui.mvvmlazy.http.LoginUnNormalBean;
import com.rui.mvvmlazy.utils.GlobalUtils;

public class UserInfoUtils {
    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private String SHARED_NAME = "spname";//sp的文件名 自定义


    private UserInfoUtils() {
        share = GlobalUtils.getContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = share.edit();
    }

    /**
     * 单例模式
     */
    private static UserInfoUtils instance;//单例模式 双重检查锁定

    public static UserInfoUtils getInstance() {
        if (instance == null) {
            synchronized (UserInfoUtils.class) {
                if (instance == null) {
                    instance = new UserInfoUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 保存对象
     * 针对复杂类型存储<对象>
     * 注意：要保存的对象必须序列化
     *
     * @param key
     * @param object
     */
    private void setUserInfo(String key, LoginUnNormalBean object) {
        //创建字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*
        对象输出流
        ObjectOutputStream 是实现序列化的关键类，它可以将对象转换为二进制流
         */
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            //将对象写进该流中
            out.writeObject(object);
            //将二进制数据转换为字符串
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setUserInfo(LoginUnNormalBean object) {
        setUserInfo(SPKeyGlobal.USER_INFO, object);
    }

    public LoginUnNormalBean getUserInfo() {
        return getUserInfo(SPKeyGlobal.USER_INFO);
    }

    /**
     * 根据字符串key获取对象
     *
     * @param key
     * @param
     * @return UserInfoEntity
     */
    private LoginUnNormalBean getUserInfo(String key) {
        if (share.contains(key)) {
            //获取该key对象的字符串值
            String objectVal = share.getString(key, null);
            //将字符串转换为二进制数据
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                return (LoginUnNormalBean) ois.readObject();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public void setAccount(String account) {
        editor.putString(SPKeyGlobal.ACCOUNT, account);
        editor.commit();
    }
    public String getAccount() {
        String auth = share.getString(SPKeyGlobal.ACCOUNT, null);
        return auth;
    }
    public void setPsd(String psd) {
        editor.putString(SPKeyGlobal.PSD, psd);
        editor.commit();
    }
    public String getPsd() {
        String auth = share.getString(SPKeyGlobal.PSD, null);
        return auth;
    }
    public String getPhone() {
        String auth = share.getString(SPKeyGlobal.PHONE, null);
        return auth;
    }
    public void setPhone(String phone) {
        editor.putString(SPKeyGlobal.PHONE, phone);
        editor.commit();
    }
    public void setAuth(String data) {
        editor.putString(SPKeyGlobal.AUTH, data);
        editor.commit();
    }

    public String getAuth() {
        String auth = share.getString(SPKeyGlobal.AUTH, "");
        return auth;
    }


    public void remove(String key) {
        editor.remove(key);
        editor.commit();

    }

    /**
     * 判断用户是否登录，用户未登录，直接跳转到登录界面
     */
    public boolean isLogin() {

        if (null == getUserInfo() || TextUtils.isEmpty(getUserInfo().getAccess_token())) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 判断用户是否登录，不跳转
     */
    public boolean isLogin2() {

        if (null == getUserInfo() || TextUtils.isEmpty(getUserInfo().getAccess_token())) {

            return false;
        } else {
            return true;
        }

    }
    /**
     * 退出登录
     */
    public void logOut() {
        remove(SPKeyGlobal.USER_INFO);
        remove(SPKeyGlobal.AUTH);
    }
}
