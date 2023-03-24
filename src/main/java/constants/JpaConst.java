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

    // ユーザーテーブル
    String TABLE_USER = "users"; // テーブル名
    // ユーザーテーブルカラム
    String USER_COL_ID  = "id"; // id
    String USER_COL_CODE = "code"; // ユーザー番号
    String USER_COL_NAME = "name"; // 氏名
    String USER_COL_COMPANY = "company"; // 所属会社
    String USER_COL_PASS = "password"; // パスワード
    String USER_COL_ADMIN_FLAG = "admin_flag"; // 従業員管理者権限
    String USER_COL_CREATED_AT = "created_at"; // 登録日時
    String USER_COL_UPDATED_AT = "updated_at"; // 更新日時
    String USER_COL_DELETE_FLAG = "delete_flag"; // 削除フラグ

    int ROLE_ADMIN = 1; // 管理者権限ON(管理者)
    int ROLE_GENERAL = 0; // 管理者権限OFF(一般)
    int USER_DEL_TRUE = 1; // 削除フラグON(削除済み)
    int USER_DEL_FALSE = 0; // 削除フラグOFF(現役)

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
    String PRD_COL_USER = "employee_id"; // 商品情報を作成したユーザーのid

    int PRD_OBS_TRUE = 1; // 廃盤フラグON(廃盤済)
    int PRD_OBS_FALSE = 0; // 廃盤フラグOFF(現役)

    String PRD_COL_CREATED_AT = "created_at"; // 登録日時
    String PRD_COL_UPDATED_AT = "updated_at"; // 更新日時

    // Entity名
    String ENTITY_USER = "user"; //ユーザー
    String ENTITY_PRD = "pruduct"; //商品情報

    // JPQL内パラメーター
    String JPQL_PARM_CODE = "code"; //ユーザー番号
    String JPQL_PARM_PASSWORD = "password"; // パスワード
    String JPQL_PARM_USER = "user"; // ユーザー

    // NameQueryのnameとquery
    // 全てのユーザーをidの降順に取得する
    String Q_USER_GET_ALL = ENTITY_USER + ".getALL"; // name
    String Q_USER_GET_ALL_DEF = "SELECT u FROM User AS u ORDER BY u.id DESC"; //query
    // 全てのユーザーの件数を取得する
    String Q_USER_COUNT = ENTITY_USER + ".count";
    String Q_USER_COUNT_DEF = "SELECT COUNT(u) FROM User AS u";
    // ユーザー番号とハッシュ化(ゴミ)済パスワードを条件に未削除のユーザーを取得する
    String Q_USER_GET_BY_CODE_AND_PASS = ENTITY_USER + ".getByCodeAndPass";
    String Q_USER_GET_BY_CODE_AND_PASS_DEF = "SELECT u FROM User AS u WHERE u.deleteFlag = 0 AND u.code = :" + JPQL_PARM_CODE +" AND u.password = :" + JPQL_PARM_PASSWORD;
    // 指定した社員番号を保持するユーザーの件数を取得する
    String Q_USER_COUNT_REGISTERED_BY_CODE = ENTITY_USER + ".countRegisteredByCode";
    String Q_USER_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(u) FROM User AS u WHERE .code = :" + JPQL_PARM_CODE;
    // 全ての商品情報をidの降順に取得する
    String Q_PRD_GET_ALL = ENTITY_PRD + ".getAll";
    String Q_PRD_GET_ALL_DEF = "SELECT p FROM Product AS  ORDER BY p.id DESC";
    // 全ての商品情報の件数を取得する
    String Q_PRD_COUNT = ENTITY_PRD + ".count";
    String Q_PRD_COUNT_DEF = "SELECT COUNT(p) FROM Product AS p";
    // 指定したユーザーが作成した商品情報を全件idの降順で取得する
    String Q_PRD_GET_ALL_MINE = ENTITY_PRD + ".getAllMine";
    String Q_PRD_GET_ALL_MINE_DEF = "SELECT p FROM Product AS p WHERE p.user = :" + JPQL_PARM_USER + " ORDER BY p.id DESC";
    // 指定したユーザーが作成した商品情報の件数を取得する
    String Q_PRD_COUNT_ALL_MINE = ENTITY_PRD + ".countAllMine";
    String Q_PRD_COUNT_ALL_MINE_DEF = "SELECT COUNT(p) FROM Product AS p WHERE p.user = :" + JPQL_PARM_USER;

}