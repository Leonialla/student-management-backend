package com.example.controller.admin;

import com.example.model.vo.response.ResultVO;
import com.example.config.permission.annotation.Admin;
import com.example.controller.BaseController;
import com.example.model.entity.DepartmentEntity;
import com.example.service.admin.DepartmentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.config.permission.annotation.Admin.DEPARTMENT_MANAGE;

@Admin(DEPARTMENT_MANAGE)
@RequestMapping("/admin/department")
@RestController
public class DepartmentController extends BaseController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated DepartmentEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated DepartmentEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String name) {
        return service.getPageCount(name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String name) {
        return service.getPage(1, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String name) {
        return service.getPage(index, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}
