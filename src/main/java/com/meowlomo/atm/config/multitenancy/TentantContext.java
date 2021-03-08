package com.meowlomo.atm.config.multitenancy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

@Component
public class TentantContext {
    private static final String KEY_CURRENT_TENANT_ID = "CURRENT_TENANT_ID";
    private static final Map<String, Long> mContext = Maps.newConcurrentMap();

    private static final String TENANT_ID_COLUMN_NAME = "tenant_id";
    private static final List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("driver_type_instruction_type_link",
            "test_case_module_link", "qrtz_cron_triggers", "qrtz_scheduler_state", "test_case_driver_alias_map",
            "run_set_notification_link", "instruction_option", "driver_pack_driver_type_map",
            "notification_email_notification_target_link", "qrtz_calendars", "log_level", "run_type", "content_type",
            "qrtz_simprop_triggers", "test_case_tag_link", "driver_vendor",
            "instruction_action_instruction_option_link", "\"group\"", "element_locator_type",
            "element_type_instruction_action_link", "element_type", "project_type", "status",
            "system_requirement_pack_system_requirement_link", "source_type", "qrtz_job_details", "tenant",
            "test_case_share_folder_test_case_link", "system_requirement_type",
            "instruction_type_instruction_option_link", "instruction_type", "qrtz_fired_triggers", "driver_property",
            "driver_type", "instruction_type_element_type_link", "qrtz_blob_triggers", "run_set_alias_name_map",
            "file_type", "instruction_action", "test_case_driver_type_map", "element_type_element_locator_type_link",
            "test_case_instruction_type_map", "run_set_type", "qrtz_triggers", "step_log_type",
            "driver_pack_driver_alias_map", "test_case_type", "qrtz_simple_triggers",
            "instruction_type_instruction_action_link", "test_case_option", "color",
            "element_type_instruction_option_link", "flyway_schema_history", "qrtz_locks", "run_set_alias_link",
            "qrtz_paused_trigger_grps", "driver_property_predefined_value", "system_requirement", "driver","instruction_option_map");

    private ApplicationContext appContext;

    @Value("${meowlomo.atm.multitenancy.enable:false}")
    private boolean enableMultitenancy;

    public TentantContext(ApplicationContext cc) {
        appContext = cc;
    }

    public void setCurrentTentatantId(Long currentTenantId) {
        if (currentTenantId != null && currentTenantId.longValue() != 1) {
            TenantSqlParser tenantSqlParser = new TenantSqlParser().setTenantHandler(new TenantHandler() {
                @Override
                public Expression getTenantId(boolean where) {
                    if (enableMultitenancy) {
                        throw new RuntimeException("error on getting tennant id from logined use context.");
                    }
                    return new LongValue(currentTenantId);
                }

                @Override
                public String getTenantIdColumn() {
                    return TENANT_ID_COLUMN_NAME;
                }

                @Override
                public boolean doTableFilter(String tableName) {
                    return TentantContext.IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
                }
            });
            PaginationInterceptor bean = appContext.getBean(PaginationInterceptor.class);
            bean.setSqlParserList(Lists.newArrayList(tenantSqlParser));
        }
        else {
            PaginationInterceptor bean = appContext.getBean(PaginationInterceptor.class);
            bean.setSqlParserList(null);
        }
        mContext.put(KEY_CURRENT_TENANT_ID, currentTenantId);
    }

    public Long getCurrentTenantId() {
        return mContext.get(KEY_CURRENT_TENANT_ID);
    }

}
