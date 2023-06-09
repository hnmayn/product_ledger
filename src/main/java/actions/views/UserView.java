package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 従業員情報について画面の入力値・出力値を扱うViewモデル
 */
@Getter // 全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter // 全てのクラスフィールドについてSetterを自動生成する(Lombok)
@NoArgsConstructor // 引数なしコンストラクタを自動生成する(Lombk)
@AllArgsConstructor // 全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class UserView {

    /*
     * id
     */
    private Integer id;

    /*
     * ユーザー番号
     */
    private String code;

    /*
     * 氏名
     */
    private String name;

    /*
     * 部署
     */
    private String department;

    /*
     * パスワード
     */
    private String password;

    /*
     * 管理者権限があるかどうか(一般:0,管理者:1)
     */
    private Integer adminFlag;

    /*
     * 登録日時
     */
    private LocalDateTime createdAt;

    /*
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /*
     *削除されたユーザーかどうか(現役:0,削除済:1)
     */
    private Integer deleteFlag;
}
