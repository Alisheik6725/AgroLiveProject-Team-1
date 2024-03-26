package com.jsp.agro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipment, String> {
	@Query("select a from Equipment a where a.name=?1")
	public List<Equipment> findByName(String Name) ;
	@Query("select a from Equipment a where a.user=?1")
	public List<Equipment> findByUser(User user) ;

}
