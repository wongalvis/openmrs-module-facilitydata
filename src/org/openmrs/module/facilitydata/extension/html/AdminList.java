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

package org.openmrs.module.facilitydata.extension.html;

import com.google.common.collect.Maps;
import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

import java.util.Collections;
import java.util.Map;

public class AdminList extends AdministrationSectionExt {
    @Override
    public MEDIA_TYPE getMediaType() {
        return Extension.MEDIA_TYPE.html;
    }

    @Override
    public String getTitle() {
        return "facilitydata.title";
    }

    @Override
    public Map<String, String> getLinks() {
        Map<String, String> map = Maps.newTreeMap();
        map.put("module/facilitydata/manage.form", "facilitydata.view-enter");
        map.put("module/facilitydata/schema.list", "facilitydata.manage-form-schema");
        map.put("module/facilitydata/question.list", "facilitydata.manage-question");
        map.put("module/facilitydata/section.list", "facilitydata.manage-sections");
        return Collections.unmodifiableMap(map);
    }
}