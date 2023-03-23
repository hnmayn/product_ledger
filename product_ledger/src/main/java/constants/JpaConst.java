package constants;

/*
 * DB関連の項目値を定義するインターフェイス
 * ※インターフェイスに定義した変数は public static final 修飾子がついていると見なされる
 */
public interface JpaConst {

    // persistence-unit名
    String PERSISTENCE_UNIT_NAME = "product_ledger";

    // データ取得件数の最大値
    int ROW_PER_PAGE = 15; // 1ページに表示するレコードの数

    // 従業員テーブル
    String TABLE_EMP = "employees"; // テーブル名
    // 従業員テーブルカラム
    String EMP_COL_ID  = "id"; // id
    String EMP_COL_CODE = "code"; // 社員番号
    String EMP_COL_NAME = "name"; // 氏名
    String EMP_COL_PASS = "password"; // パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; // 管理者権限
    String EMP_COL_CREATED_AT = "created_at"; // 登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; // 更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; // 削除フラグ

    int ROLE_ADMIN = 1; // 管理者権限ON(管理者)
    int ROLE_GENERAL = 0; // 管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; // 削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; // 削除フラグOFF(現役)

    // 商品情報テーブル
    String TABLE_PRD = "product"; // テーブル名
    // 商品情報テーブルカラム
    String PRD_COL_ID = "id"; // id
    String PRD_COL_NAME = "name"; // 商品名
    String PRD_COL_PRC = "price"; // 価格
    String PRD_COL_SIZE = "size"; // 商品サイズ
    String PRD_COL_MTRL = "material"; // 素材
    String PRD_COL_CONTENT = "content"; // 商品説明
    String PRD_COL_QUANTITY = "quantity"; // 在庫数
    String PRD_COL_OBSOLETE = "obsolete_flag"; // 廃盤
    String PRD_COL_EMP = "employee_id"; // 商品情報を作成した従業員のid

    int PRD_OBS_TRUE = 1; // 廃盤フラグON(廃盤済)
    int PRD_OBS_FALSE = 0; // 廃盤フラグOFF(現役)

    String PRD_COL_CREATED_AT = "created_at"; // 登録日時
    String PRD_COL_UPDATED_AT = "updated_at"; // 更新日時

    // Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_PRD = "pruduct"; //商品情報

    // JPQL内パラメーター
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; // パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; // 従業員

    // NameQueryのnameとquery
    // 全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getALL"; // name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    // 全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    // 社員番号とハッシュ化(ゴミ)済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE +" AND e.password = :" + JPQL_PARM_PASSWORD;
    // 指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_REGISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    // 全ての商品情報をidの降順に取得する
    String Q_PRD_GET_ALL = ENTITY_PRD + ".getAll";
    String Q_PRD_GET_ALL_DEF = "SELECT r FROM Product AS r ORDER BY r.id DESC";
    // 全ての商品情報の件数を取得する
    String Q_PRD_COUNT = ENTITY_PRD + ".count";
    String Q_PRD_COUNT_DEF = "SELECT COUNT(r) FROM Product AS r";
    // 指定した従業員が作成した商品情報を全件idの降順で取得する
    String Q_PRD_GET_ALL_MINE = ENTITY_PRD + ".getAllMine";
    String Q_PRD_GET_ALL_MINE_DEF = "SELECT r FROM Product AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    // 指定した従業員が作成した商品情報の件数を取得する
    String Q_PRD_COUNT_ALL_MINE = ENTITY_PRD + ".countAllMine";
    String Q_PRD_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Product AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;

}
