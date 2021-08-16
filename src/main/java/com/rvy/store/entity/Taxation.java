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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="taxation_rvy")
public class Taxation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "NUMERIC(4)")
	private Integer taxId;
	
	@NotNull
	@Column(columnDefinition = "NUMERIC(10)", unique = true)
	private Integer code;
	
	@NotBlank
	@Column(length = 30)
	private String name;
	
	@NotNull
	@Column(columnDefinition = "NUMERIC(4,2)")
	private Float taxAmount;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	@NotNull
	@Column(columnDefinition = "DATE")
	private LocalDate effectiveDate;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "tax", fetch = FetchType.LAZY)
	private List<Region> regionList;
	

}
