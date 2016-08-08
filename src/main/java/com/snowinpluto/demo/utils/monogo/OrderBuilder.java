package com.snowinpluto.demo.utils.monogo;

import static com.google.common.base.Preconditions.checkNotNull;

public final class OrderBuilder {

    private Order order;


    private OrderBuilder(int seq) {
        this.order = new Order().setSeq(seq);
    }

    public static OrderBuilder asc() {
        return new OrderBuilder(1);
    }

    public static OrderBuilder desc() {
        return new OrderBuilder(-1);
    }

    public Order sortBy(String fieldName) {
        return this.order.setField(checkNotNull(fieldName));
    }

    /**
     * 排序
     */
    public class Order {
        // 排序字段名
        private String field;

        // ASC or DESC
        // 1 : ASC
        // -1 : DESC
        private int seq;

        public Order() {}

        public String getField() {
            return field;
        }

        public Order setField(String field) {
            this.field = field;
            return this;
        }

        public int getSeq() {
            return seq;
        }

        public Order setSeq(int seq) {
            this.seq = seq;
            return this;
        }
    }
}
