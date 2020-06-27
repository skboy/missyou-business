package com.missyou.business.core.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.missyou.business.core.service.ICoreAdminService;
import com.missyou.repository.core.constant.UserStatus;
import com.missyou.repository.core.domain.CoreAdmin;
import com.missyou.repository.core.mapper.CoreAdminMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 管理员表服务实现类
 *
 * @author skboy
 * @since v1.0.0
 */
@Service
public class CoreAdminServiceImpl extends ServiceImpl<CoreAdminMapper, CoreAdmin> implements ICoreAdminService {

	@Override
	public boolean create(CoreAdmin coreAdmin) {
		coreAdmin.setStatus(UserStatus.CLOSED);
		coreAdmin.setDeleted(false);
		coreAdmin.setCreateTime(LocalDateTime.now());
		coreAdmin.setUpdateTime(LocalDateTime.now());

		return super.save(coreAdmin);
	}

	@Override
	public boolean remove(Long coreAdminId) {
		return super.removeById(coreAdminId);
	}

	@Override
	public boolean update(CoreAdmin coreAdmin) {
		return super.updateById(coreAdmin);
	}

	@Override
	public CoreAdmin get(Long coreAdminId) {
		return super.getById(coreAdminId);
	}

	@Override
	public IPage<CoreAdmin> page(int current, int size, Object value) {
		Page<CoreAdmin> page = new Page<>(current, size);
		return super.page(page, null);
	}

}