package constants;

/**
 *画面の項目値等を定義するEnumクラス
 *
 */

public enum AttributeConst {

    // フラッシュメッセージ(アクション実行時に簡単なメッセージを表示させる機能)
    FLUSH("flush"),

    // 一覧画面共有
    MAX_ROW("maxRow"),
    PAGE("page"),

    // 入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    // ログイン中の従業員
    LOGIN_EMP("login_employee"),

    // ログイン画面
    LOGIN_ERR("loginError"),

    // 従業員管理
    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_ID("id"),
    EMP_CODE("code"),
    EMP_PASS("password"),
    EMP_NAME("name"),
    EMP_ADMIN_FLG("admin_flag"),

    // 管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    // 削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    // 商品管理
    PRODUCT("product"),
    PRODUCTS("products"),
    PRD_COUNT("products_count"),
    PRD_ID("id"),
    PRD_NAME("name"),
    PRD_PRC("price"),
    PRD_SIZE("size"),
    PRD_MTRL("material"),
    PRD_CONTENT("content"),
    PRD_QUANTITY("quantity"),
    PRD_OBS_FLAG("obsolete_flag"),

    // 廃盤フラグ
    PRD_OBS_TRUE(1), // 廃盤済
    PRD_OBS_FALSE(0); // 現役

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }
}
