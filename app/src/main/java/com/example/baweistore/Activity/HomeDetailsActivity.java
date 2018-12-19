package com.example.baweistore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.ggy.Dao.UserDao;
import com.example.baweistore.Adapter.Home_ViewPager_Details;
import com.example.baweistore.Bean.AddShopCar;
import com.example.baweistore.Bean.Home_details;
import com.example.baweistore.Bean.ShopList;
import com.example.baweistore.Bean.User;
import com.example.baweistore.MyApplication;
import com.example.baweistore.MyScrollView;
import com.example.baweistore.Okutils;
import com.example.baweistore.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class HomeDetailsActivity extends AppCompatActivity {

    private ViewPager details_viewpager_show;
    private TextView details_textview_shownum;
    private TextView details_textview_sprice;
    private TextView details_textview_sold;
    private TextView details_textview_title;
    private TextView details_textview_Weight;
    private SimpleDraweeView details_Image_details;
    private TextView details_textview_describe;
    private SimpleDraweeView details_Image_describe;
    private RecyclerView details_recview_comments;
    private TextView details_textview_comments;
    private MyScrollView details_scroll_changecolor;
    private ImageView details_image_return;
    private TextView details_text_goodsT;
    private TextView details_text_detailsT;
    private TextView details_text_commentsT;
    private TextView details_text_goods;
    private TextView details_text_details;
    private TextView details_text_comments;
    private RelativeLayout details_relative_changer;
    private RelativeLayout details_relat_changecolor;
    private RelativeLayout details_relative_addshoppingcar;
    private RelativeLayout details_relative_pay;
    private String comid;
    private String[] split;

    private int count;
    private String mid;
    private String pid;
    private ImageView add_shopcar;
    private ImageView img_mai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        initView();

    }

    private void initView() {
        details_viewpager_show = (ViewPager) findViewById(R.id.details_viewpager_show);
        details_textview_shownum = (TextView) findViewById(R.id.details_textview_shownum);
        details_textview_sprice = (TextView) findViewById(R.id.details_textview_sprice);
        details_textview_sold = (TextView) findViewById(R.id.details_textview_sold);
        details_textview_title = (TextView) findViewById(R.id.details_textview_title);
        details_textview_Weight = (TextView) findViewById(R.id.details_textview_Weight);
        details_Image_details = (SimpleDraweeView) findViewById(R.id.details_Image_details);
        details_textview_describe = (TextView) findViewById(R.id.details_textview_describe);
        details_Image_describe = (SimpleDraweeView) findViewById(R.id.details_Image_describe);
        details_recview_comments = (RecyclerView) findViewById(R.id.details_recview_comments);
        details_textview_comments = (TextView) findViewById(R.id.details_textview_comments);
        details_scroll_changecolor = (MyScrollView) findViewById(R.id.details_scroll_changecolor);
        details_image_return = (ImageView) findViewById(R.id.details_image_return);
        details_text_goodsT = (TextView) findViewById(R.id.details_text_goodsT);
        details_text_detailsT = (TextView) findViewById(R.id.details_text_detailsT);
        details_text_commentsT = (TextView) findViewById(R.id.details_text_commentsT);
        details_text_goods = (TextView) findViewById(R.id.details_text_goods);
        details_text_details = (TextView) findViewById(R.id.details_text_details);
        details_text_comments = (TextView) findViewById(R.id.details_text_comments);
        details_relative_changer = (RelativeLayout) findViewById(R.id.details_relative_changer);
        details_relat_changecolor = (RelativeLayout) findViewById(R.id.details_relat_changecolor);
        details_relative_addshoppingcar = (RelativeLayout) findViewById(R.id.details_relative_addshoppingcar);
        details_relative_pay = (RelativeLayout) findViewById(R.id.details_relative_pay);


        details_image_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        //热销商品
        Intent intent = getIntent();
        comid = intent.getStringExtra("id");

        //Toast.makeText(this, comid, Toast.LENGTH_SHORT).show();


        getdata();

        mid = intent.getStringExtra("mid");
        //Toast.makeText(this, mid, Toast.LENGTH_SHORT).show();
        pid = intent.getStringExtra("pid");
        getmdata();
        //Toast.makeText(this, pid, Toast.LENGTH_SHORT).show();
        getpdata();

        add_shopcar = (ImageView) findViewById(R.id.add_shopcar);


        img_mai = (ImageView) findViewById(R.id.img_mai);

    }

    private void getpdata() {
        String url = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=" + pid;
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Home_details home_details = new Gson().fromJson(s, Home_details.class);
                setmpData(home_details);


            }


            @Override
            public void failed(Exception e) {

            }
        });
    }

    private void setmpData(final Home_details home_details) {
        details_textview_sprice.setText("￥" + home_details.getResult().getPrice());
        details_textview_sold.setText("已售" + home_details.getResult().getSaleNum() + "件");
        details_textview_title.setText(home_details.getResult().getCommodityName());
        details_textview_Weight.setText(home_details.getResult().getWeight() + "kg");
        details_textview_describe.setText(home_details.getResult().getDescribe());

        String Pictures = home_details.getResult().getPicture().trim();
        split = Pictures.split(",");

        details_Image_details.setImageURI(split[0]);
        details_Image_describe.setImageURI(split[1]);
        Home_ViewPager_Details home_viewPager_details = new Home_ViewPager_Details(this, split);
        count = home_viewPager_details.getCount();

        details_viewpager_show.setAdapter(home_viewPager_details);
        add_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = home_details.getResult().getCommodityId();
                String urls = "";
                UserDao userDao = MyApplication.getDaoSeesion().getUserDao();
                User user = new User();
                user.setCid(cid);
                user.setCount(1);
                userDao.insert(user);
                List<User> users = userDao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    int num = 1;
                    /*if (users.get(i).getCid()==cid){

                        Toast.makeText(HomeDetailsActivity.this, "买的够多了，别买了", Toast.LENGTH_SHORT).show();
                        return;

                    }*/
                    String url = "{commodityId:" + users.get(i).getCid() + ",count:" + users.get(i).getCount() + "},";
                    urls += url;
                }
                String substring = urls.substring(0, urls.length() - 1);
                String u = "[" + substring + "]";
                String path = "http://172.17.8.100/small/order/verify/v1/syncShoppingCart";
                HashMap<String, String> map = new HashMap<>();
                map.put("data", u);
                Okutils.getInstance().doHeadPut(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        AddShopCar addShopCar = new Gson().fromJson(s, AddShopCar.class);
                        String message = addShopCar.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });
            }
        });

        img_mai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = home_details.getResult().getCommodityId();
                int price = home_details.getResult().getPrice();
                String path="http://172.17.8.100/small/order/verify/v1/createOrder";
                HashMap<String, String> map = new HashMap<>();
                map.put("totalPrice",price+"");
                map.put("addressId","112");
                //map.put("data","[{"+"commodityId"+":"+mid+","+"count"+":"+"1"+"}]");
                map.put("orderInfo","[{"+"commodityId"+":"+cid+","+"amount"+":"+"1"+"}]");
                Okutils.getInstance().doHeadPost(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        ShopList shopList = new Gson().fromJson(s, ShopList.class);
                        String message = shopList.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });

            }
        });
    }

    private void getmdata() {

        String url = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=" + mid;
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Home_details home_detailsa = new Gson().fromJson(s, Home_details.class);
                setmData(home_detailsa);

            }


            @Override
            public void failed(Exception e) {

            }
        });
    }

    private void setmData(final Home_details home_details) {
        details_textview_sprice.setText("￥" + home_details.getResult().getPrice());
        details_textview_sold.setText("已售" + home_details.getResult().getSaleNum() + "件");
        details_textview_title.setText(home_details.getResult().getCommodityName());
        details_textview_Weight.setText(home_details.getResult().getWeight() + "kg");
        details_textview_describe.setText(home_details.getResult().getDescribe());


        String Pictures = home_details.getResult().getPicture().trim();
        split = Pictures.split(",");

        details_Image_details.setImageURI(split[0]);
        details_Image_describe.setImageURI(split[1]);
        Home_ViewPager_Details home_viewPager_details = new Home_ViewPager_Details(this, split);
        count = home_viewPager_details.getCount();

        details_viewpager_show.setAdapter(home_viewPager_details);
        add_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urls = "";
                int sid = home_details.getResult().getCommodityId();
                UserDao userDao = MyApplication.getDaoSeesion().getUserDao();
                User user = new User();
                user.setCid(sid);
                user.setCount(1);
                userDao.insert(user);
                List<User> users = userDao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    int num = 1;
                    /*if (users.get(i).getCid()==sid){

                        Toast.makeText(HomeDetailsActivity.this, "买的够多了，别买了", Toast.LENGTH_SHORT).show();
                        return;

                    }*/
                    String url = "{commodityId:" + users.get(i).getCid() + ",count:" + users.get(i).getCount() + "},";
                    urls += url;
                }
                String substring = urls.substring(0, urls.length() - 1);
                String u = "[" + substring + "]";

                String path = "http://172.17.8.100/small/order/verify/v1/syncShoppingCart";
                HashMap<String, String> map = new HashMap<>();
                map.put("data", u);
                Okutils.getInstance().doHeadPut(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        AddShopCar addShopCar = new Gson().fromJson(s, AddShopCar.class);
                        String message = addShopCar.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });
            }
        });
        img_mai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sid = home_details.getResult().getCommodityId();
                int price = home_details.getResult().getPrice();
                String path="http://172.17.8.100/small/order/verify/v1/createOrder";
                HashMap<String, String> map = new HashMap<>();
                map.put("totalPrice",price+"");
                map.put("addressId","112");
                //map.put("data","[{"+"commodityId"+":"+mid+","+"count"+":"+"1"+"}]");
                map.put("orderInfo","[{"+"commodityId"+":"+sid+","+"amount"+":"+"1"+"}]");
                Okutils.getInstance().doHeadPost(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        ShopList shopList = new Gson().fromJson(s, ShopList.class);
                        String message = shopList.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });
            }
        });

    }

    private void getdata() {


        String url = "http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId=" + comid;
        Okutils.getInstance().doGet(url, new Okutils.OkHttpCallBack() {
            @Override
            public void success(String s) {
                Home_details home_details = new Gson().fromJson(s, Home_details.class);
                setData(home_details);

            }


            @Override
            public void failed(Exception e) {

            }
        });
    }

    private void setData(final Home_details home_details) {
        details_textview_sprice.setText("￥" + home_details.getResult().getPrice());
        details_textview_sold.setText("已售" + home_details.getResult().getSaleNum() + "件");
        details_textview_title.setText(home_details.getResult().getCommodityName());
        details_textview_Weight.setText(home_details.getResult().getWeight() + "kg");
        details_textview_describe.setText(home_details.getResult().getDescribe());

        String Pictures = home_details.getResult().getPicture().trim();
        split = Pictures.split(",");

        details_Image_details.setImageURI(split[0]);
        details_Image_describe.setImageURI(split[1]);
        Home_ViewPager_Details home_viewPager_details = new Home_ViewPager_Details(this, split);
        count = home_viewPager_details.getCount();

        details_viewpager_show.setAdapter(home_viewPager_details);
        add_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urls = "";
                int mid = home_details.getResult().getCommodityId();
                UserDao userDao = MyApplication.getDaoSeesion().getUserDao();
                User user = new User();
                user.setCid(mid);
                user.setCount(1);
                userDao.insert(user);
                List<User> users = userDao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    int num = 1;
                   /* if (users.get(i).getCid()==mid){

                        Toast.makeText(HomeDetailsActivity.this, "买的够多了，别买了", Toast.LENGTH_SHORT).show();
                       return;
                    }*/
                    String url = "{commodityId:" + users.get(i).getCid() + ",count:" + users.get(i).getCount() + "},";
                    urls += url;
                }
                String substring = urls.substring(0, urls.length() - 1);
                String u = "[" + substring + "]";
                String path = "http://172.17.8.100/small/order/verify/v1/syncShoppingCart";

                HashMap<String, String> map = new HashMap<>();
                map.put("data", u);
                Okutils.getInstance().doHeadPut(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        AddShopCar addShopCar = new Gson().fromJson(s, AddShopCar.class);
                        String message = addShopCar.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void failed(Exception e) {

                    }
                });
            }
        });
        img_mai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mid = home_details.getResult().getCommodityId();
                int price = home_details.getResult().getPrice();
                String path="http://172.17.8.100/small/order/verify/v1/createOrder";
                HashMap<String, String> map = new HashMap<>();
                map.put("totalPrice",price+"");
                map.put("addressId","112");
                //map.put("data","[{"+"commodityId"+":"+mid+","+"count"+":"+"1"+"}]");
                map.put("orderInfo","[{"+"commodityId"+":"+mid+","+"amount"+":"+"1"+"}]");
                Okutils.getInstance().doHeadPost(path, map, new Okutils.OkHttpCallBack() {
                    @Override
                    public void success(String s) {
                        ShopList shopList = new Gson().fromJson(s, ShopList.class);
                        String message = shopList.getMessage();
                        Toast.makeText(HomeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(Exception e) {

                    }
                });
            }
        });
    }


}
