package constants;

/**
 *各出力メッセージを定義するEnumクラス
 *
 */

public enum MessageConst {

    // 認証
    I_LOGINED("ログインしました。"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    // DB更新
    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),
    P_DELETED("廃盤にしました"),

    // バリデーション
    U_NONAME("氏名を入力してください。"),
    U_NOCOMPANY("所属会社を入力してください。"),
    U_NOPASSWORD("パスワードを入力してください。"),
    U_NOUSER_CODE("ユーザー番号を入力してください。"),
    U_USER_CODE_EXIST("入力されたユーザー番号の情報は既に存在しています。"),
    P_NONAME("商品名を入力してください。"),
    P_NOPRICE("価格を入力してください。"),
    P_NOSIZE("サイズを入力してください"),
    P_NOMATERIAL("素材を入力してください"),
    P_NOCONTENT("商品説明を入力してください"),
    P_NOQUANTITY("在庫数を入力してください");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getMessage() {
        return this.text;
    }


}
