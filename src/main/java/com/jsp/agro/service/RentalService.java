package com.jsp.agroLiveProject.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agroLiveProject.dao.EquipmentDao;
import com.jsp.agroLiveProject.dao.RentalDao;
import com.jsp.agroLiveProject.entity.Equipment;
import com.jsp.agroLiveProject.entity.PaymentHistory;
import com.jsp.agroLiveProject.entity.Rental;
import com.jsp.agroLiveProject.util.ResponseStructure;

@Service
public class RentalService {

	@Autowired
	private RentalDao rDao;
	
	@Autowired
	private EquipmentDao eDao;

	public ResponseEntity<ResponseStructure<Rental>> saveRental(int eid, Rental r) {
		Equipment edb = eDao.fetchEquipmentById(eid);
		if(edb!=null) {
			r.setEquipment(edb);
			Duration time = Duration.between(r.getStartTime(), r.getEndTime());
			double days=time.toDaysPart();
			double hrs=time.toHoursPart();
			double min=time.toMinutesPart();
			double totaltime=days*24+hrs+min/60;
			double amount=totaltime*edb.getCostPerHr();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(time);
			System.out.println(days+""+hrs+""+min+""+totaltime+":"+amount);
			PaymentHistory pay=new PaymentHistory();
			pay.setAmount(amount);
			r.setPaymentHistory(pay);
			Rental rdb = rDao.saveRental(r);
			
			
			ResponseStructure<Rental> rs=new ResponseStructure<Rental>();
			rs.setData(rdb);
			rs.setMsg("Rental saved succeesfully");
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Rental>>(rs,HttpStatus.CREATED);
			
		}
		return null;
	}
}
