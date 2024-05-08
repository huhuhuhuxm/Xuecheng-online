package com.xuecheng.base.constant;

/**
 * 字典枚举常量
 */
public class DictionaryConstant {

    /**
     * 公共属性类型
     */
    public enum PublicAttributeType {
        USE("1", 1, "使用态"),
        DELETE("0", 0,"删除态"),
        TEMPORARY("-1",-1, "暂时态");
        private final String code;
        private final int codeInt;
        private final String desc;

        PublicAttributeType(String code, int codeInt, String desc) {
            this.code = code;
            this.codeInt = codeInt;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public int getCodeInt() {
            return codeInt;
        }

        public String getDesc() {
            return desc;
        }
    }


    /**
     * 对象的审核状态
     */
    public enum ObjectAuditStatus {
        AUDIT_NOT_PASSED("002001", "审核未通过"),
        NOT_AUDITED("002002", "未审核"),
        AUDIT_PASSED("002003", "审核通过");
        private final String code;
        private final String description;

        ObjectAuditStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }


    /**
     * 资源类型
     */
    public enum ResourceType {
        IMAGE("001001", "图片"),
        VIDEO("001002", "视频"),
        OTHER("001003", "其它");

        private final String code;
        private final String desc;

        ResourceType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 课程审核状态
     */
    public enum CourseAuditStatus {
        AUDIT_NOT_PASSED("202001", "审核未通过"),
        NOT_SUBMITTED("202002", "未提交"),
        SUBMITTED("202003", "已提交"),
        AUDIT_PASSED("202004", "审核通过");

        private final String code;
        private final String description;

        CourseAuditStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }

    /**
     * 课程收费情况
     */

    public enum CourseFees {
        FREE("201000", "免费"),
        PAID("201001", "收费");

        private final String code;
        private final String description;

        CourseFees(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 课程等级
     */

    public enum courseLevel {
        PRIMARY("204001", "初级"),
        INTERMEDIATE("204002", "中级"),
        ADVANCED("204003", "高级");

        private final String code;
        private final String description;

        courseLevel(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 课程模式状态
     */

    public enum CourseModeStatus {
        RECORDED("200002", "录播"),
        LIVE("200003", "直播");

        private final String code;
        private final String description;

        CourseModeStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 课程发布状态
     */
    public enum CoursePublishStatus {
        UNPUBLISHED("203001", "未发布"),
        PUBLISHED("203002", "已发布"),
        OFFLINE("203003", "下线");

        private final String code;
        private final String description;

        CoursePublishStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 订单交易状态
     */

    public enum OrderTransactionTypeStatus {
        UNPAID("600001", "未支付"),
        PAID("600002", "已支付"),
        CLOSED("600003", "已关闭"),
        REFUNDED("600004", "已退款"),
        COMPLETED("600005", "已完成");

        private final String code;
        private final String description;

        OrderTransactionTypeStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 课程作业记录审批状态
     */

    public enum CourseWorkRecordApprovalStatus {
        NOT_SUBMITTED("306001", "未提交"),
        TO_BE_GRADED("306002", "待批改"),
        GRADED("306003", "已批改");

        private final String code;
        private final String description;

        CourseWorkRecordApprovalStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }


    /**
     * 消息通知状态
     */

    public enum MessageNotificationStatus {
        NOT_NOTIFIED("003001", "未通知"),
        SUCCESS("003002", "成功");

        private final String code;
        private final String description;

        MessageNotificationStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }


    /**
     * 支付交易状态
     */

    public enum PaymentRecordsTransactionStatus {
        UNPAID("601001", "未支付"),
        PAID("601002", "已支付"),
        REFUNDED("601003", "已退款");

        private final String code;
        private final String description;

        PaymentRecordsTransactionStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }


    /**
     * 业务订单类型
     */
    public enum BusinessOrderType {
        PURCHASE_COURSE("60201", "购买课程"),
        STUDY_MATERIAL("60202", "学习资料");

        private final String code;
        private final String description;

        BusinessOrderType(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 第三方支付渠道编号
     */

    public enum ThirdPartyPaymentChannelID {
        WECHAT_PAY("603001", "微信支付"),
        ALIPAY("603002", "支付宝");

        private final String code;
        private final String description;

        ThirdPartyPaymentChannelID(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 选课类型
     */
    public enum CourseSelectionType {
        FREE("700001", "免费课程"),
        PAID("700002", "收费课程");

        private final String code;
        private final String description;

        CourseSelectionType(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 课程状态
     */
    public enum CourseStatus {
        ENROLLMENT_SUCCESS("701001", "选课成功"),
        AWAITING_PAYMENT("701002", "待支付");

        private final String code;
        private final String description;

        CourseStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 选修学习资格
     */
    public enum ElectiveStudyQualification {
        ENROLLMENT_SUCCESS("701001", "选课成功"),
        AWAITING_PAYMENT("701002", "待支付");

        private final String code;
        private final String description;

        ElectiveStudyQualification(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * orderby初始值
     */
    public static final Integer ORDERBY_INITIVAL_VALUE = 0;


    /**
     * 课程计划移动类型
     */
    public enum MoveType {
        MOVE_UP("moveup"),
        MOVE_DOWN("movedown");
        private final String description;

        MoveType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


}
