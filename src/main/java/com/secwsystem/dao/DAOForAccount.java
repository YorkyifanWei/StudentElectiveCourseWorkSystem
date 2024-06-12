package com.secwsystem.dao;

import java.util.ArrayList;

/**
 * 数据访问对象接口，用于处理公有和私有账户数据的操作。
 * <p>
 * 该接口定义了对账户数据的增、删、改、查操作，支持公有数据和私有数据两种类型。
 * 其中，TPublic 表示公有账户数据类型，TPrivate 表示私有账户数据类型。
 * 这种泛型设计允许接口适用于不同类型的账户数据操作，提高了代码的通用性和可维护性。
 */

public interface DAOForAccount<TPublic, TPrivate> {

    // getAll
    ArrayList<TPrivate> getAll();

    /**
     * 添加私有账户数据。
     *
     * @param t 待添加的私有账户数据。
     * @return 添加操作是否成功。
     */
    boolean add(TPrivate t);

    /**
     * 删除私有账户数据。
     *
     * @param id@return 删除操作是否成功。
     */
    boolean delete(String id);

    /**
     * 更新公有账户数据。
     *
     * @param t 待更新的公有账户数据。
     * @return 更新操作是否成功。
     */
    boolean updatePublic(TPublic t);

    /**
     * 更新私有账户数据。
     *
     * @param t 待更新的私有账户数据。
     * @return 更新操作是否成功。
     */
    boolean updatePrivate(TPrivate t);

    /**
     * 根据ID获取公有账户数据。
     *
     * @param id 公有账户数据的唯一标识。
     * @return 对应的公有账户数据。
     */
    TPublic getPublic(String id);

    /**
     * 根据ID获取私有账户数据。
     *
     * @param id 私有账户数据的唯一标识。
     * @return 对应的私有账户数据。
     */
    TPrivate getPrivate(String id);
}
