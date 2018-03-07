package com.cqx.sqlGen.db.dao;

import com.cqx.sqlGen.db.model.Canton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BG307435 on 2017/9/21.
 */
public interface CantonRepository extends JpaRepository<Canton, Integer> {

    List<Canton> findCantonsByLevel(int level);
}
