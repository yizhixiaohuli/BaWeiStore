package com.example.baweistore.Bean;

import java.util.List;

/**
 * date:2018/12/14
 * author:别的小朋友(别的小朋友)
 * function:
 */
public class ShopCar {

    /**
     * result : [{"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","count":2,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499}]
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
         * commodityId : 3
         * commodityName : Lara style女神的魔盒全套彩妆
         * count : 2
         * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg
         * price : 3499
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
