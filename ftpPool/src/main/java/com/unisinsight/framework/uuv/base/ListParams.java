/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.base;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;
import com.unisinsight.framework.uuv.common.utils.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页list
 *
 * @author liuran [KF.liuranA@h3c.com]
 * @date 2018/9/7 9:41
 * @since 1.0
 */
public class ListParams<T> implements Serializable {
    private static final long serialVersionUID = -3534183000968816018L;
    private static final String AND = " and ";
    private static final String OR = " or ";

    private int pageIndex = 0;

    private int pageSize = 10;

    private boolean count = false;

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public ListParams(Class<T> entityClass) {
        condition = new Condition(entityClass);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public void parseFilter(String[] filter) {
        if (filter == null) {
            return;
        }
        for (String line : filter) {
            if (StringUtils.isEmpty(line)) {
                continue;
            }

            Example.Criteria criteria = condition.and();
            if (line.contains(OR) && line.contains(AND)) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "filter 不能同时包含and和or");
            }

            String[] orList = null;
            boolean isOr = false;
            if (line.contains(OR)) {
                isOr = true;
                orList = line.split(OR);
            } else {
                orList = line.split(AND);
            }

            for (String operationLine : orList) {
                String[] operation = Operation.parese(operationLine);
                if (operation.length != 3 && operation.length != 5) {
                    throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "参数长度不够");
                }
                String field = StringUtils.withoutUnderscoreName(operation[0]);
                if (operation.length == 3) {
                    Object value = operation[2];
                    Operation op = Operation.get(operation[1]);
                    switch (op) {
                        case eq:
                            if (isOr) {
                                criteria.orEqualTo(field, value);
                            } else {
                                criteria.andEqualTo(field, value);
                            }
                            break;
                        case like:
                            if (isOr) {
                                criteria.orLike(field, value.toString());
                            } else {
                                criteria.andLike(field, value.toString());
                            }
                            break;
                        case gt:
                            if (isOr) {
                                criteria.orGreaterThan(field, value);
                            } else {
                                criteria.andGreaterThan(field, value);
                            }
                            break;
                        case ge:
                            if (isOr) {
                                criteria.orGreaterThanOrEqualTo(field, value);
                            } else {
                                criteria.andGreaterThanOrEqualTo(field, value);
                            }
                            break;
                        case lt:
                            if (isOr) {
                                criteria.orLessThan(field, value);
                            } else {
                                criteria.andLessThan(field, value);
                            }
                            break;
                        case le:
                            if (isOr) {
                                criteria.orLessThanOrEqualTo(field, value);
                            } else {
                                criteria.andLessThanOrEqualTo(field, value);
                            }
                            break;
                        default:
                            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "不支持该运算符");
                    }
                } else {
                    if (Operation.get(operation[1]) == null || Operation.get(operation[3]) == null) {
                        throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "不支持该运算符");
                    }
                    if (isOr) {
                        criteria.orBetween(operation[0], operation[2], operation[4]);
                    } else {
                        criteria.andBetween(operation[0], operation[2], operation[4]);
                    }
                }

            }

        }
    }

    public void parseOrderBy(String orderBy) {
        if (StringUtils.isEmpty(orderBy)) {
            return;
        }
        String[] array = orderBy.split(" ");
        if (array.length != 2) {
            throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR, "order by参数长度不符合要求");
        }

        condition.setOrderByClause(orderBy);
    }

    enum Operation {
        eq, like, gt, ge, lt, le, between, and, in;
        private static Map<String, Operation> map = new HashMap<>();

        static {
            for (Operation o : values()) {
                map.put(o.toString(), o);
            }
        }

        static Operation get(String var) {
            return map.get(var);
        }

        static String[] parese(String line) {
            line = line.trim();
            Operation firstOp = null;
            int firstIndex = Integer.MAX_VALUE;
            for (Operation operation : values()) {
                int index = line.indexOf(" " + operation.toString() + " ");
                if (index >= 0 && index < firstIndex) {
                    firstIndex = index;
                    firstOp = operation;
                }
            }

            if (firstOp == null) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }


            if (between.equals(firstOp)) {
                if (!line.contains(" " + and.toString() + " ")) {
                    throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
                }

                String[] result = new String[5];
                String[] arrayBetween = line.split(" " + between.toString() + " ");
                result[0] = arrayBetween[0];
                result[1] = between.toString();
                String[] arrayAnd = arrayBetween[1].split(" " + and.toString() + " ");
                result[2] = arrayAnd[0];
                result[3] = and.toString();
                result[4] = arrayAnd[1];

                return result;
            } else {
                String[] result = new String[3];
                String[] array = line.split(" " + firstOp + " ");
                result[0] = array[0];
                result[1] = firstOp.toString();
                result[2] = array[1];
                return result;
            }
        }
    }
}
