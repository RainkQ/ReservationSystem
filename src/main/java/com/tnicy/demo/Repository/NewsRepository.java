package com.tnicy.demo.Repository;

import com.tnicy.demo.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News,Integer> {
    public News findByNid(int nid);

    @Modifying
    @Transactional
    public void deleteByNid(int nid);
}
