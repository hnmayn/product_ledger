package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品登録情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ProductView {

    /**
     * id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 価格
     */
    private String price;

    /**
     * サイズ(幅)
     */
    private String width;

    /**
     * サイズ(奥行)
     */
    private String depth;

    /**
     * サイズ(高さ)
     */
    private String height;

    /**
     * 素材
     */
    private String material;

    /**
     * 商品説明
     */
    private String content;

    /**
     * 在庫数
     */
    private String quantity;

    /**
     * 廃盤かどうか(現役:0,廃盤済:1)
     */
    private Integer obsoleteFlag;

    /**
     * 商品を登録した従業員
     */
    private UserView user;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;
}
