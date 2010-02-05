/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.facilitydata.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.facilitydata.service.FacilityDataService;
import org.openmrs.module.facilitydata.util.FacilityDataUtil;
import org.openmrs.module.facilitydata.util.FacilityDataDateUtils;
import org.openmrs.module.facilitydata.model.FacilityDataValue;
import org.openmrs.module.facilitydata.model.FacilityDataFormSchema;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/module/facilitydata/manage.form")
public class FacilityDataManagementController {
    private static final Logger log = Logger.getLogger(FacilityDataManagementController.class);

    @ModelAttribute("reports")
    public List<FacilityDataValue> getReports() {
        return FacilityDataUtil.getService().getAllFacilityDataValues();
    }

    @ModelAttribute("months")
    public List<String> getMonths() {
        List<String> months = Lists.newArrayList();
        for (int i = 1; i <= 12; i++) {
            months.add((i < 10 ? "0" + i : "" + i));
        }
        return months;
    }

    @ModelAttribute("today")
    public String getToday() {
        return FacilityDataDateUtils.today();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String managementHomepage(ModelMap map, @RequestParam(required = false) Integer schamaId,
                                     @RequestParam(required = false) Integer site, @RequestParam(required = false) String year,
                                     @RequestParam(required = false) String month, HttpServletRequest request) throws ParseException {
        FacilityDataService service = FacilityDataUtil.getService();
        if (service.getAllFacilityDataFormSchemas().size() > 0)
            map.addAttribute("schemas", service.getAllFacilityDataFormSchemas());
        else {
            List<FacilityDataFormSchema> schemas = Lists.newArrayList();
            schemas.add(FacilityDataUtil.createMockSchema());
            map.addAttribute("schemas",schemas);
        }

        if (schamaId != null) {
            map.addAttribute("schema", service.getFacilityDataFormSchema(schamaId));
        }
        if (site != null) {
            map.addAttribute("site", Context.getLocationService().getLocation(site));
        }
        return "/module/facilitydata/manageReport";
    }
}