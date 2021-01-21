package com.kevin.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin.yang
 */
public enum StatusEnum {
    INIT(0, "初始化", 100),
    ING(1, "进行中", 101),
    FINISH(2, "已完成", 200),
    CANCEL(3, "已取消", 300),
    ;

    public Integer status;
    public String desc;
    public Integer code;

    StatusEnum(Integer status, String desc, Integer code) {
        this.status = status;
        this.desc = desc;
        this.code = code;
    }

    private static Map<Integer, Integer> STATUS_MAP;
    static {
        STATUS_MAP = new HashMap<>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            STATUS_MAP.put(statusEnum.status, statusEnum.code);
        }
    }

    public static Integer getCodeByStatus(Integer status) {
        return STATUS_MAP.get(status);
    }
}
