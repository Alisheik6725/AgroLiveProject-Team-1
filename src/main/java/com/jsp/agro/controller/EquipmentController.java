package com.jsp.agro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Equipment;
import com.jsp.agro.service.EquipmentService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService service;
	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam String userId,@RequestBody Equipment e){
		return service.saveEquipment(userId,e);
	}
	@GetMapping("/fetchEquipmentById")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentById(@RequestParam String equipmentId){
		return service.fetchEquipmentById(equipmentId);
	}
	@GetMapping("/fetchAllEquipment")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentAll(){
		return service.fetchEquipmentAll();
	}
	@GetMapping("/fetchEquipmentByName")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentByName(@RequestParam String equipmentName){
		return service.fetchEquipmentByName(equipmentName);
	}
	@GetMapping("/fetchEquipmentByUid")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchEquipmentByUid(@RequestParam String userId){
		return service.fetchEquipmentByUid(userId);
	}
	
	@PutMapping("/updateEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment e){
		return service.updateEquipment(e);
	}
	@DeleteMapping("/deleteEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam String equipmentId){
		return service.deleteEquipment(equipmentId);
	}
}
