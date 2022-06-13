package com.yjc.entity;

public class Order {
    private int amount;//原始订单金额
    private int score;//积分

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", score=" + score +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
