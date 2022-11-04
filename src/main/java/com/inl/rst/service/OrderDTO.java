package com.inl.rst.service;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class OrderDTO {
	
	private long id;
	
	private long version;
	
	@NotBlank
    @Size(min = 0, max = 64)
	private String name;
	
	@NotBlank
    @Size(min = 0, max = 256)
	private String description;
	
	public OrderDTO() {
		this(0, null, null, 0);
	}

	public OrderDTO(long id, String name, String description, long version) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name)
				&& version == other.version;
	}
}