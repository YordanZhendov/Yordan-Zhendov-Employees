package com.example.sma;

import com.example.sma.entity.DataFromCSV;
import com.example.sma.entity.DataFromCSVRepository;
import com.example.sma.entity.ServiceLogic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@SpringBootApplication
public class SmaApplication {


    public static void main(String[] args) {
        SpringApplication.run(SmaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DataFromCSVRepository dataFromCSVRepository, ServiceLogic serviceLogic) {
        return args -> {
            try (Scanner scanner = new Scanner(new File("C:\\Workspace\\sma\\src\\main\\resources\\static\\sirma.txt"))) {

                while (scanner.hasNext()) {
                    String currentRow = scanner.nextLine();
                    String[] currentInformation = currentRow.split(",");
                    String employeeId = currentInformation[0];
                    int projectId = Integer.parseInt(currentInformation[1]);
                    String dateFromCSV = currentInformation[2];
                    String dateToCSV = currentInformation[3];

                    LocalDate parseDateFrom = LocalDate.parse(dateFromCSV);
                    LocalDate parseDateTo;

                    DataFromCSV dataFromCSV = new DataFromCSV();
                    dataFromCSV.setEmployeeId(employeeId);
                    dataFromCSV.setProjectId(projectId);
                    dataFromCSV.setDateFrom(parseDateFrom);

                    if(dateToCSV.equals("NULL")){
                        parseDateTo = LocalDate.now();
                    }else {
                        parseDateTo = LocalDate.parse(dateToCSV);
                    }

                    dataFromCSV.setDateTo(parseDateTo);

                    long totalDaysInProject = DAYS.between(parseDateFrom, parseDateTo);
                    dataFromCSV.setTotalDaysInProject(totalDaysInProject);
                    dataFromCSVRepository.save(dataFromCSV);
                }

            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }

            System.out.println(String.join(", ", serviceLogic.getOrderedEmployees()));
        };
    }


}
