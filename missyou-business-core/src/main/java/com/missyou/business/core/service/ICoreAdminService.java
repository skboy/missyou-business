package com.missyou.business.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.missyou.repository.core.domain.CoreAdmin;

/**
 * 管理员表服务类
 *
 * @author skboy
 * @since v1.0.0
 */
public interface ICoreAdminService extends IService<CoreAdmin> {

	/**
	 * 新增
	 * @param coreAdmin {@link CoreAdmin}
	 * @return {@code boolean}
	 */
	boolean create(CoreAdmin coreAdmin);

	/**
	 * 删除
	 * @param coreAdminId {@code Long} 管理员 ID
	 * @return {@code boolean}
	 */
	boolean remove(Long coreAdminId);

	/**
	 * 更新（全量）
	 * @param coreAdmin {@link CoreAdmin}
	 * @return {@code boolean}
	 */
	boolean update(CoreAdmin coreAdmin);

	/**
	 * 获取
	 * @param coreAdminId {@code Long} 管理员 ID
	 * @return {@link CoreAdmin}
	 */
	CoreAdmin get(Long coreAdminId);

	/**
	 * 分页
	 * @param current {@code int} 页码
	 * @param size {@code int} 笔数
	 * @param value {@code Object} 任意条件
	 * @return {@code IPage} 管理员分页数据
	 */
	IPage<CoreAdmin> page(int current, int size, Object value);

}