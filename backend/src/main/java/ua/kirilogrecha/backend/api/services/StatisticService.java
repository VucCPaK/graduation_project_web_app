package ua.kirilogrecha.backend.api.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.kirilogrecha.backend.api.dto.Period;
import ua.kirilogrecha.backend.api.dto.Statistic;
import ua.kirilogrecha.backend.api.entities.EItem;
import ua.kirilogrecha.backend.api.entities.EOrder;
import ua.kirilogrecha.backend.api.repositories.ItemRepository;
import ua.kirilogrecha.backend.api.repositories.OrderRepository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    // for admin
    // get statistics by all goods in store
    public Resource getDataByPeriod(Period period) {
        List<EOrder> orders =  orderRepository.getListByPeriod(period.getStartDate(), period.getEndDate());
        List<Statistic> itemsInStatistic = new ArrayList<>();

        orders.forEach(o ->
                o.getItems().forEach(i -> {
                    EItem item = i.getEItem();

                    BigDecimal TAX = BigDecimal.valueOf(0.18);

                    long amountStored = item.getAmount();
                    long quantity = i.getQuantity();
                    String name = item.getName();
                    BigDecimal proceeds = item.getPrice().multiply(BigDecimal.valueOf(quantity));
                    BigDecimal profit = proceeds.subtract(proceeds.multiply(TAX));

                    Optional<Statistic> containsInStatistic = itemsInStatistic.stream()
                            .filter(it -> name.equals(it.getName()))
                            .findFirst();


                    if (containsInStatistic.isPresent()) {
                        var statistic = containsInStatistic.get();
                        statistic.setQuantitySold(statistic.getQuantitySold() + quantity);
                        statistic.setProceeds(statistic.getProceeds().add(proceeds));
                        statistic.setProfit(statistic.getProfit().add(profit));
                    } else {
                        itemsInStatistic.add(new Statistic(name, amountStored, quantity, proceeds, profit));
                    }
                }));

        Workbook workbook = createStatisticTable(itemsInStatistic);

        return getResourceFromByteArray(workbook);
    }

    // for supplier
    // get statistic by all goods that possesses supplier
    public Resource getDataByPeriod(Period period, String id) {
        List<EOrder> orders =  orderRepository.getListByPeriod(period.getStartDate(), period.getEndDate());
        List<Statistic> itemsInStatistic = new ArrayList<>();

        for (var order : orders) {
            for (var itemInOrder : order.getItems()) {
                EItem item = itemInOrder.getEItem();

                if (item.getId().equals(id)) {
                    BigDecimal TAX = BigDecimal.valueOf(0.18);

                    long amountStored = item.getAmount();
                    long quantity = itemInOrder.getQuantity();
                    String name = item.getName();
                    BigDecimal proceeds = item.getPrice().multiply(BigDecimal.valueOf(quantity));
                    BigDecimal profit = proceeds.subtract(proceeds.multiply(TAX));

                    Optional<Statistic> containsInStatistic = itemsInStatistic.stream()
                            .filter(it -> name.equals(it.getName()))
                            .findFirst();


                    if (containsInStatistic.isPresent()) {
                        var statistic = containsInStatistic.get();
                        statistic.setQuantitySold(statistic.getQuantitySold() + quantity);
                        statistic.setProceeds(statistic.getProceeds().add(proceeds));
                        statistic.setProfit(statistic.getProfit().add(profit));
                    } else {
                        itemsInStatistic.add(new Statistic(name, amountStored, quantity, proceeds, profit));
                    }
                }
            }
        }

        Workbook workbook = createStatisticTable(itemsInStatistic);

        return getResourceFromByteArray(workbook);
    }

    private Workbook createStatisticTable(List<Statistic> statistics) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create a header row
        String[] columns = {"Name", "Amount stored", "Quantity sold", "Proceeds", "Profit"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Fill data
        int rowNum = 1;
        for (var obj : statistics) {
            Row row = sheet.createRow(rowNum++);
            System.out.println(obj.getName());
            row.createCell(0).setCellValue(obj.getName());
            System.out.println(obj.getAmountStored());
            row.createCell(1).setCellValue(obj.getAmountStored());
            System.out.println(obj.getQuantitySold());
            row.createCell(2).setCellValue(obj.getQuantitySold());
            System.out.println(obj.getProceeds());
            row.createCell(3).setCellValue(obj.getProceeds().doubleValue());
            System.out.println(obj.getProfit());
            row.createCell(4).setCellValue(obj.getProfit().doubleValue());
        }

        // Resize columns to fit the content
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    private ByteArrayResource getResourceFromByteArray(Workbook workbook) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }


        return new ByteArrayResource(bos.toByteArray());
    }
}
