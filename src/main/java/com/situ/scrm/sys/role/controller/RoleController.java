/**
 * @Company:中享思途   
 * @Title:RoleController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午3:21:45     
 */
package com.situ.scrm.sys.role.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.commons.domain.LayTree;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.role.param.RoleAuth;
import com.situ.scrm.sys.role.service.RoleService;

/**
 * @ClassName:RoleController
 * @Description:(角色的Controller层)
 */
@RestController
@RequestMapping("/role")
public class RoleController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ROLE_INDEX = "sys/role/role_index";
	private static final String PAGE_ROLE_ADD_EDIT = "sys/role/role_add_edit";
	private static final String PAGE_ROLE_SET_AUTH = "sys/role/role_set_auth";
	@Autowired
	private RoleService roleService;

	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_ROLE_INDEX);
		return modelAndView;
	}

	/**
	 * @Title: /form
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_ROLE_ADD_EDIT);
		return modelAndView;
	}

	/**
	 * @Title: checkRoleName
	 * @Description:(检测角色名称的唯一)
	 * @param roleName
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkRoleName(String roleName) {
		return roleService.checkRoleName(roleName);
	}

	/**
	 * @Title: doAddRole
	 * @Description:(执行新增功能)
	 * @param role
	 * @return
	 */
	@PostMapping
	public Long doAddRole(Role role) {
		return roleService.saveRole(role);
	}

	/**
	 * @Title: findRoleByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchRole 查询的条件
	 * @return
	 */
	// http://localhost:8080/layoa/role/1/10?roleKind=&roleName=
	// restful 匹配 http://localhost:8080/layoa/role/1/10
	@GetMapping("/{page}/{limit}")
	public LayResult findRoleByPage(@PathVariable Integer page, @PathVariable Integer limit, Role searchRole) {
		return roleService.findRoleByPage(page, limit, searchRole);
	}

	/**
	 * @Title: doDeleteRole
	 * @Description:(执行删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteRole(@PathVariable Long rowId) {
		return roleService.doDeleteRole(rowId);
	}

	/**
	 * @Title: goEditRole
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Role goEditRole(@PathVariable Long rowId) {
		return roleService.getRoleById(rowId);
	}

	/**
	 * @Title: doEditRole
	 * @Description:(执行修改)
	 * @param role
	 * @return
	 */
	@PutMapping
	public Integer doEditRole(Role role) {
		return roleService.doEditRole(role);
	}

	/**
	 * @Title: goRoleSetAuth
	 * @Description:(进设置权限页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/goauth/{rowId}")
	public ModelAndView goRoleSetAuth(@PathVariable Long rowId, ModelAndView modelAndView) {
		List<Long> resourceIdList = roleService.findParentResourceIdList();
		modelAndView.addObject("role", roleService.getRoleById(rowId));
		modelAndView.addObject("resourceIdList", resourceIdList);
		modelAndView.setViewName(PAGE_ROLE_SET_AUTH);
		return modelAndView;
	}

	/**
	 * @Title: findRoleAuthDataList
	 * @Description:(查询权限设置的资源数据)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/findauth/{rowId}")
	public Map<Long, List<LayTree>> findRoleAuthDataList(@PathVariable Long rowId) {
		return roleService.findRoleAuthDataList(rowId);
	}

	/**
	 * @Title: setRoleAuth
	 * @Description:(设置权限)
	 * @param roleId
	 * @param rescCodeList
	 * @return
	 */
	@PutMapping("/setauth")
	public Integer setRoleAuth(RoleAuth roleAuth) {
		return roleService.setRoleAuth(roleAuth);
	}
}
