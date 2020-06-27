package com.missyou.business.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.missyou.business.core.service.ICoreUserService;
import com.missyou.repository.core.domain.CoreUser;
import com.missyou.repository.core.mapper.CoreUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author skboy
 * @since 2020-06-27
 */
@Service
public class CoreUserServiceImpl extends ServiceImpl<CoreUserMapper, CoreUser> implements ICoreUserService {

	@Override
	public boolean create(CoreUser coreUser) {
		return super.save(coreUser);
	}

	@Override
	public boolean remove(Long id) {
		return super.removeById(id);
	}

	@Override
	public boolean update(CoreUser coreUser) {
		return super.updateById(coreUser);
	}

	@Override
	public CoreUser get(Long id) {
		return super.getById(id);
	}

	@Override
	public IPage<CoreUser> page(int current, int size, CoreUser coreUser) {
		Page<CoreUser> page = new Page<>(current, size);
		LambdaQueryWrapper<CoreUser> wrapper = new LambdaQueryWrapper<>();

		// TODO 查询
		// TODO 排序

		return super.page(page, wrapper);
	}

}
