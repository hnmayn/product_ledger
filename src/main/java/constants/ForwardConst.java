package constants;

/*
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等・画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    // action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_USER("user"),
    ACT_PRD("Product"),
    ACT_AUTH("Auth"),

    // command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),

    // jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_USER_INDEX("employees/index"),
    FW_USER_SHOW("employees/show"),
    FW_USER_NEW("employees/new"),
    FW_USER_EDIT("employees/edit"),
    FW_PRD_INDEX("products/index"),
    FW_PRD_SHOW("products/show"),
    FW_PRD_NEW("products/new"),
    FW_PRD_EDIT("products/edit");

    /*
     * 文字列
     */
    private final String text;

    /*
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値（文字列）取得
     */
    public String getValue() {
        return this.text;
    }
}
