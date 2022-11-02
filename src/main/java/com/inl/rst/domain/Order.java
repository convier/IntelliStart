package com.inl.rst.domain;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Ordering") // order is a keyword
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", length=64) 
	@NotNull
	private String name;

	@Column(name="description", length=128) 
	private String description;
	
	@OneToMany(mappedBy="order")
	private Set<Item> items;
	
	public Order() {
		this(null, null, null);
	}

	public Order(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, items, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(items, other.items) && Objects.equals(name, other.name);
	}
	
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", description=" + description + ", items=" + items + "]";
	}

	@PrePersist
	public void postPeristEvent() {
		System.out.println("Being persisted order, id: " + this.toString());
	}
	
	@PreRemove
	public void preRemoveEvent() {
		System.out.println("Being removed order, id: " + getId());
	}
}
