package com.tnicy.demo.Repository;

import com.tnicy.demo.Entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
    public Period findByPid(Integer pid);
    public ArrayList<Period> findBySid(Integer sid);
    public ArrayList<Period> findAll();

    @Modifying
    @Transactional
    public void deleteByPid(Integer pid);

    @Modifying
    @Transactional
    public void deleteAllBySid(Integer sid);
}
