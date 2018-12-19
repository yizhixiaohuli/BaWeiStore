package com.example.baweistore.Bean;

import java.util.List;

/**
 * date:2018/12/13
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class Home_search {

    /**
     * result : [{"commodityId":101,"commodityName":"vivo X23幻彩版 多套餐 全面屏拍照美颜超大广角摄影水滴屏智能4G 正品vivox23手机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/2/1.jpg","price":2798,"saleNum":0},{"commodityId":121,"commodityName":"佳能（Canon）EOS 3000D 入门级数码单反相机18-55mm IS II防抖镜头套装 32G内存卡 备用电池 三脚架摄影包等礼包版","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/zxj/1/1.jpg","price":3099,"saleNum":0},{"commodityId":179,"commodityName":"莎米特SUMMIT拉杆箱22英寸PC材质万向轮旅行箱行李箱PC154T4A可扩容","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/lgx/3/1.jpg","price":199,"saleNum":0},{"commodityId":113,"commodityName":"闪充充电器 9V-2A快充 适用 X21/X20/X9s/X9sPlus/X9/XPlay6/Xplay5/X7/X7Plus","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sjpj/7/1.jpg","price":85,"saleNum":0},{"commodityId":171,"commodityName":"HOTBOOM2018男士双肩包休闲背包潮流学生书包多功能笔记本商务14英寸电脑包5606 时尚灰","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/2/1.jpg","price":109,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 101
         * commodityName : vivo X23幻彩版 多套餐 全面屏拍照美颜超大广角摄影水滴屏智能4G 正品vivox23手机
         * masterPic : http://172.17.8.100/images/small/commodity/sjsm/sj/2/1.jpg
         * price : 2798
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
