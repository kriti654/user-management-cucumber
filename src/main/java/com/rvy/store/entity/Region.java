package com.rvy.store.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "region_rvy")
public class Region {
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   @Column(columnDefinition = "NUMERIC(4)")
       private Integer regionId;
	   
	   @NotBlank
	   @Column(length=30)
       private String name;
	   
	   @NotBlank
	   @Column(length=20)
       private String city;
	   
	   @NotBlank
	   @Column(length=20)
       private String state;
	   
	   @NotBlank
	   @Column(length=20)
       private String country;
	   
	    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "region", fetch = FetchType.LAZY)
	    @JsonIgnore
		private List<Store> storeList;
	   
	    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
		@JoinColumn(name = "tax_id")
		private Taxation tax;
	    
}



