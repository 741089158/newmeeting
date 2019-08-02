package com.cc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cc.entity.SysAdmin;

/**
 * @author TANGXIAN
 */
@Repository
public interface SysAdminDao {

	void save(@Param("sysAdmin") SysAdmin sysAdmin);

	void delete(@Param("sysAdmin") SysAdmin sysAdmin);

	SysAdmin findOne(String key);

	List<SysAdmin> findAll();

}
