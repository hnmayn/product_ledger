package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ProductView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ProductService;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

    private ProductService service;

    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new ProductService();

        // メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException {

        // セッションからログイン中のユーザー情報を取得
        UserView loginEmployee = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        // ログイン中のユーザーが作成した商品データを、指定されたページ数の一覧画面に表示する分取得する
        int page = getPage();
        List<ProductView> products = service.getMinePerPage(loginEmployee, page);

        // ログイン中のユーザーが作成した商品データの件数を取得
        long myProductsCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.PRODUCT, products); // 取得した商品データ
        putRequestScope(AttributeConst.PRD_COUNT, myProductsCount); // ログイン中のユーザーが作成した商品データの数
        putRequestScope(AttributeConst.PAGE, page); // ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); // 1ページに表示するレコードの数

        // セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        // 一覧画面を表示
        forward(ForwardConst.FW_TOP_INDEX);
    }
}
