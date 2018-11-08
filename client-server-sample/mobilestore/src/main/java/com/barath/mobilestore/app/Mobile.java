
package com.barath.mobilestore.app;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.ClientRegion;

@ClientRegion("MOBILES")
public class Mobile implements Serializable {
	
	private static final long serialVersionUID = -2041926012538657653L;

	@Id
	private String mobileId;
	
	private String mobileName;

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", mobileName=" + mobileName + "]";
	}
	
	@PersistenceConstructor
	public Mobile(String mobileId, String mobileName) {
		super();
		this.mobileId = mobileId;
		this.mobileName = mobileName;
	}

	public Mobile() {
		super();
		
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mobile other = (Mobile) obj;
		if (mobileId != other.mobileId)
			return false;
		if (mobileName == null) {
			if (other.mobileName != null)
				return false;
		} else if (!mobileName.equals(other.mobileName))
			return false;
		return true;
	}
	
	

}
