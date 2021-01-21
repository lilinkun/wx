package com.android.wx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.wx.model.DaoMaster;
import com.android.wx.model.DaoSession;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.MenuInfoDao;
import com.android.wx.model.MenuTypeBean;
import com.android.wx.model.MenuTypeBeanDao;
import com.android.wx.model.Table;
import com.android.wx.model.TableDao;
import com.android.wx.model.UserInfo;
import com.android.wx.model.UserInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBManager {
    private final static String dbName = "wx_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }
    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入登录用户
     * @param userInfo
     */
    public void insertUserInfo(UserInfo userInfo) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserInfoDao userInfoDao = daoSession.getUserInfoDao();
        userInfoDao.insert(userInfo);
    }

    /**
     * 查询登录用户
     * @param psd
     */
    public List<UserInfo> queryUserInfo(String psd) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserInfoDao wxUserInfoDao = daoSession.getUserInfoDao();
        QueryBuilder<UserInfo> qb = wxUserInfoDao.queryBuilder().where(UserInfoDao.Properties.Pwd.eq(psd));
        List<UserInfo> list = qb.list();
        return list;
    }

    /**
     * 插入菜品类型
     * @param menuTypeBean
     */
    public void insertMenuTypeBean(MenuTypeBean menuTypeBean) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuTypeBeanDao menuTypeBeanDao = daoSession.getMenuTypeBeanDao();
        menuTypeBeanDao.insert(menuTypeBean);
    }

    /**
     * 查询所有菜
     */
    public List<MenuTypeBean> queryMenuTypeBean() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuTypeBeanDao menuTypeBeanDao = daoSession.getMenuTypeBeanDao();
        QueryBuilder<MenuTypeBean> qb = menuTypeBeanDao.queryBuilder();
        List<MenuTypeBean> list = qb.list();
        return list;
    }

    /**
     * 查询菜品类型下所有菜
     * @param menuTypeBean
     */
    public List<MenuTypeBean> queryMenuTypeBean(String menuTypeBean) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuTypeBeanDao menuTypeBeanDao = daoSession.getMenuTypeBeanDao();
        QueryBuilder<MenuTypeBean> qb = menuTypeBeanDao.queryBuilder().where(MenuTypeBeanDao.Properties.MenuType.eq(menuTypeBean));
        List<MenuTypeBean> list = qb.list();
        return list;
    }

    /**
     * 插入餐桌信息
     * @param table
     */
    public void insertTable(Table table) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TableDao tableDao = daoSession.getTableDao();
        tableDao.insert(table);
    }

    /**
     * 查询餐桌信息
     */
    public List<Table> queryTable() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TableDao tableDao = daoSession.getTableDao();
        QueryBuilder<Table> qb = tableDao.queryBuilder();
        List<Table> list = qb.list();
        return list;
    }

}
