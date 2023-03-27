package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Product;

/**
 * 商品データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ProductConverter {
    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param pv ProductViewのインスタンス
     * @return Productのインスタンス
     */
    public static Product toModel(ProductView pv) {
        return new Product(
                pv.getId(),
                pv.getName(),
                toNumber(pv.getPrice()),
                toNumber(pv.getWidth()),
                toNumber(pv.getDepth()),
                toNumber(pv.getHeight()),
                pv.getMaterial(),
                pv.getContent(),
                toNumber(pv.getQuantity()),
                pv.getObsoleteFlag() == null
                ? null
                        : pv.getObsoleteFlag() == AttributeConst.PRD_OBS_TRUE.getIntegerValue()
                        ? JpaConst.PRD_OBS_TRUE
                                : JpaConst.PRD_OBS_FALSE,
                                UserConverter.toModel(pv.getUser()),
                                pv.getCreatedAt(),
                                pv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param p Productのインスタンス
     * @return ProductViewのインスタンス
     */
    public static ProductView toView(Product p) {

        if (p == null) {
            return null;
        }

        return new ProductView(
                p.getId(),
                p.getName(),
                toString(p.getPrice()),
                toString(p.getWidth()),
                toString(p.getDepth()),
                toString(p.getHeight()),
                p.getMaterial(),
                p.getContent(),
                toString(p.getQuantity()),
                p.getObsoleteFlag(),
                UserConverter.toView(p.getUser()),
                p.getCreatedAt(),
                p.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ProductView> toViewList(List<Product> list){
        List<ProductView> evs = new ArrayList<>();

        for (Product p : list) {
            evs.add(toView(p));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param pv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Product p, ProductView pv) {
        p.setId(pv.getId());
        p.setName(pv.getName());
        p.setPrice(toNumber(pv.getPrice()));
        p.setWidth(toNumber(pv.getWidth()));
        p.setDepth(toNumber(pv.getDepth()));
        p.setHeight(toNumber(pv.getHeight()));
        p.setMaterial(pv.getMaterial());
        p.setContent(pv.getContent());
        p.setQuantity(toNumber(pv.getQuantity()));
        p.setObsoleteFlag(pv.getObsoleteFlag());
        UserConverter.toView(p.getUser());
        p.setCreatedAt(pv.getCreatedAt());
        p.setUpdatedAt(pv.getUpdatedAt());
    }

    /*
     * 文字列を数値に変換する
     * @param strNumber 変換前文字列
     * @return 変換後数値
     */
    protected static int toNumber(String strNumber) {
        int number = 0;
        try {
            number = Integer.parseInt(strNumber);
        } catch (Exception e) {
            number = Integer.MIN_VALUE;
        }
        return number;
    }

    /*
     * 数値を文字列に変換する
     * @param num 変換前数値
     * @return 変換後文字列
     */
    protected static String toString(Integer num) {
        String str = "";
        try {
            str = String.valueOf(num);
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

}
