package com.jsp.agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EquipmentNotFound;
import com.jsp.agro.repo.EquipmentRepo;
import com.jsp.agro.util.ResponseStructure;

@Repository
public class EquipmentDao {
	@Autowired
	private EquipmentRepo erepo;
	@Autowired
	private RentalDao rdao;
	
	public Equipment saveEquipment(Equipment e) {
		return erepo.save(e);
	}
	public Equipment fetchEquipmentById(String id) {
		Optional<Equipment> db = erepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		return null;
		
	}
	public List<Equipment> fetchEquipmentAll() {
		 List<Equipment> db = erepo.findAll();
		if(db!=null) {
			return db;
		}
		return null;
		
	}
	public List<Equipment> fetchEquipmentByName(String name) {
		 List<Equipment> db = erepo.findByName(name);
		if(db!=null) {
			return db;
		}
		return null;
		
	}
	public List<Equipment> fetchEquipmentByUid(User u) {
		 List<Equipment> db = erepo.findByUser(u);
		if(db!=null) {
			return db;
		}
		return null;
		
	}
	public Equipment updateEquipment(Equipment e) {
		Optional<Equipment> db = erepo.findById(e.getId());
		if(db.isPresent()) {
			Equipment eqDb = db.get();
			if(e.getCostPerHrs()!=0) {
				eqDb.setCostPerHrs(e.getCostPerHrs());
				
			}
			
			return erepo.save(eqDb);
		}
		return null;
	}
	public Equipment deleteEquipment(String id) {
		Optional<Equipment> db = erepo.findById(id);
		Equipment eqDb = db.get();
		if(db.isPresent()) {
			Rental rentalDb = rdao.fetchByEquipment(eqDb);
			if(rentalDb!=null) {
				rentalDb.setEquipment(null);
				rdao.updateRental(rentalDb);
			}
			erepo.delete(eqDb);
			return eqDb;
		}
		return null;
	}

}
