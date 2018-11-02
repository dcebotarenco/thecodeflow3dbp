package com.codeflow.application.client;

import com.codeflow.application.client.container.Container;
import com.codeflow.application.client.stock.Stock;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Input {
    Container container;
    List<Stock> stock;

    public Input(Container container, List<Stock> stock) {
        this.container = container;
        this.stock = stock;
    }

    public Container getContainer() {
        return container;
    }

    public List<Stock> getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        return new EqualsBuilder()
                .append(container, input.container)
                .append(stock, input.stock)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(container)
                .append(stock)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Input{" +
                "container=" + container +
                ", articleDTOList=" + stock +
                '}';
    }
}
