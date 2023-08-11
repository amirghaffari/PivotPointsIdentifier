package com.example.pivotpoints;

import com.example.pivotpoints.dao.PivotDataAccess;
import com.example.pivotpoints.model.PivotResponse;
import com.example.pivotpoints.model.StockPrice;
import com.example.pivotpoints.service.PivotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PivotCalculationTest {

    @InjectMocks
    PivotService pivotService;

    @Mock
    PivotDataAccess dao;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void getPivotPointsHighTest()
    {
        List<StockPrice> stockList = new ArrayList<>();
        StockPrice stock1 = new StockPrice("2018-10-10", 256.88f, 12781560, 264.61f, 295.51f, 247.77f);
        StockPrice stock2 = new StockPrice("2018-10-09", 262.8f, 12037780, 255.25f, 266.77f, 243.3f);
        StockPrice stock3 = new StockPrice("2018-10-08", 250.56f, 13371180, 264.52f, 267.7599f, 249f);
        StockPrice stock4 = new StockPrice("2018-10-05", 261.95f, 17900710, 274.65f, 284.88f, 240f);
        StockPrice stock5 = new StockPrice("2018-10-04", 281.83f, 9638885, 293.95f, 274f, 267.67f);
        StockPrice stock6 = new StockPrice("2018-10-03", 294.8f, 7982272, 303.33f, 204.6f, 201.57f);
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);
        stockList.add(stock4);
        stockList.add(stock5);
        stockList.add(stock6);
        when(dao.getStockPrice()).thenReturn(stockList);
        PivotResponse pivotResponse =pivotService.getPivotPoints("2018-10-05", 2);
        // the next 2 days high and previous 2 days high should be lower than 2018-10-05 high
        Assertions.assertTrue(pivotResponse.isPivotHigh());
    }

    @Test
    public void getPivotPointsLowTest()
    {
        List<StockPrice> stockList = new ArrayList<>();
        StockPrice stock1 = new StockPrice("2018-10-10", 256.88f, 12781560, 264.61f, 265.51f, 247.77f);
        StockPrice stock2 = new StockPrice("2018-10-09", 262.8f, 12037780, 255.25f, 296.77f, 283.3f);
        StockPrice stock3 = new StockPrice("2018-10-08", 250.56f, 13371180, 264.52f, 287.7599f, 279f);
        StockPrice stock4 = new StockPrice("2018-10-05", 261.95f, 17900710, 274.65f, 274.88f, 260f);
        StockPrice stock5 = new StockPrice("2018-10-04", 281.83f, 9638885, 293.95f, 294f, 267.67f);
        StockPrice stock6 = new StockPrice("2018-10-03", 294.8f, 7982272, 303.33f, 304.6f, 291.57f);
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);
        stockList.add(stock4);
        stockList.add(stock5);
        stockList.add(stock6);
        when(dao.getStockPrice()).thenReturn(stockList);
        PivotResponse pivotResponse =pivotService.getPivotPoints("2018-10-05", 2);
        // the next 2 days low and previous 2 days low should be higher than 2018-10-05 low
        Assertions.assertTrue(pivotResponse.isPivotLow());
    }

    @Test
    public void getPivotPointsErrorTest()
    {
        List<StockPrice> stockList = new ArrayList<>();
        StockPrice stock1 = new StockPrice("2018-10-10", 256.88f, 12781560, 264.61f, 265.51f, 247.77f);
        StockPrice stock2 = new StockPrice("2018-10-09", 262.8f, 12037780, 255.25f, 266.77f, 253.3f);
        StockPrice stock3 = new StockPrice("2018-10-08", 250.56f, 13371180, 264.52f, 267.7599f, 249f);
        StockPrice stock4 = new StockPrice("2018-10-05", 261.95f, 17900710, 274.65f, 274.88f, 260f);
        StockPrice stock5 = new StockPrice("2018-10-04", 281.83f, 9638885, 293.95f, 294f, 277.67f);
        StockPrice stock6 = new StockPrice("2018-10-03", 294.8f, 7982272, 303.33f, 304.6f, 291.57f);
        stockList.add(stock1);
        stockList.add(stock2);
        stockList.add(stock3);
        stockList.add(stock4);
        stockList.add(stock5);
        stockList.add(stock6);
        when(dao.getStockPrice()).thenReturn(stockList);
        PivotResponse pivotResponse =pivotService.getPivotPoints("2018-10-06", 2);
        // 2018-10-06 is not a valid date in the list
        Assertions.assertEquals(pivotResponse.getErrorMessage(), "invalid argument date=2018-10-06 days=2");
    }
}
