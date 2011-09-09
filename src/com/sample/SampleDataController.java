package com.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * @author Stanislav Kurilin
 */
@Controller
public class SampleDataController {
    private final SampleDataCreator sampleDataCreator;

    @Inject
    public SampleDataController(SampleDataCreator sampleDataCreator) {
        this.sampleDataCreator = sampleDataCreator;
    }

    @RequestMapping("sample")
    @ResponseBody
    public String create() {
        sampleDataCreator.create();
        return "Sample data created";
    }
}
