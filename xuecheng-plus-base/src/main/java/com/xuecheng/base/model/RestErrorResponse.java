package com.xuecheng.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 与前端约定返回异常信息的模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse implements Serializable {
    private String errMessage;
}
