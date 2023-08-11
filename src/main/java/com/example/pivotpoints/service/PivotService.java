package com.example.pivotpoints.service;

import com.example.pivotpoints.dao.PivotDataAccess;
import com.example.pivotpoints.model.PivotResponse;
import com.example.pivotpoints.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PivotService {

    private PivotDataAccess pivotDataAccess;

    @Autowired
    public PivotService(PivotDataAccess pivotDataAccess) {
        this.pivotDataAccess = pivotDataAccess;
    }

    public PivotResponse getPivotPoints(String date, int days) {
        List<StockPrice> result = new ArrayList<>();
        List<StockPrice> stocks = pivotDataAccess.getStockPrice();
        int index = IntStream.range(0, stocks.size())
                .filter(i -> stocks.get(i).getDate().equals(date))
                .findFirst()
                .orElse(-1);
        if (index == -1 || days < 1)
            return new PivotResponse("invalid argument date=" + date + " days=" + days);
        result.addAll(stocks.subList(Math.max(0, index - days), Math.min(index + days, stocks.size() - 1)));
        return calcPivotPoints(result, date, days);
    }

    private PivotResponse calcPivotPoints(List<StockPrice> stocks, String date, int days) {
        PivotResponse pivotResponse = new PivotResponse(null);
        int index = IntStream.range(0, stocks.size())
                .filter(i -> stocks.get(i).getDate().equals(date))
                .findFirst()
                .orElseThrow();
        pivotResponse.setPivotLow(stocks.stream().filter(s -> !s.getDate().equals(date)).allMatch(s -> s.getLow() > stocks.get(index).getLow()));
        pivotResponse.setPivotHigh(stocks.stream().filter(s -> !s.getDate().equals(date)).allMatch(s -> s.getHigh() < stocks.get(index).getHigh()));
        if (pivotResponse.isPivotHigh()) {
            pivotResponse.setStockPrice(stocks.get(index));
        }
        return pivotResponse;
    }
}
