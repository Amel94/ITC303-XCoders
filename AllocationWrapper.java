package lk.dialog.ist.reslo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lk.dialog.ist.reslo.services.entities.Allocation;
import lk.dialog.ist.reslo.services.entities.Employee;
import lk.dialog.ist.reslo.services.entities.Project;
import lk.dialog.ist.reslo.services.util.AllocationStatus;

public class AllocationWrapper {
	
	Allocation allocation;

	public AllocationWrapper(Allocation allocation) {
        this.allocation = allocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AllocationWrapper)) return false;
        AllocationWrapper other = (AllocationWrapper) obj;
        return  Objects.equals(allocation.getProject().getCrNo(), other.allocation.getProject().getCrNo()) ||
        		allocation.getProject().getProjectName() == other.allocation.getProject().getProjectName();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}