package lk.dialog.ist.reslo.services.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lk.dialog.ist.reslo.services.entities.ApplicationUser;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Holidays;

@Repository
public interface HolidayRepo extends CrudRepository<Holidays, Date> {
	
	
}
