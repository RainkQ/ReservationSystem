package com.tnicy.demo.Repository;

import com.tnicy.demo.Entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface SiteRepository extends JpaRepository<Site,Integer > {
    public ArrayList<Site> findAll();
    public Site findBySid(Integer sid);

    @Modifying
    @Transactional
    public void deleteBySid(Integer sid);
}

