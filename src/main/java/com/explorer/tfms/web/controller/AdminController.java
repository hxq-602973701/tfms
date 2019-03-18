package com.explorer.tfms.web.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.service.AdminService;
import com.explorer.tfms.service.AdminShopService;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.JSONUtils;
import com.explorer.tfms.utils.MD5Utils;
import com.explorer.tfms.utils.StringUtils;
import com.explorer.tfms.web.dto.AjaxObj;

@Controller("adminController")
@RequestMapping("/admin/")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "shopService")
    private ShopService shopService;
    @Resource(name = "adminShopService")
    private AdminShopService adminShopService;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("adminList", this.adminService.listAllAdmins());
        return "admin/adminList";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/addAdmin";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Validated Admin admin, BindingResult br) {
        if (br.hasErrors()) {
            return "admin/addAdmin";
        }
        this.adminService.saveAdmin(admin);
        return "redirect:/admin/list";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("admin", this.adminService.getAdmin(id));
        return "admin/updateAdmin";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(Model model, Admin admin, BindingResult br) {
        if (br.hasErrors()) {
            return "admin/updateAdmin";
        }
        this.adminService.updateAdmin(admin);
        return "redirect:/admin/list";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String update(@PathVariable Long id) {
        this.adminService.deleteAdmin(id);
        return "redirect:/admin/list";
    }

    /**
     * 初始化密码
     */
    @RequestMapping(value = "{id}/initPassword", method = RequestMethod.GET)
    public String initPassword(@PathVariable Long id) {
        this.adminService.initPwd(id);
        return "redirect:/admin/list";
    }

    /**
     * login方法
     *
     * @date: 3-31 下午10:07:22
     * @version: V1.0
     *
     * @RequestMapping({"/","/index"})
     */
    @RequestMapping(value = {"alogin", "/"}, method = RequestMethod.GET)
    public String alogin() {
        return "login";
    }

    @RequestMapping(value = "alogin", method = RequestMethod.POST)
    public void alogin(Admin admin, String kaptcha, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        AjaxObj ajax = null;
        String kaptchaInSession = (String) session.getAttribute("kaptcha");
        if (kaptchaInSession.equalsIgnoreCase(kaptcha)) {
            Admin a = this.adminService.getAdmin(admin.getUsername());
            if (a != null) {
                if (a.getPassword().equals(MD5Utils.getInstance().genMD5Pwd(admin.getPassword()))) {
                    session.setAttribute("admin", a);
                    TFMSSessionContext.addSession(session);
                    System.out.println("将session放到map集合");
                    ajax = new AjaxObj(3); //成功登陆
                } else {
                    ajax = new AjaxObj(2); //密码名错误
                }
            } else {
                ajax = new AjaxObj(1); //用户名错误
            }
        } else {
            ajax = new AjaxObj(0); //验证码错误
        }
        response.getWriter().write(JSONUtils.getInstance().obj2Json(ajax));
    }

    /**
     * 退出方法
     */
    @RequestMapping(value = "alogout", method = RequestMethod.GET)
    public String alogout(HttpSession session) {
        TFMSSessionContext.removeSession(session);
        System.out.println("将session移除map集合");
        return "logout";
    }

    /**
     * 商铺初始化列表
     *
     * @throws IOException
     */
    @RequestMapping("initShop")
    public void initShop(HttpServletResponse response, Long adminId) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //查询商铺列表
        //List<Shop> shopList = this.shopService.listLeaveShops();
        List<Shop> shopList = this.shopService.listAllPublicShops();
        //找到管理员具有的
        List<Long> shopIds = this.shopService.listAdminShops(adminId);

        JSONArray jsonArray = new JSONArray();
        for (Shop s : shopList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", s.getId());
            jsonObject.put("pId", 0);
            jsonObject.put("name", s.getName());
            if (shopIds.contains(s.getId())) {
                jsonObject.put("checked", true);
            }
            jsonObject.put("open", true);
            jsonArray.add(jsonObject);
        }
        response.getWriter().write(jsonArray.toString());
    }

    /**
     * 添加商铺
     *
     * @throws IOException
     */
    @RequestMapping(value = "addShop2Admin")
    public void addShop2Admin(HttpServletResponse response, String shopIds, Long adminId) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        if (!StringUtils.getInstance().isEmpty(shopIds)) {
            String[] shopId = shopIds.split(",");
            Long[] shids = new Long[shopId.length];
            for (int i = 0; i < shopId.length; i++) {
                Long id = Long.parseLong(shopId[i]);
                shids[i] = id;
            }
            this.adminShopService.saveShop2Admin(shids, adminId);
        } else {
            this.adminShopService.deleteAllAdminShops(adminId);
        }
        AjaxObj ajaxObj = new AjaxObj();
        response.getWriter().write(JSONUtils.getInstance().obj2Json(ajaxObj));
    }
}
