<%--
  The contents of this file are subject to the OpenMRS Public License
  Version 1.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://license.openmrs.org

  Software distributed under the License is distributed on an "AS IS"
  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  License for the specific language governing rights and limitations
  under the License.

  Copyright (C) OpenMRS, LLC.  All Rights Reserved.

--%>
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ taglib prefix="facilitydata" uri="/WEB-INF/view/module/facilitydata/facilitydata.tld" %>
<%@ attribute name="question" required="true" type="org.openmrs.module.facilitydata.model.StockQuestion" %>
<%@ attribute name="formQuestion" required="true"
              type="org.openmrs.module.facilitydata.model.FacilityDataFormQuestion" %>
<%@ attribute name="value" required="false" type="org.openmrs.module.facilitydata.model.FacilityDataValue" %>
<%@ attribute name="editable" type="java.lang.Boolean" required="false"
              description="Denotes whether or not the report should be editable." %>

<tr align="left">
    <td width="50%" align="left">
<tr align="left">
    <td width="50%" align="right">${formQuestion.name}</td>
    <c:if test="${editable or editable eq null}">
        <td width="50%" align="left">
            <select name="question.${question.uuid}">
                <option value="" ${value == null ? "selected" : ""}></option>
                <c:forEach items="${question.codedOptions}" var="option">
                    <option value="${option}" ${(value != null && value.valueText == option) ? "selected" : ""}>${option}</option>
                </c:forEach>
            </select>
            for <input type="text" size="5" name="${question.uuid}_days"
                       value="${facilitydata:getDays(value.comments)}"/> days
            <input type="text" size="20" name="${question.uuid}_reason"
                   value="${facilitydata:getReason(value.comments)}">
        </td>
    </c:if>
    <c:if test="${editable != null && !editable}">
        <td style="color:#f11;font-weight:bold;" width="50%" align="left">
            <c:choose>
                <c:when test="${value != null && fn:length(value.valueText) > 0}">
                    ${value.valueText}
                    <c:if test="${facilitydata:hasText(facilitydata:getDays(value.comments))}">
                        <spring:message code="facilitydata.stock.days" arguments="${facilitydata:getDays(value.comments)}"/>
                    </c:if>
                    <c:if test="${facilitydata:hasText(facilitydata:getReason(value.comments))}">
                        -- ${facilitydata:getReason(value.comments)}
                    </c:if>
                </c:when>
                <c:otherwise>
                    <spring:message code="facilitydata.no-answer"/>
                </c:otherwise>
            </c:choose>
        </td>
    </c:if>
</tr>
</tr>