package com.jsp.agro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.EquipmentDao;
import com.jsp.agro.dao.RentalDao;
import com.jsp.agro.dao.UserCrud;
import com.jsp.agro.entity.Equipment;
import com.jsp.agro.entity.Rental;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EquipmentNotFound;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class EquipmentService {
	@Autowired
	private  EquipmentDao edao;
	@Autowired
	private UserCrud udao;
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(String uid,Equipment e){
		User userDb = udao.fetchById(uid);
		if(userDb!=null) {
			e.setUser(userDb);
			Equipment equipmentDb = edao.saveEquipment(e);
			ResponseStructure<Equipment> r=new ResponseStructure<Equipment>();
			r.setData(equipmentDb);
			r.setMsg("Equipment saved successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new UserNotFoundException("user Not Found for id : "+uid);
	}
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentById(String id){
		Equipment eqDb = edao.fetchEquipmentById(id);
		if(eqDb!=null) {
			ResponseStructure<Equipment> r=new ResponseStructure<Equipment>();
			r.setData(eqDb);
			r.setMsg("Equipment found successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found for id : "+id);
	}
	public ResponseEntity<ResponseStructure< List<Equipment>>> fetchEquipmentAll(){
		  List<Equipment> eqDb = edao.fetchEquipmentAll();
		if(eqDb!=null) {
			ResponseStructure< List<Equipment>> r=new ResponseStructure< List<Equipment>>();
			r.setData(eqDb);
			r.setMsg("Equipment found successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure< List<Equipment>>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found  ");
	}
	public ResponseEntity<ResponseStructure< List<Equipment>>> fetchEquipmentByName(String name){
		List<Equipment> eqDb = edao.fetchEquipmentByName(name);
		if(eqDb!=null) {
			ResponseStructure< List<Equipment>> r=new ResponseStructure< List<Equipment>>();
			r.setData(eqDb);
			r.setMsg("Equipment found successfully...");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure< List<Equipment>>>(r,HttpStatus.FOUND);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found  ");
	}
public ResponseEntity<ResponseStructure< List<Equipment>>> fetchEquipmentByUid(String uid){
		User userDb = udao.fetchById(uid);
		if(userDb!=null) {
			List<Equipment> eqDb = edao.fetchEquipmentByUid(userDb);
			  if(eqDb!=null) {
			ResponseStructure< List<Equipment>> r=new ResponseStructure< List<Equipment>>();
			r.setData(eqDb);
			r.setMsg("Equipment found successfully...");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure< List<Equipment>>>(r,HttpStatus.FOUND);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found  ");
		}else {
			throw new UserNotFoundException("user not found for id : "+uid);
		}
	}
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(Equipment e){
		Equipment eqDb = edao.fetchEquipmentById(e.getId());
		if(eqDb!=null) {
			ResponseStructure<Equipment> r=new ResponseStructure<Equipment>();
			r.setData(edao.updateEquipment(e));
			r.setMsg("Equipment updated successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found for id : "+e.getId());
	}
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(String id){
		Equipment eqDb = edao.fetchEquipmentById(id);
		if(eqDb!=null) {
			ResponseStructure<Equipment> r=new ResponseStructure<Equipment>();
			r.setData(edao.deleteEquipment(id));
			r.setMsg("Equipment deleted successfully...");
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(r,HttpStatus.ACCEPTED);
			
		}else
			throw new EquipmentNotFound("Equipment Not Found for id : "+id);
	}
}
