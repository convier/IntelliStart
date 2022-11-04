package com.inl.rst.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {

	@Id
	@GeneratedValue(generator = "order_seq")
	private Long Id;

	@Column(name = "name", length = 64)
	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, name, order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(name, other.name) && Objects.equals(order, other.order);
	}
}
