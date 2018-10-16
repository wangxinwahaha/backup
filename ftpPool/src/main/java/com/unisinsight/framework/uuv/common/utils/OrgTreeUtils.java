/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;

import com.unisinsight.framework.uuv.model.OrganizationExtraDO;
import org.springframework.util.CollectionUtils;
import java.util.*;

/**
 * description 组织树构建
 *
 * @author long [KF.long@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class OrgTreeUtils {

    /**
     * 获取root节点
     *
     * @param orgs
     * @return
     */
    public static OrganizationExtraDO getRoot(List<OrganizationExtraDO> orgs) {
        for (OrganizationExtraDO org : orgs) {
            if (org.getParentId().equals(0)) {
                return org;
            }
        }
        return null;
    }

    /**
     * 获取子节点（OrganizationTreeResDTO版）
     *
     * @param allOrg
     * @param rootOrg
     * @return
     */
    public static List<OrganizationExtraDO> getChildNode(List<OrganizationExtraDO> allOrg, OrganizationExtraDO rootOrg) {
        ArrayList<OrganizationExtraDO> orgs = new ArrayList<>();
        for (OrganizationExtraDO org : allOrg) {
            if (org.getParentId().equals(rootOrg.getOrgId()) && !org.getOrgId().equals(rootOrg.getOrgId())) {
                orgs.add(org);
            }
        }
        orgs.sort(Comparator.naturalOrder());
        return orgs;
    }

    /**
     * 获取子节点（获取子组织id）
     *
     * @param allOrg
     * @param orgId
     * @return
     */
    private static List<Integer> getChildNode(List<OrganizationExtraDO> allOrg, Integer orgId) {
        ArrayList<Integer> orgIds = new ArrayList<>();
        for (OrganizationExtraDO org : allOrg) {
            if (org.getParentId().equals(orgId) && !org.getOrgId().equals(orgId)) {
                orgIds.add(org.getOrgId());
            }
        }
        return orgIds;
    }

    /**
     * 判断是否存在子节点
     *
     * @param orgs
     * @param targetOrg
     * @return
     */
    private static Boolean hasChild(List<OrganizationExtraDO> orgs, OrganizationExtraDO targetOrg) {
        for (OrganizationExtraDO org : orgs) {
            if (org.getOrgId().equals(targetOrg.getOrgId()) || org.getParentId().equals(targetOrg.getOrgId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在子节点（传id）
     *
     * @param orgs
     * @param orgId
     * @return
     */
    private static Boolean hasChild(List<OrganizationExtraDO> orgs, Integer orgId) {
        for (OrganizationExtraDO org : orgs) {
            if (org.getOrgId().equals(orgId) || org.getParentId().equals(orgId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 递归打印树，返回OrganizationTreeResDTO（无成员信息，平铺）
     *
     * @param orgs
     * @param rootOrg
     * @return
     */
//    public static List<OrganizationExtraDO> buildOrgTree(List<OrganizationExtraDO> orgs,
//                                                         OrganizationExtraDO rootOrg,
//                                                         List<OrganizationExtraDO> result) {
//        List<OrganizationExtraDO> childNodes = getChildNode(orgs, rootOrg);
//        result.addAll(childNodes);
//        for (int i = 0; i < childNodes.size(); i++) {
//            if (hasChild(orgs, childNodes.get(i))) {
//                buildOrgTree(orgs, childNodes.get(i), result);
//            }
//        }
//        return result;
//    }

    /**
     * 递归打印树，返回OrganizationTreeResDTO（无成员信息，树形）
     *
     * @param orgs
     * @param rootOrg
     * @return
     */
    public static OrganizationExtraDO buildOrgTree(List<OrganizationExtraDO> orgs,
                                                   OrganizationExtraDO rootOrg) {
        Map<Integer, List<OrganizationExtraDO>> parentChildMap = new HashMap<>(128);
        for (OrganizationExtraDO org : orgs){
            Integer parent = org.getParentId();
            List<OrganizationExtraDO> child = parentChildMap.get(parent);
            if (child == null){
                child = new ArrayList<>();
                parentChildMap.put(parent, child);
            }
            child.add(org);
        }

        OrganizationTree tree = new OrganizationTree();

        List<OrganizationExtraDO> parentIds = new ArrayList<>();
        parentIds.add(rootOrg);

        while (!CollectionUtils.isEmpty(parentIds)){
            List<OrganizationExtraDO> levelOrgs = new ArrayList<>();
            for (OrganizationExtraDO parent : parentIds){

                tree.addChild(parent);

                List<OrganizationExtraDO> allChild = parentChildMap.get(parent.getOrgId());
                if (allChild != null){
                    levelOrgs.addAll(allChild);
                }
            }
            if (CollectionUtils.isEmpty(levelOrgs)){
                break;
            }
            levelOrgs.sort(Comparator.naturalOrder());

            parentIds.clear();
            parentIds.addAll(levelOrgs);
        }

        return tree.getRoot();

//        //递归写法
//        List<OrganizationExtraDO> childNodes = getChildNode(orgs, rootOrg);
//        //获取目标组织全部子节点
//        rootOrg.setChild(childNodes);
//        for (int i = 0; i < childNodes.size(); i++) {
//            //递归
//            buildOrgTree(orgs, childNodes.get(i));
//        }
//        return rootOrg;
    }

    public static List<Integer> getSubNodes(List<OrganizationExtraDO> orgs,
                                           Integer orgId){
        List<Integer> result = new ArrayList<>();
        getSubNodes(orgs, orgId, result);
        return result;
    }

    /**
     * 递归打印树，返回成员id
     *
     * @param orgs
     * @param orgId
     * @param orgIds
     * @return
     */
    public static void getSubNodes(List<OrganizationExtraDO> orgs,
                                   Integer orgId,
                                   List<Integer> orgIds) {
        List<Integer> childNodes = getChildNode(orgs, orgId);
        orgIds.addAll(childNodes);
        for (int i = 0; i < childNodes.size(); i++) {
            getSubNodes(orgs, childNodes.get(i), orgIds);
        }
    }
}
