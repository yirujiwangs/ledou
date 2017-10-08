package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HistoryService;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2015/12/5.
 */
@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @RequestMapping(value = "index.do")
    @ResponseBody
    public String index(){
        return historyService.index().toJSONString();
    }
    @RequestMapping(value = "search.do")
    @ResponseBody
    public String search(@RequestBody String param){

        return historyService.search(param).toJSONString();
    }
}
