package com.missyou.business.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.missyou.business.core.service.ICoreUserService;
import com.missyou.commons.base.BaseServiceImpl;
import com.missyou.commons.exceptions.BusinessException;
import com.missyou.commons.response.ResponseCode;
import com.missyou.repository.core.constant.UserStatus;
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
public class CoreUserServiceImpl extends BaseServiceImpl<CoreUserMapper, CoreUser> implements ICoreUserService {


    @Override
    public boolean create(CoreUser coreUser) {
        if (!checkUsername(coreUser
                .getUsername())
                && !checkNickname(coreUser
                .getNickname())
                && !checkEmail(coreUser
                .getEmail())) {
            coreUser
                    .setStatus(UserStatus.CLOSED);
            return super.save(coreUser
            );
        }
        return false;
    }


    @Override
    public IPage<CoreUser> page(int current, int size, CoreUser coreUser) {

        Page<CoreUser
                > page = new Page<>(current, size);
        LambdaQueryWrapper<CoreUser
                > wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(String.valueOf(coreUser
                .getId())), CoreUser
                ::getId, coreUser
                .getId())
                .or().like(StringUtils.isNotBlank(String.valueOf(coreUser
                .getUsername())), CoreUser
                ::getUsername, coreUser
                .getUsername())
                .or().like(StringUtils.isNotBlank(String.valueOf(coreUser
                .getNickname())), CoreUser
                ::getNickname, coreUser
                .getNickname())
                .or().like(StringUtils.isNotBlank(String.valueOf(coreUser
                .getEmail())), CoreUser
                ::getEmail, coreUser
                .getEmail());
        return super.page(page, wrapper);
    }

    // 私有方法 ------------------------------------------- Begin

    /**
     * 检查账号是否存在
     *
     * @param username {@code String} 账号
     * @return {@code boolean} 账号已存在则抛出异常
     */
    private boolean checkUsername(String username) {
        if (checkUniqueness(CoreUser
                ::getUsername, username)) {
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
        if (checkUniqueness(CoreUser
                ::getNickname, nickname)) {
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
        if (checkUniqueness(CoreUser
                ::getEmail, email)) {
            throw new BusinessException(ResponseCode.USER_EMAIL_HAS_EXISTED);
        }
        return false;
    }


    private boolean checkUniqueness(SFunction<CoreUser
            , Object> column, Object value) {
	/*	QueryWrapper<CoreUser
> wrapper = new QueryWrapper<>();
		wrapper.eq(column, value);
*/
        LambdaQueryWrapper<CoreUser
                > wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(column, value);
        return super.count(wrapper) > 0;
    }
}
