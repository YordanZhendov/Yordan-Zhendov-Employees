package com.example.sma.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ServiceLogicImpl implements ServiceLogic {

    private final DataFromCSVRepository dataFromCSVRepository;

    @Override
    public String getOrderedEmployees() {

        List<DataFromCSV> allEmployees = dataFromCSVRepository.findAllByTotalDaysInProject();
        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < allEmployees.size(); i++) {
            DataFromCSV firstEmployee = allEmployees.get(i);
            DataFromCSV secondEmployee = null;

            for (int j = i+1; j < allEmployees.size() ; j++) {
                DataFromCSV cur = allEmployees.get(j);
                if(cur.getProjectId() == firstEmployee.getProjectId()){
                    secondEmployee = cur;
                    break;
                }
            }

            if(secondEmployee != null){
                pairs.add(firstEmployee.getEmployeeId());
                pairs.add(secondEmployee.getEmployeeId());
            }

        }

        return String.join(", ", pairs);
    }
}
