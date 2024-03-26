package com.jsp.agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.repo.RentalRepo;
@Repository
public class RentalDao {
	@Autowired
	private RentalRepo rrepo;
	
	
	public Rental fetchByEquipment(Equipment e) {
		return rrepo.fetchByEquipment(e);
	}
	public Rental updateRental(Rental r) {
		return rrepo.save(r);
	}
	
	
	public Rental saveRental(Rental r) {
		return rrepo.save(r);
	}
}
