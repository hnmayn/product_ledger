package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.User;

/*
 * ユーザーデータのDTOモデル⇆Viewモデルの変換を行うクラス
 */
public class UserConverter {

    /*
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param uv UserViewのインスタンス
     * @return Userのインスタンス
     */
    public static User toModel(UserView uv) {

        return new User(
                uv.getId(),
                uv.getCode(),
                uv.getName(),
                uv.getDepartment(),
                uv.getPassword(),
                uv.getAdminFlag() == null
                ? null
                        : uv.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()
                        ? JpaConst.ROLE_ADMIN
                                : JpaConst.ROLE_GENERAL,

                                uv.getCreatedAt(),
                                uv.getUpdatedAt(),
                                uv.getDeleteFlag() == null
                                ? null
                                        : uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                        ? JpaConst.USER_DEL_TRUE
                                                : JpaConst.USER_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */
    public static UserView toView(User u) {

        if(u == null) {
            return null;
        }

        return new UserView(
                u.getId(),
                u.getCode(),
                u.getName(),
                u.getDepartment(),
                u.getPassword(),
                u.getAdminFlag() == null
                ? null
                        : u.getAdminFlag() == JpaConst.ROLE_ADMIN
                        ? AttributeConst.ROLE_ADMIN.getIntegerValue()
                                : AttributeConst.ROLE_GENERAL.getIntegerValue(),

                                u.getCreatedAt(),
                                u.getUpdatedAt(),
                                u.getDeleteFlag());

    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param  list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<UserView> toViewList(List<User> list){
        List<UserView> uvs = new ArrayList<>();

        for (User u : list) {
            uvs.add(toView(u));
        }

        return uvs;
    }

    /**
     * Viewモデルの全フィールド内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(User u, UserView uv) {
        u.setId(uv.getId());
        u.setCode(uv.getCode());
        u.setName(uv.getName());
        u.setPassword(uv.getPassword());
        u.setAdminFlag(uv.getAdminFlag());
        u.setCreatedAt(uv.getCreatedAt());
        u.setUpdatedAt(uv.getUpdatedAt());
        u.setDeleteFlag(uv.getDeleteFlag());
    }
}
