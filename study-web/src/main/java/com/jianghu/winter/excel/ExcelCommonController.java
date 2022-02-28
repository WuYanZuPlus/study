package com.jianghu.winter.excel;

import com.github.wuyanzuplus.excel.core.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.hu
 * @Date: 2022/2/28 14:11
 */
@Api(tags = "excel导入导出功能模拟类")
@RequestMapping("/excel")
@RestController
public class ExcelCommonController {

    @ApiOperation(value = "直播课-班级详情-出勤管理-出勤统计导出")
    @PostMapping("/user/export.xlsx")
    public ModelAndView userExport1() {
        List<ApiEntity> data = getInitApiExportList();
        Map<String, Object> map = new HashMap<>();
        map.put(ExcelUtil.EXCEL_EXPORT_DATA_KEY, data);
        return new ModelAndView("apiXlsxView", map);
    }

    private static List<ApiEntity> getInitApiExportList() {
        return Arrays.asList(
                newApi(Platform.系统, "api1", "test_add", true, "权限管理", "/abc/def1"),
                newApi(Platform.系统, "api2", "test_update", true, "权限管理", "/abc/def2"),
                newApi(Platform.租户, "api3", "test_delete", true, "会员", "/abc/def3")
        );
    }

    private static ApiEntity newApi(Platform apiPlatform, String apiName, String apiCode, Boolean valid, String project, String apiUrl) {
        ApiEntity api = new ApiEntity();
        api.setApiPlatform(apiPlatform);
        api.setApiName(apiName);
        api.setApiCode(apiCode);
        api.setValid(valid);
        api.setProject(project);
        api.setApiUrl(apiUrl);
        return api;
    }

}
