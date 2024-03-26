package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.Rental;

public interface RentalRepo extends JpaRepository<Rental, String> {
	@Query("select a from Rental a Where a.equipment=?1")
	public Rental fetchByEquipment(Equipment e) ;
}
