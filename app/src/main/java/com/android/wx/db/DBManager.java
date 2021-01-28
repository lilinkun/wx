package com.android.wx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.wx.model.DaoMaster;
import com.android.wx.model.DaoSession;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.MenuInfoDao;
import com.android.wx.model.MenuTypeBean;
import com.android.wx.model.MenuTypeBeanDao;
import com.android.wx.model.OrderInfoBean;
import com.android.wx.model.OrderInfoBeanDao;
import com.android.wx.model.Table;
import com.android.wx.model.TableDao;
import com.android.wx.model.UserInfo;
import com.android.wx.model.UserInfoDao;
import com.android.wx.model.VipInfoBean;
import com.android.wx.model.VipInfoBeanDao;

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
     * 修改餐桌信息
     * @param table
     */
    public void updateTable(Table table) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TableDao tableDao = daoSession.getTableDao();
        tableDao.update(table);
    }

    /**
     * 查询餐桌信息
     */
    public List<Table> queryTable(String tableNum) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TableDao tableDao = daoSession.getTableDao();
        QueryBuilder<Table> qb = tableDao.queryBuilder().where(TableDao.Properties.FloorName.eq(tableNum));
        List<Table> list = qb.list();
        return list;
    }


    /**
     * 插入订单信息
     * @param info
     */
    public void insertOrderInfo(MenuInfo info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuInfoDao menuInfoDao = daoSession.getMenuInfoDao();
        menuInfoDao.insert(info);
    }

    /**
     * 修改订单信息
     * @param info
     */
    public void updateOrderInfo(MenuInfo info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuInfoDao menuInfoDao = daoSession.getMenuInfoDao();
        menuInfoDao.insert(info);
    }

    /**
     * 查询订单信息
     * @param orderId
     */
    public List<MenuInfo> queryOrderInfo(String orderId) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuInfoDao menuInfoDao = daoSession.getMenuInfoDao();
        QueryBuilder<MenuInfo> qb = menuInfoDao.queryBuilder().where(MenuInfoDao.Properties.OrderId.eq(orderId));
        List<MenuInfo> list = qb.list();
        return list;
    }

    /**
     * 查询所有订单信息
     */
    public List<MenuInfo> queryOrderInfo() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        MenuInfoDao menuInfoDao = daoSession.getMenuInfoDao();
        QueryBuilder<MenuInfo> qb = menuInfoDao.queryBuilder();
        List<MenuInfo> list = qb.list();
        return list;
    }

    /**
     * 插入订单信息
     * @param info
     */
    public void insertOrder(OrderInfoBean info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        OrderInfoBeanDao orderInfoBeanDao = daoSession.getOrderInfoBeanDao();
        orderInfoBeanDao.insert(info);
    }


    /**
     * 查询所有订单信息
     */
    public List<OrderInfoBean> queryOrder() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        OrderInfoBeanDao orderInfoBeanDao = daoSession.getOrderInfoBeanDao();
        QueryBuilder<OrderInfoBean> qb = orderInfoBeanDao.queryBuilder();
        List<OrderInfoBean> list = qb.list();
        return list;
    }


    /**
     * 插入会员信息
     * @param info
     */
    public void insertVipCard(VipInfoBean info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        VipInfoBeanDao vipInfoBeanDao = daoSession.getVipInfoBeanDao();
        vipInfoBeanDao.insert(info);
    }

    /**
     * 通过会员号查询会员信息
     */
    public List<VipInfoBean> queryVipCard(String vipCrad) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        VipInfoBeanDao vipInfoBeanDao = daoSession.getVipInfoBeanDao();
        QueryBuilder<VipInfoBean> qb = vipInfoBeanDao.queryBuilder().where(VipInfoBeanDao.Properties.CardId.eq(vipCrad));
        List<VipInfoBean> list = qb.list();
        return list;
    }

    /**
     * 查询所有会员信息
     */
    public List<VipInfoBean> queryVipCard() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        VipInfoBeanDao vipInfoBeanDao = daoSession.getVipInfoBeanDao();
        QueryBuilder<VipInfoBean> qb = vipInfoBeanDao.queryBuilder();
        List<VipInfoBean> list = qb.list();
        return list;
    }

}
