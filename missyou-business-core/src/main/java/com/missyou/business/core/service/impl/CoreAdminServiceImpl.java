package com.missyou.business.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.missyou.business.core.service.ICoreAdminService;
import com.missyou.commons.base.BaseServiceImpl;
import com.missyou.commons.exceptions.BusinessException;
import com.missyou.commons.response.ResponseCode;
import com.missyou.repository.core.constant.UserStatus;
import com.missyou.repository.core.domain.CoreAdmin;
import com.missyou.repository.core.mapper.CoreAdminMapper;
import org.springframework.stereotype.Service;

/**
 * 管理员表服务实现类
 *
 * @author skboy
 * @since v1.0.0
 */
@Service
public class CoreAdminServiceImpl extends BaseServiceImpl<CoreAdminMapper, CoreAdmin> implements ICoreAdminService {



	@Override
	public boolean create(CoreAdmin coreAdmin) {
		if (!checkUsername(coreAdmin.getUsername())
				&& !checkNickname(coreAdmin.getNickname())
				&& !checkEmail(coreAdmin.getEmail())) {
			coreAdmin.setStatus(UserStatus.CLOSED);
			return super.save(coreAdmin);
		}
		return false;
	}





	@Override
	public IPage<CoreAdmin> page(int current, int size, CoreAdmin coreAdmin) {

		Page<CoreAdmin> page = new Page<>(current, size);
		LambdaQueryWrapper<CoreAdmin> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(String.valueOf(coreAdmin.getId())),CoreAdmin::getId,coreAdmin.getId())
		.or().like(StringUtils.isNotBlank(String.valueOf(coreAdmin.getUsername())),CoreAdmin::getUsername,coreAdmin.getUsername())
		.or().like(StringUtils.isNotBlank(String.valueOf(coreAdmin.getNickname())),CoreAdmin::getNickname,coreAdmin.getNickname())
		.or().like(StringUtils.isNotBlank(String.valueOf(coreAdmin.getEmail())),CoreAdmin::getEmail,coreAdmin.getEmail());
		return super.page(page,wrapper);
	}

	// 私有方法 ------------------------------------------- Begin

	/**
	 * 检查账号是否存在
	 *
	 * @param username {@code String} 账号
	 * @return {@code boolean} 账号已存在则抛出异常
	 */
	private boolean checkUsername(String username) {
		if (checkUniqueness(CoreAdmin::getUsername, username)) {
			throw new BusinessException(ResponseCode.USER_HAS_EXISTED);
		}
		return false;
	}

	/**
	 * 检查昵称是否存在
	 *
	 * @param nickname {@code String} 昵称
	 * @return {@code boolean} 昵称已存在则抛出异常
	 */
	private boolean checkNickname(String nickname) {
		if (checkUniqueness(CoreAdmin::getNickname, nickname)) {
			throw new BusinessException(ResponseCode.USER_NICK_HAS_EXISTED);
		}
		return false;
	}

	/**
	 * 检查邮箱是否存在
	 *
	 * @param email {@code String} 邮箱
	 * @return {@code boolean} 邮箱已存在则抛出异常
	 */
	private boolean checkEmail(String email) {
		if (checkUniqueness(CoreAdmin::getEmail, email)) {
			throw new BusinessException(ResponseCode.USER_EMAIL_HAS_EXISTED);
		}
		return false;
	}



	private boolean checkUniqueness(SFunction<CoreAdmin, Object> column, Object value) {
	/*	QueryWrapper<CoreAdmin> wrapper = new QueryWrapper<>();
		wrapper.eq(column, value);
*/
		LambdaQueryWrapper<CoreAdmin> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(column, value);
		return super.count(wrapper) > 0;
	}
}