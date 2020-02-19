package lk.dialog.ist.reslo.services.Config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lk.dialog.ist.reslo.dto.AllocationDto;
import lk.dialog.ist.reslo.dto.ChartDto;
import lk.dialog.ist.reslo.dto.EmployeeDto;
import lk.dialog.ist.reslo.dto.HolidayDto;
import lk.dialog.ist.reslo.dto.ProjectDto;
import lk.dialog.ist.reslo.dto.UserDto;


/**
 * Generic configuration
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "lk.dialog.ist.reslo")
public class ApplicationConfig {

	@Bean
    public UserDto userDto() {
        return new UserDto();
    }
	
	@Bean
    public EmployeeDto employeeDto() {
        return new EmployeeDto();
    }

	
	@Bean
    public ProjectDto projectDto() {
        return new ProjectDto();
    }
	
	@Bean
    public AllocationDto allocationDto() {
        return new AllocationDto();
    }
	
	@Bean
    public HolidayDto holidayDto() {
        return new HolidayDto();
    }
	
	@Bean
    public ChartDto chartDto() {
        return new ChartDto();
    }
	
	
}
