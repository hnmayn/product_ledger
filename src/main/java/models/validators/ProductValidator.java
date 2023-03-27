package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ProductView;
import constants.MessageConst;

/**
 * 商品インスタンスに設定されている値のバリデーションを行うクラス
 */
public class ProductValidator {

    /**
     * 商品インスタンスの各項目についてバリデーションを行う
     * @param pv 商品インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ProductView pv) {
        List<String> errors = new ArrayList<String>();

        // 商品名のチェック
        String nameError = validateName(pv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        // 価格のチェック
        String priceError = validatePrice(pv.getPrice());
        if (!priceError.equals("")) {
            errors.add(priceError);
        }

        // サイズのチェック(幅)
        String widthError = validateWidth(pv.getWidth());
        if (!widthError.equals("")) {
            errors.add(widthError);
        }

        // サイズのチェック(奥行)
        String depthError = validateDepth(pv.getDepth());
        if (!depthError.equals("")) {
            errors.add(depthError);
        }

        // サイズのチェック(高さ)
        String heightError = validateHeight(pv.getHeight());
        if (!heightError.equals("")) {
            errors.add(heightError);
        }

        // 素材のチェック
        String materialError = validateMaterial(pv.getMaterial());
        if (!materialError.equals("")) {
            errors.add(materialError);
        }

        // 商品説明のチェック
        String contentError = validateContent(pv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        // 在庫数のチェック
        String quantityError = validateQuantity(pv.getQuantity());
        if (!quantityError.equals("")) {
            errors.add(quantityError);
        }
        return errors;
    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 商品名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if (name == null || name.equals("")) {
            return MessageConst.P_NONAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 価格に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param price 価格
     * @return エラーメッセージ
     */
    private static String validatePrice(String price) {
        if (price == null || price.equals("")) {
            return MessageConst.P_NOPRICE.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param size サイズ(幅)
     * @return エラーメッセージ
     */
    private static String validateWidth(String width) {
        if (width == null || width.equals("")) {
            return MessageConst.P_NOWIDTH.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param size サイズ(奥行き)
     * @return エラーメッセージ
     */
    private static String validateDepth(String depth) {
        if (depth == null || depth.equals("")) {
            return MessageConst.P_NODEPTH.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param size サイズ(高さ)
     * @return エラーメッセージ
     */
    private static String validateHeight(String height) {
        if (height == null || height.equals("")) {
            return MessageConst.P_NOHEIGHT.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param material 素材
     * @return エラーメッセージ
     */
    private static String validateMaterial(String material) {
        if (material == null || material.equals("")) {
            return MessageConst.P_NOMATERIAL.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 商品説明
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.P_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param quantity 在庫数
     * @return エラーメッセージ
     */
    private static String validateQuantity(String quantity) {
        if (quantity == null || quantity.equals("")) {
            return MessageConst.P_NOQUANTITY.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}
