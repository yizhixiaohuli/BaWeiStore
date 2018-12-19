package com.example.baweistore.Fragment;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.baweistore.Bean.ShopCar;
import com.example.baweistore.R;
import com.example.baweistore.ShopAddView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
class ShopMadapter extends RecyclerSwipeAdapter<ShopMadapter.ViewHolder> {
    Context context;
    ArrayList<ShopCar.ResultBean> list;
    List<Bean_Cart_Checkbox> ckList= new ArrayList<>();
    public ShopMadapter(Context context, ArrayList<ShopCar.ResultBean> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shopcar_item,null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.icon.setImageURI(Uri.parse(list.get(position).getPic()));
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥"+list.get(position).getPrice());
        holder.mAddSubView.setNumber(list.get(position).getCount());
        holder.ck.setChecked(ckList.get(position).isCk());
        //设置侧滑显示模式
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        //设置侧滑显示位置方向
        //itemViewHold.parentLayout.addDrag(SwipeLayout.DragEdge.Right,itemViewHold.bottom_wrapper);
        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                //实现动画效果展现隐藏层
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.bottom_wrapper));
            }
        });

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeLayout.close();//隐藏侧滑菜单区域
                int position = holder.getLayoutPosition();//在增加数据或者减少数据时候，position和index就不一样了
                    mCartListener.onDeleteClick(position);
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();


            }
        });

        //点击商品回调
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCartListener!=null){
                    boolean isGoodsOk=holder.ck.isChecked();
                    ckList.get(position).setCk(isGoodsOk);
                    mCartListener.GoodsChange();

                }
            }
        });
        //点击商品数量时
        holder.mAddSubView.setOnNumberChangeListener(new ShopAddView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int num) {
                if(mCartListener!=null){
                    mCartListener.NumChange(position,num);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list.size()>0){
            for (int i = 0; i <list.size(); i++) {
                ckList.add(new Bean_Cart_Checkbox(false));
                if(ckList.size()==list.size()){
                    break;
                }
            }
        }
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView icon;
        private TextView name,price;
        private ShopAddView mAddSubView;
        private CheckBox ck;
        SwipeLayout swipeLayout;
        LinearLayout bottom_wrapper;//侧滑区域
        LinearLayout mTop;//置顶
        LinearLayout mDelete;//删除
        LinearLayout listItemLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.mycart_simple);
            name=itemView.findViewById(R.id.mycart_name);
            price=itemView.findViewById(R.id.mycart_price);
            ck=itemView.findViewById(R.id.mycart_checkbot);
            mAddSubView=itemView.findViewById(R.id.mycart_addsub);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipeLayout);
            bottom_wrapper = (LinearLayout)itemView.findViewById(R.id.bottom_wrapper);
            mDelete = (LinearLayout)itemView.findViewById(R.id.swipe_bottom_del_layout);
            listItemLayout = (LinearLayout) itemView.findViewById(R.id.surfaceView);

        }
    }


    public interface ShoppingListener{

        //商品选中状态改变
        void GoodsChange();
        //商品数量改变
        void NumChange(int index,int num);

        void onDeleteClick(int position);
    }



    public ShoppingListener mCartListener;

    public void setCartListener(ShoppingListener cartListener) {
        mCartListener = cartListener;
    }










    //计算总价
    public float setZongji() {
        float zong=0.0f;
        for (int i = 0; i <list.size() ; i++) {
            if(ckList.get(i).isCk()==true){
                int num=list.get(i).getCount();
                float price=list.get(i).getPrice();
                zong+=num*price;
            }
        }
        return zong;
    }
    //改变商品数量
    public void changeGoodsNum(int index, int num) {
        list.get(index).setCount(num);
    }

    public void setAllGoodsIsSelected(boolean b) {
        for (int i = 0; i < ckList.size(); i++) {
            ckList.get(i).setCk(b?true:false);

        }
    }
    //是否所有商品被选中
    public boolean isAllGoodsIsSelected() {
        for (int i = 0; i < list.size(); i++) {
            if(ckList.get(i).isCk()==false){
                return false;
            }
        }
        return true;
    }

    //是否所有商品被选中
    public boolean isAllGoodsIsSelected1() {

        mCartListener.GoodsChange();

        for (int i = 0; i < list.size(); i++) {
            if(ckList.get(i).isCk()==false){
                return false;
            }
        }
        return true;
    }

    //设置商品数量
    public int setNum() {
        int num=0;
        for (int i = 0; i <list.size() ; i++) {
            if(ckList.get(i).isCk()==true){
                num+=list.get(i).getCount();
            }
        }
        return num;
    }

    //定义接口回调

}
