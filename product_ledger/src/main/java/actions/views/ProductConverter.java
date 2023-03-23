package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Product;

/**
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class ProductConverter {
    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param pv ReportViewのインスタンス
     * @return Reportのインスタンス
     */
    public static Product toModel(ProductView pv) {
        return new Product(
                pv.getId(),
                pv.getName(),
                pv.getPrice(),
                pv.getSize(),
                pv.getMaterial(),
                pv.getContent(),
                pv.getQuantity(),
                pv.getObsoleteFlag() == null
                    ? null
                    : pv.getObsoleteFlag() == AttributeConst.PRD_OBS_TRUE.getIntegerValue()
                    ? JpaConst.PRD_OBS_TRUE
                    : JpaConst.PRD_OBS_FALSE,
                    EmployeeConverter.toModel(pv.getEmployee()),
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
                p.getPrice(),
                p.getSize(),
                p.getMaterial(),
                p.getContent(),
                p.getQuantity(),
                p.getObsoleteFlag(),
                EmployeeConverter.toView(p.getEmployee()),
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
        p.setPrice(pv.getPrice());
        p.setSize(pv.getSize());
        p.setMaterial(pv.getMaterial());
        p.setContent(pv.getContent());
        p.setQuantity(pv.getQuantity());
        p.setObsoleteFlag(pv.getObsoleteFlag());
        EmployeeConverter.toView(p.getEmployee());
        p.setCreatedAt(pv.getCreatedAt());
        p.setUpdatedAt(pv.getUpdatedAt());
    }
}
