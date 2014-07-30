package org.sixlab.projecteuler.pro03;

import java.math.BigInteger;

/**
 * Created by loki on 2014/7/18.
 */
public class BigNumber {
    public static void main(String[] args) {
        StringBuffer sql = new StringBuffer();
        sql.append("select bm.id bidId, " +
                "       bm.bid_code bidCode, " +
                "       bm.bid_name bidName, " +
                "       bp.id packageId, " +
                "       bp.package_code packageCode, " +
                "       bp.package_name packageName, " +
                "       bm.agent_id agentId, " +
                "       bm.agent_code agentCode, " +
                "       bm.agent_name agentName, " +
                "       bnoa.supplier_cp_id supplierCpId, " +
                "       bnoa.supplier_cp_name supplierCpName, " +
                "       (case bm.win_bid_fee_mode " +
                "         when '20' then " +
                "          bp.win_bid_fee_mode_f " +
                "         else " +
                "          to_char(bnoa.commission_price_rmb) " +
                "       end) agentFees " +
                "  from bid_main bm, bid_package bp, bdb_notice_of_award bnoa " +
                " where bp.bid_id = bm.id " +
                "   and bnoa.package_id = bp.id " +
                " order by bp.id desc");
        sql.append("from demo_Company t1 ");
        sql.append("where 1=1 and is_deleted=0");
    }
}