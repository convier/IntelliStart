package com.inl.rst.service;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class OrderDTO {
	
	private final long id;
	
	private final long version;
	
	@NotBlank
    @Size(min = 0, max = 64)
	private final String name;
	
	@NotBlank
    @Size(min = 0, max = 256)
	private final String description;
	
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

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public long getVersion() {
		return version;
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