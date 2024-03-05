package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Address;
import com.jsp.agro.repo.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;
	
	
	public Address saveAddress(Address a) {
		Optional<Address> db = repo.findById(a.getId());
		if(db.isPresent()) {
			Address aDb=db.get();
			if(a.getHouseNum()==null) {
				a.setHouseNum(aDb.getHouseNum());
			}
			if(a.getStreet()==null) {
				a.setStreet(aDb.getStreet());
			}
			if(a.getLandMark()==null) {
				a.setLandMark(aDb.getLandMark());
			}
			if(a.getState()==null) {
				a.setState(aDb.getState());
			}
			if(a.getDistrict()==null) {
				a.setDistrict(null);
			}
			if(a.getMandal()==null) {
				a.setMandal(aDb.getMandal());
			}
			if(a.getPinCode()==0) {
				a.setPinCode(aDb.getPinCode());
			}
			return repo.save(a);
		}
		return null;
		
	}

}
