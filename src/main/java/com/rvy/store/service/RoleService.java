package com.rvy.store.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvy.store.dao.IRoleRepository;
import com.rvy.store.entity.Role;
import com.rvy.store.exceptions.RoleException;

@Service
@Transactional
public class RoleService implements IRoleService {
	@Autowired
    private IRoleRepository roleRepo;
	
	@Override
	public Role addRole(Role role) throws RoleException {
		try {
			 role.setRoleId(null);
			 return roleRepo.save(role);
		}catch(DataAccessException e){
			throw new RoleException(e.getMessage(),e);
		}
	}

	@Override
	public Role getRoleById(Integer roleId) throws RoleException {
		try {
			Optional<Role> optional = 
									roleRepo.findById(roleId);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PersistenceException("Invalid Role ID");
			}			
			
		}catch(DataAccessException e) {
			throw new RoleException(e.getMessage(),e);
		}
	}

	@Override
	public Boolean deleteRole(Integer roleId) throws RoleException {
		try {
			roleRepo.deleteById(roleId);
			return true;
		}catch(DataAccessException e) {
			throw new RoleException(e.getMessage(),e);
		}
	}
}
