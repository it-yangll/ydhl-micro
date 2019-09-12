package com.ydhl.micro.base.service;

import com.github.pagehelper.PageInfo;
import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ResponseDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.base.entity.SysDictype;

import java.util.List;

/**
 * @ClassName SysDictService
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 22:16
 * @Version 1.0
 **/
public interface SysDictService {

    List<SysDictype> findAll();

    List<ResponseDicitemDTO> list();

    /** 单条查询 */
    ResponseDicitemDTO findById(Long id);

    /** 分页条件查询 */
    PageInfo<ResponseDicitemDTO> pageSearch(SearchDicitemDTO searchDTO);

    /** 条件查询 */
    List<ResponseDicitemDTO> search(SearchDicitemDTO searchDTO);

    /** 新增 */
    void create(CreateDicitemDTO dto);

    /** 修改 */
    void modify(ModifyDicitemDTO dto);

    /** 批量操作-删除 */
    void delete(List<Long> idList);

    /** 批量操作-冻结 */
    void frozen(List<Long> idList);

    /** 批量操作-启用 */
    void enable(List<Long> idList);
}
