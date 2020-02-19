package lk.dialog.ist.reslo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lk.dialog.ist.reslo.dto.HolidayRequestDto;
import lk.dialog.ist.reslo.request.EmployeeCreateRequest;
import lk.dialog.ist.reslo.request.ProjectAddRequest;
import lk.dialog.ist.reslo.request.UserCreateRequest;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Holidays;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.repository.ApplicationUserRepo;
import lk.dialog.ist.reslo.services.repository.EmployeeRepo;
import lk.dialog.ist.reslo.services.repository.HolidayRepo;
import lk.dialog.ist.reslo.services.util.EmployeeStatus;
import lk.dialog.ist.reslo.services.util.UserStatus;


@Component
public class HolidayDao{
	
	@Autowired
	private HolidayRepo holidayRepo;
	
	public List<Holidays> addHoliday(List<HolidayRequestDto> holidayList) throws Exception{
		// TODO Auto-generated method stub
		
		List<Holidays> holidays = new ArrayList<Holidays>();
		Holidays holiday = null;
		for (HolidayRequestDto dto : holidayList) {
			holiday = new Holidays();
			
			holiday.setDate(dto.getDate());
			holiday.setDescription(dto.getDesc());
			holidays.add(holiday);
		}

		holidayRepo.saveAll(holidays);
		
		return holidays;
	}
	
	public List<Holidays> getAllHolidays() throws Exception{
		return (List<Holidays>) holidayRepo.findAll();
	}

}
