package com.ydhl.micro.api.enpoint.admin.sys;

import com.ydhl.micro.api.dto.admin.sys.dict.CreateDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.ModifyDicitemDTO;
import com.ydhl.micro.api.dto.admin.sys.dict.SearchDicitemDTO;
import com.ydhl.micro.api.dto.common.HttpPageResultDTO;
import com.ydhl.micro.api.dto.common.HttpResultDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName SysDictApi
 * @Description TODO
 * @Author Ly
 * @Date 2019/4/25 15:58
 * @Version 1.0
 **/
public interface SysDictApi {

    /** 数据字典类别列表 */
    @RequestMapping(value = "dic/type", method = RequestMethod.GET)
    HttpResultDTO findDicTypeList();

    /** 单条查询 */
    @RequestMapping(value = "dic/{id}", method = RequestMethod.GET)
    HttpResultDTO findById(@PathVariable(name = "id") Long id);

    /** 分页条件查询 */
    @RequestMapping(value = "dic/pageData", method = RequestMethod.POST)
    HttpPageResultDTO pageSearch(@RequestBody SearchDicitemDTO searchDTO);

    /** 条件查询 */
    @RequestMapping(value = "dic/data", method = RequestMethod.POST)
    HttpResultDTO search(@RequestBody SearchDicitemDTO searchDTO);

    /** 获取小程序商品查询条件 */
    @RequestMapping(value = "dic/getDictTypes", method = RequestMethod.GET)
    HttpResultDTO getDictTypes();

    /** 获取小程序商品权重查询条件 */
    @RequestMapping(value = "dic/getStarDictTypes", method = RequestMethod.GET)
    HttpResultDTO getStarDictTypes();

    /** 新增 */
    @RequestMapping(value = "dic/add", method = RequestMethod.POST)
    HttpResultDTO create(@Valid @RequestBody CreateDicitemDTO dto);

    /** 修改 */
    @RequestMapping(value = "dic/modifiy", method = RequestMethod.POST)
    HttpResultDTO modify(@Valid @RequestBody ModifyDicitemDTO dto);

    /** 批量操作-删除 */
    @RequestMapping(value = "dic/delete", method = RequestMethod.POST)
    HttpResultDTO delete(@RequestBody List<Long> idList);

    /** 批量操作-冻结 */
    @RequestMapping(value = "dic/frozen", method = RequestMethod.POST)
    HttpResultDTO frozen(@RequestBody List<Long> idList);

    /** 批量操作-启用 */
    @RequestMapping(value = "dic/enable", method = RequestMethod.POST)
    HttpResultDTO enable(@RequestBody List<Long> idList);

}
