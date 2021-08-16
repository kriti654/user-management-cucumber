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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
@Table(name = "store_rvy")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "NUMERIC(4)")
	private Integer storeId;
    
    @NotBlank(message = "Store Name should not be empty ")
    @Column(length=50)
	private String name;
    
    @NotBlank(message = "Store Address should not be empty ")
    @Column(length=100)
    private String address;
    
    @NotNull
    @Column(columnDefinition="NUMERIC(10)", nullable = false)
    private Long mobileNumber; 
    
    @NotNull
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter correct email")
    @Column(length=30)
	private String email; 
     
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "store", fetch = FetchType.LAZY)
	private List<User> userList;

}
