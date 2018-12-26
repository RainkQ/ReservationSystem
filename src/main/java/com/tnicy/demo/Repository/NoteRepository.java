package com.tnicy.demo.Repository;

import com.tnicy.demo.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Comment,Integer> {
    public Comment findByCid(int cid);
    public Comment findByName(String name);
}
