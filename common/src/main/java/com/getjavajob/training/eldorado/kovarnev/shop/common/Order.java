package com.getjavajob.training.eldorado.kovarnev.shop.common;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseEntity {
    private List<Position> positions;

    public Order() {
        positions = new ArrayList<>();
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        return positions.equals(order.positions);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "positions=" + positions +
                '}';
    }
}
