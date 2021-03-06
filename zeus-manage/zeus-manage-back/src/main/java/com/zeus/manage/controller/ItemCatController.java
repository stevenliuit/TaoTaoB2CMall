package com.zeus.manage.controller;

import com.zeus.manage.pojo.ItemCat;
import com.zeus.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("item/cat")
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 根据父节点id查询类目列表
     * 
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ItemCat>> queryItemCat(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {
        try {
            ItemCat record = new ItemCat();
            record.setParentId(parentId);
            List<ItemCat> itemCats = this.itemCatService.queryListByWhere(record);
            if (itemCats == null || itemCats.isEmpty()) {
                // 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemCats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
