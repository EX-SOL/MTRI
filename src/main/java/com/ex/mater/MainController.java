package com.ex.mater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/load-page")
    public String loadPage(String pageName) throws Exception {
        logger.info("loadPage pageName: {}", pageName);
        return pageName;
    }

}
