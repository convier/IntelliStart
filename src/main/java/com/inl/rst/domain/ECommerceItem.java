package com.inl.rst.domain;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class ECommerceItem extends Item {
	
	private String platform;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(platform);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ECommerceItem other = (ECommerceItem) obj;
		return Objects.equals(platform, other.platform);
	}
}
