package com.rvy.store.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="user_rvy")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "NUMERIC(4)")
	private Integer userId;

	@Column(length = 30, unique = true)
	private String uin;

	@NotBlank(message="Name should not be blank")
	@Column(length=30)
	private String name;
	
	@Past(message="Hire Date should not be blank")
	private LocalDate hiredate;

	@NotNull
	@NotBlank(message = "Email should not be empty")
	@Email(message = "Enter correct email")
	@Column(length=30)
	private String email;

	@NotNull
	@Column(columnDefinition="NUMERIC(10)", nullable = false)
	private Long mobile; 

	@NotBlank(message="Address should not be blank")
	@Column(length=50)
	private String address1;

	@Column(length=50)
	private String address2;

	@NotBlank(message="City should not be blank")
	@Column(length=20)
	private String city;

	@NotBlank(message="State should not be blank")
	@Column(length=20)
	private String state;

	@Column(columnDefinition = "NUMERIC(6)")
	private Integer zipcode;

	@NotBlank(message ="Country should not be blank")
	@Column(length=20)
	private String country;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
	
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role",
			joinColumns = {@JoinColumn(name="userId")},
			inverseJoinColumns = { @JoinColumn(name="roleId")}
			)
	private List<Role> roleList;
}
