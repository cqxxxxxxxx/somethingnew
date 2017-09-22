package com.cqx.gen.excel.dao;

import com.cqx.gen.excel.model.Canton;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BG307435 on 2017/9/21.
 */
public interface CantonRepository extends JpaRepository<Canton, Integer> {

    List<Canton> findCantonsByLevel(int level);
}
