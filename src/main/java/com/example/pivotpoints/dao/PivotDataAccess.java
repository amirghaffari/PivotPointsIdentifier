package com.example.pivotpoints.dao;

import com.example.pivotpoints.model.StockPrice;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PivotDataAccess {

    private static final String COMMA_DELIMITER = ",";

    public List<StockPrice> getStockPrice() {
        List<StockPrice> stocks = new ArrayList<>();
        List<List<String>> records = loadCsvFile();
        for (List<String> csvRecord : records) {
            StockPrice stock = new StockPrice(
                    csvRecord.get(0),
                    Float.parseFloat(csvRecord.get(1)),
                    Integer.parseInt(csvRecord.get(2)),
                    Float.parseFloat(csvRecord.get(3)),
                    Float.parseFloat(csvRecord.get(4)),
                    Float.parseFloat(csvRecord.get(5)));

            stocks.add(stock);
        }
        return stocks;
    }

    private List<List<String>> loadCsvFile() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/tesla-stock-price.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
