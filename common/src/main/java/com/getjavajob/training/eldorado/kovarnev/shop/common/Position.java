package com.getjavajob.training.eldorado.kovarnev.shop.common;

public class Position extends BaseEntity {
    private double price;
    private int count;

    public Position(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public Position() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Position position = (Position) o;

        if (Double.compare(position.price, price) != 0) return false;
        return count == position.count;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "price=" + price +
                ", count=" + count +
                '}';
    }
}
