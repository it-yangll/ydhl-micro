package com.ydhl.micro.base.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.ydhl.micro.api.dto.admin.sys.role.SearchRoleDTO;
import com.ydhl.micro.base.entity.SysMsgTemplate;
import com.ydhl.micro.base.service.*;
import com.ydhl.micro.core.consts.RedisKeyConst;
import com.ydhl.micro.core.util.CacheHelper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @ClassName ApplicationStartup
 * @Description TODO
 * @Author yangll
 * @Date 2019-9-10 15:36:50
 * @Version 1.0
 **/
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        SysRoleService roleService = contextRefreshedEvent.getApplicationContext().getBean(SysRoleService.class);
        SysResourceService resourceService = contextRefreshedEvent.getApplicationContext().getBean(SysResourceService.class);
        SysDeptService deptService = contextRefreshedEvent.getApplicationContext().getBean(SysDeptService.class);
        SysDictService dictService = contextRefreshedEvent.getApplicationContext().getBean(SysDictService.class);
        CacheHelper cacheHelper = contextRefreshedEvent.getApplicationContext().getBean(CacheHelper.class);
        SysMsgTemplateService sysMsgTemplateService = contextRefreshedEvent.getApplicationContext().getBean(SysMsgTemplateService.class);

        cacheHelper.delete(RedisKeyConst.ROLE_KEY);
        cacheHelper.saveRoles(roleService.search(new SearchRoleDTO()));

        cacheHelper.delete(RedisKeyConst.RESOURCE_KEY);
        cacheHelper.saveResources(resourceService.findAll());

        cacheHelper.delete(RedisKeyConst.DEPT_KEY);
        cacheHelper.saveDepts(deptService.findAll());

        cacheHelper.delete(cacheHelper.getKeys("sys:dict:*"));
        cacheHelper.saveDicts(dictService.list());

        cacheHelper.delete(RedisKeyConst.MSG_TEMPLATE_MAP_KEY);
        List<SysMsgTemplate> msgTemplates = sysMsgTemplateService.findAll();
        if (CollectionUtil.isNotEmpty(msgTemplates)) {
            msgTemplates.forEach(r -> {
                cacheHelper.saveMsgTemplate(r.getMsgCode(), r.getMsgTemplate());
            });
        }

    }
}
