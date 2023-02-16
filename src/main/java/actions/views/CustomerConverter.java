package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Customer;

/**
 * お客様データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class CustomerConverter {
    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param cv CustomerViewのインスタンス
     * @return Customerのインスタンス
     */
    public static Customer toModel(CustomerView cv) {

        return new Customer(
                cv.getId(),
                cv.getCode(),
                cv.getName(),
                cv.getAge(),
                cv.getGender(),
                cv.getPassword(),
                cv.getAdminFlag() == null
                        ? null
                        : cv.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()
                                ? JpaConst.ROLE_ADMIN
                                : JpaConst.ROLE_GENERAL,
                cv.getCreatedAt(),
                cv.getUpdatedAt(),
                cv.getDeleteFlag() == null
                        ? null
                        : cv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.CUS_DEL_TRUE
                                : JpaConst.CUS_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param c Customerのインスタンス
     * @return CustomerViewのインスタンス
     */
    public static CustomerView toView(Customer c) {

        if(c == null) {
            return null;
        }

        return new CustomerView(
                c.getId(),
                c.getCode(),
                c.getName(),
                c.getAge(),
                c.getGender(),
                c.getPassword(),
                c.getAdminFlag() == null
                        ? null
                        : c.getAdminFlag() == JpaConst.ROLE_ADMIN
                                ? AttributeConst.ROLE_ADMIN.getIntegerValue()
                                : AttributeConst.ROLE_GENERAL.getIntegerValue(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getDeleteFlag() == null
                        ? null
                        : c.getDeleteFlag() == JpaConst.CUS_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CustomerView> toViewList(List<Customer> list) {
        List<CustomerView> cvs = new ArrayList<>();

        for (Customer c : list) {
            cvs.add(toView(c));
        }

        return cvs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param c DTOモデル(コピー先)
     * @param cv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer c, CustomerView cv) {
        c.setId(cv.getId());
        c.setCode(cv.getCode());
        c.setName(cv.getName());
        c.setAge(cv.getAge());
        c.setGender(cv.getGender());
        c.setPassword(cv.getPassword());
        c.setAdminFlag(cv.getAdminFlag());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
        c.setDeleteFlag(cv.getDeleteFlag());

    }

}
