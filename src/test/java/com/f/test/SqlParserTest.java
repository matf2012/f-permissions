package com.f.test;

import com.f.permissions.IPermissions;
import com.f.permissions.constants.PermissionsOperator;
import com.f.permissions.entity.PermissionsDTO;
import com.f.permissions.entity.PermissionsWhereDTO;
import com.f.permissions.core.FPermissionsCore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-06-07
 */
public class SqlParserTest {

    public static String sql_1= "SELECT DISTINCT  `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`,  `a`.`sum_insured` AS `sum_insured`,  `a`.`premium_tax` AS `premium_tax`,  `a`.`premium_notax` AS `premium_notax`,  `a`.`policy_startdate` AS `policy_startdate`,  `a`.`policy_enddate` AS `policy_enddate`,  `brir`.`coinsurance_code` AS `bxrbh`,  `brir`.`coinsurance_name` AS `coinsurance_name`,  `brin`.`settlement_period` AS `settlement_period`,  (  `brin`.`consultation_amount` * ( CASE WHEN ( `brst`.`amtsum` < 0 ) THEN -( 1 ) ELSE 1 END )) AS `consultation_amount`,  (  `brpn`.`insurance_premium` * ( CASE WHEN (( `brst`.`amtsum` < 0 ) AND ( `brpn`.`insurance_premium` > 0 )) THEN -( 1 ) ELSE 1 END )) AS `insurance_premium`,  `brpn`.`period_time` AS `period_time`,  `brpn`.`settlement_rate` AS `settlement_rate`,  ifnull( `brst`.`seqno`, `brpn`.`seqno` ) AS `seqno`,  `brst`.`invoice_no` AS `invoice_no`,  `brst`.`bill_date` AS `bill_date`,  `brst`.`amtsum` AS `amtsum`,  `brst`.`tax_money` AS `tax_money`,  `brst`.`voucher_seqno` AS `voucher_seqno`,  `brst`.`voucher_date` AS `voucher_date`,  `brpn`.`income_date` AS `income_date`,  `bri`.`assess_time` AS `assess_time`,  `bri`.`entry_by` AS `entry_by`,  `brst`.`invoice_title` AS `invoice_title`,  `brst`.`fee_status` AS `fee_status`,  `brst`.`intotal` AS `intotal`,  `brst`.`insum` AS `insum`,  `brst`.`in_date` AS `in_date`,  `brpn`.`confirm_situation` AS `confirm_situation`,  `brin`.`create_time` AS `create_time`,  `bri`.`biz_source` AS `biz_source`,  `brst`.`id` AS `jsb_id`,  `brst`.`refund_amt` AS `refund_amt`,  `brst`.`refund_date` AS `refund_date`,  `bri`.`renewal_status` AS `renewal_status`,  `brst`.`in_voucher_seqno` AS `in_voucher_seqno`,  `brst`.`in_voucher_date` AS `in_voucher_date`,  `c`.`cust_type` AS `cust_type`  FROM  `bcs_record_insureconsultation` `brin`  JOIN `bcs_record_info` `bri`  LEFT JOIN `bcs_biz_customer` `c` ON `bri`.`cust_id` = `c`.`cust_id`   AND `bri`.`customer_name` = `c`.`cust_name`  JOIN `bcs_record_direct` `a`  JOIN `bcs_record_insurer` `brir`  JOIN `bcs_record_periodization` `brpn`  LEFT JOIN `bcs_record_settlement` `brst` ON `brpn`.`period_id` = `brst`.`period_id`   AND `brst`.`delete_flag` = '0'  WHERE  `brin`.`record_id` = `bri`.`record_id`   AND `brir`.`insurer_id` = `brpn`.`item_id`   AND `brin`.`record_id` = `a`.`record_id`   AND `brir`.`item_id` = `brin`.`consultation_id`   AND `brin`.`delete_flag` = '0'   AND `bri`.`delete_flag` = '0'   AND `bri`.`record_status` IN ( '03', '05', '08' )   AND `brpn`.`delete_flag` = '0'   AND `a`.`delete_flag` = '0'   AND `brir`.`delete_flag` = '0'";
    public static String sql_union_all= "SELECT DISTINCT  `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`,  `a`.`sum_insured` AS `sum_insured`,  `a`.`premium_tax` AS `premium_tax`,  `a`.`premium_notax` AS `premium_notax`,  `a`.`policy_startdate` AS `policy_startdate`,  `a`.`policy_enddate` AS `policy_enddate`,  `brir`.`coinsurance_code` AS `bxrbh`,  `brir`.`coinsurance_name` AS `coinsurance_name`,  `brin`.`settlement_period` AS `settlement_period`,  (  `brin`.`consultation_amount` * ( CASE WHEN ( `brst`.`amtsum` < 0 ) THEN -( 1 ) ELSE 1 END )) AS `consultation_amount`,  (  `brpn`.`insurance_premium` * ( CASE WHEN (( `brst`.`amtsum` < 0 ) AND ( `brpn`.`insurance_premium` > 0 )) THEN -( 1 ) ELSE 1 END )) AS `insurance_premium`,  `brpn`.`period_time` AS `period_time`,  `brpn`.`settlement_rate` AS `settlement_rate`,  ifnull( `brst`.`seqno`, `brpn`.`seqno` ) AS `seqno`,  `brst`.`invoice_no` AS `invoice_no`,  `brst`.`bill_date` AS `bill_date`,  `brst`.`amtsum` AS `amtsum`,  `brst`.`tax_money` AS `tax_money`,  `brst`.`voucher_seqno` AS `voucher_seqno`,  `brst`.`voucher_date` AS `voucher_date`,  `brpn`.`income_date` AS `income_date`,  `bri`.`assess_time` AS `assess_time`,  `bri`.`entry_by` AS `entry_by`,  `brst`.`invoice_title` AS `invoice_title`,  `brst`.`fee_status` AS `fee_status`,  `brst`.`intotal` AS `intotal`,  `brst`.`insum` AS `insum`,  `brst`.`in_date` AS `in_date`,  `brpn`.`confirm_situation` AS `confirm_situation`,  `brin`.`create_time` AS `create_time`,  `bri`.`biz_source` AS `biz_source`,  `brst`.`id` AS `jsb_id`,  `brst`.`refund_amt` AS `refund_amt`,  `brst`.`refund_date` AS `refund_date`,  `bri`.`renewal_status` AS `renewal_status`,  `brst`.`in_voucher_seqno` AS `in_voucher_seqno`,  `brst`.`in_voucher_date` AS `in_voucher_date`,  `c`.`cust_type` AS `cust_type`  FROM  `bcs_record_insureconsultation` `brin`  JOIN `bcs_record_info` `bri`  LEFT JOIN `bcs_biz_customer` `c` ON `bri`.`cust_id` = `c`.`cust_id`   AND `bri`.`customer_name` = `c`.`cust_name`  JOIN `bcs_record_direct` `a`  JOIN `bcs_record_insurer` `brir`  JOIN `bcs_record_periodization` `brpn`  LEFT JOIN `bcs_record_settlement` `brst` ON `brpn`.`period_id` = `brst`.`period_id`   AND `brst`.`delete_flag` = '0'  WHERE  `brin`.`record_id` = `bri`.`record_id`   AND `brir`.`insurer_id` = `brpn`.`item_id`   AND `brin`.`record_id` = `a`.`record_id`   AND `brir`.`item_id` = `brin`.`consultation_id`   AND `brin`.`delete_flag` = '0'   AND `bri`.`delete_flag` = '0'   AND `bri`.`record_status` IN ( '03', '05', '08' )   AND `brpn`.`delete_flag` = '0'   AND `a`.`delete_flag` = '0'   AND `brir`.`delete_flag` = '0'  UNION ALL SELECT DISTINCT  `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`,  `bc`.`sum_insured` AS `sum_insured`,  ifnull(   `bc`.`premium_tax`,(   ifnull( `bc`.`premium_tax_in`, 0 ) + ifnull( `bc`.`premium_tax_out`, 0 ))) AS `premium_tax`,  ifnull(   `bc`.`premium_notax`,(   ifnull( `bc`.`premium_notax_in`, 0 ) + ifnull( `bc`.`premium_notax_out`, 0 ))) AS `premium_notax`,  `b`.`policy_startdate` AS `policy_startdate`,  `b`.`policy_enddate` AS `policy_enddate`,  `brir`.`coinsurance_code` AS `bxrbh`,  `brir`.`coinsurance_name` AS `coinsurance_name`,  `brin`.`settlement_period` AS `settlement_period`,(   `brin`.`consultation_amount` * ( CASE WHEN ( `brst`.`amtsum` < 0 ) THEN -( 1 ) ELSE 1 END )) AS `consultation_amount`,(  `brpn`.`insurance_premium` * ( CASE WHEN (( `brst`.`amtsum` < 0 ) AND ( `brpn`.`insurance_premium` > 0 )) THEN -( 1 ) ELSE 1 END )) AS `insurance_premium`,  ifnull( `brpn`.`commission_amount`, `brpn`.`settletment_amount` ) AS `commission_amount`,  `brpn`.`period_time` AS `period_time`,  `brpn`.`settlement_rate` AS `settlement_rate`,  ifnull( `brst`.`seqno`, `brpn`.`seqno` ) AS `seqno`,  `brst`.`invoice_no` AS `invoice_no`,  `brst`.`bill_date` AS `bill_date`,  `brst`.`amtsum` AS `amtsum`,  `brst`.`tax_money` AS `tax_money`,  `brst`.`voucher_seqno` AS `voucher_seqno`,  `brst`.`voucher_date` AS `voucher_date`,  `brpn`.`income_date` AS `income_date`,  `bri`.`assess_time` AS `assess_time`,  `bri`.`entry_by` AS `entry_by`,  `brst`.`invoice_title` AS `invoice_title`,  `brst`.`fee_status` AS `fee_status`,  `brst`.`intotal` AS `intotal`,  `brst`.`insum` AS `insum`,  `brst`.`in_date` AS `in_date`,  `brpn`.`confirm_situation` AS `confirm_situation`,  `brin`.`create_time` AS `create_time`,  `bri`.`biz_source` AS `biz_source`,  `brst`.`id` AS `jsb_id`,  `brst`.`refund_amt` AS `refund_amt`,  `brst`.`refund_date` AS `refund_date`,  `bri`.`renewal_status` AS `renewal_status`,  `brst`.`in_voucher_seqno` AS `in_voucher_seqno`,  `brst`.`in_voucher_date` AS `in_voucher_date`,  `c`.`cust_type` AS `cust_type`  FROM  ((((((        `bcs_record_insureconsultation` `brin`        JOIN `bcs_record_info` `bri`         )       LEFT JOIN `bcs_biz_customer` `c` ON (((          `bri`.`cust_id` = `c`.`cust_id`           )         AND ( `bri`.`customer_name` = `c`.`cust_name` ))))      JOIN `bcs_record_reinsur` `b`       )     JOIN `bcs_record_cession` `bc`      )    JOIN `bcs_record_insurer` `brir`     )   JOIN (    `bcs_record_periodization` `brpn`    LEFT JOIN `bcs_record_settlement` `brst` ON (((       `brpn`.`period_id` = `brst`.`period_id`        )      AND ( `brst`.`delete_flag` = '0' )))))  WHERE  ((    `brin`.`record_id` = `bri`.`record_id`     )    AND ( `brir`.`insurer_id` = `brpn`.`order_id` )    AND ( `brin`.`item_id` = `b`.`record_id` )    AND ( `brir`.`direct_id` = `brin`.`consultation_id` )    AND (   `bri`.`record_status` IN ( '03', '05', '08' ))    AND ( `brin`.`delete_flag` = '0' )    AND ( `bri`.`delete_flag` = '0' )    AND ( `brpn`.`delete_flag` = '0' )    AND ( `brir`.`delete_flag` = '0' )    AND ( `brir`.`delete_flag` = '0' )    AND ( `b`.`delete_flag` = '0' )   AND ( `bc`.`delete_flag` = '0' )   AND ( `bc`.`reinsur_id` = `b`.`reinsur_id` ))";

    public static String sql_2 = "SELECT   `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`  from bcs_record_info bri,bcs_record_direct brd  union   SELECT   `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`  from bcs_record_info bri,bcs_record_reinsur brr  where bri.record_id = brr.record_id  union   SELECT   `bri`.`record_id` AS `record_id`,  '4' AS `ba_type`,  `bri`.`cust_id` AS `cust_id`,  `bri`.`customer_name` AS `customer_name`,  `bri`.`customer_type` AS `customer_type`,  `bri`.`record_name` AS `record_name`,  `bri`.`org_code` AS `org_code`,  `bri`.`biz_property` AS `biz_property`,  `bri`.`insurtype_code` AS `insurtype_code`,  `bri`.`record_status` AS `record_status`,  ifnull( `bri`.`renewal_times`, 0 ) AS `renewal_times`  from bcs_record_info bri left join bcs_record_cession brc  on bri.record_id = brr.record_id";



    @Test
    public void test_2_1(){
        IPermissions permissions = new FPermissionsCore();

        PermissionsDTO dto = new PermissionsDTO();
        dto.setSql(sql_union_all);

        List<PermissionsWhereDTO> values = new ArrayList<>();

        values.add(genWHere("bcs_record_direct","status","1"));
        values.add(genWHere("bcs_record_info","status","1","2"));
        values.add(genWHere("bcs_record_reinsur","status","1","2"));

        dto.setValues(values);

        permissions.handle(dto);

    }


    @Test
    public void test_2_2(){
        IPermissions permissions = new FPermissionsCore();

        PermissionsDTO dto = new PermissionsDTO();
        dto.setSql(sql_2);

        List<PermissionsWhereDTO> values = new ArrayList<>();

        values.add(genWHere("bcs_record_direct","period_time","1"));
        values.add(genWHere("bcs_record_info","status","1","2"));
        values.add(genWHere("bcs_record_reinsur","confirm_situation","1","2"));

        dto.setValues(values);

        permissions.handle(dto);

    }
    @Test
    public void test_3_1(){
        IPermissions permissions = new FPermissionsCore();

        PermissionsDTO dto = new PermissionsDTO();
        dto.setSql(sql_2);

        List<PermissionsWhereDTO> values = new ArrayList<>();

        values.add(genWHere("bcs_record_direct","period_time","1"));
        values.add(genWHere("bcs_record_info","status","1","2"));
        values.add(genWHere("bcs_record_reinsur","confirm_situation",null));

        dto.setValues(values);

        permissions.handle(dto);

    }

    private PermissionsWhereDTO genWHere(String table,String column,String ... values){

        PermissionsWhereDTO v1 = new PermissionsWhereDTO();
        v1.setTableName(table);
        v1.setColumn(column);
        if(values != null){
            List<String> v1Values = Arrays.asList(values);
            v1.setValues(v1Values);
            if(values.length > 1){
                v1.setOperator(PermissionsOperator.IN);
            }
        }
        return v1;
    }
}
