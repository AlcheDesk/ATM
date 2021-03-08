package com.meowlomo.atm.core.resource;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.atm.core.model.UserActivityLog;
import com.meowlomo.atm.core.model.UserActivityLogExample;
import com.meowlomo.atm.core.resource.exception.CustomBadRequestException;
import com.meowlomo.atm.core.resource.exception.CustomForbiddenException;
import com.meowlomo.atm.core.resource.exception.CustomInternalServerErrorException;
import com.meowlomo.atm.core.resource.exception.CustomNotAcceptableException;
import com.meowlomo.atm.core.resource.exception.CustomNotAllowedException;
import com.meowlomo.atm.core.resource.exception.CustomNotAuthorizedException;
import com.meowlomo.atm.core.resource.exception.CustomNotFoundException;
import com.meowlomo.atm.core.resource.exception.CustomNotSupportedException;
import com.meowlomo.atm.core.resource.exception.CustomServiceUnavailableException;
import com.meowlomo.atm.core.resource.model.MeowlomoResponse;
import com.meowlomo.atm.core.resource.query.SearchExampleGenerator;
import com.meowlomo.atm.core.service.base.UserActivityLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/userActivityLogs")
@Api(value = "userActivityLog resources", produces = "application/json")
public class UserActivityLogResource {

    private final Logger logger = LoggerFactory.getLogger(UserActivityLogResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "PRO";

    @Autowired
    private UserActivityLogService userActivityLogService;

    // =====Get Method Start=====

    /**
     * Select by example.
     *
     * @return the meowlomo response
     * @throws Exception
     *             the exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取UserActivityLog", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "log (case-sensitive)", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "orderBy", required = false, dataType = "string", paramType = "query") })
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest)
            throws Exception {
        logger.info("received userActivityLog select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                UserActivityLogExample example = new UserActivityLogExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", userActivityLogService.countByExample(example));
                List<UserActivityLog> records = userActivityLogService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                UserActivityLogExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new UserActivityLogExample().createCriteria();
                }
                UserActivityLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        UserActivityLogExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", userActivityLogService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else if (queryParams.containsKey("userUuid")) {
                UserActivityLogExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new UserActivityLogExample().createCriteria();
                }
                String userUuid = queryParams.getFirst("userUuid");
                criteria.andUserUuidEqualTo(UUID.fromString(userUuid));
                UserActivityLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        UserActivityLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", userActivityLogService.countByExample(example));
                List<UserActivityLog> records = null;
                records = userActivityLogService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("activityUuid")) {
                UserActivityLogExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new UserActivityLogExample().createCriteria();
                }
                String activityUuid = queryParams.getFirst("activityUuid");
                criteria.andUserUuidEqualTo(UUID.fromString(activityUuid));
                UserActivityLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        UserActivityLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", userActivityLogService.countByExample(example));
                List<UserActivityLog> records = null;
                records = userActivityLogService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else {
                UserActivityLogExample.Criteria criteria = null;
                if (queryParams.getFirst("isDeleted") == null) {
                    criteria = new UserActivityLogExample().createCriteria();
                }
                UserActivityLogExample example = this.searchExampleGenerator.generateExample(uriInfo, criteria,
                        UserActivityLogExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", userActivityLogService.countByExample(example));
                List<UserActivityLog> records = null;
                records = userActivityLogService.selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
        }
        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
            logger.error("Class:{},",this.getClass().getName(),ex);
            throw ex;
        }
        catch (Exception ex) {
            UUID exuuid = UUID.randomUUID();
            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
            String code = ERROR_TYPE + "CORE01SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }

    // =====Get Method End=====

}