package org.winterframework.dashboard.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.winterframework.dashboard.base.model.data.MenuTree;

import java.util.List;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    MenuService menuService;

    @Test
    void test() throws JsonProcessingException {
        var json =
                "[{\"path\":\"dashboard\",\"title\":\"Dashboard\",\"icon\":\"DashboardOutlined\",\"extra\":null,\"type\":\"dir\",\"data\":null,\"keepAlive\":false,\"children\":[{\"path\":\"workplace\",\"title\":\"工作台\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/dashboard/Workplace.vue\"}]},{\"path\":\"external\",\"title\":\"项目文档\",\"icon\":\"DocumentTextOutline\",\"extra\":null,\"type\":\"link\",\"keepAlive\":false,\"data\":\"https://github.com/winter-studio/winter-dashboard-ui\"},{\"path\":\"exception\",\"title\":\"异常页面\",\"icon\":\"ExclamationCircleOutlined\",\"extra\":null,\"type\":\"dir\",\"keepAlive\":false,\"data\":null,\"children\":[{\"path\":\"403\",\"title\":\"403\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/basic/exception/403.vue\"},{\"path\":\"404\",\"title\":\"404\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/basic/exception/404.vue\"},{\"path\":\"500\",\"title\":\"500\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/basic/exception/500.vue\"}]},{\"path\":\"frame\",\"title\":\"外部页面\",\"icon\":\"DesktopOutline\",\"extra\":null,\"type\":\"dir\",\"keepAlive\":false,\"data\":null,\"children\":[{\"path\":\"naive-ui\",\"title\":\"NaiveUi\",\"icon\":null,\"extra\":null,\"type\":\"iframe\",\"keepAlive\":true,\"data\":\"https://www.naiveui.com\"}]},{\"path\":\"result\",\"title\":\"结果页面\",\"icon\":\"CheckCircleOutlined\",\"extra\":null,\"type\":\"dir\",\"keepAlive\":false,\"data\":null,\"children\":[{\"path\":\"success\",\"title\":\"成功页\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/result/Success.vue\"},{\"path\":\"fail\",\"title\":\"失败页\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/result/Failure.vue\"},{\"path\":\"info\",\"title\":\"信息页\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/result/Info.vue\"}]},{\"path\":\"setting\",\"title\":\"设置页面\",\"icon\":\"SettingOutlined\",\"extra\":null,\"type\":\"dir\",\"keepAlive\":false,\"data\":null,\"children\":[{\"path\":\"account\",\"title\":\"个人设置\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/setting/account/Account.vue\"},{\"path\":\"system\",\"title\":\"系统设置\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/setting/system/System.vue\"}]},{\"path\":\"system\",\"title\":\"系统设置\",\"icon\":\"OptionsSharp\",\"extra\":null,\"type\":\"dir\",\"keepAlive\":false,\"data\":null,\"children\":[{\"path\":\"menu\",\"title\":\"菜单权限管理\",\"icon\":null,\"extra\":null,\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/system/menu/Menu.vue\"}]},{\"path\":\"about\",\"title\":\"关于\",\"icon\":\"ProjectOutlined\",\"extra\":\"new\",\"type\":\"view\",\"keepAlive\":true,\"data\":\"views/About.vue\"}]";
        ObjectMapper mapper = new ObjectMapper();

        var list = mapper.readValue(json, new TypeReference<List<MenuTree>>() {
        });


        saveTrees(list, null);


    }

    private void saveTrees(List<MenuTree> list, Integer pid) {
        for (MenuTree menuTree : list) {
            menuTree.setParentId(pid);
            menuService.save(menuTree);
            List<MenuTree> children = menuTree.getChildren();
            if (children != null && children.size() > 0) {
                saveTrees(children, menuTree.getId());
            }
        }
    }
}
