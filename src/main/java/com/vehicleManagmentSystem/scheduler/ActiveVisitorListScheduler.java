package com.vehicleManagmentSystem.scheduler;

import com.vehicleManagmentSystem.entity.Visitors;
import com.vehicleManagmentSystem.repository.VisitorRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class ActiveVisitorListScheduler {
    @Autowired
    VisitorRepository visitorRepository;

    @Scheduled(cron = "0 0 23 * * *") // this perform At 11 PM.
    @Transactional
    void getListOfActiveVisitors() {
        List<String> typeOfVisitors = Arrays.asList("GUEST", "DELIVERY");
        List<Visitors> listOfActiveVisitors = visitorRepository.getListOfActiveVisitors(typeOfVisitors);
        LocalDate today = LocalDate.now();
        String path = "E:\\vehicalManagementSystem\\Visitor log";
        String fileName = "Visitors_history_log"+today+".xlsx";

        try {
            Workbook workbook = new XSSFWorkbook();
            FileOutputStream out = new FileOutputStream( new File(path +"//"+fileName));

            Sheet sheet = workbook.createSheet("ActiveVisitors");
            //This create header row
            Row headerRow = sheet.createRow(0);
             headerRow.createCell(0).setCellValue("Visitor Name");
             headerRow.createCell(1).setCellValue("Visitor Mobile No.");
             headerRow.createCell(2).setCellValue("Vehical Name");
             headerRow.createCell(3).setCellValue("Vehical Registration Number");
             headerRow.createCell(4).setCellValue("Visit Purpose");
             headerRow.createCell(5).setCellValue("In Time");
             headerRow.createCell(6).setCellValue("ResidentName");
             headerRow.createCell(7).setCellValue("Resident Flat No.");
          //fill the data into Rows
           int rowIdx = 1;
          for (Visitors visitor : listOfActiveVisitors) {
              Row row = sheet.createRow(rowIdx++);
              row.createCell(0).setCellValue(visitor.getVisitorName());
              row.createCell(1).setCellValue(visitor.getPhoneNumber());
              row.createCell(2).setCellValue(visitor.getVehicleName());
              row.createCell(3).setCellValue(visitor.getVehicleRegisterationNumber());
              row.createCell(4).setCellValue(visitor.getVisitPurpose());
              row.createCell(5).setCellValue(String.valueOf(visitor.getTimeIn()));
              row.createCell(6).setCellValue(visitor.getResident().getFName() + " " + visitor.getResident().getLName());
              row.createCell(7).setCellValue(visitor.getResident().getFlatNo());
          }
          workbook.write(out);
            System.err.println("Excel file created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
