package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品情報のDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_PRD)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_PRD_GET_ALL,
            query = JpaConst.Q_PRD_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_COUNT,
            query = JpaConst.Q_PRD_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_GET_ALL_MINE,
            query = JpaConst.Q_PRD_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_COUNT_ALL_MINE,
            query = JpaConst.Q_PRD_COUNT_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_GET_OBSOLETE_ALL,
            query = JpaConst.Q_PRD_GET_OBSOLETE_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_COUNT_OBSOLETE_ALL,
            query = JpaConst.Q_PRD_COUNT_OBSOLETE_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_GET_BY_NAME_NOT_OBSPLETE_ALL,
            query = JpaConst.Q_PRD_GET_BY_NAME_NOT_OBSPLETE_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_COUNT_BY_NAME_NOT_OBSPLETE_ALL,
            query = JpaConst.Q_PRD_COUNT_BY_NAME_NOT_OBSPLETE_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_GET_NOT_OBSPLETE_ALL,
            query = JpaConst.Q_PRD_GET_NOT_OBSPLETE_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_PRD_COUNT_NOT_OBSPLETE_ALL,
            query = JpaConst.Q_PRD_COUNT_NOT_OBSPLETE_ALL_DEF),
})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Product {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.PRD_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品名
     */
    @Column(name = JpaConst.PRD_COL_NAME)
    private String name;

    /**
     * 価格
     */
    @Column(name = JpaConst.PRD_COL_PRC)
    private Integer price;

    /**
     * サイズ(幅)
     */
    @Column(name = JpaConst.PRD_COL_WIDTH)
    private Integer width;

    /**
     * サイズ(奥行)
     */
    @Column(name = JpaConst.PRD_COL_DEPTH)
    private Integer depth;

    /**
     * サイズ(高さ)
     */
    @Column(name = JpaConst.PRD_COL_HEIGHT)
    private Integer height;


    /**
     * 素材
     */
    @Column(name = JpaConst.PRD_COL_MTRL)
    private String material;

    /**
     * 商品説明
     */
    @Column(name = JpaConst.PRD_COL_CONTENT, length = 255, nullable = false)
    private String content;

    /**
     * 在庫数
     */
    @Column(name = JpaConst.PRD_COL_QUANTITY)
    private Integer quantity;

    /**
     * 廃盤
     */
    @Column(name = JpaConst.PRD_COL_OBSOLETE)
    private Integer obsoleteFlag;

    /**
     * 商品を登録したユーザー
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.PRD_COL_USER, nullable = false)
    private User user;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.PRD_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.PRD_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;
}