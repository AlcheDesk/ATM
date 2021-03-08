package com.meowlomo.atm.core.resource;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLink;
import com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample;
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
import com.meowlomo.atm.core.service.base.InstructionActionInstructionOptionLinkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/instructionActionInstructionOptionLinks")
@Api(value = "instructionActionInstructionOptionLink resources", produces = "application/json")
public class InstructionActionInstructionOptionLinkResource {

    private final Logger logger = LoggerFactory.getLogger(InstructionActionInstructionOptionLinkResource.class);

    @Autowired
    private SearchExampleGenerator searchExampleGenerator;

    private static final String ERROR_TYPE = "RSETTCLINK";

    @Autowired
    private InstructionActionInstructionOptionLinkService instructionActionInstructionOptionLinkService;

    // @Autowired
    // private InstructionActionInstructionOptionLinkReferenceService
    // instructionActionInstructionOptionLinkReferenceService;

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
    @ApiOperation(value = "读取InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "GET")
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
    public MeowlomoResponse selectByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) throws Exception {
        logger.info("received instructionActionInstructionOptionLink select call");
        try {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
            if (queryParams.isEmpty()) {
                InstructionActionInstructionOptionLinkExample example = new InstructionActionInstructionOptionLinkExample();
                example.or().andIdIsNotNull();
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionInstructionOptionLinkService.countByExample(example));
                List<InstructionActionInstructionOptionLink> records = instructionActionInstructionOptionLinkService
                        .selectByExampleWithRowbounds(example, rowBounds);
                return new MeowlomoResponse(metadata, records, null);
            }
            else if (queryParams.containsKey("count")) {
                InstructionActionInstructionOptionLinkExample.Criteria criteria = null;
                InstructionActionInstructionOptionLinkExample example = this.searchExampleGenerator
                        .generateExample(uriInfo, criteria, InstructionActionInstructionOptionLinkExample.class);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionInstructionOptionLinkService.countByExample(example));
                return new MeowlomoResponse(metadata, null, null);
            }
            else {
                InstructionActionInstructionOptionLinkExample.Criteria criteria = null;
                InstructionActionInstructionOptionLinkExample example = this.searchExampleGenerator
                        .generateExample(uriInfo, criteria, InstructionActionInstructionOptionLinkExample.class);
                RowBounds rowBounds = this.searchExampleGenerator.generateSearchRowBounds(uriInfo);
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", instructionActionInstructionOptionLinkService.countByExample(example));
                List<InstructionActionInstructionOptionLink> records = null;
                // if (queryParams.containsKey("ref")) {
                // records =
                // instructionActionInstructionOptionLinkReferenceService.selectByExampleWithRowbounds(example,
                // rowBounds);
                // }
                // else {
                // records =
                // instructionActionInstructionOptionLinkService.selectByExampleWithRowbounds(example,
                // rowBounds);
                // }
                records = instructionActionInstructionOptionLinkService.selectByExampleWithRowbounds(example,
                        rowBounds);
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

    /**
     * Select by primary id.
     *
     * @param id
     *            the id
     * @return the meowlomo response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "读取单个InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
    @ApiImplicitParam(name = "id", value = "instructionActionInstructionOptionLink id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
    public MeowlomoResponse selectByPrimaryId(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("id") Long id) {
        logger.info("received instructionActionInstructionOptionLink select by id call");
        try {
            // MultivaluedMap<String, String> queryParams =
            // uriInfo.getQueryParameters();
            InstructionActionInstructionOptionLink selectRecord = null;
            // if (queryParams.containsKey("ref")) {
            // selectRecord =
            // instructionActionInstructionOptionLinkReferenceService.selectByPrimaryKey(id);
            // }
            // else {
            // selectRecord =
            // instructionActionInstructionOptionLinkService.selectByPrimaryKey(id);
            // }
            selectRecord = instructionActionInstructionOptionLinkService.selectByPrimaryKey(id);
            if (selectRecord != null) {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 1);
                return new MeowlomoResponse(metadata, selectRecord, null);
            }
            else {
                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
                metadata.put("count", 0);
                return new MeowlomoResponse(metadata, null, null);
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
            String code = ERROR_TYPE + "CORE02SYS";
            logger.error(message, ex);
            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
        }
    }
    // =====Get Method End=====

//    // =====Delete Method Start=====
//
//    /**
//     * Delete by ID.
//     *
//     * @param id
//     *            the id
//     * @return the meowlomo response
//     */
//    @DELETE
//    @LogUserActivity
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "删除单个InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "DELETE")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    @ApiImplicitParam(name = "id", value = "instructionActionInstructionOptionLink id", required = true, allowEmptyValue = false, dataType = "string", paramType = "path")
//    public MeowlomoResponse deleteByID(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, @PathParam("id") long id) {
//        logger.info("received instructionActionInstructionOptionLink delete by id call " + uriInfo.getPath());
//        try {
//            // select the record first
//            InstructionActionInstructionOptionLink record = instructionActionInstructionOptionLinkService
//                    .selectByPrimaryKey(id);
//            int deleteResult = instructionActionInstructionOptionLinkService.deleteByPrimaryKey(id);
//            if (deleteResult == 0 && record == null) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, record, null);
//            }
//            else if (deleteResult == 1 && record != null) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 1);
//                return new MeowlomoResponse(metadata, record, null);
//            }
//            else {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " resource with UUID = " + id + " does not exists.";
//                String message = "不存在ID为" + id + "的对象，无法删除。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE01DEL";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomNotFoundException(null, message, trace, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "CORE03SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//
//    /**
//     * Delete by example.
//     *
//     * @return the meowlomo response
//     */
//    @DELETE
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "批量删除InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "DELETE")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "ids", value = "ids", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "name", value = "name", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "comment", value = "comment", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "type", value = "type", required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "log", value = "log (case-sensitive)", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "startDate", value = "start date [unix second]", required = false, dataType = "long", paramType = "query"),
//            @ApiImplicitParam(name = "endDate", value = "end date [unix second]", required = false, dataType = "long", paramType = "query") })
//    public MeowlomoResponse deleteByExample(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest) {
//        logger.info("received instructionActionInstructionOptionLink delete call " + uriInfo.getPath());
//        try {
//            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
//            if (queryParams.isEmpty()) {
//                InstructionActionInstructionOptionLinkExample example = new InstructionActionInstructionOptionLinkExample();
//                example.or().andIdIsNotNull();
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                List<InstructionActionInstructionOptionLink> records = instructionActionInstructionOptionLinkService
//                        .selectByExample(example);
//                metadata.put("count", instructionActionInstructionOptionLinkService.countByExample(example));
//                int deleteResult = instructionActionInstructionOptionLinkService.deleteByExample(example);
//                if (deleteResult == records.size()) {
//                    return new MeowlomoResponse(metadata, records, null);
//                }
//                else {
//                    try {
//                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                    }
//                    catch (Exception e) {
//
//                    }
//                    UUID exuuid = UUID.randomUUID();
//                    String trace = "exception UUID=" + exuuid + " target count <> delete count ";
//                    String message = "无法完成删除，删除数与目标数。问题唯一码[" + exuuid + "]";
//                    String code = ERROR_TYPE + "CORE02DEL";
//                    logger.error(trace, httpServletRequest.getContextPath());
//                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//                }
//            }
//            else {
//                // default to set is not deleted
//                com.meowlomo.atm.core.model.InstructionActionInstructionOptionLinkExample.Criteria criteria = new InstructionActionInstructionOptionLinkExample()
//                        .createCriteria();
//                criteria.andIdIsNotNull();
//                InstructionActionInstructionOptionLinkExample example = this.searchExampleGenerator
//                        .generateExample(uriInfo, criteria, InstructionActionInstructionOptionLinkExample.class);
//                // count
//                List<InstructionActionInstructionOptionLink> countRecords = instructionActionInstructionOptionLinkService
//                        .selectByExample(example);
//                // mark deleted
//                int deleteResult = instructionActionInstructionOptionLinkService.deleteByExample(example);
//                // get back records
//                List<Long> deletedIds = new ArrayList<Long>();
//                for (InstructionActionInstructionOptionLink target : countRecords) {
//                    deletedIds.add(target.getId());
//                }
//                InstructionActionInstructionOptionLinkExample deletedExample = new InstructionActionInstructionOptionLinkExample();
//                deletedExample.or().andIdIsNotNull().andIdIn(deletedIds);
//                List<InstructionActionInstructionOptionLink> records = instructionActionInstructionOptionLinkService
//                        .selectByExample(deletedExample);
//                if (deleteResult == records.size()) {
//                    ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                    metadata.put("count", countRecords.size());
//                    return new MeowlomoResponse(metadata, records, null);
//                }
//                else {
//                    try {
//                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                    }
//                    catch (Exception e) {
//
//                    }
//                    UUID exuuid = UUID.randomUUID();
//                    String trace = "exception UUID=" + exuuid + " target count <> delete count ";
//                    String message = "无法完成删除，删除执行数与目标数不一致。问题唯一码[" + exuuid + "]";
//                    String code = ERROR_TYPE + "CORE03DEL";
//                    logger.error(trace, httpServletRequest.getContextPath());
//                    throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//                }
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "04SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//
//    // =====Delete Method End=====
//
//    // =====Patch Method Start=====
//
//    /**
//     * Update selective.
//     *
//     * @param records
//     *            the records
//     * @return the meowlomo response
//     * @throws Exception
//     *             the exception
//     */
//    @PATCH
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "更新InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, responseContainer = "List", httpMethod = "PATCH")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "更改操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class) })
//    public MeowlomoResponse updateSelective(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, InstructionActionInstructionOptionLink[] records) throws Exception {
//        logger.info("received patch instructionActionInstructionOptionLink by id call " + uriInfo.getPath());
//        try {
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "更新内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE01PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//
//            List<Long> targetIds = new ArrayList<Long>();
//            // loop and check each record
//            for (int i = 0; i < records.length; i++) {
//                InstructionActionInstructionOptionLink record = records[i];
//                if (record.getId() != null && record.getId() > 0) {
//                    targetIds.add(record.getId());
//                }
//            }
//            // check all have id
//            List<Long> errorIndex = new ArrayList<Long>();
//            if (records.length != targetIds.size()) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "部分更新请求不含ID。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE02PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else {
//                // start the update
//                // update one by one
//                for (int i = 0; i < records.length; i++) {
//                    InstructionActionInstructionOptionLink record = records[i];
//                    int updateResult = instructionActionInstructionOptionLinkService
//                            .updateByPrimaryKeySelective(records[i]);
//                    if (updateResult != 1) {
//                        errorIndex.add(record.getId());
//                    }
//                }
//            }
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                InstructionActionInstructionOptionLinkExample example = new InstructionActionInstructionOptionLinkExample();
//                example.or().andIdIn(targetIds);
//                List<InstructionActionInstructionOptionLink> finalRecords = instructionActionInstructionOptionLinkService
//                        .selectByExample(example);
//                // sort return result
//                List<InstructionActionInstructionOptionLink> finalReturnRecords = new ArrayList<InstructionActionInstructionOptionLink>();
//                for (Long id : targetIds) {
//                    for (InstructionActionInstructionOptionLink oRecord : finalRecords) {
//                        if (oRecord.getId().equals(id)) {
//                            finalReturnRecords.add(oRecord);
//                        }
//                    }
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalReturnRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " could not patch all records ";
//                String message = "部分或全部更新失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE03PAT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "CORE05SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//
//    // =====Patch Method End=====
//
//    // =====Put Method Start=====
//
//    /**
//     * Replace.
//     *
//     * @param records
//     *            the records
//     * @return the meowlomo response
//     */
//    @PUT
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "替换或添加InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "PUT")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "exception UUID=\" + exuuid + \" put body is empty", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "部分替换请求不含ID。问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "部分或全部替换失败。 问题唯一码[\" + exuuid + \"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    public MeowlomoResponse replace(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, InstructionActionInstructionOptionLink[] records) {
//        logger.info("received put instructionActionInstructionOptionLink by primary id call");
//        try {
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " put body is empty";
//                String message = "替换内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE01PUT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//
//            // validation
//            BeanValidator.BeanValidation(records, ERROR_TYPE);
//
//            // with id do replace, no id do insert
//            List<Long> errorIndex = new ArrayList<Long>();
//            List<InstructionActionInstructionOptionLink> finalRecords = new ArrayList<InstructionActionInstructionOptionLink>();
//            for (int i = 0; i < records.length; i++) {
//                InstructionActionInstructionOptionLink record = records[i];
//                if (record.getId() == null) {
//                    // do insert
//                    long insertResult = instructionActionInstructionOptionLinkService.insertSelective(record);
//                    if (insertResult != 1) {
//                        errorIndex.add((long) i);
//                    }
//                    else {
//                        finalRecords.add(record);
//                    }
//                }
//                else {
//                    // do update
//                    int updateResult = instructionActionInstructionOptionLinkService.updateByPrimaryKey(record);
//                    if (updateResult != 1) {
//                        errorIndex.add((long) i);
//                    }
//                    else {
//                        finalRecords
//                                .add(instructionActionInstructionOptionLinkService.selectByPrimaryKey(record.getId()));
//                    }
//                }
//            }
//
//            // check all update sucess
//            if (errorIndex.isEmpty()) {
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isCompleted();
//                }
//                catch (Exception e) {
//
//                }
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " could not put all record ";
//                String message = "部分或全部替换添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CROE02PUT";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomForbiddenException(null, message, trace, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "CORE06SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//    // =====Put Method End=====
//
//    // =====Post Method Start=====
//
//    /**
//     * Insert.
//     *
//     * @param records
//     *            the records
//     * @return the meowlomo response
//     */
//    @POST
//    @LogUserActivity
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @ApiOperation(value = "添加InstructionActionInstructionOptionLink", response = MeowlomoResponse.class, httpMethod = "POST")
//    @ApiResponses(value = { @ApiResponse(code = HttpServletResponse.SC_OK, message = "NO MESSAGE"),
//            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "添加操作无法完成，请与管理员联系。并提供唯一码[\"+exuuid+\"]", response = MeowlomoResponse.class),
//            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "遇到系统内部错误请与管理员联系。并提供错误唯一码[\"+exuuid+\"]。", response = MeowlomoResponse.class) })
//    public MeowlomoResponse insert(@Context UriInfo uriInfo, @Context HttpServletRequest httpServletRequest, InstructionActionInstructionOptionLink[] records) {
//        logger.info("received post instructionActionInstructionOptionLink call ");
//        try {
//            // empty just return
//            if (records == null) {
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " patch body is empty ";
//                String message = "添加内容为空。问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE01POS";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomBadRequestException(null, message, trace, code, exuuid);
//            }
//            else if (records.length == 0) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", 0);
//                return new MeowlomoResponse(metadata, null, null);
//            }
//
//            // validation
//            BeanValidator.BeanValidation(records, ERROR_TYPE);
//
//            // check all have id
//            List<Long> errorIndex = new ArrayList<Long>();
//            // start the insert
//            // insert one by one
//            List<InstructionActionInstructionOptionLink> finalRecords = new ArrayList<InstructionActionInstructionOptionLink>();
//            for (int i = 0; i < records.length; i++) {
//                InstructionActionInstructionOptionLink record = records[i];
//                record.setId(null);
//                long insertResult = instructionActionInstructionOptionLinkService.insertSelective(record);
//                if (insertResult != 1) {
//                    errorIndex.add((long) i);
//                }
//                else {
//                    finalRecords.add(record);
//                }
//            }
//            // check all insert success
//            if (errorIndex.isEmpty()) {
//                ObjectNode metadata = JsonNodeFactory.instance.objectNode();
//                metadata.put("count", finalRecords.size());
//                return new MeowlomoResponse(metadata, finalRecords, null);
//            }
//            else {// not all success
//                try {
//                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
//                }
//                catch (Exception e) {
//
//                }
//                UUID exuuid = UUID.randomUUID();
//                String trace = "exception UUID=" + exuuid + " could not post all record ";
//                String message = "部分或全部添加失败，失败序列。" + errorIndex.toString() + " 问题唯一码[" + exuuid + "]";
//                String code = ERROR_TYPE + "CORE02POS";
//                logger.error(trace, httpServletRequest.getContextPath());
//                throw new CustomNotAcceptableException(null, message, trace, code, exuuid);
//            }
//        }
//        catch (CustomNotAuthorizedException | CustomBadRequestException | CustomForbiddenException
//                | CustomNotAcceptableException | CustomNotAllowedException | CustomNotFoundException
//                | CustomNotSupportedException | CustomServiceUnavailableException ex) {
//            logger.error("Class:{},",this.getClass().getName(),ex);
//            throw ex;
//        }
//        catch (Exception ex) {
//            UUID exuuid = UUID.randomUUID();
//            String message = "遇到系统内部错误 请与管理员联系。并提供错误唯一码[" + exuuid + "]。";
//            String code = ERROR_TYPE + "CORE07SYS";
//            logger.error(message, ex);
//            throw new CustomInternalServerErrorException(ex, message, ex.getMessage(), code, exuuid);
//        }
//    }
//    // ===== Post Method End=====
}