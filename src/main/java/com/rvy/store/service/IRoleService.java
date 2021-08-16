package com.rvy.store.service;

import com.rvy.store.entity.Role;
import com.rvy.store.exceptions.RoleException;

public interface IRoleService {
	public abstract Role getRoleById(Integer roleId) throws RoleException;
	public abstract Role addRole(Role role) throws RoleException;
	public abstract Boolean deleteRole(Integer roleId) throws RoleException;
}
