package com.example.sma.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DataFromCSVRepository extends JpaRepository<DataFromCSV, Integer> {

    @Query(value = "SELECT sv from DataFromCSV as sv ORDER BY sv.totalDaysInProject DESC ")
    List<DataFromCSV> findAllByTotalDaysInProject();
}
